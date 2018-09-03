package com.unip.views;

import com.unip.conection.PlayerDAO;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

public class RecoverPassword extends javax.swing.JFrame {

    PlayerDAO pDao = new PlayerDAO();
    private Login login;
    private String nickname;
    private String question;
    
    public RecoverPassword(Login login, String nickname) {        
        question = pDao.findQuestion(nickname);
        this.login = login;
        this.nickname = nickname;
        initComponents();
        formConfig();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnReset = new javax.swing.JButton();
        lblAnswer = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        txtAnswer = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        lblQuestion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnReset.setFont(new java.awt.Font("Gisha", 0, 14)); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        lblAnswer.setFont(new java.awt.Font("Gisha", 0, 14)); // NOI18N
        lblAnswer.setText("Answer");

        lblPassword.setFont(new java.awt.Font("Gisha", 0, 14)); // NOI18N
        lblPassword.setText("New Password");

        txtAnswer.setFont(new java.awt.Font("Gisha", 0, 14)); // NOI18N
        txtAnswer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAnswerKeyReleased(evt);
            }
        });

        txtPassword.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPasswordKeyReleased(evt);
            }
        });

        lblQuestion.setFont(new java.awt.Font("Gisha", 0, 14)); // NOI18N
        lblQuestion.setText("Pergunta de Securança");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnReset)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblQuestion)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(lblAnswer)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(lblPassword)
                                .addGap(18, 18, 18)
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblQuestion)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAnswer)
                    .addComponent(txtAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPassword)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnReset)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        String answer = txtAnswer.getText();
        String pwd = String.valueOf(txtPassword.getPassword());
        System.out.println(nickname);
        System.out.println(answer);
        if(!pDao.checkSecurityAnswer(nickname, answer)){
            JOptionPane.showMessageDialog(null, "This answer is not right. "
                    + "Please put the right answer to change your password", "ERROR", JOptionPane.ERROR_MESSAGE );            
        }
        else{
            if(pDao.resetPassword(nickname, pwd)){
                login.login(nickname, pwd);
                this.dispose();
            }
            else{
                JOptionPane.showMessageDialog(null, "An error ocurried while trying to change your "
                        + "password. Please come back later.", "ERROR", JOptionPane.ERROR_MESSAGE );  
            }
        }
    }//GEN-LAST:event_btnResetActionPerformed

    private void txtAnswerKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnswerKeyReleased
        if ((evt.getKeyCode() == KeyEvent.VK_ENTER)||(evt.getKeyCode() == KeyEvent.VK_TAB)){            
            txtPassword.requestFocus();            
        }
    }//GEN-LAST:event_txtAnswerKeyReleased

    private void txtPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            btnReset.doClick();
        }
    }//GEN-LAST:event_txtPasswordKeyReleased

    private void formConfig(){
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Reset Password");
        lblQuestion.setText(question);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReset;
    private javax.swing.JLabel lblAnswer;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblQuestion;
    private javax.swing.JTextField txtAnswer;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}
