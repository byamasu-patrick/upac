package upac;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;
import static upac.MemberDetail.rowId;
import static upac.StudentDetail.promotion;
import static upac.StudentDetail.rowId;
import upac.helper.DatabaseManager;
import upac.helper.Login;
import upac.helper.UniversalMethod;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class CarteDialog extends javax.swing.JDialog {

    /**
     * Creates new form Carte
     */
    public static int rowId;
    public static boolean member = false;
    public static boolean student = true;
    ArrayList<String[]> students = null;
    ArrayList<MembreData> members = null;
    private DatabaseManager db;
    public CarteDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        db = Login.db;
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/upac-logo-300x298.png")));
        setDefaultImage(new ImageIcon(getClass().getResource("/images/rdc.png")), rdc);
        setDefaultImage(new ImageIcon(getClass().getResource("/images/upac-logo-300x298.png")), upc);
        setDefaultImage(new ImageIcon(getClass().getResource("/images/qrcode.png")), matricule);
        setDefaultImage(new ImageIcon(getClass().getResource("/images/default.jpg")), profile);
        setDefaultImage(new ImageIcon(getClass().getResource("/images/signature.png")), signature);
        
        
        if (member) {
            members = UniversalMethod.data;
            setDefaultImage(UniversalMethod.convertStringToImageIcon( members.get(rowId).getProfile()), profile);
            setDefaultImage(UniversalMethod.convertStringToImageIcon( members.get(rowId).getMatricule()), matricule);
            fullname.setText(members.get(rowId).getNom());
            poste.setText(members.get(rowId).getPoste());
            String carteID = members.get(rowId).getAffectation().substring(0, 2).toUpperCase() +"#"+ 
                         members.get(rowId).getId() +"-"+ members.get(rowId).getTimestamp().split(" ")[0];
                 numerodelaCarte.setText(carteID);
            
        }
        if (student) {
            if (promotion.equals("BAC 1")) {
                students = UniversalMethod.studentDataAttenteBAC1;
                
                 String carteID = students.get(rowId)[2].substring(0, 2).toUpperCase() +"#"+ 
                         students.get(rowId)[4] +"-"+ students.get(rowId)[9].split(" ")[0];
                 numerodelaCarte.setText(carteID);
            }
            else if (promotion.equals("BAC 2")) {
                students = UniversalMethod.studentDataAttenteBAC2;
                String carteID = students.get(rowId)[2].substring(0, 2).toUpperCase() +"#"+ 
                         students.get(rowId)[4] +"-"+ students.get(rowId)[9].split(" ")[0];
                 numerodelaCarte.setText(carteID);
            }
            else if (promotion.equals("BAC 3")) {
                students = UniversalMethod.studentDataAttenteBAC3;
                String carteID = students.get(rowId)[2].substring(0, 2).toUpperCase() +"#"+ 
                         students.get(rowId)[4] +"-"+ students.get(rowId)[9].split(" ")[0];
                 numerodelaCarte.setText(carteID);
            }
            if (students != null) {
                fullname.setText(students.get(rowId)[0]);
                poste.setText(students.get(rowId)[2]);
                setDefaultImage(UniversalMethod.convertStringToImageIcon( students.get(rowId)[1]), profile);
                setDefaultImage(UniversalMethod.convertStringToImageIcon( students.get(rowId)[6]), matricule);

            }
        //JPanel gamePanel = new JPanel();
//        Image background = Toolkit.getDefaultToolkit().createImage("Background.png");
//        cartePanel.idrawImage(background, 1, 0, null);
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
        cartePanel = new javax.swing.JPanel();
        upc = new javax.swing.JLabel();
        rdc = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        profile = new javax.swing.JLabel();
        matricule = new javax.swing.JLabel();
        numerodelaCarte = new javax.swing.JLabel();
        fullname = new javax.swing.JLabel();
        poste = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        signature = new javax.swing.JLabel();
        enregistrer = new javax.swing.JButton();
        imprimer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(350, 300));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cartePanel.setBackground(new java.awt.Color(255, 255, 255));
        cartePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(77, 56, 91), 2));
        cartePanel.setMaximumSize(new java.awt.Dimension(350, 210));
        cartePanel.setPreferredSize(new java.awt.Dimension(350, 210));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Univerisite Panafricaine  du Congo");

        profile.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(77, 56, 91)));

        matricule.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        numerodelaCarte.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        numerodelaCarte.setText("ID92296499");

        fullname.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        fullname.setText("Byamasu Patrick Paul");

        poste.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        poste.setText("Faculte de Science de L'ingenieur");

        jLabel6.setText("Date d'expiration : 2 Fevrier 2024");

        javax.swing.GroupLayout cartePanelLayout = new javax.swing.GroupLayout(cartePanel);
        cartePanel.setLayout(cartePanelLayout);
        cartePanelLayout.setHorizontalGroup(
            cartePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cartePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cartePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cartePanelLayout.createSequentialGroup()
                        .addComponent(rdc, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(upc, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cartePanelLayout.createSequentialGroup()
                        .addGroup(cartePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(fullname, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(poste, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, cartePanelLayout.createSequentialGroup()
                                .addComponent(matricule, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(signature, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(cartePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(profile, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(numerodelaCarte, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45))))
        );
        cartePanelLayout.setVerticalGroup(
            cartePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cartePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cartePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(upc, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdc, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(cartePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cartePanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fullname, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(poste)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(cartePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(matricule, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(signature, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(cartePanelLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(profile, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(numerodelaCarte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel1.add(cartePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 210));

        enregistrer.setBackground(new java.awt.Color(77, 56, 91));
        enregistrer.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        enregistrer.setForeground(new java.awt.Color(255, 255, 255));
        enregistrer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Save.png"))); // NOI18N
        enregistrer.setText("Enregistrer");
        enregistrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enregistrerActionPerformed(evt);
            }
        });
        jPanel1.add(enregistrer, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 240, 160, 50));

        imprimer.setBackground(new java.awt.Color(77, 56, 91));
        imprimer.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        imprimer.setForeground(new java.awt.Color(255, 255, 255));
        imprimer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Folder.png"))); // NOI18N
        imprimer.setText("Imprimer");
        imprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprimerActionPerformed(evt);
            }
        });
        jPanel1.add(imprimer, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 160, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void imprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imprimerActionPerformed
        // TODO add your handling code here:
        //UniversalMethod.saveScreenshot( cartePanel ,"patrick paul.png");
        imprimer.setIcon(new ImageIcon(getClass().getResource("/images/ajax_loader_gray_32.gif")));
        printCarteRecord(cartePanel);
        
        //JOptionPane.showConfirmDialog(this, "La carte a bien ete regenerer!", "Carte", JOptionPane.CLOSED_OPTION);
    }//GEN-LAST:event_imprimerActionPerformed

    private void enregistrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enregistrerActionPerformed
        // TODO add your handling code here:
       // try {
            String path = FileSystemView.getFileSystemView().getDefaultDirectory().getPath().concat("\\"+ fullname.getText() +".png");
            File file = UniversalMethod.saveScreenshot( cartePanel , path);        
            if (student) {
                 enregistrer.setIcon(new ImageIcon(getClass().getResource("/images/ajax_loader_gray_32.gif")));
                db.insertCardStudent(Integer.parseInt(students.get(rowId)[4]), UniversalMethod.encodeFileToBase64Binary(file), "");
            }
            else if (member) {                    
                db.insertCarte(members.get(rowId).getId(), UniversalMethod.encodeFileToBase64Binary(file), "");
            }
            this.dispose();
            JOptionPane.showConfirmDialog(this, "La carte a bien ete regenerer! ", "Carte", JOptionPane.CLOSED_OPTION);

            return;
        //} catch (Exception e) {    
    }//GEN-LAST:event_enregistrerActionPerformed

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
            java.util.logging.Logger.getLogger(CarteDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CarteDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CarteDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CarteDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CarteDialog dialog = new CarteDialog(new javax.swing.JFrame(), true);
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
      private void setDefaultImage(ImageIcon imageIcon, JLabel label){
       
        Image image = imageIcon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
            
        ImageIcon iconResized = new ImageIcon(image);
            
        label.setIcon(iconResized);
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel cartePanel;
    private javax.swing.JButton enregistrer;
    private javax.swing.JLabel fullname;
    private javax.swing.JButton imprimer;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel matricule;
    private javax.swing.JLabel numerodelaCarte;
    private javax.swing.JLabel poste;
    private javax.swing.JLabel profile;
    private javax.swing.JLabel rdc;
    private javax.swing.JLabel signature;
    private javax.swing.JLabel upc;
    // End of variables declaration//GEN-END:variables
private void printCarteRecord(JPanel panel){
    try {
        PrinterJob printerJob = PrinterJob.getPrinterJob();
            printerJob.setJobName("Print Record");
            printerJob.setPrintable(new Printable() {
            @Override
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex){
                 //To change body of generated methods, choose Tools | Templates.
                 try {
                    if (pageIndex > 0) {
                        return Printable.NO_SUCH_PAGE;
                    }
                    Graphics2D graphic2D = (Graphics2D) graphics;
                    graphic2D.translate(pageFormat.getImageableX() * 2, pageFormat.getImageableY() * 2);
                    graphic2D.scale(0.5, 0.5);
                    
                    panel.paint(graphic2D);
                    
                    return Printable.PAGE_EXISTS;
                    
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Une erreur est survenu avant d'imprimer");
                }
                 return 0;
            }
        });
            boolean returningResult = printerJob.printDialog();
            if (returningResult) {
                try {
                    // Now print the content
                    printerJob.print();
                     this.dispose();
                     JOptionPane.showMessageDialog(null, "L'Impression a terminer");
                } catch (PrinterException e) {
                    JOptionPane.showMessageDialog(null, "Une erreur est survenu");
                }
            }
    } catch (Exception e) {
    }
}

}
