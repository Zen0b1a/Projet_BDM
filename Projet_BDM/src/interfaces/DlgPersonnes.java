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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import mapping.Personne;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;
import utils.ConnexionUtils;

/**
 *
 * @author ag092850
 */
public class DlgPersonnes extends javax.swing.JFrame {

    /**
     * Creates new form DlgPersonnes
     */
    public DlgPersonnes() {
        initComponents();
        this.initialiserPersonnes();
        this.repaint();
    }

    private void initialiserPersonnes()
    {
        int nbPersonnes;
        try 
        {
            //Initialisation du nombre de personnes
            OraclePreparedStatement stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("SELECT COUNT(*) FROM bdm_personne");
            OracleResultSet rs = (OracleResultSet)stmt.executeQuery();
            rs.next();
            nbPersonnes = rs.getInt(1);
            this.NbPersonnes.setText(""+nbPersonnes);
            //Création de l'interface
            this.PanelPersonnes.removeAll();
            this.PanelPersonnes.setLayout(new GridLayout((int)Math.ceil(nbPersonnes/5), 5));
            stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("SELECT id FROM bdm_personne ORDER BY id");
            rs = (OracleResultSet)stmt.executeQuery();
            Font fonte = new Font("Courier",Font.PLAIN,18);
            while(rs.next())
            {
                Personne personne = new Personne().chargerPersonne(rs.getInt("ID"));
                JButton button = new JButton();
                button.setLayout(new GridLayout(2, 1));
                button.setName(""+personne.getId());
                //Ajout des informations dans le bouton
                button.setIcon(new ImageIcon(personne.getCheminPhoto()));
                button.setFont(fonte);
                button.setText("<HTML><body>Nom : "+personne.getNom()+"<br>Prénom : "+personne.getPrenom()+"</HTML></body>");
                button.setVerticalTextPosition(SwingConstants.BOTTOM); 
                button.setHorizontalTextPosition(SwingConstants.CENTER); 
                
                //Ajout du listener
                button.addActionListener(new java.awt.event.ActionListener() 
                {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        afficherPersonne(evt);
                    }
                });
                
                this.PanelPersonnes.add(button);
            }
            rs.close();
            stmt.close();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DlgPersonnes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void afficherPersonne(java.awt.event.ActionEvent evt)
    {
        JButton jb = (JButton)evt.getSource();
        int id = Integer.parseInt(jb.getName());
        DlgAffichePersonne dlg = new DlgAffichePersonne(id);
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
        NbPersonnes = new javax.swing.JLabel();
        Ajouter = new javax.swing.JButton();
        jScrollPane = new javax.swing.JScrollPane();
        PanelPersonnes = new javax.swing.JPanel();

        setTitle("Liste des personnes");
        setPreferredSize(new java.awt.Dimension(792, 500));
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

        jPanel1.setBackground(new java.awt.Color(226, 220, 207));
        jPanel1.setLayout(new java.awt.GridLayout(1, 3));

        jLabel1.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel1.setText("Nombre de personnes:");
        jPanel1.add(jLabel1);

        NbPersonnes.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        jPanel1.add(NbPersonnes);

        Ajouter.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        Ajouter.setText("Ajouter");
        Ajouter.setPreferredSize(new java.awt.Dimension(109, 40));
        Ajouter.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                AjouterActionPerformed(evt);
            }
        });
        jPanel1.add(Ajouter);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jScrollPane.setBackground(new java.awt.Color(226, 220, 207));

        PanelPersonnes.setBackground(new java.awt.Color(226, 220, 207));
        PanelPersonnes.setLayout(new java.awt.GridLayout(1, 0));
        jScrollPane.setViewportView(PanelPersonnes);

        getContentPane().add(jScrollPane, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AjouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AjouterActionPerformed
        DlgAjoutPersonne dlgAjoutPersonne = new DlgAjoutPersonne();
        dlgAjoutPersonne.setVisible(true);
    }//GEN-LAST:event_AjouterActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        this.initialiserPersonnes();
        this.setSize(this.getHeight()-1, this.getWidth()-1);
        this.setSize(this.getHeight()+1, this.getWidth()+1);
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
            java.util.logging.Logger.getLogger(DlgPersonnes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DlgPersonnes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DlgPersonnes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DlgPersonnes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DlgPersonnes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Ajouter;
    private javax.swing.JLabel NbPersonnes;
    private javax.swing.JPanel PanelPersonnes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane;
    // End of variables declaration//GEN-END:variables
}
