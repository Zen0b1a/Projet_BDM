/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;
import utils.ConnexionUtils;

/**
 *
 * @author Annabelle
 */
public class DlgAfficheTemoignage extends javax.swing.JFrame
{
    private int id;
    /**
     * Creates new form DlgAfficheTemoignage
     */
    public DlgAfficheTemoignage(int id)
    {
        initComponents();
        this.Contenu.setEditable(false);
        this.id = id;
        try 
        {
            OraclePreparedStatement stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("SELECT DEREF(personneT).nom, DEREF(personneT).prenom, "
                    + "dateT, contenu FROM bdm_temoignage WHERE id=?");
            stmt.setInt(1, this.id);
            OracleResultSet rs = (OracleResultSet)stmt.executeQuery();
            rs.next();
            //Récupération des informations de l'enquête
            this.Personne.setText(rs.getString("DEREF(personneT).nom")+" "+rs.getString("DEREF(personneT).prenom"));
            this.DateT.setText(rs.getString("DATET"));
            this.Contenu.setText(rs.getString("CONTENU"));
            rs.close();
            stmt.close();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DlgAfficheTemoignage.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        Personne = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        DateT = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Contenu = new javax.swing.JTextArea();

        setTitle("Témoignage");
        setMinimumSize(new java.awt.Dimension(400, 300));

        jPanel1.setBackground(new java.awt.Color(226, 220, 207));
        jPanel1.setLayout(new java.awt.GridLayout(1, 4));

        Personne.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jPanel1.add(Personne);

        jLabel2.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Le ");
        jPanel1.add(jLabel2);

        DateT.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        DateT.setPreferredSize(new java.awt.Dimension(200, 0));
        jPanel1.add(DateT);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        Contenu.setColumns(20);
        Contenu.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        Contenu.setRows(5);
        jScrollPane1.setViewportView(Contenu);

        jPanel2.add(jScrollPane1);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(DlgAfficheTemoignage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(DlgAfficheTemoignage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(DlgAfficheTemoignage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(DlgAfficheTemoignage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Contenu;
    private javax.swing.JLabel DateT;
    private javax.swing.JLabel Personne;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
