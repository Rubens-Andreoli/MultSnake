package com.unip.views;

import java.awt.event.KeyEvent;
import javax.swing.ButtonGroup;

public class MultiSelector extends javax.swing.JFrame {

    private Login login;
    private Main m;
    
    public MultiSelector(Main m) {
        initComponents();  
        this.m = m;
        ConfigRadio();
        rdbBot.setSelected(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);   
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnChoose = new javax.swing.JButton();
        rdbBot = new javax.swing.JRadioButton();
        rdbPlayer = new javax.swing.JRadioButton();
        lblPlayAgainst = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnChoose.setFont(new java.awt.Font("Gisha", 0, 14)); // NOI18N
        btnChoose.setMnemonic('G');
        btnChoose.setText("Choose");
        btnChoose.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnChooseFocusGained(evt);
            }
        });
        btnChoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseActionPerformed(evt);
            }
        });

        rdbBot.setFont(new java.awt.Font("Gisha", 0, 14)); // NOI18N
        rdbBot.setText("Bot");
        rdbBot.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                rdbBotFocusGained(evt);
            }
        });

        rdbPlayer.setFont(new java.awt.Font("Gisha", 0, 14)); // NOI18N
        rdbPlayer.setText("Player 2");
        rdbPlayer.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                rdbPlayerFocusGained(evt);
            }
        });

        lblPlayAgainst.setFont(new java.awt.Font("Gisha", 0, 16)); // NOI18N
        lblPlayAgainst.setText("Play Against");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rdbBot)
                    .addComponent(rdbPlayer)
                    .addComponent(lblPlayAgainst, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnChoose, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblPlayAgainst)
                .addGap(18, 18, 18)
                .addComponent(rdbBot)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rdbPlayer)
                .addGap(18, 18, 18)
                .addComponent(btnChoose)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnChooseFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnChooseFocusGained
        btnChoose.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER){
                    btnChoose.doClick();
                }
            }
        });
    }//GEN-LAST:event_btnChooseFocusGained

    private void btnChooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseActionPerformed
        if(rdbPlayer.isSelected()){
            login = new Login(m);
            login.setVisible(true);
            login.bot = false;
            this.dispose();
        }
        else if(rdbBot.isSelected()){
            login = new Login(m);
            login.setVisible(true);
            login.bot = true;
            this.dispose();
        }
    }//GEN-LAST:event_btnChooseActionPerformed

    private void rdbBotFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_rdbBotFocusGained
        rdbBot.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER){
                    if(rdbBot.isSelected()){
                        btnChoose.doClick();
                    }
                    else{
                        rdbPlayer.setSelected(false);
                        rdbBot.setSelected(true);
                    }
                }
            }
        });
    }//GEN-LAST:event_rdbBotFocusGained

    private void rdbPlayerFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_rdbPlayerFocusGained
        rdbPlayer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER){
                    if(rdbPlayer.isSelected()){
                        btnChoose.doClick();
                    }
                    else{
                        rdbPlayer.setSelected(true);
                        rdbBot.setSelected(false);
                    }
                }
            }
        });
    }//GEN-LAST:event_rdbPlayerFocusGained

    private void ConfigRadio(){
        ButtonGroup bg = new ButtonGroup();
        bg.add(rdbBot);
        bg.add(rdbPlayer);
        rdbBot.setSelected(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChoose;
    private javax.swing.JLabel lblPlayAgainst;
    private javax.swing.JRadioButton rdbBot;
    private javax.swing.JRadioButton rdbPlayer;
    // End of variables declaration//GEN-END:variables
}
