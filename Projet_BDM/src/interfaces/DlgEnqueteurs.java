/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.awt.*;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import oracle.jdbc.*;
import oracle.ord.im.OrdImage;
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
            this.PanelEnqueteurs.removeAll();
            this.PanelEnqueteurs.setLayout(new GridLayout((int)Math.ceil(nbEnqueteurs/5), 5));
            stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("SELECT id, nom, prenom, badge, photo FROM bdm_enqueteur ORDER BY id");
            rs = (OracleResultSet)stmt.executeQuery();
            int idEnqueteur;
            while(rs.next())
            {
                idEnqueteur = rs.getInt("ID");
                JButton button = new JButton();
                button.setLayout(new GridLayout(2, 1));
                button.setName(""+idEnqueteur);
                //Ajout des informations dans le bouton
                OrdImage img = (OrdImage)rs.getORAData("PHOTO", OrdImage.getORADataFactory());
                String fichier = "temp/"+idEnqueteur;
                img.getDataInFile(fichier);
                button.setIcon(new ImageIcon(fichier));
                button.setText("<HTML><body>Badge : "+rs.getString("BADGE")+"<br>Nom : "+rs.getString("NOM")+"<br>Prénom : "+rs.getString("PRENOM")+"</HTML></body>");
                button.setVerticalTextPosition(SwingConstants.BOTTOM); 
                button.setHorizontalTextPosition(SwingConstants.CENTER); 
                
                //Ajout du listener
                button.addActionListener(new java.awt.event.ActionListener() 
                {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        afficherEnqueteur(evt);
                    }
                });
                
                this.PanelEnqueteurs.add(button);
            }
            rs.close();
            stmt.close();
        } 
        catch (SQLException | IOException ex) 
        {
            Logger.getLogger(DlgEnqueteurs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void afficherEnqueteur(java.awt.event.ActionEvent evt)
    {
        JButton jb = (JButton)evt.getSource();
        int id = Integer.parseInt(jb.getName());
        DlgAfficheEnqueteur dlg = new DlgAfficheEnqueteur(id);
        dlg.setVisible(true);
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
        NbEnqueteurs = new javax.swing.JLabel();
        Ajouter = new javax.swing.JButton();
        jScrollPane = new javax.swing.JScrollPane();
        PanelEnqueteurs = new javax.swing.JPanel();

        setMinimumSize(new java.awt.Dimension(500, 500));
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

        jPanel1.setLayout(new java.awt.GridLayout(1, 3));

        jLabel1.setText("Nombre d'enquêteurs :");
        jPanel1.add(jLabel1);
        jPanel1.add(NbEnqueteurs);

        Ajouter.setText("Ajouter");
        Ajouter.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
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

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowGainedFocus
    {//GEN-HEADEREND:event_formWindowGainedFocus
        this.initialiserEnqueteurs();
    }//GEN-LAST:event_formWindowGainedFocus

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
