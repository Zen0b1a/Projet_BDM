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
import javax.swing.filechooser.FileNameExtensionFilter;
import oracle.jdbc.*;
import oracle.ord.im.OrdImage;
import utils.ConnexionUtils;

/**
 *
 * @author Annabelle
 */
public class DlgAjoutPersonne extends javax.swing.JFrame
{

    private Image photo;
    private String cheminPhoto;
    
    /**
     * Creates new form DlgAjoutPersonne
     */
    public DlgAjoutPersonne()
    {
        this.photo = null;
        initComponents();
    }

    private void affichePhoto()
    {
        Graphics g = this.Photo.getGraphics();
        g.drawImage(this.photo, 0, 0, this.Photo.getWidth(), this.Photo.getHeight(), this);
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Nom = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        Prenom = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        NumeroRue = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        NomRue = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        Ville = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        Telephone1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        Telephone2 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        Photo = new javax.swing.JPanel();
        Parcourir = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        Annuler = new javax.swing.JButton();
        Ajouter = new javax.swing.JButton();

        setTitle("Ajout d'une personne");

        jPanel1.setBackground(new java.awt.Color(226, 220, 207));
        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        jPanel2.setBackground(new java.awt.Color(226, 220, 207));
        jPanel2.setLayout(new java.awt.GridLayout(8, 2));

        jLabel1.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel1.setText("Nom :");
        jLabel1.setPreferredSize(new java.awt.Dimension(55, 50));
        jPanel2.add(jLabel1);

        Nom.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        jPanel2.add(Nom);

        jLabel2.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel2.setText("Prénom :");
        jPanel2.add(jLabel2);

        Prenom.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        jPanel2.add(Prenom);

        jLabel8.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel8.setText("Adresse");
        jPanel2.add(jLabel8);
        jPanel2.add(jLabel9);

        jLabel3.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel3.setText("Numéro de rue :");
        jPanel2.add(jLabel3);

        NumeroRue.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        jPanel2.add(NumeroRue);

        jLabel4.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel4.setText("Rue :");
        jPanel2.add(jLabel4);

        NomRue.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        jPanel2.add(NomRue);

        jLabel5.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel5.setText("Ville :");
        jPanel2.add(jLabel5);

        Ville.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        jPanel2.add(Ville);

        jLabel6.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel6.setText("Téléphone 1 :");
        jPanel2.add(jLabel6);

        Telephone1.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        jPanel2.add(Telephone1);

        jLabel10.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel10.setText("Téléphone 2 :");
        jPanel2.add(jLabel10);

        Telephone2.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        jPanel2.add(Telephone2);

        jPanel1.add(jPanel2);

        jPanel3.setBackground(new java.awt.Color(226, 220, 207));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel11.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Photo");
        jPanel3.add(jLabel11, java.awt.BorderLayout.PAGE_START);

        Photo.setBackground(new java.awt.Color(226, 220, 207));

        javax.swing.GroupLayout PhotoLayout = new javax.swing.GroupLayout(Photo);
        Photo.setLayout(PhotoLayout);
        PhotoLayout.setHorizontalGroup(
            PhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 330, Short.MAX_VALUE)
        );
        PhotoLayout.setVerticalGroup(
            PhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 208, Short.MAX_VALUE)
        );

        jPanel3.add(Photo, java.awt.BorderLayout.CENTER);

        Parcourir.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        Parcourir.setText("Pacourir");
        Parcourir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ParcourirActionPerformed(evt);
            }
        });
        jPanel3.add(Parcourir, java.awt.BorderLayout.PAGE_END);

        jPanel1.add(jPanel3);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel4.setBackground(new java.awt.Color(226, 220, 207));
        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        Annuler.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        Annuler.setText("Annuler");
        Annuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnnulerActionPerformed(evt);
            }
        });
        jPanel4.add(Annuler);

        Ajouter.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        Ajouter.setText("Ajouter");
        Ajouter.setPreferredSize(new java.awt.Dimension(111, 40));
        Ajouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjouterActionPerformed(evt);
            }
        });
        jPanel4.add(Ajouter);

        getContentPane().add(jPanel4, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ParcourirActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_ParcourirActionPerformed
    {//GEN-HEADEREND:event_ParcourirActionPerformed
        //Fenêtre de sélection
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choisir une photo");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "bmp", "jpg", "jpeg", "png");
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(filter);
        if(fileChooser.showOpenDialog(this)==JFileChooser.APPROVE_OPTION)
        {
            //Récupération de l'image
            this.cheminPhoto = fileChooser.getSelectedFile().getAbsolutePath();
            this.photo = Toolkit.getDefaultToolkit().getImage(this.cheminPhoto);
            this.affichePhoto();
        }
    }//GEN-LAST:event_ParcourirActionPerformed

    private void AnnulerActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_AnnulerActionPerformed
    {//GEN-HEADEREND:event_AnnulerActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_AnnulerActionPerformed

    private void AjouterActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_AjouterActionPerformed
    {//GEN-HEADEREND:event_AjouterActionPerformed
        String nom = this.Nom.getText();
        String prenom = this.Prenom.getText();
        String numeroRue = this.NumeroRue.getValue().toString();
        int numRue = 0;
        String nomRue = this.NomRue.getText();
        String ville = this.Ville.getText();
        String telephone1 = this.Telephone1.getText();
        String telephone2 = this.Telephone2.getText();
        boolean valide = true;
        JOptionPane jop = new JOptionPane();
        //Vérification du contenu des champs
        if(nom.equals("") || prenom.equals("") || numeroRue.equals("") || nomRue.equals("") ||
            ville.equals("") || telephone1.equals("") || telephone2.equals(""))
        {
            valide = false;
            jop.showMessageDialog(null, "Toutes les informations doivent être fournies.", "Ajout invalide", JOptionPane.INFORMATION_MESSAGE, null);
        }
        //Vérification de la sélection de la photo
        if(valide && this.photo==null)
        {
            valide = false;
            jop.showMessageDialog(null, "Une photo doit être sélectionnée.", "Ajout invalide", JOptionPane.INFORMATION_MESSAGE, null);
        }
        //Vérification que le numéro de rue est bien un nombre
        if(valide)
        {
            try
            {
                numRue = Integer.parseInt(numeroRue);
            }
            catch(NumberFormatException e)
            {
                valide = false;
                jop.showMessageDialog(null, "Le numéro de rue doit être un nombre.", "Ajout invalide", JOptionPane.INFORMATION_MESSAGE, null);
            }
        }
        //Vérification que les numéros de téléphone sont bien des nombres
        if(valide)
        {
            try
            {
                Integer.parseInt(telephone1);
                Integer.parseInt(telephone2);
            }
            catch(NumberFormatException e)
            {
                valide = false;
                jop.showMessageDialog(null, "Les numéros de téléphone doivent être des nombres.", "Ajout invalide", JOptionPane.INFORMATION_MESSAGE, null);
            }
        }
        //Si tout est valide, on fait l'ajout dans la base de données
        if(valide)
        {
            try
            {
                //Récupération de l'id
                OraclePreparedStatement stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("SELECT MAX(id) FROM bdm_personne");
                OracleResultSet rs = (OracleResultSet)stmt.executeQuery();
                rs.next();
                int id = rs.getInt(1)+1;
                ConnexionUtils.getInstance().setAutoCommit(false);
                //Insertion
                stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("INSERT INTO bdm_personne VALUES(?, ?, ?, "
                    + "bdm_adresse_type(?, ?, ?), bdm_telephones_type(bdm_telephone_type(?), bdm_telephone_type(?)), ORDSYS.ORDImage.init())");
                stmt.setInt(1, id);
                stmt.setString(2, nom);
                stmt.setString(3, prenom);
                stmt.setInt(4, numRue);
                stmt.setString(5, nomRue);
                stmt.setString(6, ville);
                stmt.setString(7, telephone1);
                stmt.setString(8, telephone2);
                stmt.executeQuery();
                //Ajout de la photo
                Statement ps = (Statement)ConnexionUtils.getInstance().createStatement();
                rs = (OracleResultSet)ps.executeQuery("SELECT photo FROM bdm_personne WHERE id="+id+" FOR UPDATE");
                rs.next();
                OrdImage imgObj = (OrdImage)rs.getORAData(1, OrdImage.getORADataFactory());
                imgObj.loadDataFromFile(this.cheminPhoto);
                imgObj.setProperties();
                stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("UPDATE bdm_personne SET photo=? WHERE id=?");
                stmt.setORAData(1, imgObj);
                stmt.setInt(2, id);
                stmt.execute();
                ConnexionUtils.getInstance().commit();
                ps.close();
                rs.close();
                stmt.close();
                ConnexionUtils.getInstance().setAutoCommit(true);
            }
            catch (SQLException | IOException ex)
            {
                Logger.getLogger(DlgAjoutEnqueteur.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.setVisible(false);
        }
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
            java.util.logging.Logger.getLogger(DlgAjoutPersonne.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(DlgAjoutPersonne.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(DlgAjoutPersonne.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(DlgAjoutPersonne.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new DlgAjoutPersonne().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Ajouter;
    private javax.swing.JButton Annuler;
    private javax.swing.JTextField Nom;
    private javax.swing.JTextField NomRue;
    private javax.swing.JSpinner NumeroRue;
    private javax.swing.JButton Parcourir;
    private javax.swing.JPanel Photo;
    private javax.swing.JTextField Prenom;
    private javax.swing.JTextField Telephone1;
    private javax.swing.JTextField Telephone2;
    private javax.swing.JTextField Ville;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}
