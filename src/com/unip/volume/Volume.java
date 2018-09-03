package com.unip.volume;

import br.unip.engine.SoundManager;
import java.awt.event.KeyEvent;

public class Volume extends javax.swing.JFrame{

    private int vol;
    private SoundManager sound;
    
    public Volume(SoundManager sound) {
	initComponents();
        vol = (int)(sound.getMasterVolume()*100);
        this.sound = sound;
        slider.setValue(vol-30);
        formConfig();
        setComponents();
    }
    
    private void setComponents(){
	lbl_vol.setText(this.vol+"%");
	if(vol >= 75)
	    lbl_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/unip/volume/audio_h.png")));
	else if(vol >= 50)
	    lbl_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/unip/volume/audio_m.png")));
	else if (vol > 0)
	    lbl_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/unip/volume/audio_l.png")));
	else
	    lbl_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/unip/volume/audio_n.png")));
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        slider = new javax.swing.JSlider();
        btn_ok = new javax.swing.JButton();
        lbl_vol = new javax.swing.JLabel();
        lbl_icon = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Volume");

        slider.setValue(100);
        slider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderStateChanged(evt);
            }
        });
        slider.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sliderKeyPressed(evt);
            }
        });

        btn_ok.setText("OK");
        btn_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_okActionPerformed(evt);
            }
        });

        lbl_vol.setText("0");

        lbl_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/unip/volume/audio_l.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_icon)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lbl_vol, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_ok))
                    .addComponent(slider, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(slider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_vol)
                            .addComponent(btn_ok)))
                    .addComponent(lbl_icon))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_okActionPerformed
        
        setVisible(false);
        this.dispose();
    }//GEN-LAST:event_btn_okActionPerformed

    
    private void sliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderStateChanged
	vol = slider.getValue();
        sound.setMasterVolume((vol+30)/100F);
	setComponents();
    }//GEN-LAST:event_sliderStateChanged

    private void sliderKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sliderKeyPressed
	if(evt.getKeyCode() == KeyEvent.VK_ENTER)
	    btn_ok.doClick();
    }//GEN-LAST:event_sliderKeyPressed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_ok;
    private javax.swing.JLabel lbl_icon;
    private javax.swing.JLabel lbl_vol;
    private javax.swing.JSlider slider;
    // End of variables declaration//GEN-END:variables

    public int getVol() {
	return vol;
    }
    
    private void formConfig(){
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }
    
}
