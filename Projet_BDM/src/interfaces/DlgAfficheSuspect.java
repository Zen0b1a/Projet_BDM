/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MediaTracker;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import mapping.Personne;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;
import utils.ConnexionUtils;

/**
 *
 * @author ag092850
 */
public class DlgAfficheSuspect extends javax.swing.JFrame {
    
    private int idS;
    private int idP;
    private Image photo;
    private Personne personne;
    /**
     * Creates new form DlgAfficheSuspect
     */
    public DlgAfficheSuspect(int idS) {
        initComponents();
        this.idS = idS;
        this.initialiserPersonne();
        this.initialiserSuspect();
    }

    private void initialiserSuspect()
    {
        try 
        {
            OraclePreparedStatement stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("SELECT etat, alibi FROM bdm_suspect WHERE id=?");
            stmt.setInt(1, this.idS);
            OracleResultSet rs = (OracleResultSet)stmt.executeQuery();
            rs.next();
            //Récupération des informations de le suspect
            String alibi = rs.getString("ALIBI");
            this.Alibi.setText(alibi);
            if(alibi!=null && !alibi.equals(""))
                this.AjoutAlibi.setText("Modifier l'alibi");
            String etat = rs.getString("ETAT");
            switch(etat)
            {
                case "coupable" : this.Coupable.setSelected(true); this.PanelAlibi.setVisible(false); break;
                case "disculpe" : this.Disculpe.setSelected(true); this.PanelAlibi.setVisible(true); break;
                case "non defini" : this.NonDefini.setSelected(true); this.PanelAlibi.setVisible(false); break;
            }
            rs.close();
            stmt.close();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DlgAfficheSuspect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void initialiserPersonne()
    {
        try 
        {
            OraclePreparedStatement stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("SELECT id FROM bdm_personne WHERE id=(SELECT DEREF(personneS).id FROM bdm_suspect WHERE id=?)");
            stmt.setInt(1, this.idS);
            OracleResultSet rs = (OracleResultSet)stmt.executeQuery();
            rs.next();
            //Récupération des informations de la personne
            this.idP = rs.getInt("ID");
            this.personne = new Personne().chargerPersonne(this.idP);
            //Affichage des informations de la personne
            this.Nom.setText(this.personne.getNom());
            this.Prenom.setText(this.personne.getPrenom());
            this.NumeroRue.setText(""+this.personne.getNumeroRue());
            this.NomRue.setText(this.personne.getNomRue());
            this.Ville.setText(this.personne.getVille());
            this.Telephone1.setText(this.personne.getTelephone1());
            this.Telephone2.setText(this.personne.getTelephone2());
            this.photo = this.Photo.getToolkit().getImage(this.personne.getCheminPhoto());
            rs.close();
            stmt.close();
            //On vérifie que l'image est chargée
            MediaTracker tracker=new MediaTracker(this);
            tracker.addImage(this.photo, 0);
            try {tracker.waitForID(0);}
            catch(InterruptedException e) {}
            this.affichePhoto();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(DlgAfficheSuspect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void affichePhoto()
    {
        Graphics2D g = (Graphics2D)this.Photo.getGraphics();
        Double scaleWidth = this.Photo.getWidth()/new Double(this.photo.getWidth(null));
	Double scaleHeight = this.Photo.getHeight()/new Double(this.photo.getHeight(null));
        if (scaleWidth>scaleHeight)
            scaleWidth = scaleHeight;
        else
            scaleHeight = scaleWidth;
        int x = (int)((this.Photo.getWidth() - (this.photo.getWidth(null)*scaleWidth)) / 2);
        int y = (int)((this.Photo.getHeight() - (this.photo.getHeight(null)*scaleHeight)) / 2);
        g.translate(x, y);
        g.scale(scaleWidth, scaleHeight);
        g.drawImage(this.photo, 0, 0, null);
    }
    
    public void paint(Graphics g)
    {
        super.paint(g);
        if(this.photo!=null)
            this.affichePhoto();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Etat = new javax.swing.ButtonGroup();
        jPanel6 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        Coupable = new javax.swing.JRadioButton();
        NonDefini = new javax.swing.JRadioButton();
        Disculpe = new javax.swing.JRadioButton();
        PanelAlibi = new javax.swing.JPanel();
        PanelAlibi1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        AjoutAlibi = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Alibi = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        Nom = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Prenom = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ModifierAdresse = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        NumeroRue = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        NomRue = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Ville = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        Photo = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        Telephone1 = new javax.swing.JLabel();
        ModifierTelephone1 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        Telephone2 = new javax.swing.JLabel();
        ModifierTelephone2 = new javax.swing.JButton();

        jPanel6.setBackground(new java.awt.Color(226, 220, 207));
        jPanel6.setLayout(new java.awt.GridLayout(1, 2));

        jPanel9.setBackground(new java.awt.Color(226, 220, 207));
        jPanel9.setLayout(new java.awt.GridLayout(1, 2));

        jLabel1.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel1.setText("Etat :");
        jPanel9.add(jLabel1);

        jPanel10.setBackground(new java.awt.Color(226, 220, 207));
        jPanel10.setLayout(new java.awt.GridLayout(3, 1));

        Coupable.setBackground(new java.awt.Color(226, 220, 207));
        Etat.add(Coupable);
        Coupable.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        Coupable.setText("Coupable");
        Coupable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CoupableActionPerformed(evt);
            }
        });
        jPanel10.add(Coupable);

        NonDefini.setBackground(new java.awt.Color(226, 220, 207));
        Etat.add(NonDefini);
        NonDefini.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        NonDefini.setText("Non défini");
        NonDefini.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NonDefiniActionPerformed(evt);
            }
        });
        jPanel10.add(NonDefini);

