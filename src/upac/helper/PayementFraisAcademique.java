/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upac.helper;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author user
 */
public class PayementFraisAcademique extends javax.swing.JDialog {

    /**
     * Creates new form AjoutMembre
     */
    SpinnerListModel model;
    String[] affectationSpinner = {"Apparitora", "Inscription", "Assurance Maladie", "Frais Divers", "Frais Academique"};
    String[] posteSpinner = {"Appariteur", "Administrateur Chef Technique", "Chef de bureaux"};
    File file = null;
    DatabaseManager db;
    //JSpinner affectation ;
    public PayementFraisAcademique(java.awt.Frame parent, boolean modal) {
        super(parent, modal);        
        initComponents();
        addItemToSpinner();
        db = Login.db;
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/upac-logo-300x298.png")));
        setDefaultImage(new ImageIcon(getClass().getResource("/images/avatar2.jpeg")));
        setDefaultImageLogo(new ImageIcon(getClass().getResource("/images/upac-logo-300x298.png")));
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        nom = new javax.swing.JTextField();
        postnom = new javax.swing.JTextField();
        prenom = new javax.swing.JTextField();
        numeroBord = new javax.swing.JTextField();
        createStudent = new javax.swing.JButton();
        imagePorfile = new javax.swing.JButton();
        profile = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        tranche = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();
        promotion = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setPreferredSize(new java.awt.Dimension(813, 580));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setBackground(new java.awt.Color(77, 56, 91));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Post Nom");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 80, 20));

        jLabel5.setBackground(new java.awt.Color(77, 56, 91));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Pre Nom");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 90, 20));

        jLabel7.setBackground(new java.awt.Color(77, 56, 91));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Nom");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 90, 20));

        nom.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nom.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(77, 56, 91), 1, true));
        nom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomActionPerformed(evt);
            }
        });
        jPanel1.add(nom, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 180, 30));

        postnom.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        postnom.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(77, 56, 91), 1, true));
        jPanel1.add(postnom, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 180, 30));

        prenom.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        prenom.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(77, 56, 91), 1, true));
        jPanel1.add(prenom, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 180, 30));

        numeroBord.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        numeroBord.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(77, 56, 91), 1, true));
        numeroBord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numeroBordActionPerformed(evt);
            }
        });
        jPanel1.add(numeroBord, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 300, 140, 30));

        createStudent.setBackground(new java.awt.Color(77, 56, 91));
        createStudent.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        createStudent.setForeground(new java.awt.Color(255, 255, 255));
        createStudent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Yes.png"))); // NOI18N
        createStudent.setText("Enregistrer");
        createStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createStudentActionPerformed(evt);
            }
        });
        jPanel1.add(createStudent, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 380, 170, 50));

        imagePorfile.setBackground(new java.awt.Color(77, 56, 91));
        imagePorfile.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        imagePorfile.setForeground(new java.awt.Color(255, 255, 255));
        imagePorfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Add.png"))); // NOI18N
        imagePorfile.setText(" Profile Pic");
        imagePorfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imagePorfileActionPerformed(evt);
            }
        });
        jPanel1.add(imagePorfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 250, 150, 38));

        profile.setBackground(new java.awt.Color(0, 51, 102));
        profile.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(77, 56, 91), 2, true));
        profile.setOpaque(true);
        jPanel1.add(profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, 150, 160));

        jLabel12.setBackground(new java.awt.Color(77, 56, 91));
        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Promotion");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 80, 20));

        jLabel13.setBackground(new java.awt.Color(77, 56, 91));
        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Tranche");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 70, 20));

        tranche.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Premier Tranche", "Deuxieme Tranche", "Troisieme Tranche" }));
        tranche.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(77, 56, 91), 1, true));
        tranche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trancheActionPerformed(evt);
            }
        });
        jPanel1.add(tranche, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, 180, 30));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(77, 56, 91));
        jLabel16.setText("Frais Academique");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 270, 40));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 450, 10));

        jLabel14.setBackground(new java.awt.Color(77, 56, 91));
        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("N# de Bordereau");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 130, 20));
        jPanel1.add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 50, 40));

        promotion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BAC 1", "BAC 2", "BAC 3" }));
        promotion.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(77, 56, 91), 1, true));
        jPanel1.add(promotion, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, 180, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void nomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomActionPerformed

    private void numeroBordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numeroBordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numeroBordActionPerformed

    private void createStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createStudentActionPerformed
        // TODO add your handling code here:
        enregisterPayements();
    }//GEN-LAST:event_createStudentActionPerformed

    private void imagePorfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imagePorfileActionPerformed
        // TODO add your handling code here:
        chooseProfilePicute();
    }//GEN-LAST:event_imagePorfileActionPerformed

    private void trancheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trancheActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_trancheActionPerformed

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
            java.util.logging.Logger.getLogger(PayementFraisAcademique.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PayementFraisAcademique.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PayementFraisAcademique.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PayementFraisAcademique.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PayementFraisAcademique dialog = new PayementFraisAcademique(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    private void setDefaultImage(ImageIcon imageIcon){
       
        Image image = imageIcon.getImage().getScaledInstance(profile.getWidth(), profile.getHeight(), Image.SCALE_SMOOTH);
            
        ImageIcon iconResized = new ImageIcon(image);
            
        profile.setIcon(iconResized);
        
    }
    private void setDefaultImageLogo(ImageIcon imageIcon){
       
        Image image = imageIcon.getImage().getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_SMOOTH);
            
        ImageIcon iconResized = new ImageIcon(image);
            
        logo.setIcon(iconResized);
        
    }
    private void addItemToSpinner(){
        //model = new SpinnerListModel(affectationSpinner);
//        affectation.addItem(affectationSpinner[0]);
//        affectation.addItem(affectationSpinner[1]);
//        affectation.addItem(affectationSpinner[2]);
//        affectation.addItem(affectationSpinner[3]);
//        affectation.addItem(affectationSpinner[4]);
//        
//        poste.addItem(posteSpinner[0]);
//        poste.addItem(posteSpinner[1]);
//        poste.addItem(posteSpinner[2]);
        //affectation = new JSpinner(model);
        //jSpinnerPanell.add(affectation);
    }
    private void chooseProfilePicute(){
        JFileChooser fileChooser = new JFileChooser();
        int imageChooser = fileChooser.showOpenDialog(null);
        fileChooser.setDialogTitle("Selectioner le photo...");
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("IMAGES", "png", "jpg", "jpeg");
        fileChooser.setFileFilter(filter);
        if ( imageChooser == JFileChooser.APPROVE_OPTION ) {
            file = fileChooser.getSelectedFile();
            String profileName = file.getName();
            String profilePath = file.getAbsolutePath();
            //imagePorfile.setText(profileName);
            
            ImageIcon icon = new ImageIcon(profilePath);
            Image image = icon.getImage().getScaledInstance(profile.getWidth(), profile.getHeight(), Image.SCALE_SMOOTH);
            
            ImageIcon iconResized = new ImageIcon(image);
            
            profile.setIcon(iconResized);
        }
    }
    private void enregisterPayements(){
        if (!nom.getText().equals("") && !postnom.getText().equals("") && !prenom.equals("")
                && !promotion.getSelectedItem().toString().equals("") && !numeroBord.getText().toString().equals("")) {
            
            if (file != null) {
                int studentId = db.getStudentByNames(nom.getText().trim(), postnom.getText().trim(), 
                        prenom.getText().trim(), promotion.getSelectedItem().toString().trim());
                if ( studentId != 0) {  
                    createStudent.setIcon(new ImageIcon(getClass().getResource("/images/ajax_loader_gray_32.gif")));
                    db.appPayementFraisAcademique(studentId, numeroBord.getText(),
                            UniversalMethod.encodeFileToBase64Binary(file),
                            tranche.getSelectedItem().toString(),
                            promotion.getSelectedItem().toString(),
                            UniversalMethod.getCurrentDateAndTime());
                    this.dispose();
                }
                else{
                      JOptionPane.showMessageDialog(null, "Cette etudiant n'appartient pas dans cette promotion !");
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Veuillez selectionner le photo du borderau et ressayer!!!");
            }
        }
         else{
                JOptionPane.showMessageDialog(null, "Veuillez remplir toutes le formulaire et ressayer!!!");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton createStudent;
    private javax.swing.JButton imagePorfile;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel logo;
    private javax.swing.JTextField nom;
    private javax.swing.JTextField numeroBord;
    private javax.swing.JTextField postnom;
    private javax.swing.JTextField prenom;
    private javax.swing.JLabel profile;
    private javax.swing.JComboBox<String> promotion;
    private javax.swing.JComboBox<String> tranche;
    // End of variables declaration//GEN-END:variables
}
