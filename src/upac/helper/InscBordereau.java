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
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author user
 */
public class InscBordereau extends javax.swing.JDialog {

    /**
     * Creates new form AjoutMembre
     */
    SpinnerListModel model;
    String[] affectationSpinner = {"Apparitora", "Inscription", "Assurance Maladie", "Frais Divers", "Frais Academique"};
    String[] posteSpinner = {"Appariteur", "Administrateur Chef Technique", "Chef de bureaux"};
    File file = null;
    DatabaseManager db;
    public static int idStudent;
    //JSpinner affectation ;
    public InscBordereau(java.awt.Frame parent, boolean modal) {
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
        bordereauNumber = new javax.swing.JTextField();
        createStudent = new javax.swing.JButton();
        imagePorfile = new javax.swing.JButton();
        profile = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setPreferredSize(new java.awt.Dimension(813, 580));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bordereauNumber.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bordereauNumber.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(77, 56, 91), 1, true));
        bordereauNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bordereauNumberActionPerformed(evt);
            }
        });
        jPanel1.add(bordereauNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, 170, 30));

        createStudent.setBackground(new java.awt.Color(77, 56, 91));
        createStudent.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        createStudent.setForeground(new java.awt.Color(255, 255, 255));
        createStudent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Load.png"))); // NOI18N
        createStudent.setText("Enregistrer");
        createStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createStudentActionPerformed(evt);
            }
        });
        jPanel1.add(createStudent, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 330, 150, 50));

        imagePorfile.setBackground(new java.awt.Color(77, 56, 91));
        imagePorfile.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        imagePorfile.setForeground(new java.awt.Color(255, 255, 255));
        imagePorfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Add.png"))); // NOI18N
        imagePorfile.setText("Profile Pic");
        imagePorfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imagePorfileActionPerformed(evt);
            }
        });
        jPanel1.add(imagePorfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 250, 38));

        profile.setBackground(new java.awt.Color(0, 51, 102));
        profile.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(77, 56, 91), 2, true));
        profile.setOpaque(true);
        jPanel1.add(profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 250, 180));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(77, 56, 91));
        jLabel16.setText("Borderaux de Depot");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 320, 40));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 450, 10));

        jLabel14.setBackground(new java.awt.Color(77, 56, 91));
        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("N# de Bordereau");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, 130, 20));
        jPanel1.add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 50, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bordereauNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bordereauNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bordereauNumberActionPerformed

    private void createStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createStudentActionPerformed
        // TODO add your handling code here:
        if (!bordereauNumber.getText().equals("")) {
            if (file != null) {
                createStudent.setIcon(new ImageIcon(getClass().getResource("/images/ajax_loader_gray_32.gif")));
                db.appPayementInscription(idStudent, bordereauNumber.getText(), 
                        UniversalMethod.encodeFileToBase64Binary(file) , 
                        UniversalMethod.getCurrentDateAndTime());
                this.dispose();
            }            
        }
    }//GEN-LAST:event_createStudentActionPerformed

    private void imagePorfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imagePorfileActionPerformed
        // TODO add your handling code here:
        chooseProfilePicute();
    }//GEN-LAST:event_imagePorfileActionPerformed

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
            java.util.logging.Logger.getLogger(InscBordereau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InscBordereau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InscBordereau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InscBordereau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                InscBordereau dialog = new InscBordereau(new javax.swing.JFrame(), true);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bordereauNumber;
    private javax.swing.JButton createStudent;
    private javax.swing.JButton imagePorfile;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel logo;
    private javax.swing.JLabel profile;
    // End of variables declaration//GEN-END:variables
}