/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.awt.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import oracle.jdbc.*;
import utils.ConnexionUtils;

/**
 *
 * @author ag092850
 */
public class DlgEnqueteurs extends javax.swing.JFrame {

    /**
     * Creates new form DlgEnqueteurs
     */
    public DlgEnqueteurs() {
        initComponents();
        this.initialiserEnqueteurs();
    }

    
    private void initialiserEnqueteurs()
    {
        int nbEnqueteurs;
        try 
        {
            //Initialisation du nombre d'enquêteurs
            OraclePreparedStatement stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("SELECT COUNT(*) FROM bdm_enqueteur");
            OracleResultSet rs = (OracleResultSet)stmt.executeQuery();
            rs.next();
            nbEnqueteurs = rs.getInt(1);
            this.NbEnqueteurs.setText(""+nbEnqueteurs);
            //Création de l'interface
            this.PanelEnqueteurs.setLayout(new GridLayout((int)Math.ceil(nbEnqueteurs/5), 5));
            stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("SELECT * FROM bdm_enqueteur");
            rs = (OracleResultSet)stmt.executeQuery();
            int idEnqueteur;
            while(rs.next())
            {
                idEnqueteur = rs.getInt("ID");
                JButton button = new JButton();
                button.setLayout(new GridLayout(2, 1));
                button.setName(""+idEnqueteur);
                JPanel image = new JPanel();
                //Ajout de l'image dans le panel
                Image im = (Image)rs.getObject("PHOTO");
                JPanel pan_im = new JPanel();
                //TODO
                //Ajout des informations (numéro badge, nom, prénom)
                JLabel j_badge = new JLabel(rs.getString("BADGE"));
                JLabel j_nom = new JLabel(rs.getString("NOM"));
                JLabel j_prenom = new JLabel(rs.getString("PRENOM"));
                JPanel pan_informations = new JPanel();
                pan_informations.add(j_badge);
                pan_informations.add(j_nom);
                pan_informations.add(j_prenom);
                //Ajout de l'image et des informations dans le bouton
                button.add(pan_im);
                button.add(pan_informations);
                
                //Ajout du listener
                button.addActionListener(new java.awt.event.ActionListener() 
                {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        afficherEnqueteur(evt);
                    }
                });
                
                this.PanelEnqueteurs.add(button);
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DlgEnqueteurs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void afficherEnqueteur(java.awt.event.ActionEvent evt)
    {
        int id = Integer.parseInt(this.getName());
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
        jLabel1 = new javax.swing.JLabel();
        NbEnqueteurs = new javax.swing.JLabel();
        Ajouter = new javax.swing.JButton();
        jScrollPane = new javax.swing.JScrollPane();
        PanelEnqueteurs = new javax.swing.JPanel();

        setMinimumSize(new java.awt.Dimension(500, 500));

        jPanel1.setLayout(new java.awt.GridLayout(1, 3));

        jLabel1.setText("Nombre d'enquêteurs :");
        jPanel1.add(jLabel1);
        jPanel1.add(NbEnqueteurs);

        Ajouter.setText("Ajouter");
        Ajouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjouterActionPerformed(evt);
            }
        });
        jPanel1.add(Ajouter);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        PanelEnqueteurs.setLayout(new java.awt.GridLayout(1, 0));
        jScrollPane.setViewportView(PanelEnqueteurs);

        getContentPane().add(jScrollPane, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AjouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AjouterActionPerformed
        DlgAjoutEnqueteur dlgAjoutEnqueteur = new DlgAjoutEnqueteur();
        dlgAjoutEnqueteur.setVisible(true);
    }//GEN-LAST:event_AjouterActionPerformed

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
            java.util.logging.Logger.getLogger(DlgEnqueteurs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DlgEnqueteurs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DlgEnqueteurs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DlgEnqueteurs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DlgEnqueteurs().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Ajouter;
    private javax.swing.JLabel NbEnqueteurs;
    private javax.swing.JPanel PanelEnqueteurs;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane;
    // End of variables declaration//GEN-END:variables
}
