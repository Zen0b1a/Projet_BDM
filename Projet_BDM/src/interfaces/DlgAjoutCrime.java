/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.awt.Font;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;
import oracle.jdbc.*;
import utils.ConnexionUtils;

/**
 *
 * @author Annabelle
 */
public class DlgAjoutCrime extends javax.swing.JFrame
{
    private List<String> personnesVictimes; //Format : id - Nom Prenom
    /**
     * Creates new form DlgAjoutCrime
     */
    public DlgAjoutCrime()
    {
        initComponents();
        this.personnesVictimes = new ArrayList<>();
        this.initialisationCbEnquetes();
        this.initialisationCbVictimes();
    }

    private void remplirPanelVictimes()
    {
        String id;
        String nom;
        String[] split;
        Font fonte = new Font("Courier",Font.PLAIN,14);
        this.Victimes.removeAll();
        this.Victimes.setLayout(new GridLayout(this.personnesVictimes.size(), 3));
        for(int i=0; i<this.personnesVictimes.size(); i++)
        {
            //Récupération de l'id et du nom/prénom
            split = this.personnesVictimes.get(i).split(" - ");
            id = split[0];
            nom = split[1];
            //Ajout des informations dans le panel
            JLabel jl = new JLabel();
            jl.setText(id+" - "+nom);
            JComboBox jcb = new JComboBox();
            jcb.setFont(fonte);
            jcb.setName("jcb "+id);
            jcb.removeAllItems();
            jcb.addItem("vivant");
            jcb.addItem("mort");
            JButton jb = new JButton();
            jb.setFont(fonte);
            jb.setText("Supprimer cette victime.");
            jb.setName(this.personnesVictimes.get(i));
            //Ajout du listener
            jb.addActionListener(new java.awt.event.ActionListener() 
            {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    supprimerVictime(evt);
                }
            });
            this.Victimes.add(jl);
            this.Victimes.add(jcb);
            this.Victimes.add(jb);
        }
        this.setSize(this.getWidth()+1, this.getHeight()+1);
        this.setSize(this.getWidth()-1, this.getHeight()-1);
    }
    
    private void initialisationCbEnquetes()
    {
        try 
        {
            //Remplissage de la combobox
            OraclePreparedStatement stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("SELECT id, nom FROM bdm_enquete WHERE etat='en-cours' ORDER BY id");
            OracleResultSet rs = (OracleResultSet)stmt.executeQuery();
            int idEnquete;
            String nomEnquete;
            this.ListeEnquetes.removeAllItems();
            this.ListeEnquetes.setSelectedIndex(-1);
            while(rs.next())
            {
                idEnquete = rs.getInt("ID");
                nomEnquete = rs.getString("NOM");
                this.ListeEnquetes.addItem(idEnquete+" - "+nomEnquete);
            }
            rs.close();
            stmt.close();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DlgAjoutCrime.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void initialisationCbVictimes()
    {
        try 
        {
            //Remplissage de la combobox
            OraclePreparedStatement stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("SELECT id, nom, prenom FROM bdm_personne ORDER BY id");
            OracleResultSet rs = (OracleResultSet)stmt.executeQuery();
            int idPersonne;
            String nomPersonne;
            String prenomPersonne;
            this.ListeVictimes.removeAllItems();
            this.ListeVictimes.setSelectedIndex(-1);
            while(rs.next())
            {
                idPersonne = rs.getInt("ID");
                nomPersonne = rs.getString("NOM");
                prenomPersonne = rs.getString("PRENOM");
                if(!this.personnesVictimes.contains(idPersonne+" - "+nomPersonne+" "+prenomPersonne))
                    this.ListeVictimes.addItem(idPersonne+" - "+nomPersonne+" "+prenomPersonne);
            }
            rs.close();
            stmt.close();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DlgAjoutCrime.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void supprimerVictime(java.awt.event.ActionEvent evt)
    {
        JButton jb = (JButton)evt.getSource();
        String victime = jb.getName();
        this.personnesVictimes.remove(victime);
        this.ListeVictimes.addItem(victime);
        this.remplirPanelVictimes();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Fait = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        Lieu = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        Date = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        ListeEnquetes = new javax.swing.JComboBox();
        CreerEnquete = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        Victimes = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        ListeVictimes = new javax.swing.JComboBox();
        AjouterVictime = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        Annuler = new javax.swing.JButton();
        Ajouter = new javax.swing.JButton();

        setTitle("Ajout d'un crime");
        setPreferredSize(new java.awt.Dimension(700, 600));
        addWindowFocusListener(new java.awt.event.WindowFocusListener()
        {
            public void windowGainedFocus(java.awt.event.WindowEvent evt)
            {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt)
            {
            }
        });
        getContentPane().setLayout(new java.awt.GridLayout(9, 1));

        jPanel6.setBackground(new java.awt.Color(226, 220, 207));
        jPanel6.setLayout(new java.awt.GridLayout(1, 2));

        jLabel1.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel1.setText("Fait");
        jLabel1.setPreferredSize(new java.awt.Dimension(44, 40));
        jPanel6.add(jLabel1);

        Fait.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        jPanel6.add(Fait);

        getContentPane().add(jPanel6);

        jPanel7.setBackground(new java.awt.Color(226, 220, 207));
        jPanel7.setLayout(new java.awt.GridLayout(1, 2));

        jLabel2.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel2.setText("Lieu");
        jPanel7.add(jLabel2);

        Lieu.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        jPanel7.add(Lieu);

        getContentPane().add(jPanel7);

        jPanel8.setBackground(new java.awt.Color(226, 220, 207));
        jPanel8.setLayout(new java.awt.GridLayout(1, 2));

        jLabel3.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel3.setText("Date");
        jPanel8.add(jLabel3);

        Date.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        jPanel8.add(Date);

        getContentPane().add(jPanel8);

        jPanel3.setBackground(new java.awt.Color(226, 220, 207));
        jPanel3.setLayout(new java.awt.GridLayout(1, 2));

        jLabel4.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel4.setText("Enquête :");
        jPanel3.add(jLabel4);

        jLabel5.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        jPanel3.add(jLabel5);

        getContentPane().add(jPanel3);

        jPanel9.setLayout(new java.awt.GridLayout(1, 2));

        ListeEnquetes.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        jPanel9.add(ListeEnquetes);

        CreerEnquete.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        CreerEnquete.setText("Créer une nouvelle enquête");
        CreerEnquete.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                CreerEnqueteActionPerformed(evt);
            }
        });
        jPanel9.add(CreerEnquete);

        getContentPane().add(jPanel9);

        jPanel4.setBackground(new java.awt.Color(226, 220, 207));
        jPanel4.setLayout(new java.awt.GridLayout(1, 2));

        jLabel6.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel6.setText("Victime(s) :");
        jPanel4.add(jLabel6);

        jPanel10.setBackground(new java.awt.Color(226, 220, 207));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 511, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel10);

        getContentPane().add(jPanel4);

        jPanel2.setBackground(new java.awt.Color(226, 220, 207));
        jPanel2.setLayout(new java.awt.GridLayout(1, 1));

        Victimes.setBackground(new java.awt.Color(226, 220, 207));

        javax.swing.GroupLayout VictimesLayout = new javax.swing.GroupLayout(Victimes);
        Victimes.setLayout(VictimesLayout);
        VictimesLayout.setHorizontalGroup(
            VictimesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1023, Short.MAX_VALUE)
        );
        VictimesLayout.setVerticalGroup(
            VictimesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );

        jPanel2.add(Victimes);

        getContentPane().add(jPanel2);

        jPanel5.setBackground(new java.awt.Color(226, 220, 207));
        jPanel5.setLayout(new java.awt.GridLayout(1, 2));

        ListeVictimes.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        jPanel5.add(ListeVictimes);

        AjouterVictime.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        AjouterVictime.setText("Ajouter victime");
        AjouterVictime.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                AjouterVictimeActionPerformed(evt);
            }
        });
        jPanel5.add(AjouterVictime);

        getContentPane().add(jPanel5);

        jPanel1.setBackground(new java.awt.Color(226, 220, 207));
        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        Annuler.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        Annuler.setText("Annuler");
        Annuler.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                AnnulerActionPerformed(evt);
            }
        });
        jPanel1.add(Annuler);

        Ajouter.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        Ajouter.setText("Ajouter");
        Ajouter.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                AjouterActionPerformed(evt);
            }
        });
        jPanel1.add(Ajouter);

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AjouterActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_AjouterActionPerformed
    {//GEN-HEADEREND:event_AjouterActionPerformed
        String fait = this.Fait.getText();
        String lieu = this.Lieu.getText();
        String date = this.Date.getText();
        int idEnquete = this.ListeEnquetes.getSelectedIndex();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        JOptionPane jop = new JOptionPane();
        String message = "Les problèmes suivants ont été rencontrés :";
        boolean valide = true;
        //Vérification du fait
        if(fait.equals(""))
        {
            message += "\nLe fait doit être renseigné.";
            valide = false;
        }
        if(fait.length()>50)
        {
            message += "\nLe fait doit faire moins de 50 caractères.";
            valide = false;
        }
        //Vérification du lieu
        if(lieu.equals(""))
        {
            message += "\nLe lieu doit être renseigné.";
            valide = false;
        }
        if(lieu.length()>50)
        {
            message += "\nLe lieu doit faire moins de 50 caractères.";
            valide = false;
        }
        //Vérification de la date
        try
        {
            simpleDateFormat.parse(date);
        } 
        catch (ParseException ex)
        {
            message += "\nLe format de la date est incorrect (format attendu jj/mm/aaaa).";
            valide = false;
        }
        //Vérification de l'enquête
        if(idEnquete != -1)
            idEnquete = Integer.parseInt(this.ListeEnquetes.getSelectedItem().toString().split(" - ")[0]);
        else
        {
            message += "\nIl faut sélectionner une enquête.";
            valide = false;
        }
        //Vérification du nombre de victimes
        if(this.personnesVictimes.size()<=0)
        {
            message += "\nIl doit y avoir au moins une victime";
            valide = false;
        }
        
        if(valide)
        {
            try 
            {
                //Ajout du crime
                //Récupération de l'id
                OraclePreparedStatement stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("SELECT MAX(id) FROM bdm_crime");
                OracleResultSet rs = (OracleResultSet)stmt.executeQuery();
                rs.next();
                int id = rs.getInt(1)+1;
                rs.close();
                //Insertion
                stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("INSERT INTO bdm_crime VALUES(?, ?, ?, "
                    + "TO_DATE(?, 'DD/MM/YYYY'), (SELECT REF(e) FROM bdm_enquete e WHERE e.id=?))");
                stmt.setInt(1, id);
                stmt.setString(2, fait);
                stmt.setString(3, lieu);
                stmt.setString(4, date);
                stmt.setInt(5, idEnquete);
                stmt.executeQuery();
                
                //Ajout du crime dans l'enquête
                stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("INSERT INTO THE "
                        + "(SELECT crimes FROM bdm_enquete WHERE id=?) SELECT REF(c) FROM bdm_crime c WHERE c.id=?");
                stmt.setInt(1, idEnquete);
                stmt.setInt(2, id);
                stmt.executeQuery();
                
                //Ajout des victimes
                int idVictime;
                String etat;
                int idPersonne;
                JComboBox jb;
                for(int i=0; i<this.personnesVictimes.size(); i++)
                {
                    idPersonne = Integer.parseInt(this.personnesVictimes.get(i).split(" - ")[0]);
                    jb = (JComboBox)this.Victimes.getComponent((i*3)+1);
                    etat = jb.getSelectedItem().toString();
                    //Récupération de l'id
                    stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("SELECT MAX(id) FROM bdm_victime");
                    rs = (OracleResultSet)stmt.executeQuery();
                    rs.next();
                    idVictime = rs.getInt(1)+1;
                    stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("INSERT INTO bdm_victime VALUES(?, ?, "
                    + "(SELECT REF(p) FROM bdm_personne p WHERE p.id=?), (SELECT REF(c) FROM bdm_crime c WHERE c.id=?))");
                    stmt.setInt(1, idVictime);
                    stmt.setString(2, etat);
                    stmt.setInt(3, idPersonne);
                    stmt.setInt(4, id);
                    stmt.executeQuery();
                }
                stmt.close();
                System.out.println("Crime ajouté !");
                this.setVisible(false);
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(DlgAjoutCrime.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
            jop.showMessageDialog(null, message, "Erreur lors de l'ajout", JOptionPane.INFORMATION_MESSAGE, null);
    }//GEN-LAST:event_AjouterActionPerformed

    private void AnnulerActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_AnnulerActionPerformed
    {//GEN-HEADEREND:event_AnnulerActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_AnnulerActionPerformed

    private void CreerEnqueteActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_CreerEnqueteActionPerformed
    {//GEN-HEADEREND:event_CreerEnqueteActionPerformed
        DlgAjoutEnquete dlg = new DlgAjoutEnquete();
        dlg.setVisible(true);
    }//GEN-LAST:event_CreerEnqueteActionPerformed

    private void AjouterVictimeActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_AjouterVictimeActionPerformed
    {//GEN-HEADEREND:event_AjouterVictimeActionPerformed
        String victime = this.ListeVictimes.getSelectedItem().toString();
        this.personnesVictimes.add(victime);
        this.ListeVictimes.removeItem(victime);
        this.remplirPanelVictimes();
    }//GEN-LAST:event_AjouterVictimeActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowGainedFocus
    {//GEN-HEADEREND:event_formWindowGainedFocus
        this.initialisationCbVictimes();
        this.initialisationCbEnquetes();
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
            java.util.logging.Logger.getLogger(DlgAjoutCrime.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(DlgAjoutCrime.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(DlgAjoutCrime.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(DlgAjoutCrime.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new DlgAjoutCrime().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Ajouter;
    private javax.swing.JButton AjouterVictime;
    private javax.swing.JButton Annuler;
    private javax.swing.JButton CreerEnquete;
    private javax.swing.JTextField Date;
    private javax.swing.JTextField Fait;
    private javax.swing.JTextField Lieu;
    private javax.swing.JComboBox ListeEnquetes;
    private javax.swing.JComboBox ListeVictimes;
    private javax.swing.JPanel Victimes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
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
    // End of variables declaration//GEN-END:variables
}
