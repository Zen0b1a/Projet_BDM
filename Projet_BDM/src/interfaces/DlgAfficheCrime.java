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
import javax.swing.SwingConstants;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;
import utils.ConnexionUtils;

/**
 *
 * @author Annabelle
 */
public class DlgAfficheCrime extends javax.swing.JFrame
{
    private int id;
    /**
     * Creates new form DlgAfficheCrime
     */
    public DlgAfficheCrime(int id)
    {
        initComponents();
        this.id = id;
        try 
        {
            OraclePreparedStatement stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("SELECT fait, dateC, lieu FROM bdm_crime WHERE id=?");
            stmt.setInt(1, this.id);
            OracleResultSet rs = (OracleResultSet)stmt.executeQuery();
            rs.next();
            //Récupération des informations de l'enquête
            this.Fait.setText(rs.getString("FAIT"));
            this.Date.setText(rs.getString("DATEC"));
            this.Lieu.setText(rs.getString("LIEU"));
            rs.close();
            stmt.close();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DlgAfficheEnquete.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Récupération de la liste des témoignages
        this.initialisationTemoignages();

        //Récupération de la liste des victimes
        this.initialisationVictimes();
    }
    
    private void initialisationTemoignages()
    {
        try
        {
            this.Temoignages.removeAll();
            this.Temoignages.setLayout(new GridLayout());
            OraclePreparedStatement stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("SELECT id, DEREF(personneT).nom, DEREF(personneT).prenom, dateT FROM bdm_temoignage "
                    + "WHERE DEREF(crimeT).id=? ORDER BY id");
            stmt.setInt(1, this.id);
            OracleResultSet rs = (OracleResultSet)stmt.executeQuery();
            int idTemoignage;
            Font fonte = new Font("Courier",Font.PLAIN,14);
            while(rs.next())
            {
                idTemoignage = rs.getInt("ID");
                JButton button = new JButton();
                button.setName(""+idTemoignage);
                //Ajout des informations dans le bouton
                button.setFont(fonte);
                button.setText("<HTML><body>Nom : "+rs.getString("DEREF(personneT).nom")+"<br>Prénom : "+rs.getString("DEREF(personneT).prenom")+"<br>Date : "+rs.getString("DATET")+"</HTML></body>");
                button.setVerticalTextPosition(SwingConstants.BOTTOM);
                button.setHorizontalTextPosition(SwingConstants.CENTER); 
                button.setSize(50, 50);
                
                //Ajout du listener
                button.addActionListener(new java.awt.event.ActionListener() 
                {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        afficherTemoignage(evt);
                    }
                });
                
                this.Temoignages.add(button);
            }
            rs.close();
            stmt.close();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(DlgAfficheCrime.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void initialisationVictimes()
    {
        try
        {
            this.Victimes.removeAll();
            this.Victimes.setLayout(new GridLayout());
            OraclePreparedStatement stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("SELECT DEREF(personneV).id, DEREF(personneV).nom, DEREF(personneV).prenom FROM bdm_victime "
                    + "WHERE DEREF(crimeV).id=? ORDER BY id");
            stmt.setInt(1, this.id);
            OracleResultSet rs = (OracleResultSet)stmt.executeQuery();
            int idVictime;
            Font fonte = new Font("Courier",Font.PLAIN,14);
            while(rs.next())
            {
                idVictime = rs.getInt("DEREF(personneV).id");
                JButton button = new JButton();
                button.setFont(fonte);
                button.setName(""+idVictime);
                //Ajout des informations dans le bouton
                button.setText("<HTML><body>Nom : "+rs.getString("DEREF(personneV).nom")+"<br>Prénom : "+rs.getString("DEREF(personneV).prenom")+"</HTML></body>");
                button.setVerticalTextPosition(SwingConstants.BOTTOM);
                button.setHorizontalTextPosition(SwingConstants.CENTER); 
                button.setSize(50, 50);
                
                //Ajout du listener
                button.addActionListener(new java.awt.event.ActionListener() 
                {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        afficherVictime(evt);
                    }
                });
                
                this.Victimes.add(button);
            }
            
            rs.close();
            stmt.close();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(DlgAfficheCrime.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void afficherTemoignage(java.awt.event.ActionEvent evt)
    {
        JButton jb = (JButton)evt.getSource();
        int id = Integer.parseInt(jb.getName());
        DlgAfficheTemoignage dlg = new DlgAfficheTemoignage(id);
        dlg.setVisible(true);
    }
    
    private void afficherVictime(java.awt.event.ActionEvent evt)
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
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Fait = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Date = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Lieu = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        AjoutTemoignage = new javax.swing.JButton();
        Temoignages = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        Victimes = new javax.swing.JPanel();

        setTitle("Crime");
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        getContentPane().setLayout(new java.awt.GridLayout(3, 1));

        jPanel1.setBackground(new java.awt.Color(226, 220, 207));
        jPanel1.setLayout(new java.awt.GridLayout(3, 2));

        jLabel1.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel1.setText("Fait :");
        jPanel1.add(jLabel1);

        Fait.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        jPanel1.add(Fait);

        jLabel3.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel3.setText("Date :");
        jPanel1.add(jLabel3);

        Date.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        jPanel1.add(Date);

        jLabel5.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel5.setText("Lieu :");
        jPanel1.add(jLabel5);

        Lieu.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        jPanel1.add(Lieu);

        getContentPane().add(jPanel1);

        jPanel12.setBackground(new java.awt.Color(226, 220, 207));
        jPanel12.setLayout(new java.awt.BorderLayout());

        jPanel13.setBackground(new java.awt.Color(226, 220, 207));
        jPanel13.setLayout(new java.awt.GridLayout(1, 2));

        jLabel6.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel6.setText("Témoignages");
        jPanel13.add(jLabel6);

        AjoutTemoignage.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        AjoutTemoignage.setText("Ajouter un témoignage");
        AjoutTemoignage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjoutTemoignageActionPerformed(evt);
            }
        });
        jPanel13.add(AjoutTemoignage);

        jPanel12.add(jPanel13, java.awt.BorderLayout.PAGE_START);

        Temoignages.setBackground(new java.awt.Color(226, 220, 207));

        javax.swing.GroupLayout TemoignagesLayout = new javax.swing.GroupLayout(Temoignages);
        Temoignages.setLayout(TemoignagesLayout);
        TemoignagesLayout.setHorizontalGroup(
            TemoignagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 530, Short.MAX_VALUE)
        );
        TemoignagesLayout.setVerticalGroup(
            TemoignagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 71, Short.MAX_VALUE)
        );

