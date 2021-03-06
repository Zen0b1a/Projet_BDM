/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;
import oracle.ord.im.OrdImage;
import utils.ConnexionUtils;

/**
 *
 * @author ag092850
 */
public class DlgAffichePreuveImage extends javax.swing.JFrame {
    private int idE;
    private int idP;
    private Image image;
    private int indice;
    private List<Integer> id;
    private List<Image> preuves;
    private List<String> descriptions;
    /**
     * Creates new form DlgAffichePreuveImage
     */
    public DlgAffichePreuveImage(int idE, int idP) {
        initComponents();
        this.idE = idE;
        this.idP = idP;
        this.indice = 0;
        this.preuves = new ArrayList<>();
        this.descriptions = new ArrayList<>();
        this.id = new ArrayList<>();
        this.initialiserPreuvesImage();
        this.repaint();
    }

    private void initialiserPreuvesImage()
    {
        try 
        {
            OraclePreparedStatement stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("SELECT id, description, image FROM bdm_preuve_image WHERE DEREF(enqueteP).id=? ORDER BY id");
            stmt.setInt(1, this.idE);
            OracleResultSet rs = (OracleResultSet)stmt.executeQuery();
            while(rs.next())
            {
                //Récupération de la description et de l'id
                this.id.add(rs.getInt("ID"));
                if(this.idP==rs.getInt("ID"))
                    this.indice = this.id.size()-1;
                this.descriptions.add(rs.getString("DESCRIPTION"));
                //Récupération de la photo
                OrdImage img = (OrdImage)rs.getORAData("IMAGE", OrdImage.getORADataFactory());
                String fichier = "temp/image/"+rs.getInt("ID");
                img.getDataInFile(fichier);
                this.preuves.add(this.Image.getToolkit().getImage(fichier));
            }
            this.image = this.preuves.get(this.indice);
            //On vérifie que l'image est chargée
            MediaTracker tracker=new MediaTracker(this);
            tracker.addImage(this.image,0);
            try {tracker.waitForID(0);}
            catch(InterruptedException e) {}
            this.Description.setText(this.descriptions.get(this.indice));
            afficheImage();
            rs.close();
            stmt.close();
        } 
        catch (SQLException | IOException ex) 
        {
            Logger.getLogger(DlgAfficheEnqueteur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void afficheImage()
    {
        Graphics2D g = (Graphics2D)this.Image.getGraphics();
        Double scaleWidth = this.Image.getWidth()/new Double(this.image.getWidth(null));
	Double scaleHeight = this.Image.getHeight()/new Double(this.image.getHeight(null));
        if (scaleWidth>scaleHeight)
            scaleWidth = scaleHeight;
        else
            scaleHeight = scaleWidth;
        int x = (int)((this.Image.getWidth() - (this.image.getWidth(null)*scaleWidth)) / 2);
        int y = (int)((this.Image.getHeight() - (this.image.getHeight(null)*scaleHeight)) / 2);
        g.translate(x, y);
        g.scale(scaleWidth, scaleHeight);
        g.drawImage(this.image, 0, 0, null);
    }
    
    public void paint(Graphics g)
    {
        super.paint(g);
        if(this.image!=null)
            this.afficheImage();
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
        CompareImages = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        Precedent = new javax.swing.JButton();
        Suivant = new javax.swing.JButton();
        Annuler = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        Image = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Description = new javax.swing.JTextArea();

        setTitle("Preuve image");

        jPanel1.setBackground(new java.awt.Color(226, 220, 207));
        jPanel1.setLayout(new java.awt.GridLayout(3, 0));

        CompareImages.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        CompareImages.setText("Chercher une correspondance");
        CompareImages.setPreferredSize(new java.awt.Dimension(329, 40));
        CompareImages.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CompareImagesActionPerformed(evt);
            }
        });
        jPanel1.add(CompareImages);

        jPanel4.setLayout(new java.awt.GridLayout(1, 2));

        Precedent.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        Precedent.setText("<");
        Precedent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrecedentActionPerformed(evt);
            }
        });
        jPanel4.add(Precedent);

        Suivant.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        Suivant.setText(">");
        Suivant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SuivantActionPerformed(evt);
            }
        });
        jPanel4.add(Suivant);

        jPanel1.add(jPanel4);

        Annuler.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        Annuler.setText("Retour");
        Annuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnnulerActionPerformed(evt);
            }
        });
        jPanel1.add(Annuler);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jPanel2.setBackground(new java.awt.Color(226, 220, 207));
        jPanel2.setLayout(new java.awt.BorderLayout());

        Image.setBackground(new java.awt.Color(226, 220, 207));
        Image.setPreferredSize(new java.awt.Dimension(500, 400));

        javax.swing.GroupLayout ImageLayout = new javax.swing.GroupLayout(Image);
        Image.setLayout(ImageLayout);
        ImageLayout.setHorizontalGroup(
            ImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 993, Short.MAX_VALUE)
        );
        ImageLayout.setVerticalGroup(
            ImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 108, Short.MAX_VALUE)
        );

        jPanel2.add(Image, java.awt.BorderLayout.CENTER);

        jPanel3.setBackground(new java.awt.Color(226, 220, 207));
        jPanel3.setLayout(new java.awt.GridLayout(1, 1));

        Description.setEditable(false);
        Description.setColumns(20);
        Description.setRows(5);
        jScrollPane1.setViewportView(Description);

        jPanel3.add(jScrollPane1);

        jPanel2.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnnulerActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_AnnulerActionPerformed

    private void PrecedentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrecedentActionPerformed
        this.indice = (this.indice-1)%this.preuves.size();
        if(this.indice<0)
            this.indice = -this.indice;
        this.image = null;
        repaint();
        this.image = this.preuves.get(this.indice);
        //On vérifie que l'image est chargée
        MediaTracker tracker=new MediaTracker(this);
        tracker.addImage(this.image,0);
        try {tracker.waitForID(0);}
        catch(InterruptedException e) {}
        this.Description.setText(this.descriptions.get(this.indice));
        this.afficheImage();
    }//GEN-LAST:event_PrecedentActionPerformed

    private void SuivantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SuivantActionPerformed
        this.indice = (this.indice+1)%this.preuves.size();
        this.image = null;
        repaint();
        this.image = this.preuves.get(this.indice);
        //On vérifie que l'image est chargée
        MediaTracker tracker=new MediaTracker(this);
        tracker.addImage(this.image,0);
        try {tracker.waitForID(0);}
        catch(InterruptedException e) {}
        this.Description.setText(this.descriptions.get(this.indice));
        this.afficheImage();
    }//GEN-LAST:event_SuivantActionPerformed

    private void CompareImagesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CompareImagesActionPerformed
        DlgComparaisonImages dlg = new DlgComparaisonImages(this.id.get(this.indice), this.image);
        dlg.setVisible(true);
    }//GEN-LAST:event_CompareImagesActionPerformed

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
            java.util.logging.Logger.getLogger(DlgAffichePreuveImage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DlgAffichePreuveImage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DlgAffichePreuveImage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DlgAffichePreuveImage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Annuler;
    private javax.swing.JButton CompareImages;
    private javax.swing.JTextArea Description;
    private javax.swing.JPanel Image;
    private javax.swing.JButton Precedent;
    private javax.swing.JButton Suivant;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