        Disculpe.setBackground(new java.awt.Color(226, 220, 207));
        Etat.add(Disculpe);
        Disculpe.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        Disculpe.setText("Disculpé");
        Disculpe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DisculpeActionPerformed(evt);
            }
        });
        jPanel10.add(Disculpe);

        jPanel9.add(jPanel10);

        jPanel6.add(jPanel9);

        PanelAlibi.setBackground(new java.awt.Color(226, 220, 207));
        PanelAlibi.setLayout(new java.awt.BorderLayout());

        PanelAlibi1.setBackground(new java.awt.Color(226, 220, 207));
        PanelAlibi1.setLayout(new java.awt.GridLayout(2, 1));

        jLabel8.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel8.setText("Alibi :");
        PanelAlibi1.add(jLabel8);

        AjoutAlibi.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        AjoutAlibi.setText("Ajouter un alibi");
        AjoutAlibi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjoutAlibiActionPerformed(evt);
            }
        });
        PanelAlibi1.add(AjoutAlibi);

        PanelAlibi.add(PanelAlibi1, java.awt.BorderLayout.LINE_START);

        Alibi.setColumns(20);
        Alibi.setRows(5);
        Alibi.setEnabled(false);
        jScrollPane1.setViewportView(Alibi);

        PanelAlibi.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel6.add(PanelAlibi);

        getContentPane().add(jPanel6, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBackground(new java.awt.Color(230, 215, 184));
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 280));
        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        jPanel2.setBackground(new java.awt.Color(226, 220, 207));
        jPanel2.setPreferredSize(new java.awt.Dimension(330, 100));
        jPanel2.setLayout(new java.awt.GridLayout(6, 2));

        jLabel3.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel3.setText("Nom :");
        jPanel2.add(jLabel3);

        Nom.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        jPanel2.add(Nom);

        jLabel5.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel5.setText("Prenom :");
        jPanel2.add(jLabel5);

        Prenom.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        jPanel2.add(Prenom);

        jLabel2.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel2.setText("Adresse");
        jPanel2.add(jLabel2);

        ModifierAdresse.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        ModifierAdresse.setText("Modifier");
        ModifierAdresse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModifierAdresseActionPerformed(evt);
            }
        });
        jPanel2.add(ModifierAdresse);

        jLabel4.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel4.setText("Numéro de rue :");
        jLabel4.setPreferredSize(new java.awt.Dimension(150, 21));
        jPanel2.add(jLabel4);

        NumeroRue.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        jPanel2.add(NumeroRue);

        jLabel7.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel7.setText("Rue :");
        jPanel2.add(jLabel7);

        NomRue.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        jPanel2.add(NomRue);

        jLabel9.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel9.setText("Ville :");
        jPanel2.add(jLabel9);

        Ville.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        jPanel2.add(Ville);

        jPanel1.add(jPanel2);

        jPanel5.setBackground(new java.awt.Color(226, 220, 207));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(226, 220, 207));
        jPanel3.setPreferredSize(new java.awt.Dimension(0, 470));
        jPanel3.setLayout(new java.awt.GridLayout(1, 1));

        Photo.setBackground(new java.awt.Color(226, 220, 207));
        Photo.setPreferredSize(new java.awt.Dimension(0, 370));

        javax.swing.GroupLayout PhotoLayout = new javax.swing.GroupLayout(Photo);
        Photo.setLayout(PhotoLayout);
        PhotoLayout.setHorizontalGroup(
            PhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 495, Short.MAX_VALUE)
        );
        PhotoLayout.setVerticalGroup(
            PhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel3.add(Photo);

        jPanel5.add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel5);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel4.setLayout(new java.awt.GridLayout(2, 1));

        jPanel7.setBackground(new java.awt.Color(226, 220, 207));
        jPanel7.setLayout(new java.awt.GridLayout(1, 3));

        jLabel6.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel6.setText("Téléphone 1 :");
        jLabel6.setPreferredSize(new java.awt.Dimension(143, 50));
        jPanel7.add(jLabel6);

        Telephone1.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        jPanel7.add(Telephone1);

        ModifierTelephone1.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        ModifierTelephone1.setText("Modifier");
        ModifierTelephone1.setPreferredSize(new java.awt.Dimension(121, 40));
        ModifierTelephone1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModifierTelephone1ActionPerformed(evt);
            }
        });
        jPanel7.add(ModifierTelephone1);

        jPanel4.add(jPanel7);

        jPanel8.setBackground(new java.awt.Color(226, 220, 207));
        jPanel8.setLayout(new java.awt.GridLayout(1, 3));

        jLabel10.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel10.setText("Téléphone 2 :");
        jPanel8.add(jLabel10);

        Telephone2.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        jPanel8.add(Telephone2);

        ModifierTelephone2.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        ModifierTelephone2.setText("Modifier");
        ModifierTelephone2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModifierTelephone2ActionPerformed(evt);
            }
        });
        jPanel8.add(ModifierTelephone2);

        jPanel4.add(jPanel8);

        getContentPane().add(jPanel4, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ModifierAdresseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModifierAdresseActionPerformed
        //JTextField modification de l'adresse
        JTextField num = new JTextField();
        num.setSize(100,20);
        JTextField rue = new JTextField();
        rue.setSize(100,20);
        JTextField ville = new JTextField();
        ville.setSize(100,20);
        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(4,2));
        JLabel message = new JLabel("Modifier l'adresse : ");
        jp.add(message);
        jp.add(new JLabel(""));
        jp.add(new JLabel("Numero :"));
        jp.add(num);
        jp.add(new JLabel("Rue :"));
        jp.add(rue);
        jp.add(new JLabel("Ville :"));
        jp.add(ville);
        JOptionPane jop = new JOptionPane();
        int adresse;
        int numeroRue = -1;
        //Vérification adresse
        boolean continuer = true;
        while(continuer)
        {
            boolean valide = true;
            adresse = jop.showConfirmDialog(null, jp, "Modifier l'adresse", JOptionPane.OK_CANCEL_OPTION);
            message.setText("Erreur :");
            if(adresse==JOptionPane.CANCEL_OPTION || adresse==JOptionPane.CLOSED_OPTION)
            {
                continuer=false;
            }
            else
            {
                if(num.getText().equals("") || rue.getText().equals("") || ville.getText().equals(""))
                {
                    message.setText(message.getText()+" Veuillez remplir tous les champs.");
                    valide=false;
                }
                if(rue.getText().length()>50 || ville.getText().length()>50)
                {
                    message.setText(message.getText()+" La rue et la ville ne doivent pas contenir plus de 50 caractères.");
                    valide = false;
                }
                try
                {
                    numeroRue = Integer.parseInt(num.getText());
                    if(numeroRue<0)
                    {
                        message.setText(message.getText()+" Veuillez entrer un numéro de rue positif.");
                        valide=false;
                    }
                }
                catch(NumberFormatException e)
                {
                    message.setText(message.getText()+" Veuillez entrer un numéro de rue valide.");
                    valide = false;      
                }
                if(valide)
                { 
                    this.personne.majAdresse(numeroRue, rue.getText(), ville.getText());
                    continuer = false;
                    this.NumeroRue.setText(""+this.personne.getNumeroRue());
                    this.NomRue.setText(this.personne.getNomRue());
                    this.Ville.setText(this.personne.getVille());
                } 
            }
        }
    }//GEN-LAST:event_ModifierAdresseActionPerformed

    private void ModifierTelephone1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModifierTelephone1ActionPerformed
        //Modification du numéro de téléphone 1
        JOptionPane jop = new JOptionPane();
        String telephone1;
        //Vérification numéro
        boolean continuer = true;
        String mes = "Modifier le numéro de téléphone 1 : ";
        while(continuer)
        {
            boolean valide = true;
            telephone1 = jop.showInputDialog(null, mes, "Modifier", JOptionPane.QUESTION_MESSAGE);
            if(telephone1==null)
                continuer=false;
            else 
            {
                if(telephone1.length()<=0 || telephone1.length()>10)
                {
                    valide = false;
                    mes = "Le numéro de téléphone doit comprendre de 1 à 10 chiffres.";
                }
                else
                {
                    try
                    {
                        Long.parseLong(telephone1);
                    }
                    catch(NumberFormatException e)
                    {
                        mes="Veuillez rentrer un numéro de téléphone valide";
                        valide=false;  
                    }
                    if(valide)
                    {
                        this.personne.majTelephone(1, telephone1);
                        this.Telephone1.setText(this.personne.getTelephone1());
                        continuer = false;
                    }
                }
            }
        }
    }//GEN-LAST:event_ModifierTelephone1ActionPerformed

    private void ModifierTelephone2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModifierTelephone2ActionPerformed
        //Modification du numéro de téléphone 2
        JOptionPane jop = new JOptionPane();
        String telephone2;
        //Vérification numéro
        boolean continuer = true;
        String mes = "Modifier le numéro de téléphone 2 : ";
        while(continuer)
        {
            boolean valide = true;
            telephone2 = jop.showInputDialog(null, mes, "Modifier", JOptionPane.QUESTION_MESSAGE);
            if(telephone2==null)
            {
                continuer=false;
            }
            else 
            {
                if(telephone2.length()<=0 || telephone2.length()>10)
                {
                    valide = false;
                    mes = "Le numéro de téléphone doit comprendre de 1 à 10 chiffres.";
                }
                else
                {
                    try
                    {
                        Long.parseLong(telephone2);
                    }
                    catch(NumberFormatException e)
                    {
                        mes="Veuillez rentrer un numéro de téléphone valide";
                        valide=false;  
                    }
                    if(valide)
                    {
                        this.personne.majTelephone(2, telephone2);
                        this.Telephone2.setText(this.personne.getTelephone2());
                        continuer = false;
                    }
                }
            }
        }
    }//GEN-LAST:event_ModifierTelephone2ActionPerformed

    private void CoupableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CoupableActionPerformed
        try 
        {
            OraclePreparedStatement stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("UPDATE bdm_suspect SET etat='coupable' WHERE id=?");
            stmt.setInt(1, this.idS);
            stmt.executeUpdate();
            stmt.close();
            this.PanelAlibi.setVisible(false);
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DlgAfficheSuspect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_CoupableActionPerformed

    private void NonDefiniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NonDefiniActionPerformed
        try 
        {
            OraclePreparedStatement stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("UPDATE bdm_suspect SET etat='non defini' WHERE id=?");
            stmt.setInt(1, this.idS);
            stmt.executeUpdate();
            stmt.close();
            this.PanelAlibi.setVisible(false);
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DlgAfficheSuspect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_NonDefiniActionPerformed

    private void DisculpeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DisculpeActionPerformed
        try 
        {
            OraclePreparedStatement stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("UPDATE bdm_suspect SET etat='disculpe' WHERE id=?");
            stmt.setInt(1, this.idS);
            stmt.executeUpdate();
            stmt.close();
            this.PanelAlibi.setVisible(true);
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DlgAfficheSuspect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_DisculpeActionPerformed

    private void AjoutAlibiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AjoutAlibiActionPerformed
        try
        {
            //JOptionPane de choix d'un enquêteur
            JTextArea jta = new JTextArea();
            jta.setSize(100,100);
            String[] options = { "Ajouter", "Annuler" };
            int selection = JOptionPane.showOptionDialog(null, jta, "Ajouter un alibi", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            //Récupération de la sélection
            if(selection<=0) 
            {
                String alibi = jta.getText();
                if(alibi.length()>0 && alibi.length()<=500)
                {
                    OraclePreparedStatement stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("UPDATE bdm_suspect SET alibi=? WHERE id=?");
                    stmt.setString(1, alibi);
                    stmt.setInt(2, this.idS);
                    stmt.executeQuery();
                    stmt.close();
                    this.Alibi.setText(alibi);
                    this.AjoutAlibi.setText("Modifier l'alibi");
                }
                else
                    JOptionPane.showMessageDialog(null, "L'alibi doit faire entre 1 et 500 caractères.", "Erreur lors de l'ajout", JOptionPane.INFORMATION_MESSAGE, null);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DlgAfficheSuspect.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }//GEN-LAST:event_AjoutAlibiActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DlgAfficheSuspect.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DlgAfficheSuspect.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DlgAfficheSuspect.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DlgAfficheSuspect.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AjoutAlibi;
    private javax.swing.JTextArea Alibi;
    private javax.swing.JRadioButton Coupable;
    private javax.swing.JRadioButton Disculpe;
    private javax.swing.ButtonGroup Etat;
    private javax.swing.JButton ModifierAdresse;
    private javax.swing.JButton ModifierTelephone1;
    private javax.swing.JButton ModifierTelephone2;
    private javax.swing.JLabel Nom;
    private javax.swing.JLabel NomRue;
    private javax.swing.JRadioButton NonDefini;
    private javax.swing.JLabel NumeroRue;
    private javax.swing.JPanel PanelAlibi;
    private javax.swing.JPanel PanelAlibi1;
    private javax.swing.JPanel Photo;
    private javax.swing.JLabel Prenom;
    private javax.swing.JLabel Telephone1;
    private javax.swing.JLabel Telephone2;
    private javax.swing.JLabel Ville;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
