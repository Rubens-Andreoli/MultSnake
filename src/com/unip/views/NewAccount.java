package com.unip.views;

import com.unip.conection.PlayerDAO;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import com.unip.conection.Player;

public class NewAccount extends javax.swing.JFrame {

    Login login;
    
    public NewAccount(Login login, String nickname, String password) {   
        this.login = login;
        initComponents();        
        String nick = (nickname == null)? "" : nickname;
        String pass = (password == null)? "" : nickname;
        txtNickname.setText(nick);
        txtPassword.setText(pass);
        formConfig();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnRegister = new javax.swing.JButton();
        txtAnswer = new javax.swing.JTextField();
        lblAnswer = new javax.swing.JLabel();
        lblQuestion = new javax.swing.JLabel();
        txtQuestion = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        lblPassword = new javax.swing.JLabel();
        lblNickname = new javax.swing.JLabel();
        txtNickname = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnRegister.setFont(new java.awt.Font("Gisha", 0, 14)); // NOI18N
        btnRegister.setText("Register");
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        txtAnswer.setFont(new java.awt.Font("Gisha", 0, 14)); // NOI18N
        txtAnswer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAnswerKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAnswerKeyTyped(evt);
            }
        });

        lblAnswer.setFont(new java.awt.Font("Gisha", 0, 14)); // NOI18N
        lblAnswer.setText("Security Answer");

        lblQuestion.setFont(new java.awt.Font("Gisha", 0, 14)); // NOI18N
        lblQuestion.setText("Security Question");

        txtQuestion.setFont(new java.awt.Font("Gisha", 0, 14)); // NOI18N
        txtQuestion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQuestionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtQuestionKeyTyped(evt);
            }
        });

        txtPassword.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPasswordKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPasswordKeyTyped(evt);
            }
        });

        lblPassword.setFont(new java.awt.Font("Gisha", 0, 14)); // NOI18N
        lblPassword.setText("Password");

        lblNickname.setFont(new java.awt.Font("Gisha", 0, 14)); // NOI18N
        lblNickname.setText("Nickname");

        txtNickname.setFont(new java.awt.Font("Gisha", 0, 14)); // NOI18N
        txtNickname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNicknameKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNicknameKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPassword)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnRegister)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblQuestion)
                                .addComponent(lblNickname)
                                .addComponent(lblAnswer))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtNickname)
                                .addComponent(txtPassword)
                                .addComponent(txtQuestion)
                                .addComponent(txtAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNickname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNickname))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPassword))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblQuestion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAnswer))
                .addGap(18, 18, 18)
                .addComponent(btnRegister)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        String nickname = txtNickname.getText().trim();
        String password = String.valueOf(txtPassword.getPassword()).trim();
        String question = txtQuestion.getText().trim();
        String answer = txtAnswer.getText().trim();
        
        if((!"".equals(nickname))&&(!"".equals(password))&&(!"".equals(question))&&(!"".equals(answer))){
            if(nickname.length()<3 || password.length()<3 || question.length()< 3 || answer.length() <3){
                JOptionPane.showMessageDialog(null, "The fields must be more than "
                        + "3 characters each", "WRONG SIZE", JOptionPane.INFORMATION_MESSAGE);   
            }
            else{
                Player p = new Player(nickname, password, question, answer);
                PlayerDAO pDao = new PlayerDAO();

                if(pDao.createAccount(p)){
                    login.login(nickname, password);
                    this.dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "An error occuried while trying to "
                            + "register. Please, try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }  
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "All the fields are required to "
                    + "register. Please, try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void txtNicknameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNicknameKeyTyped
        if (txtNickname.getText().length() > 20) {
		evt.setKeyChar((char) KeyEvent.VK_CLEAR);
	}
    }//GEN-LAST:event_txtNicknameKeyTyped

    private void txtPasswordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyTyped
        String pass = String.valueOf(txtPassword.getPassword());        
        if (pass.length() > 16) {
		evt.setKeyChar((char) KeyEvent.VK_CLEAR);
	}
    }//GEN-LAST:event_txtPasswordKeyTyped

    private void txtQuestionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuestionKeyTyped
        if (txtQuestion.getText().length() > 30) {
		evt.setKeyChar((char) KeyEvent.VK_CLEAR);
	}
    }//GEN-LAST:event_txtQuestionKeyTyped

    private void txtAnswerKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnswerKeyTyped
        if (txtAnswer.getText().length() > 30) {
		evt.setKeyChar((char) KeyEvent.VK_CLEAR);
	}
    }//GEN-LAST:event_txtAnswerKeyTyped

    private void txtNicknameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNicknameKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
             txtPassword.requestFocus();
        } 
    }//GEN-LAST:event_txtNicknameKeyReleased

    private void txtPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
             txtQuestion.requestFocus();
        } 
    }//GEN-LAST:event_txtPasswordKeyReleased

    private void txtQuestionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuestionKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
             txtAnswer.requestFocus();
        } 
    }//GEN-LAST:event_txtQuestionKeyReleased

    private void txtAnswerKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnswerKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            btnRegister.doClick();
        } 
    }//GEN-LAST:event_txtAnswerKeyReleased
   
     private void formConfig(){
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Get That Cherry");
        txtNickname.requestFocus();
        //if (txtNicknam)
    }  
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegister;
    private javax.swing.JLabel lblAnswer;
    private javax.swing.JLabel lblNickname;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblQuestion;
    private javax.swing.JTextField txtAnswer;
    private javax.swing.JTextField txtNickname;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtQuestion;
    // End of variables declaration//GEN-END:variables
}
