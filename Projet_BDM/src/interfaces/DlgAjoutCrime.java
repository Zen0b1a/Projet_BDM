/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

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
            jcb.setName("jcb "+id);
            jcb.removeAllItems();
            jcb.addItem("vivant");
            jcb.addItem("mort");
            JButton jb = new JButton();
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Fait = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        Lieu = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Date = new javax.swing.JTextField();
        Ajouter = new javax.swing.JButton();
        Annuler = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        ListeEnquetes = new javax.swing.JComboBox();
        CreerEnquete = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        Victimes = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        ListeVictimes = new javax.swing.JComboBox();
        AjouterVictime = new javax.swing.JButton();
        NouvelleVictime = new javax.swing.JButton();

        getContentPane().setLayout(new java.awt.GridLayout(1, 2));

        jPanel1.setLayout(new java.awt.GridLayout(4, 2));

        jLabel1.setText("Fait");
        jPanel1.add(jLabel1);
        jPanel1.add(Fait);

        jLabel2.setText("Lieu");
        jPanel1.add(jLabel2);
        jPanel1.add(Lieu);

        jLabel3.setText("Date");
        jPanel1.add(jLabel3);
        jPanel1.add(Date);

        Ajouter.setText("Ajouter");
        Ajouter.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                AjouterActionPerformed(evt);
            }
        });
        jPanel1.add(Ajouter);

        Annuler.setText("Annuler");
        Annuler.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                AnnulerActionPerformed(evt);
            }
        });
        jPanel1.add(Annuler);

        getContentPane().add(jPanel1);

        jPanel2.setLayout(new java.awt.GridLayout(2, 1));

        jPanel3.setLayout(new java.awt.GridLayout(2, 2));

        jLabel4.setText("Enquête :");
        jPanel3.add(jLabel4);
        jPanel3.add(jLabel5);

        jPanel3.add(ListeEnquetes);

        CreerEnquete.setText("Créer une nouvelle enquête");
        CreerEnquete.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                CreerEnqueteActionPerformed(evt);
            }
        });
        jPanel3.add(CreerEnquete);

        jPanel2.add(jPanel3);

        jPanel4.setLayout(new java.awt.GridLayout(2, 2));

        jLabel6.setText("Victime(s) :");
        jPanel4.add(jLabel6);

        javax.swing.GroupLayout VictimesLayout = new javax.swing.GroupLayout(Victimes);
        Victimes.setLayout(VictimesLayout);
        VictimesLayout.setHorizontalGroup(
            VictimesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 292, Short.MAX_VALUE)
        );
        VictimesLayout.setVerticalGroup(
            VictimesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 75, Short.MAX_VALUE)
        );

        jPanel4.add(Victimes);

        jPanel5.setLayout(new java.awt.GridLayout(1, 2));

        jPanel5.add(ListeVictimes);

        AjouterVictime.setText("Ajouter victime");
        AjouterVictime.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                AjouterVictimeActionPerformed(evt);
            }
        });
        jPanel5.add(AjouterVictime);

        jPanel4.add(jPanel5);

        NouvelleVictime.setText("Ajouter une nouvelle victime");
        NouvelleVictime.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                NouvelleVictimeActionPerformed(evt);
            }
        });
        jPanel4.add(NouvelleVictime);

        jPanel2.add(jPanel4);

        getContentPane().add(jPanel2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AjouterActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_AjouterActionPerformed
    {//GEN-HEADEREND:event_AjouterActionPerformed
        String fait = this.Fait.getText();
        String lieu = this.Lieu.getText();
        String date = this.Date.getText();
        int idEnquete = this.ListeEnquetes.getSelectedIndex();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateC;
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
            dateC = simpleDateFormat.parse(date);
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
                //Insertion
                stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("INSERT INTO bdm_enquete VALUES(?, ?, ?, "
                    + "?, (SELECT REF(e) FROM bdm_enquete e WHERE e.numeroE=?))");
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
        this.initialisationCbEnquetes();
    }//GEN-LAST:event_CreerEnqueteActionPerformed

    private void AjouterVictimeActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_AjouterVictimeActionPerformed
    {//GEN-HEADEREND:event_AjouterVictimeActionPerformed
        String victime = this.ListeVictimes.getSelectedItem().toString();
        this.personnesVictimes.add(victime);
        this.ListeVictimes.removeItem(victime);
        this.remplirPanelVictimes();
    }//GEN-LAST:event_AjouterVictimeActionPerformed

    private void NouvelleVictimeActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_NouvelleVictimeActionPerformed
    {//GEN-HEADEREND:event_NouvelleVictimeActionPerformed
        DlgAjoutPersonne dlg = new DlgAjoutPersonne();
        dlg.setVisible(true);
        this.initialisationCbVictimes();
    }//GEN-LAST:event_NouvelleVictimeActionPerformed

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
    private javax.swing.JButton NouvelleVictime;
    private javax.swing.JPanel Victimes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    // End of variables declaration//GEN-END:variables
}
