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
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import javax.swing.JSlider;
import javax.swing.JTextField;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;
import oracle.ord.im.OrdImage;
import utils.ConnexionUtils;

/**
 *
 * @author ag092850
 */
public class DlgComparaisonImages extends javax.swing.JFrame {
    private int idPr;
    private Image imagePreuve;
    private Image imagePersonne;
    private int indice;
    private List<Pair<Integer, Double>> resultat; //élément 1 : id, élément 2 : score
    /**
     * Creates new form DlgComparaisonImages
     */
    public DlgComparaisonImages(int idPr, Image imagePreuve) {
        initComponents();
        this.idPr = idPr;
        this.imagePreuve = imagePreuve;
        //On vérifie que l'image est chargée
        MediaTracker tracker=new MediaTracker(this);
        tracker.addImage(this.imagePreuve, 0);
        try {tracker.waitForID(0);}
        catch(InterruptedException e) {}
        this.resultat = new ArrayList<>();
        this.indice = 0;
        this.Invisible.setVisible(false);
    }
    
    private void choixImagePersonne()
    {
        if(this.resultat.size()>0)
        {
            try 
            {
                OraclePreparedStatement stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("SELECT photo FROM bdm_personne WHERE id=?");
                stmt.setInt(1, this.resultat.get(this.indice).getKey());
                OracleResultSet rs = (OracleResultSet)stmt.executeQuery();
                rs.next();
                //Récupération de la photo
                OrdImage img = (OrdImage)rs.getORAData("PHOTO", OrdImage.getORADataFactory());
                String fichier = "temp/personne/"+this.resultat.get(this.indice).getKey();
                img.getDataInFile(fichier);
                this.imagePersonne = null;
                this.repaint();
                this.imagePersonne = this.ImagePersonne.getToolkit().getImage(fichier);
                //On vérifie que l'image est chargée
                MediaTracker tracker=new MediaTracker(this);
                tracker.addImage(this.imagePersonne, 0);
                try {tracker.waitForID(0);}
                catch(InterruptedException e) {}
                afficheImagePersonne();
                rs.close();
                stmt.close();
                this.Score.setText(""+(100-this.resultat.get(this.indice).getValue()));
            } 
            catch (SQLException | IOException ex) 
            {
                Logger.getLogger(DlgComparaisonImages.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void trierResultat()
    {
        List<Pair<Integer, Double>> temp = new ArrayList<>();
        int taille = this.resultat.size();
        double min;
        int indiceMin;
        for(int i=0; i<taille; i++)
        {
            min = this.resultat.get(0).getValue();
            indiceMin = 0;
            for(int j=1; j<this.resultat.size(); j++)
            {
                if(this.resultat.get(j).getValue()<min)
                {
                    min = this.resultat.get(j).getValue();
                    indiceMin = j;
                }
            }
            temp.add(this.resultat.get(indiceMin));
            this.resultat.remove(indiceMin);
        }
        this.resultat = temp;
    }
    
    private void afficheImagePreuve()
    {
        Graphics2D g = (Graphics2D)this.ImagePreuve.getGraphics();
        Double scaleWidth = this.ImagePreuve.getWidth()/(double)this.imagePreuve.getWidth(null);
	Double scaleHeight = this.ImagePreuve.getHeight()/(double)this.imagePreuve.getHeight(null);
        if (scaleWidth>scaleHeight)
            scaleWidth = scaleHeight;
        else
            scaleHeight = scaleWidth;
        int x = (int)((this.ImagePreuve.getWidth() - (this.imagePreuve.getWidth(null)*scaleWidth)) / 2);
        int y = (int)((this.ImagePreuve.getHeight() - (this.imagePreuve.getHeight(null)*scaleHeight)) / 2);
        g.translate(x, y);
        g.scale(scaleWidth, scaleHeight);
        g.drawImage(this.imagePreuve, 0, 0, null);
    }
    
    private void afficheImagePersonne()
    {
        Graphics2D g = (Graphics2D)this.ImagePersonne.getGraphics();
        Double scaleWidth = this.ImagePersonne.getWidth()/(double)this.imagePersonne.getWidth(null);
	Double scaleHeight = this.ImagePersonne.getHeight()/(double)this.imagePersonne.getHeight(null);
        if(scaleWidth>scaleHeight)
            scaleWidth = scaleHeight;
        else
            scaleHeight = scaleWidth;
        int x = (int)((this.ImagePersonne.getWidth() - (this.imagePersonne.getWidth(null)*scaleWidth)) / 2);
        int y = (int)((this.ImagePersonne.getHeight() - (this.imagePersonne.getHeight(null)*scaleHeight)) / 2);
        g.translate(x, y);
        g.scale(scaleWidth, scaleHeight);
        g.drawImage(this.imagePersonne, 0, 0, null);
    }
    
    public void paint(Graphics g)
    {
        super.paint(g);
        if(this.imagePreuve!=null)
            this.afficheImagePreuve();
        if(this.imagePersonne!=null)
            this.afficheImagePersonne();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        ImagePreuve = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        ImagePersonne = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        Invisible = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        Precedent = new javax.swing.JButton();
        Suivant = new javax.swing.JButton();
        Score = new javax.swing.JLabel();
        CompareImage = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        PondAvgColorS = new javax.swing.JSlider();
        PondAvgColorJTF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        PondColorHistoS = new javax.swing.JSlider();
        PondColorHistoJTF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        PondPosColorS = new javax.swing.JSlider();
        PondPosColorJTF = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        PondTextureS = new javax.swing.JSlider();
        PondTextureJTF = new javax.swing.JTextField();

        setTitle("Comparaison d'images");
        setPreferredSize(new java.awt.Dimension(800, 800));

        jPanel2.setBackground(new java.awt.Color(226, 220, 207));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel7.setLayout(new java.awt.GridLayout(1, 2));

        ImagePreuve.setBackground(new java.awt.Color(226, 220, 207));
        ImagePreuve.setPreferredSize(new java.awt.Dimension(400, 400));

        javax.swing.GroupLayout ImagePreuveLayout = new javax.swing.GroupLayout(ImagePreuve);
        ImagePreuve.setLayout(ImagePreuveLayout);
        ImagePreuveLayout.setHorizontalGroup(
            ImagePreuveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2016, Short.MAX_VALUE)
        );
        ImagePreuveLayout.setVerticalGroup(
            ImagePreuveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 219, Short.MAX_VALUE)
        );

        jPanel7.add(ImagePreuve);

        jPanel5.setLayout(new java.awt.BorderLayout());

        ImagePersonne.setBackground(new java.awt.Color(226, 220, 207));
        ImagePersonne.setPreferredSize(new java.awt.Dimension(200, 200));

        javax.swing.GroupLayout ImagePersonneLayout = new javax.swing.GroupLayout(ImagePersonne);
        ImagePersonne.setLayout(ImagePersonneLayout);
        ImagePersonneLayout.setHorizontalGroup(
            ImagePersonneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2016, Short.MAX_VALUE)
        );
        ImagePersonneLayout.setVerticalGroup(
            ImagePersonneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel5.add(ImagePersonne, java.awt.BorderLayout.CENTER);

        jPanel1.setBackground(new java.awt.Color(226, 220, 207));
        jPanel1.setLayout(new java.awt.GridLayout(1, 3));

        Invisible.setBackground(new java.awt.Color(226, 220, 207));
        Invisible.setLayout(new java.awt.GridLayout(3, 1));

        jLabel9.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Taux de similarité :");
        Invisible.add(jLabel9);

        jPanel6.setBackground(new java.awt.Color(226, 220, 207));
        jPanel6.setLayout(new java.awt.GridLayout(1, 2));

        Precedent.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        Precedent.setText("<");
        Precedent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrecedentActionPerformed(evt);
            }
        });
        jPanel6.add(Precedent);

