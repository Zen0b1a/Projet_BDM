/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author Annabelle
 */
public class DlgAjoutCrime extends javax.swing.JFrame
{

    /**
     * Creates new form DlgAjoutCrime
     */
    public DlgAjoutCrime()
    {
        initComponents();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        ChoixVictime = new javax.swing.JButton();
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
        jPanel3.add(CreerEnquete);

        jPanel2.add(jPanel3);

        jPanel4.setLayout(new java.awt.GridLayout(2, 2));

        jLabel6.setText("Victime(s) :");
        jPanel4.add(jLabel6);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setEnabled(false);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel4.add(jScrollPane1);

        ChoixVictime.setText("Choisir une victime existante");
        jPanel4.add(ChoixVictime);

        NouvelleVictime.setText("Ajouter une nouvelle victime");
        jPanel4.add(NouvelleVictime);

        jPanel2.add(jPanel4);

        getContentPane().add(jPanel2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AjouterActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_AjouterActionPerformed
    {//GEN-HEADEREND:event_AjouterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AjouterActionPerformed

    private void AnnulerActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_AnnulerActionPerformed
    {//GEN-HEADEREND:event_AnnulerActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_AnnulerActionPerformed

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
    private javax.swing.JButton Annuler;
    private javax.swing.JButton ChoixVictime;
    private javax.swing.JButton CreerEnquete;
    private javax.swing.JTextField Date;
    private javax.swing.JTextField Fait;
    private javax.swing.JTextField Lieu;
    private javax.swing.JComboBox ListeEnquetes;
    private javax.swing.JButton NouvelleVictime;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}