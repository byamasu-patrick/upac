/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upac.helper;

//import com.sun.istack.internal.FragmentContentHandler;
import javafx.scene.control.DialogPane;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import upac.AcceuilFrame;
import static upac.AcceuilFrame.db;
import upac.Apparitora;
import upac.AssuranceMaladie;
import upac.FraisAcademique;
import upac.FraisDivers;
import upac.Inscription;
import upac.ServiceTechnique;
import upac.universalclasses.ShowMessagesThread;
import upac.universalclasses.UniversalThread;

/**
 *
 * @author user
 */
public class Login extends javax.swing.JDialog {

    /**
     * Creates new form Login
     */
    private static Login dialog;
    public static DatabaseManager db = null;
    public Login(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //this.db = new DatabaseManager();
        
        new ShowMessagesThread().start();
        
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        //UniversalMethod.generateQRCode("Byamasu Patrick Paul", "paul.png");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        login2 = new javax.swing.JButton();
        password = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(400, 300));
        setPreferredSize(new java.awt.Dimension(400, 300));

        jPanel3.setBackground(new java.awt.Color(77, 56, 91));
        jPanel3.setMaximumSize(new java.awt.Dimension(400, 301));
        jPanel3.setMinimumSize(new java.awt.Dimension(400, 301));

        jLabel8.setBackground(new java.awt.Color(77, 56, 91));
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Nom d'utilisateur ou Email");

        email.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        email.setText("Address email");
        email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                emailFocusGained(evt);
            }
        });
        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Mot de passe");

        login2.setBackground(new java.awt.Color(255, 255, 255));
        login2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        login2.setForeground(new java.awt.Color(77, 56, 91));
        login2.setText("Login");
        login2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });

        password.setText("jPasswordField1");
        password.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passwordFocusGained(evt);
            }
        });
        password.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                passwordMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addGap(29, 29, 29)
                            .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(login2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel8)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel8)
                .addGap(11, 11, 11)
                .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(jLabel9)
                .addGap(13, 13, 13)
                .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(login2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        // TODO add your handling code here:
        login2.setIcon(new ImageIcon(getClass().getResource("/images/ajax_loader_gray_32.gif")));
            UniversalThread t = new UniversalThread();
            t.start();
            db = UniversalThread.db;
//        
        boolean state = this.db.loginAdmin(email.getText().toString().trim(), password.getText().toString().trim());
        boolean stateMemberApp = this.db.loginApparitora(email.getText().toString().trim(), password.getText().toString().trim());// = this.db.loginAdmin(email.getText().toString().trim(), password.getText().toString().trim());
        boolean memberState = db.logiMember(email.getText().toString().trim(), password.getText().toString().trim());
        
        if (state) {

            if (UniversalMethod.adminLogin.get(0).getAdmin_state() == 0) {
                dialog.setVisible(false);
                UpdateMembre technique = new UpdateMembre(new javax.swing.JFrame(), true);
                //progressBar.dispose();
                technique.setVisible(true);
            }
            else{
                dialog.setVisible(false);
                ServiceTechnique technique = new ServiceTechnique();
                //progressBar.dispose();
                technique.setVisible(true);
            }
            //AcceuilFrame.f.setVisible(false);
        }
        else if(stateMemberApp){
            dialog.dispose();
            Apparitora app = new Apparitora();
            //progressBar.dispose();
            app.setVisible(true);
        }
        else if (memberState) {
            if (UniversalMethod.typeAff.equals("Inscription")) {
                this.dispose();
                //progressBar.dispose();
                new Inscription().setVisible(true);
            }
            else if(UniversalMethod.typeAff.equals("Assurance Maladie")){
                this.dispose();
                //progressBar.dispose();
                new AssuranceMaladie().setVisible(true);
            }
            else if(UniversalMethod.typeAff.equals("Frais Divers")){
                this.dispose();
                //progressBar.dispose();
                new FraisDivers().setVisible(true);
            }
            else if(UniversalMethod.typeAff.equals("Frais Academique")){
                this.dispose();
                //progressBar.dispose();
                new FraisAcademique().setVisible(true);
            }

        }
        else{
            JOptionPane.showMessageDialog(this, "Votre mot de passe est incorrect!");
            //System.out.println("Votre mot de passe est incorrect!");
        }
    }//GEN-LAST:event_loginActionPerformed

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void passwordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passwordMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_passwordMouseClicked

    private void passwordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFocusGained
        // TODO add your handling code here:
       password.setText("");
       //System.out.println("Here is the Text : ==>>"+ password.getText());
    }//GEN-LAST:event_passwordFocusGained

    private void emailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailFocusGained
        // TODO add your handling code here:
        email.setText("");
    }//GEN-LAST:event_emailFocusGained

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                 dialog = new Login(new JFrame(), true);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField email;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton login2;
    private javax.swing.JPasswordField password;
    // End of variables declaration//GEN-END:variables
}