        jPanel12.add(Temoignages, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel12);

        jPanel14.setBackground(new java.awt.Color(226, 220, 207));
        jPanel14.setLayout(new java.awt.BorderLayout());

        jPanel15.setBackground(new java.awt.Color(226, 220, 207));
        jPanel15.setLayout(new java.awt.GridLayout(1, 1));

        jLabel7.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel7.setText("Victimes");
        jPanel15.add(jLabel7);

        jPanel14.add(jPanel15, java.awt.BorderLayout.PAGE_START);

        Victimes.setBackground(new java.awt.Color(226, 220, 207));

        javax.swing.GroupLayout VictimesLayout = new javax.swing.GroupLayout(Victimes);
        Victimes.setLayout(VictimesLayout);
        VictimesLayout.setHorizontalGroup(
            VictimesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 530, Short.MAX_VALUE)
        );
        VictimesLayout.setVerticalGroup(
            VictimesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 79, Short.MAX_VALUE)
        );

        jPanel14.add(Victimes, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel14);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AjoutTemoignageActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_AjoutTemoignageActionPerformed
    {//GEN-HEADEREND:event_AjoutTemoignageActionPerformed
        DlgAjoutTemoignage dlg = new DlgAjoutTemoignage(this.id);
        dlg.setVisible(true);
    }//GEN-LAST:event_AjoutTemoignageActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowGainedFocus
    {//GEN-HEADEREND:event_formWindowGainedFocus
        //Récupération de la liste des témoignages
        this.initialisationTemoignages();
        //Récupération de la liste des victimes
        this.initialisationVictimes();
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
            java.util.logging.Logger.getLogger(DlgAfficheCrime.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(DlgAfficheCrime.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(DlgAfficheCrime.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(DlgAfficheCrime.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AjoutTemoignage;
    private javax.swing.JLabel Date;
    private javax.swing.JLabel Fait;
    private javax.swing.JLabel Lieu;
    private javax.swing.JPanel Temoignages;
    private javax.swing.JPanel Victimes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    // End of variables declaration//GEN-END:variables
}
