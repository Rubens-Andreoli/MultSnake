package com.unip.views;

import br.unip.gtc.GTC;
import com.unip.conection.Player;
import com.unip.conection.PlayerDAO;
import com.unip.gtc.state.Multiplayer;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {
    
    private Main principal;
    private boolean p2logou;
    public boolean bot;
    
    public Login(Main p) {
        initComponents();
        formConfig();
        this.principal = p;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblCreateAccount = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        lblNickname = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        txtNickname = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        lblRecoverPasswd = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblCreateAccount.setBackground(new java.awt.Color(255, 255, 255));
        lblCreateAccount.setFont(new java.awt.Font("Gisha", 0, 14)); // NOI18N
        lblCreateAccount.setForeground(new java.awt.Color(0, 0, 153));
        lblCreateAccount.setText("Create an account");
        lblCreateAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblCreateAccountMouseReleased(evt);
            }
        });

        btnLogin.setFont(new java.awt.Font("Gisha", 0, 14)); // NOI18N
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        lblNickname.setFont(new java.awt.Font("Gisha", 0, 14)); // NOI18N
        lblNickname.setText("Nickname");

        lblPassword.setFont(new java.awt.Font("Gisha", 0, 14)); // NOI18N
        lblPassword.setText("Password");

        txtNickname.setFont(new java.awt.Font("Gisha", 0, 14)); // NOI18N
        txtNickname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNicknameKeyReleased(evt);
            }
        });

        txtPassword.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPasswordKeyReleased(evt);
            }
        });

        lblRecoverPasswd.setBackground(new java.awt.Color(255, 255, 255));
        lblRecoverPasswd.setFont(new java.awt.Font("Gisha", 0, 14)); // NOI18N
        lblRecoverPasswd.setForeground(new java.awt.Color(0, 0, 153));
        lblRecoverPasswd.setText("Recover your password");
        lblRecoverPasswd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblRecoverPasswdMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPassword)
                            .addComponent(lblNickname))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNickname, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCreateAccount)
                            .addComponent(lblRecoverPasswd))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLogin)))
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNickname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNickname))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPassword))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblRecoverPasswd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCreateAccount))
                    .addComponent(btnLogin))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblCreateAccountMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCreateAccountMouseReleased
        NewAccount newPlayer = new NewAccount(this, txtNickname.getText(), String.valueOf(txtPassword.getPassword()));
        newPlayer.setVisible(true);        
    }//GEN-LAST:event_lblCreateAccountMouseReleased
    
    private void txtPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyReleased
         if (evt.getKeyCode() == KeyEvent.VK_ENTER){
             btnLogin.doClick();
         } 
    }//GEN-LAST:event_txtPasswordKeyReleased

    private void txtNicknameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNicknameKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
             txtPassword.requestFocus();
        } 
    }//GEN-LAST:event_txtNicknameKeyReleased

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        String user = txtNickname.getText().trim();
        String pass = String.valueOf(txtPassword.getPassword()).trim();  
       
        if ((user!="")&&(user!=null))
        {
            if ((pass!="")&&(pass!=null)){                
                login(user, pass);
            }
            else{
                JOptionPane.showMessageDialog(null, "Preencha os campos para "
                    + "continuar", "Error", JOptionPane.ERROR_MESSAGE);               
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Preencha os campos para "
                    + "continuar", "Error", JOptionPane.ERROR_MESSAGE);            
        }
        txtNickname.requestFocus();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void lblRecoverPasswdMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRecoverPasswdMouseReleased
        String nickname = txtNickname.getText();
        if ("".equals(nickname) || nickname == null){
            JOptionPane.showMessageDialog(null, "Please fill your nickname before "
                    + "trying to change your password", "Nickname", JOptionPane.INFORMATION_MESSAGE);            
        }
        else{
            RecoverPassword resetPassword = new RecoverPassword(this, nickname);
            resetPassword.setVisible(true);             
        }              
    }//GEN-LAST:event_lblRecoverPasswdMouseReleased

    private void formConfig(){
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Player 1");
        txtNickname.requestFocus();
    }
    
    public void login(String user, String pass){
        PlayerDAO pDao = new PlayerDAO(); 
        
        if(pDao.hasLogin(user, pass)){    
                        
            if(principal.single){                
                principal.p1 = new Player(user);
                System.out.println("nickname: " + principal.p1.getNickname());
                principal.bot = false;
                principal.startDificulty();
                this.dispose();
            }
            else{                
                if(bot){                    
                    principal.p1 = new Player(user);
                    principal.p2 = new Player("BOT");
                    principal.bot = true;
                    principal.startDificulty();
                    this.dispose();
                }
                else{
                    principal.bot = false;
                    if(!p2logou){  
                        System.out.println("P1 = " + user);                        
                        principal.p1 = new Player(user);    
                        this.setTitle("Player 2");
                        txtNickname.setText("");
                        txtPassword.setText("");
                        p2logou = true;       
                    }
                    else{                        
                        System.out.println("P2 que sera colocado na principal = " + user);                        
                        principal.p2 = new Player(user);

                        System.out.println("Main p1 = " + principal.p1.getNickname());
                        System.out.println("Main p2 = " + principal.p2.getNickname());
                        
                        if(!principal.p1.getNickname().trim().equals(principal.p2.getNickname().trim())){
                          principal.startDificulty();
                            System.out.println("Principal bot = " + principal.bot);
                          this.dispose();  
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Player 1 is already in!", 
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }                            
                    }            
                }
            }
        }     
        else{
            JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos!",
                    "Error", JOptionPane.ERROR_MESSAGE);                      
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel lblCreateAccount;
    private javax.swing.JLabel lblNickname;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblRecoverPasswd;
    private javax.swing.JTextField txtNickname;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}
