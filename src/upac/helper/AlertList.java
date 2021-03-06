/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upac.helper;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import upac.MemberDetail;
import upac.StudentDetail;
import upac.StudentDetailAcademique;
import upac.StudentDetailDivers;

/**
 *
 * @author user
 */
public class AlertList extends javax.swing.JDialog {

    /**
     * Creates new form ShowInfo
     */
    private DatabaseManager db;
    public static boolean attenteState = false;
    public static boolean confirmedState = false;
    public static boolean admin = false;
    public AlertList(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //message.setEditable(false);
        //message.setEnabled(false);
        sms.setVisible(false);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/upac-logo-300x298.png")));
//        JScrollPane scrPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
//            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//            scrPane.setBounds(50,10,message.getWidth(), message.getHeight());
//            //scrPane .getViewport().add( lab_test);
//            scrPane.add( message );
//            pane.add( scrPane , BorderLayout.CENTER );
        //message.setLineWrap(true);
        DefaultListModel listModel = new DefaultListModel();
        listModel.clear();
        alertListData.setModel(listModel);
        for(int i = 0; i < UniversalMethod.messages.size(); i++){
            // 2020-05-22 12:45:13
            String[] dateFromDB = UniversalMethod.messages.get(i)[7].split(" ");
            String[] date = dateFromDB[0].split("-");
            String[] currentDate = UniversalMethod.getCurrentDateAndTime().split(" ")[0].split("-");
            String[] showTime = dateFromDB[1].split(":");
            if (currentDate[2].equals(date[2])) {
                listModel.add(i, UniversalMethod.messages.get(i)[1].split(" ")[0] +"      "+ showTime[0] +":"+ showTime[1]);
            }
            else{
                 listModel.add(i, UniversalMethod.messages.get(i)[1].split(" ")[0] +"      "+ date[2] +"-"+ date[1]);
            }
           
        }
        
       // addItemToList();
       //JOptionPane.showMessageDialog(null, UniversalMethod.studentDataAttenteBAC1.size());
        
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
        jScrollPane1 = new javax.swing.JScrollPane();
        alertListData = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        sms = new javax.swing.JPanel();
        sender = new javax.swing.JLabel();
        nomEtudiant = new javax.swing.JLabel();
        faculte = new javax.swing.JLabel();
        departement = new javax.swing.JLabel();
        promotion = new javax.swing.JLabel();
        pane = new javax.swing.JPanel();
        message = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        alertListData.setBackground(new java.awt.Color(240, 240, 240));
        alertListData.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alertListData.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        alertListData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                alertListDataMouseClicked(evt);
            }
        });
        alertListData.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                alertListDataValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(alertListData);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 230, 420));

        jPanel2.setBackground(new java.awt.Color(77, 56, 91));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Nouveaux Alertes");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 40));

        sms.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(77, 56, 91), 2));

        sender.setBackground(new java.awt.Color(77, 56, 91));
        sender.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sender.setText("Billy Paul ");

        nomEtudiant.setBackground(new java.awt.Color(77, 56, 91));
        nomEtudiant.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        nomEtudiant.setText("Nom Etud :");

        faculte.setBackground(new java.awt.Color(77, 56, 91));
        faculte.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        faculte.setText("Faculte :");

        departement.setBackground(new java.awt.Color(77, 56, 91));
        departement.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        departement.setText("Deprt : ");

        promotion.setBackground(new java.awt.Color(77, 56, 91));
        promotion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        promotion.setText("Promotion :");

        message.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        message.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        message.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout paneLayout = new javax.swing.GroupLayout(pane);
        pane.setLayout(paneLayout);
        paneLayout.setHorizontalGroup(
            paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(message, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        paneLayout.setVerticalGroup(
            paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(message, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout smsLayout = new javax.swing.GroupLayout(sms);
        sms.setLayout(smsLayout);
        smsLayout.setHorizontalGroup(
            smsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(smsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(smsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nomEtudiant, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(promotion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                    .addComponent(departement, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(faculte, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        smsLayout.setVerticalGroup(
            smsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(smsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sender)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nomEtudiant)
                .addGap(18, 18, 18)
                .addComponent(faculte)
                .addGap(18, 18, 18)
                .addComponent(departement)
                .addGap(18, 18, 18)
                .addComponent(promotion)
                .addGap(30, 30, 30))
        );

        jPanel1.add(sms, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, 370, 420));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void alertListDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_alertListDataMouseClicked
        // TODO add your handling code here:
        //int itemRow = alertList.get(evt.getPoint());
        //StudentDetailDivers.rowId = itemRow;
    }//GEN-LAST:event_alertListDataMouseClicked

    private void alertListDataValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_alertListDataValueChanged
        // TODO add your handling code here:
//        JScrollPane scroller = new JScrollPane(
//                message, 
//                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
//                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
          if (!evt.getValueIsAdjusting()) {
              
               int index =  alertListData.getSelectedIndex();
               sender.setText(UniversalMethod.messages.get(index)[1]);
               message.setText("<html>"+ UniversalMethod.messages.get(index)[0] +"<br><br>"+ UniversalMethod.messages.get(index)[7] +"</html>");
               nomEtudiant.setText("Nom Etud : "+ UniversalMethod.messages.get(index)[2]);
               faculte.setText("Faculte : "+ UniversalMethod.messages.get(index)[4]);
               departement.setText("Depart : "+ UniversalMethod.messages.get(index)[5]);
               promotion.setText("Promotion : "+ UniversalMethod.messages.get(index)[6]);
               sms.setVisible(true);
            }
    }//GEN-LAST:event_alertListDataValueChanged

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
            java.util.logging.Logger.getLogger(AlertList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AlertList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AlertList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AlertList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AlertList dialog = new AlertList(new javax.swing.JFrame(), true);
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
    private javax.swing.JList<String> alertListData;
    private javax.swing.JLabel departement;
    private javax.swing.JLabel faculte;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel message;
    private javax.swing.JLabel nomEtudiant;
    private javax.swing.JPanel pane;
    private javax.swing.JLabel promotion;
    private javax.swing.JLabel sender;
    private javax.swing.JPanel sms;
    // End of variables declaration//GEN-END:variables
}
