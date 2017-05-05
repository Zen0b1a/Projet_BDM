/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.awt.Font;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;
import utils.ConnexionUtils;

/**
 *
 * @author Annabelle
 */
public class DlgAfficheEnquete extends javax.swing.JFrame
{
    private int id;
    
    /**
     * Creates new form DlgAfficheEnquete
     */
    public DlgAfficheEnquete(int id)
    {
        initComponents();
        this.id = id;
        this.initialiserEnquete();
    }

    private void initialiserEnquete()
    {
        try 
        {
            OraclePreparedStatement stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("SELECT nom, etat FROM bdm_enquete WHERE id=?");
            stmt.setInt(1, this.id);
            OracleResultSet rs = (OracleResultSet)stmt.executeQuery();
            rs.next();
            //Récupération des informations de l'enquête
            this.Nom.setText(rs.getString("NOM"));
            this.Etat.setText(rs.getString("ETAT"));
            rs.close();
            stmt.close();
            
            //Récupération de la liste des suspects
            this.initialiserSuspects();
            
            //Récupération de la liste des preuves
            this.initialiserPreuves();
            
            //Récupération de la liste des crimes
            this.initialiserCrimes();
            
            //Récupération de la liste des enquêteurs
            this.initialiserEnqueteurs();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DlgAfficheEnquete.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void initialiserEnqueteurs()
    {
        try
        {
            this.Enqueteurs.removeAll();
            this.Enqueteurs.setLayout(new GridLayout());
            OraclePreparedStatement stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("SELECT id, nom, prenom, badge FROM bdm_enqueteur "
                    + "WHERE id IN(SELECT DEREF(enqueteurEE).id FROM bdm_enqueteur_enquete WHERE DEREF(enqueteEE).id=?) ORDER BY id");
            stmt.setInt(1, this.id);
            OracleResultSet rs = (OracleResultSet)stmt.executeQuery();
            int idEnqueteur;
            Font fonte = new Font("Courier",Font.PLAIN,14);
            while(rs.next()) 
            {
                idEnqueteur = rs.getInt("ID");
                JButton button = new JButton();
                button.setName(""+idEnqueteur);
                //Ajout des informations dans le bouton
                button.setFont(fonte);
                button.setText("<HTML><body>Badge : "+rs.getString("BADGE")+"<br>Nom : "+rs.getString("NOM")+"<br>Prénom : "+rs.getString("PRENOM")+"</HTML></body>");
                button.setVerticalTextPosition(SwingConstants.BOTTOM);
                button.setHorizontalTextPosition(SwingConstants.CENTER);
                button.setSize(50, 50);
                
                //Ajout du listener
                button.addActionListener(new java.awt.event.ActionListener()
                {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        afficherEnqueteur(evt);
                    }
                });
                
                this.Enqueteurs.add(button);
            }
            rs.close();
            stmt.close();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(DlgAfficheEnquete.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void initialiserCrimes()
    {
        try
        {
            this.Crimes.removeAll();
            this.Crimes.setLayout(new GridLayout());
            OraclePreparedStatement stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("SELECT id, fait, dateC, lieu FROM bdm_crime "
                    + "WHERE DEREF(enqueteC).id=? ORDER BY dateC");
            stmt.setInt(1, this.id);
            OracleResultSet rs = (OracleResultSet)stmt.executeQuery();
            int idCrime;
            Font fonte = new Font("Courier",Font.PLAIN,14);
            while(rs.next())
            {
                idCrime = rs.getInt("ID");
                JButton button = new JButton();
                button.setName(""+idCrime);
                //Ajout des informations dans le bouton
                button.setFont(fonte);
                button.setText("<HTML><body>Fait : "+rs.getString("FAIT")+"<br>Date : "+rs.getString("DATEC")+"<br>Lieu : "+rs.getString("LIEU")+"</HTML></body>");
                button.setVerticalTextPosition(SwingConstants.BOTTOM);
                button.setHorizontalTextPosition(SwingConstants.CENTER); 
                button.setSize(50, 50);
                
                //Ajout du listener
                button.addActionListener(new java.awt.event.ActionListener() 
                {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        afficherCrime(evt);
                    }
                });
                
                this.Crimes.add(button);
            }
            rs.close();
            stmt.close();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(DlgAfficheEnquete.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void initialiserPreuves()
    {
        try
        {
            this.Preuves.removeAll();
            this.Preuves.setLayout(new GridLayout());
            //Preuves image
            OraclePreparedStatement stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("SELECT id, description FROM bdm_preuve_image "
                    + "WHERE DEREF(enqueteP).id=? ORDER BY id");
            stmt.setInt(1, this.id);
            OracleResultSet rs = (OracleResultSet)stmt.executeQuery();
            int idPreuve;
            Font fonte = new Font("Courier",Font.PLAIN,14);
            while(rs.next())
            {
                idPreuve = rs.getInt("ID");
                JButton button = new JButton();
                button.setName("image "+idPreuve);
                //Ajout des informations dans le bouton
                button.setFont(fonte);
                button.setText("<HTML><body>Id : "+idPreuve+"<br>Description : "+rs.getString("DESCRIPTION")+"</HTML></body>");
                button.setVerticalTextPosition(SwingConstants.BOTTOM);
                button.setHorizontalTextPosition(SwingConstants.CENTER); 
                button.setSize(50, 50);
                
                //Ajout du listener
                button.addActionListener(new java.awt.event.ActionListener() 
                {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        afficherPreuve(evt);
                    }
                });
                
                this.Preuves.add(button);
            }
            //Preuves audio
            stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("SELECT id, description FROM bdm_preuve_audio "
                    + "WHERE DEREF(enqueteP).id=? ORDER BY id");
            stmt.setInt(1, this.id);
            rs = (OracleResultSet)stmt.executeQuery();
            while(rs.next())
            {
                idPreuve = rs.getInt("ID");
                JButton button = new JButton();
                button.setName("audio "+idPreuve);
                //Ajout des informations dans le bouton
                button.setFont(fonte);
                button.setText("<HTML><body>Id : "+idPreuve+"<br>Description : "+rs.getString("DESCRIPTION")+"</HTML></body>");
                button.setVerticalTextPosition(SwingConstants.BOTTOM);
                button.setHorizontalTextPosition(SwingConstants.CENTER); 
                button.setSize(50, 50);
                
                //Ajout du listener
                button.addActionListener(new java.awt.event.ActionListener() 
                {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        afficherPreuve(evt);
                    }
                });
                
                this.Preuves.add(button);
            }
            //Preuves video
            stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("SELECT id, description FROM bdm_preuve_video "
                    + "WHERE DEREF(enqueteP).id=? ORDER BY id");
            stmt.setInt(1, this.id);
            rs = (OracleResultSet)stmt.executeQuery();
            while(rs.next())
            {
                idPreuve = rs.getInt("ID");
                JButton button = new JButton();
                button.setName("video "+idPreuve);
                //Ajout des informations dans le bouton
                button.setFont(fonte);
                button.setText("<HTML><body>Id : "+idPreuve+"<br>Description : "+rs.getString("DESCRIPTION")+"</HTML></body>");
                button.setVerticalTextPosition(SwingConstants.BOTTOM);
                button.setHorizontalTextPosition(SwingConstants.CENTER); 
                button.setSize(50, 50);
                
                //Ajout du listener
                button.addActionListener(new java.awt.event.ActionListener() 
                {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        afficherPreuve(evt);
                    }
                });
                
                this.Preuves.add(button);
            }
            rs.close();
            stmt.close();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(DlgAfficheEnquete.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void initialiserSuspects()
    {
        try
        {
            this.Suspects.removeAll();
            this.Suspects.setLayout(new GridLayout());
            OraclePreparedStatement stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("SELECT id, DEREF(personneS).nom, DEREF(personneS).prenom, etat FROM bdm_suspect "
                    + "WHERE DEREF(enqueteS).id=? ORDER BY id");
            stmt.setInt(1, this.id);
            OracleResultSet rs = (OracleResultSet)stmt.executeQuery();
            int idSuspect;
            Font fonte = new Font("Courier",Font.PLAIN,14);
            while(rs.next())
            {
                idSuspect = rs.getInt("ID");
                JButton button = new JButton();
                button.setName(""+idSuspect);
                //Ajout des informations dans le bouton
                button.setFont(fonte);
                button.setText("<HTML><body>Nom : "+rs.getString("DEREF(personneS).nom")+"<br>Prénom : "+rs.getString("DEREF(personneS).prenom")+"<br>Etat : "+rs.getString("ETAT")+"</HTML></body>");
                button.setVerticalTextPosition(SwingConstants.BOTTOM);
                button.setHorizontalTextPosition(SwingConstants.CENTER); 
                button.setSize(50, 50);
                
                //Ajout du listener
                button.addActionListener(new java.awt.event.ActionListener() 
                {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        afficherSuspect(evt);
                    }
                });
                
                this.Suspects.add(button);
            }
            rs.close();
            stmt.close();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(DlgAfficheEnquete.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void afficherEnqueteur(java.awt.event.ActionEvent evt)
    {
        JButton jb = (JButton)evt.getSource();
        int id = Integer.parseInt(jb.getName());
        DlgAfficheEnqueteur dlg = new DlgAfficheEnqueteur(id);
        dlg.setVisible(true);
    }
    
    private void afficherCrime(java.awt.event.ActionEvent evt)
    {
        JButton jb = (JButton)evt.getSource();
        int id = Integer.parseInt(jb.getName());
        DlgAfficheCrime dlg = new DlgAfficheCrime(id);
        dlg.setVisible(true);
    }
    
    private void afficherPreuve(java.awt.event.ActionEvent evt)
    {
        JButton jb = (JButton)evt.getSource();
        String[] split = jb.getName().split(" "); //split[0] -> image, audio ou video
        int id = Integer.parseInt(split[1]);
        //DlgAffichePreuve dlg = new DlgAffichePreuve(id);
        //dlg.setVisible(true);
    }
    
    private void afficherSuspect(java.awt.event.ActionEvent evt)
    {
        JButton jb = (JButton)evt.getSource();
        int id = Integer.parseInt(jb.getName());
        //DlgAfficheSuspect dlg = new DlgAfficheSuspect(id);
        //dlg.setVisible(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Nom = new javax.swing.JLabel();
        ModifierNom = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        Etat = new javax.swing.JLabel();
        ModifierEtat = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        AjoutSuspect = new javax.swing.JButton();
        Suspects = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        AjoutPreuve = new javax.swing.JButton();
        Preuves = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        AjoutCrime = new javax.swing.JButton();
        Crimes = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        AjoutEnqueteur = new javax.swing.JButton();
        Enqueteurs = new javax.swing.JPanel();

        setTitle("Enquête");
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        getContentPane().setLayout(new java.awt.GridLayout(5, 1));

        jPanel1.setBackground(new java.awt.Color(226, 220, 207));
        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        jPanel4.setBackground(new java.awt.Color(226, 220, 207));
        jPanel4.setLayout(new java.awt.GridLayout(2, 3));

        jLabel1.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel1.setText("Nom :");
        jPanel4.add(jLabel1);

        Nom.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        jPanel4.add(Nom);

        ModifierNom.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        ModifierNom.setText("Modifier");
        ModifierNom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModifierNomActionPerformed(evt);
            }
        });
        jPanel4.add(ModifierNom);

        jLabel3.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel3.setText("Etat :");
        jPanel4.add(jLabel3);

        Etat.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        jPanel4.add(Etat);

        ModifierEtat.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        ModifierEtat.setText("Modifier");
        ModifierEtat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModifierEtatActionPerformed(evt);
            }
        });
        jPanel4.add(ModifierEtat);

        jPanel1.add(jPanel4);

        jPanel5.setBackground(new java.awt.Color(226, 220, 207));

        jLabel7.setBackground(new java.awt.Color(226, 220, 207));
        jLabel7.setText("BOUTONS POUR FAIRE DES TRUCS ICI (comparer images...)");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jLabel7)
                .addContainerGap(116, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel7)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel5);

        getContentPane().add(jPanel1);

        jPanel10.setBackground(new java.awt.Color(226, 220, 207));
        jPanel10.setLayout(new java.awt.BorderLayout());

        jPanel11.setBackground(new java.awt.Color(226, 220, 207));
        jPanel11.setLayout(new java.awt.GridLayout(1, 2));

        jLabel5.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel5.setText("Suspects");
        jPanel11.add(jLabel5);

        AjoutSuspect.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        AjoutSuspect.setText("Ajouter un suspect");
        AjoutSuspect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjoutSuspectActionPerformed(evt);
            }
        });
        jPanel11.add(AjoutSuspect);

        jPanel10.add(jPanel11, java.awt.BorderLayout.PAGE_START);

        Suspects.setBackground(new java.awt.Color(226, 220, 207));
        Suspects.setPreferredSize(new java.awt.Dimension(1076, 120));

        javax.swing.GroupLayout SuspectsLayout = new javax.swing.GroupLayout(Suspects);
        Suspects.setLayout(SuspectsLayout);
        SuspectsLayout.setHorizontalGroup(
            SuspectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1076, Short.MAX_VALUE)
        );
        SuspectsLayout.setVerticalGroup(
            SuspectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 92, Short.MAX_VALUE)
        );

        jPanel10.add(Suspects, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel10);

        jPanel8.setBackground(new java.awt.Color(226, 220, 207));
        jPanel8.setLayout(new java.awt.BorderLayout());

        jPanel9.setBackground(new java.awt.Color(226, 220, 207));
        jPanel9.setLayout(new java.awt.GridLayout(1, 2));

        jLabel4.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel4.setText("Preuves");
        jPanel9.add(jLabel4);

        AjoutPreuve.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        AjoutPreuve.setText("Ajouter une preuve");
        AjoutPreuve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjoutPreuveActionPerformed(evt);
            }
        });
        jPanel9.add(AjoutPreuve);

        jPanel8.add(jPanel9, java.awt.BorderLayout.PAGE_START);

        Preuves.setBackground(new java.awt.Color(226, 220, 207));

        javax.swing.GroupLayout PreuvesLayout = new javax.swing.GroupLayout(Preuves);
        Preuves.setLayout(PreuvesLayout);
        PreuvesLayout.setHorizontalGroup(
            PreuvesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1076, Short.MAX_VALUE)
        );
        PreuvesLayout.setVerticalGroup(
            PreuvesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 92, Short.MAX_VALUE)
        );

        jPanel8.add(Preuves, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel8);

        jPanel12.setLayout(new java.awt.BorderLayout());

        jPanel13.setBackground(new java.awt.Color(226, 220, 207));
        jPanel13.setLayout(new java.awt.GridLayout(1, 2));

        jLabel6.setBackground(new java.awt.Color(226, 220, 207));
        jLabel6.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel6.setText("Crimes");
        jPanel13.add(jLabel6);

        AjoutCrime.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        AjoutCrime.setText("Ajouter un crime");
        AjoutCrime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjoutCrimeActionPerformed(evt);
            }
        });
        jPanel13.add(AjoutCrime);

        jPanel12.add(jPanel13, java.awt.BorderLayout.PAGE_START);

        Crimes.setBackground(new java.awt.Color(226, 220, 207));

        javax.swing.GroupLayout CrimesLayout = new javax.swing.GroupLayout(Crimes);
        Crimes.setLayout(CrimesLayout);
        CrimesLayout.setHorizontalGroup(
            CrimesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1076, Short.MAX_VALUE)
        );
        CrimesLayout.setVerticalGroup(
            CrimesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 92, Short.MAX_VALUE)
        );

        jPanel12.add(Crimes, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel12);

        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel7.setBackground(new java.awt.Color(226, 220, 207));
        jPanel7.setLayout(new java.awt.GridLayout(1, 2));

        jLabel2.setBackground(new java.awt.Color(226, 220, 207));
        jLabel2.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel2.setText("Enquêteurs");
        jPanel7.add(jLabel2);

        AjoutEnqueteur.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        AjoutEnqueteur.setText("Ajouter un enquêteur");
        AjoutEnqueteur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjoutEnqueteurActionPerformed(evt);
            }
        });
        jPanel7.add(AjoutEnqueteur);

        jPanel6.add(jPanel7, java.awt.BorderLayout.PAGE_START);

        Enqueteurs.setBackground(new java.awt.Color(226, 220, 207));

        javax.swing.GroupLayout EnqueteursLayout = new javax.swing.GroupLayout(Enqueteurs);
        Enqueteurs.setLayout(EnqueteursLayout);
        EnqueteursLayout.setHorizontalGroup(
            EnqueteursLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1076, Short.MAX_VALUE)
        );
        EnqueteursLayout.setVerticalGroup(
            EnqueteursLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 92, Short.MAX_VALUE)
        );

        jPanel6.add(Enqueteurs, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel6);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AjoutCrimeActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_AjoutCrimeActionPerformed
    {//GEN-HEADEREND:event_AjoutCrimeActionPerformed
        DlgAjoutCrime dlg = new DlgAjoutCrime();
        dlg.setVisible(true);
    }//GEN-LAST:event_AjoutCrimeActionPerformed

    private void AjoutSuspectActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_AjoutSuspectActionPerformed
    {//GEN-HEADEREND:event_AjoutSuspectActionPerformed
        // TODO add your handling code here:
        //Interface avec choix d'une personne, comparaison d'images ?
    }//GEN-LAST:event_AjoutSuspectActionPerformed

    private void AjoutPreuveActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_AjoutPreuveActionPerformed
    {//GEN-HEADEREND:event_AjoutPreuveActionPerformed
        DlgAjoutPreuve dlg = new DlgAjoutPreuve(this.id);
        dlg.setVisible(true);
    }//GEN-LAST:event_AjoutPreuveActionPerformed

    private void AjoutEnqueteurActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_AjoutEnqueteurActionPerformed
    {//GEN-HEADEREND:event_AjoutEnqueteurActionPerformed
        try
        {
            OraclePreparedStatement stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("SELECT COUNT(*) FROM bdm_enqueteur "
                    + "WHERE id NOT IN(SELECT DEREF(enqueteurEE).id FROM bdm_enqueteur_enquete WHERE DEREF(enqueteEE).id=?)");
            stmt.setInt(1, this.id);
            OracleResultSet rs = (OracleResultSet)stmt.executeQuery();
            rs.next();
            int nbEnqueteurs = rs.getInt(1);
            String[] enqueteurs = new String[nbEnqueteurs];
            stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("SELECT id, nom, prenom FROM bdm_enqueteur "
                    + "WHERE id NOT IN(SELECT DEREF(enqueteurEE).id FROM bdm_enqueteur_enquete WHERE DEREF(enqueteEE).id=?) ORDER BY id");
            stmt.setInt(1, this.id);
            rs = (OracleResultSet)stmt.executeQuery();
            int i=0;
            while(rs.next())
            {
                enqueteurs[i] = rs.getInt("ID")+" - "+rs.getString("PRENOM")+" "+rs.getString("NOM");
                i++;
            }
            //JOptionPane de choix d'un enquêteur
            JComboBox combo = new JComboBox(enqueteurs);
            String[] options = { "Ajouter", "Annuler" };
            int selection = JOptionPane.showOptionDialog(null, combo, "Ajouter un enquêteur", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            //Récupération de la sélection
            if(selection<=0) 
            {
                if(combo.getSelectedIndex()>=0)
                {
                    String enqueteur = combo.getSelectedItem().toString();
                    int idEnqueteur = Integer.parseInt(enqueteur.split(" - ")[0]);
                    stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("INSERT INTO bdm_enqueteur_enquete VALUES"
                            + "((SELECT REF(e) FROM bdm_enqueteur e WHERE e.id=?), (SELECT REF(e) FROM bdm_enquete e WHERE e.id=?))");
                    stmt.setInt(1, idEnqueteur);
                    stmt.setInt(2, this.id);
                    stmt.executeQuery();
                }
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex)
        {
            Logger.getLogger(DlgAfficheEnquete.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }//GEN-LAST:event_AjoutEnqueteurActionPerformed

    private void ModifierNomActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_ModifierNomActionPerformed
    {//GEN-HEADEREND:event_ModifierNomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ModifierNomActionPerformed

    private void ModifierEtatActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_ModifierEtatActionPerformed
    {//GEN-HEADEREND:event_ModifierEtatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ModifierEtatActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        this.initialiserEnquete();
        this.setSize(this.getWidth()+1, this.getHeight()+1);
        this.setSize(this.getWidth()-1, this.getHeight()-1);
    }//GEN-LAST:event_formWindowGainedFocus

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(DlgAfficheEnquete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(DlgAfficheEnquete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(DlgAfficheEnquete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(DlgAfficheEnquete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AjoutCrime;
    private javax.swing.JButton AjoutEnqueteur;
    private javax.swing.JButton AjoutPreuve;
    private javax.swing.JButton AjoutSuspect;
    private javax.swing.JPanel Crimes;
    private javax.swing.JPanel Enqueteurs;
    private javax.swing.JLabel Etat;
    private javax.swing.JButton ModifierEtat;
    private javax.swing.JButton ModifierNom;
    private javax.swing.JLabel Nom;
    private javax.swing.JPanel Preuves;
    private javax.swing.JPanel Suspects;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    // End of variables declaration//GEN-END:variables
}
