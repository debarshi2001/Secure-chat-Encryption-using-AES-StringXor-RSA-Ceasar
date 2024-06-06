/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package chat_app;

import static chat_app.chat_person2.din;
import static chat_app.chat_person2.dout;
import static chat_app.chat_person2.s;
import static chat_app.chat_person2.ss;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class chat_person1 extends javax.swing.JFrame {
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;

    /**
     * Creates new form chat_client
     */
    public chat_person1() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        chat_box = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        Send_btn = new javax.swing.JButton();
        msg_box = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        chat_box.setBackground(new java.awt.Color(236, 236, 199));
        chat_box.setColumns(20);
        chat_box.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        chat_box.setLineWrap(true);
        chat_box.setRows(10);
        chat_box.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jScrollPane1.setViewportView(chat_box);

        jLabel1.setBackground(new java.awt.Color(111, 255, 8));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Person 1");

        Send_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chat_app/send.png"))); // NOI18N
        Send_btn.setBorder(null);
        Send_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Send_btnActionPerformed(evt);
            }
        });

        msg_box.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        msg_box.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        msg_box.setAlignmentY(1.0F);
        msg_box.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 7, 1, 3));
        msg_box.setMargin(new java.awt.Insets(2, 12, 2, 6));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(msg_box, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Send_btn, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(msg_box)
                    .addComponent(Send_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Send_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Send_btnActionPerformed

        try {
//            Database Connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo", "root", "Rony2001sql");
            System.out.println("Success: "+connection);
            Statement statement = connection.createStatement();
            
            String AESKey = GenerateKey.generateRandomKey(16);
            String ZORKey = GenerateKey.generateRandomKey(16);
            int CeaserKey = GenerateKey.generateNumber();
            PublicKey client_public_key = RSAUtils.loadPublicKey("client1_public_key.der");
//            Update the Keys to the Database
            String sql = "UPDATE `jdbcdemo`.`securekey` SET `aeskey` = '"+AESKey+"', `strkey` = '"+ZORKey+"' , `ceaasarkey` = '"+CeaserKey+"' WHERE (`id` = '5');";
            statement.executeUpdate(sql);
            
            System.out.println("Keys: "+AESKey+" "+ZORKey+" "+CeaserKey);
            
            String msg = msg_box.getText();
            
            String SXorEnc = StrXor.encrypt(msg, ZORKey);
            String AESenc = AESUtils.encrypt(SXorEnc, AESKey);
            String CaeEnc = CaesarCipher.encrypt(AESenc, CeaserKey);
            String RSAenc = RSAUtils.encrypt(CaeEnc, client_public_key);

            
            System.out.println("\nStringXor Encrypted: "+SXorEnc + "\nAES Encrypted: "+AESenc+"\nCaesar Encrypted: "+CaeEnc+"\nRSA Encrypted: "+RSAenc);
            chat_box.setText(chat_box.getText()+"\n Me : "+msg);

            dout.writeUTF(RSAenc);
            msg_box.setText("");
            connection.close();
        } catch (Exception e) {
            //Handle Exception

        }

    }//GEN-LAST:event_Send_btnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws NoSuchAlgorithmException, Exception {
        
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
            java.util.logging.Logger.getLogger(chat_person1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(chat_person1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(chat_person1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(chat_person1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        KeyPair keyPair = RSAUtils.generateKeyPair();
        RSAUtils.savePrivateKey("client1_private_key.der", keyPair.getPrivate());
        RSAUtils.savePublicKey("client1_public_key.der",keyPair.getPublic());
        
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new chat_person1().setVisible(true);
            }
        });
        
        try{
            String msgin="";
            s= new Socket("127.0.0.1",1201);
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            
                        
            while(!msgin.equals("exit")){
                msgin = din.readUTF();
                
                String AESKey = "";
                String XORKey = "";
                int CeasarKey = 0;

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo", "root", "Rony2001sql");
                    System.out.println("Success: " + connection);
                    Statement statement = connection.createStatement();
                    ResultSet re = statement.executeQuery("SELECT * FROM securekey where (`id` = '7')");
                    while (re.next()) {
                        AESKey = re.getString(2);
                        XORKey = re.getString(3);
                        CeasarKey = re.getInt(4);
                    }
                    
                    connection.close();
                } catch (ClassNotFoundException | SQLException e) {
                    //Exception
                }
                
                
                String RSAdec = RSAUtils.decrypt(msgin, RSAUtils.loadPrivateKey("client2_private_key.der"));
                String CaeDec = CaesarCipher.decrypt(RSAdec, CeasarKey);
                String AESdec = AESUtils.decrypt(CaeDec, AESKey);
                String SXordec = StrXor.decrypt(AESdec, XORKey);
                
                chat_box.setText(chat_box.getText()+"\n Person2 : "+SXordec);
                
            }
        }
        catch(Exception e){
            
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Send_btn;
    private static javax.swing.JTextArea chat_box;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField msg_box;
    // End of variables declaration//GEN-END:variables
}
