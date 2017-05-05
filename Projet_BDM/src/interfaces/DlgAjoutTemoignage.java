/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;
import utils.ConnexionUtils;

/**
 *
 * @author Annabelle
 */
public class DlgAjoutTemoignage extends javax.swing.JFrame
{
    private int idC;
    /**
     * Creates new form DlgAjoutTemoignage
     */
    public DlgAjoutTemoignage(int idC)
    {
        initComponents();
        this.idC = idC;
        this.initialisationCbPersonnes();
    }

    private void initialisationCbPersonnes()
    {
        try 
        {
            //Remplissage de la combobox
            OraclePreparedStatement stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("SELECT id, nom, prenom FROM bdm_personne ORDER BY id");
            OracleResultSet rs = (OracleResultSet)stmt.executeQuery();
            int idPersonne;
            String nomPersonne;
            String prenomPersonne;
            this.ListePersonnes.removeAllItems();
            this.ListePersonnes.setSelectedIndex(-1);
            while(rs.next())
            {
                idPersonne = rs.getInt("ID");
                nomPersonne = rs.getString("NOM");
                prenomPersonne = rs.getString("PRENOM");
                this.ListePersonnes.addItem(idPersonne+" - "+nomPersonne+" "+prenomPersonne);
            }
            rs.close();
            stmt.close();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DlgAjoutTemoignage.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        Annuler = new javax.swing.JButton();
        Ajouter = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        ListePersonnes = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Contenu = new javax.swing.JTextArea();

        setTitle("Ajout d'un témoignage");

        jPanel1.setBackground(new java.awt.Color(226, 220, 207));
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        Annuler.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        Annuler.setText("Annuler");
        Annuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnnulerActionPerformed(evt);
            }
        });
        jPanel1.add(Annuler);

        Ajouter.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        Ajouter.setText("Ajouter");
        Ajouter.setPreferredSize(new java.awt.Dimension(111, 40));
        Ajouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjouterActionPerformed(evt);
            }
        });
        jPanel1.add(Ajouter);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jPanel2.setBackground(new java.awt.Color(226, 220, 207));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(226, 220, 207));
        jPanel3.setLayout(new java.awt.GridLayout(1, 2));

        jLabel1.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel1.setText("Personne :");
        jPanel3.add(jLabel1);

        ListePersonnes.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        ListePersonnes.setToolTipText("");
        ListePersonnes.setPreferredSize(new java.awt.Dimension(280, 40));
        jPanel3.add(ListePersonnes);

        jPanel2.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel4.setBackground(new java.awt.Color(226, 220, 207));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jLabel3.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Témoignage :");
        jLabel3.setPreferredSize(new java.awt.Dimension(132, 40));
        jPanel4.add(jLabel3, java.awt.BorderLayout.PAGE_START);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(178, 250));

        Contenu.setColumns(20);
        Contenu.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        Contenu.setRows(5);
        Contenu.setPreferredSize(new java.awt.Dimension(160, 250));
        jScrollPane1.setViewportView(Contenu);

        jPanel4.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel4, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AnnulerActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_AnnulerActionPerformed
    {//GEN-HEADEREND:event_AnnulerActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_AnnulerActionPerformed

    private void AjouterActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_AjouterActionPerformed
    {//GEN-HEADEREND:event_AjouterActionPerformed
        String contenu = this.Contenu.getText();
        boolean valide = true;
        String message = "Erreur lors de l'ajout :";
        if(this.ListePersonnes.getSelectedIndex()<=-1)
        {
            valide = false;
            message += "\nIl faut sélectionner une personne.";
        }
        if(contenu.equals(""))
        {
            valide = false;
            message += "\nLe contenu du témoignage ne peut pas être vide.";
        }
        if(valide)
        {
            try 
            {
                //Ajout du crime
                //Récupération de l'id
                OraclePreparedStatement stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("SELECT MAX(id) FROM bdm_temoignage");
                OracleResultSet rs = (OracleResultSet)stmt.executeQuery();
                rs.next();
                int idTemoignage = rs.getInt(1)+1;
                rs.close();
                //Insertion
                stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("INSERT INTO bdm_temoignage VALUES(?, SYSDATE, ?, "
                    + "(SELECT REF(p) FROM bdm_personne p WHERE p.id=?), (SELECT REF(c) FROM bdm_crime c WHERE c.id=?))");
                stmt.setInt(1, idTemoignage);
                stmt.setString(2, contenu);
                stmt.setInt(3, Integer.parseInt(this.ListePersonnes.getSelectedItem().toString().split(" - ")[0]));
                stmt.setInt(4, this.idC);
                stmt.executeQuery();
                stmt.close();
                Statement st = ConnexionUtils.getInstance().createStatement();
                st.execute("ALTER INDEX contenu_index REBUILD");
                System.out.println("Témoignage ajouté !");
                this.setVisible(false);
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(DlgAjoutTemoignage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
            JOptionPane.showMessageDialog(null, message, "Erreur lors de l'ajout", JOptionPane.INFORMATION_MESSAGE, null);
    }//GEN-LAST:event_AjouterActionPerformed

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
            java.util.logging.Logger.getLogger(DlgAjoutTemoignage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(DlgAjoutTemoignage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(DlgAjoutTemoignage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(DlgAjoutTemoignage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Ajouter;
    private javax.swing.JButton Annuler;
    private javax.swing.JTextArea Contenu;
    private javax.swing.JComboBox ListePersonnes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
