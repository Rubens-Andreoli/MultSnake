package com.unip.views;

import java.awt.event.KeyEvent;
import javax.swing.ButtonGroup;

public class Dificulty extends javax.swing.JFrame {

    private Main p;
    
    public Dificulty(Main p) {
        initComponents();
        rdbEasy.requestFocus();
        this.setResizable(false);
        this.setLocationRelativeTo(null);        
        configRadioButton();
        this.p = p;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        lblNickname = new javax.swing.JLabel();
        btnGO = new javax.swing.JButton();
        rdbMedium = new javax.swing.JRadioButton();
        rdbHard = new javax.swing.JRadioButton();
        rdbEasy = new javax.swing.JRadioButton();
        lblNickname1 = new javax.swing.JLabel();

        lblNickname.setFont(new java.awt.Font("Gisha", 0, 14)); // NOI18N
        lblNickname.setText("Nickname");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnGO.setFont(new java.awt.Font("Gisha", 0, 14)); // NOI18N
        btnGO.setMnemonic('G');
        btnGO.setText("Go!");
        btnGO.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnGOFocusGained(evt);
            }
        });
        btnGO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGOActionPerformed(evt);
            }
        });

        rdbMedium.setFont(new java.awt.Font("Gisha", 0, 13)); // NOI18N
        rdbMedium.setText("Medium");
        rdbMedium.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                rdbMediumFocusGained(evt);
            }
        });

        rdbHard.setFont(new java.awt.Font("Gisha", 0, 13)); // NOI18N
        rdbHard.setText("Hard");
        rdbHard.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                rdbHardFocusGained(evt);
            }
        });

        rdbEasy.setFont(new java.awt.Font("Gisha", 0, 13)); // NOI18N
        rdbEasy.setText("Easy");
        rdbEasy.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                rdbEasyFocusGained(evt);
            }
        });

        lblNickname1.setFont(new java.awt.Font("Gisha", 0, 14)); // NOI18N
        lblNickname1.setText("Dificulty");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnGO)
                            .addComponent(lblNickname1)))
                    .addComponent(rdbEasy)
                    .addComponent(rdbHard)
                    .addComponent(rdbMedium))
                .addGap(41, 41, 41))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblNickname1)
                .addGap(18, 18, 18)
                .addComponent(rdbEasy)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbMedium)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbHard)
                .addGap(18, 18, 18)
                .addComponent(btnGO)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGOActionPerformed
        if(rdbEasy.isSelected()){
            p.setDificulty("EASY");
            this.dispose();
        }
        else if(rdbMedium.isSelected()){
            p.setDificulty("MEDIUM");
            this.dispose();
        }
        else{
            p.setDificulty("HARD");
            this.dispose();
        }
    }//GEN-LAST:event_btnGOActionPerformed

    private void rdbEasyFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_rdbEasyFocusGained
        rdbEasy.addKeyListener(new java.awt.event.KeyAdapter() {
              public void keyPressed(java.awt.event.KeyEvent evt) {               
                if (evt.getKeyCode() == KeyEvent.VK_ENTER){   
                    if(rdbEasy.isSelected()){
                    btnGO.doClick();
                    } 
                    else{
                        rdbEasy.setSelected(true);
                        rdbMedium.setSelected(false);
                        rdbHard.setSelected(false); 
                    }                 
                }
              }
          });
    }//GEN-LAST:event_rdbEasyFocusGained

    private void rdbMediumFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_rdbMediumFocusGained
        rdbMedium.addKeyListener(new java.awt.event.KeyAdapter() {
              public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER){
                    if(rdbMedium.isSelected()){
                        btnGO.doClick();
                    } 
                    else{
                        rdbEasy.setSelected(false);
                        rdbMedium.setSelected(true);
                        rdbHard.setSelected(false);
                    }
                }
              }
        });
    }//GEN-LAST:event_rdbMediumFocusGained

    private void rdbHardFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_rdbHardFocusGained
        rdbHard.addKeyListener(new java.awt.event.KeyAdapter() {
              public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER){
                    if(rdbEasy.isSelected()){
                    btnGO.doClick();
                    } 
                    else{
                        rdbEasy.setSelected(false);
                        rdbMedium.setSelected(false);
                        rdbHard.setSelected(true);
                    }
                }
              }
        });
    }//GEN-LAST:event_rdbHardFocusGained

    private void btnGOFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnGOFocusGained
        btnGO.addKeyListener(new java.awt.event.KeyAdapter() {
              public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER){
                  btnGO.doClick();
                }
              }
        });
    }//GEN-LAST:event_btnGOFocusGained

    private void configRadioButton(){
        ButtonGroup bg = new ButtonGroup();
        bg.add(rdbEasy);
        bg.add(rdbMedium);
        bg.add(rdbHard);
        rdbEasy.setSelected(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGO;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel lblNickname;
    private javax.swing.JLabel lblNickname1;
    private javax.swing.JRadioButton rdbEasy;
    private javax.swing.JRadioButton rdbHard;
    private javax.swing.JRadioButton rdbMedium;
    // End of variables declaration//GEN-END:variables
}