        Suivant.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        Suivant.setText(">");
        Suivant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SuivantActionPerformed(evt);
            }
        });
        jPanel6.add(Suivant);

        Invisible.add(jPanel6);

        Score.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Invisible.add(Score);

        jPanel1.add(Invisible);

        jPanel5.add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jPanel7.add(jPanel5);

        jPanel2.add(jPanel7, java.awt.BorderLayout.CENTER);

        CompareImage.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        CompareImage.setText("Chercher une correspondance dans les personnes");
        CompareImage.setPreferredSize(new java.awt.Dimension(539, 40));
        CompareImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CompareImageActionPerformed(evt);
            }
        });
        jPanel2.add(CompareImage, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel4.setBackground(new java.awt.Color(226, 220, 207));
        jPanel4.setLayout(new java.awt.GridLayout(9, 2));

        jLabel1.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        jLabel1.setText("Pondération de la couleur moyenne :");
        jPanel4.add(jLabel1);
        jPanel4.add(jLabel2);

        PondAvgColorS.setBackground(new java.awt.Color(226, 220, 207));
        PondAvgColorS.setValue(100);
        PondAvgColorS.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                PondAvgColorSStateChanged(evt);
            }
        });
        jPanel4.add(PondAvgColorS);

        PondAvgColorJTF.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        PondAvgColorJTF.setText("1.0");
        PondAvgColorJTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PondAvgColorJTFKeyReleased(evt);
            }
        });
        jPanel4.add(PondAvgColorJTF);

        jLabel3.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        jLabel3.setText("Pondération de l'histogramme de couleurs :");
        jPanel4.add(jLabel3);
        jPanel4.add(jLabel4);

        PondColorHistoS.setBackground(new java.awt.Color(226, 220, 207));
        PondColorHistoS.setValue(100);
        PondColorHistoS.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                PondColorHistoSStateChanged(evt);
            }
        });
        jPanel4.add(PondColorHistoS);

        PondColorHistoJTF.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        PondColorHistoJTF.setText("1.0");
        PondColorHistoJTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PondColorHistoJTFKeyReleased(evt);
            }
        });
        jPanel4.add(PondColorHistoJTF);

        jLabel5.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        jLabel5.setText("Pondération de la couleur positionnelle :");
        jPanel4.add(jLabel5);
        jPanel4.add(jLabel6);

        PondPosColorS.setBackground(new java.awt.Color(226, 220, 207));
        PondPosColorS.setValue(100);
        PondPosColorS.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                PondPosColorSStateChanged(evt);
            }
        });
        jPanel4.add(PondPosColorS);

        PondPosColorJTF.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        PondPosColorJTF.setText("1.0");
        PondPosColorJTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PondPosColorJTFKeyReleased(evt);
            }
        });
        jPanel4.add(PondPosColorJTF);

        jLabel7.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        jLabel7.setText("Pondération de la texture :");
        jPanel4.add(jLabel7);
        jPanel4.add(jLabel8);

        PondTextureS.setBackground(new java.awt.Color(226, 220, 207));
        PondTextureS.setValue(100);
        PondTextureS.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                PondTextureSStateChanged(evt);
            }
        });
        jPanel4.add(PondTextureS);

        PondTextureJTF.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        PondTextureJTF.setText("1.0");
        PondTextureJTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PondTextureJTFKeyReleased(evt);
            }
        });
        jPanel4.add(PondTextureJTF);

        getContentPane().add(jPanel4, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PondAvgColorSStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_PondAvgColorSStateChanged
        if(!this.PondAvgColorJTF.isFocusOwner())
        {
            double valeur = ((JSlider)evt.getSource()).getValue();
            this.PondAvgColorJTF.setText(""+(valeur/100));
        }
    }//GEN-LAST:event_PondAvgColorSStateChanged

    private void PondColorHistoSStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_PondColorHistoSStateChanged
        if(!this.PondColorHistoJTF.isFocusOwner())
        {
            double valeur = ((JSlider)evt.getSource()).getValue();
            this.PondColorHistoJTF.setText(""+(valeur/100));
        }
    }//GEN-LAST:event_PondColorHistoSStateChanged

    private void PondPosColorSStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_PondPosColorSStateChanged
        if(!this.PondPosColorJTF.isFocusOwner())
        {
            double valeur = ((JSlider)evt.getSource()).getValue();
            this.PondPosColorJTF.setText(""+(valeur/100));
        }
    }//GEN-LAST:event_PondPosColorSStateChanged

    private void PondTextureSStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_PondTextureSStateChanged
        if(!this.PondTextureJTF.isFocusOwner())
        {
            double valeur = ((JSlider)evt.getSource()).getValue();
            this.PondTextureJTF.setText(""+(valeur/100));
        }
    }//GEN-LAST:event_PondTextureSStateChanged

    private void PondAvgColorJTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PondAvgColorJTFKeyReleased
        try
        {
            double valeur = Double.parseDouble(this.PondAvgColorJTF.getText());
            if(valeur>1)
                ((JTextField)evt.getSource()).setText("1.0");
            else
                this.PondAvgColorS.setValue((int)(valeur*100));
        }
        catch(NumberFormatException e)
        {
            
        }
    }//GEN-LAST:event_PondAvgColorJTFKeyReleased

    private void PondColorHistoJTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PondColorHistoJTFKeyReleased
        try
        {
            double valeur = Double.parseDouble(this.PondColorHistoJTF.getText());
            if(valeur>1)
                ((JTextField)evt.getSource()).setText("1.0");
            else
                this.PondColorHistoS.setValue((int)(valeur*100));
        }
        catch(NumberFormatException e)
        {
            
        }
    }//GEN-LAST:event_PondColorHistoJTFKeyReleased

    private void PondPosColorJTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PondPosColorJTFKeyReleased
        try
        {
            double valeur = Double.parseDouble(this.PondPosColorJTF.getText());
            if(valeur>1)
                ((JTextField)evt.getSource()).setText("1.0");
            else
                this.PondPosColorS.setValue((int)(valeur*100));
        }
        catch(NumberFormatException e)
        {
            
        }
    }//GEN-LAST:event_PondPosColorJTFKeyReleased

    private void PondTextureJTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PondTextureJTFKeyReleased
        try
        {
            double valeur = Double.parseDouble(this.PondTextureJTF.getText());
            if(valeur>1)
                ((JTextField)evt.getSource()).setText("1.0");
            else
                this.PondTextureS.setValue((int)(valeur*100));
        }
        catch(NumberFormatException e)
        {
            
        }
    }//GEN-LAST:event_PondTextureJTFKeyReleased

    private void CompareImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CompareImageActionPerformed
        double pondAvgColor = this.PondAvgColorS.getValue()/100;
        double pondHistoColor = this.PondColorHistoS.getValue()/100;
        double pondPosColor = this.PondPosColorS.getValue()/100;
        double pondTexture = this.PondTextureS.getValue()/100;
        this.resultat.clear();
        try 
        {
            OraclePreparedStatement stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("SELECT id FROM bdm_personne");
            OracleResultSet rs = (OracleResultSet)stmt.executeQuery();
            int idPe;
            double score;
            CallableStatement cstmt = ConnexionUtils.getInstance().prepareCall("{?=call compare(?,?,?,?,?,?)}");
            while(rs.next())
            {
                idPe = rs.getInt("ID");
                cstmt.registerOutParameter(1, Types.DOUBLE);
                cstmt.setInt(2, this.idPr);
                cstmt.setInt(3, idPe);
                cstmt.setDouble(4, pondAvgColor);
                cstmt.setDouble(5, pondHistoColor);
                cstmt.setDouble(6, pondPosColor);
                cstmt.setDouble(7, pondTexture);
                cstmt.execute();
                score = cstmt.getDouble(1);
                this.resultat.add(new Pair(idPe, score));
            }
            this.trierResultat();
            cstmt.close();
            rs.close();
            stmt.close();
            this.indice = 0;
            if(this.resultat.size()>0)
            {
                this.Invisible.setVisible(true);
                this.choixImagePersonne();
            }
            else
                this.Invisible.setVisible(false);
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DlgAfficheEnquete.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_CompareImageActionPerformed

    private void PrecedentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrecedentActionPerformed
        this.indice = (this.indice-1)%this.resultat.size();
        if(this.indice<0)
            this.indice = -this.indice;
        this.choixImagePersonne();
    }//GEN-LAST:event_PrecedentActionPerformed

    private void SuivantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SuivantActionPerformed
        this.indice = (this.indice+1)%this.resultat.size();
        this.choixImagePersonne();
    }//GEN-LAST:event_SuivantActionPerformed

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
            java.util.logging.Logger.getLogger(DlgComparaisonImages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DlgComparaisonImages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DlgComparaisonImages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DlgComparaisonImages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CompareImage;
    private javax.swing.JPanel ImagePersonne;
    private javax.swing.JPanel ImagePreuve;
    private javax.swing.JPanel Invisible;
    private javax.swing.JTextField PondAvgColorJTF;
    private javax.swing.JSlider PondAvgColorS;
    private javax.swing.JTextField PondColorHistoJTF;
    private javax.swing.JSlider PondColorHistoS;
    private javax.swing.JTextField PondPosColorJTF;
    private javax.swing.JSlider PondPosColorS;
    private javax.swing.JTextField PondTextureJTF;
    private javax.swing.JSlider PondTextureS;
    private javax.swing.JButton Precedent;
    private javax.swing.JLabel Score;
    private javax.swing.JButton Suivant;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    // End of variables declaration//GEN-END:variables
}
