package com.unip.views;

import br.unip.engine.Engine;
import br.unip.engine.State;
import br.unip.engine.State.StateListener;
import br.unip.gtc.GTC;
import com.unip.conection.Player;
import com.unip.gtc.state.Multiplayer;
import com.unip.gtc.state.Singleplayer;
import com.unip.volume.Volume;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main extends javax.swing.JFrame {

    private Login login;
    private Engine engine;
    public boolean single;
    public boolean isLogged;
    public boolean bot;
    public Player p1;
    public Player p2;

    public Main() {
        initComponents();
        engine = new Engine(new GTC());
        formSettings();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MainMenu = new javax.swing.JMenuBar();
        mnSession = new javax.swing.JMenu();
        mnPlay = new javax.swing.JMenu();
        mnSingleplayer = new javax.swing.JMenuItem();
        mnMultiplayer = new javax.swing.JMenuItem();
        mnStop = new javax.swing.JMenuItem();
        mnMatch = new javax.swing.JMenu();
        mnRestart = new javax.swing.JMenuItem();
        mnPause = new javax.swing.JMenuItem();
        mnStatistics = new javax.swing.JMenu();
        mnStatGlobal = new javax.swing.JMenuItem();
        mnStatSession = new javax.swing.JMenuItem();
        mnVolume = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(700, 600));

        mnSession.setText("Session");

        mnPlay.setText("Play!");

        mnSingleplayer.setText("Single player");
        mnSingleplayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnSingleplayerActionPerformed(evt);
            }
        });
        mnPlay.add(mnSingleplayer);

        mnMultiplayer.setText("Multiplayer");
        mnMultiplayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnMultiplayerActionPerformed(evt);
            }
        });
        mnPlay.add(mnMultiplayer);

        mnSession.add(mnPlay);

        mnStop.setText("Stop");
        mnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnStopActionPerformed(evt);
            }
        });
        mnSession.add(mnStop);

        MainMenu.add(mnSession);

        mnMatch.setText("Match");

        mnRestart.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_BACK_SPACE, 0));
        mnRestart.setText("Restart");
        mnRestart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnRestartActionPerformed(evt);
            }
        });
        mnMatch.add(mnRestart);

        mnPause.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_SPACE, 0));
        mnPause.setText("Pause");
        mnPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnPauseActionPerformed(evt);
            }
        });
        mnMatch.add(mnPause);

        MainMenu.add(mnMatch);

        mnStatistics.setText("Statistics");

        mnStatGlobal.setText("Global");
        mnStatGlobal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnStatGlobalActionPerformed(evt);
            }
        });
        mnStatistics.add(mnStatGlobal);

        mnStatSession.setText("Session");
        mnStatSession.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnStatSessionActionPerformed(evt);
            }
        });
        mnStatistics.add(mnStatSession);

        MainMenu.add(mnStatistics);

        mnVolume.setText("Volume");
        mnVolume.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnVolumeMouseClicked(evt);
            }
        });
        mnVolume.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnVolumeActionPerformed(evt);
            }
        });
        MainMenu.add(mnVolume);

        setJMenuBar(MainMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 581, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnSingleplayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnSingleplayerActionPerformed
        single = true;
        bot = false;
        p1 = new Player();
        p2 = new Player();
        login = new Login(this);
        login.setVisible(true);
        engine.states.getState(GTC.MULT,Multiplayer.class).vsBot(false);
    }//GEN-LAST:event_mnSingleplayerActionPerformed

    private void mnMultiplayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnMultiplayerActionPerformed
        single = false;
        bot = false;
        p1 = null;
        p2 = null;
        engine.states.getState(GTC.MULT,Multiplayer.class).vsBot(false);
        MultiSelector select = new MultiSelector(this);
        select.setVisible(true);    
    }//GEN-LAST:event_mnMultiplayerActionPerformed

    private void mnPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnPauseActionPerformed
        if(engine.states.getCurrentState().getStateID() != GTC.IDLE){
            engine.states.togglePauseCurrentState();
        }       
    }//GEN-LAST:event_mnPauseActionPerformed

    private void mnRestartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnRestartActionPerformed
        if (isLogged) {
            engine.states.restartCurrentState();
        }    
    }//GEN-LAST:event_mnRestartActionPerformed

    private void mnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnStopActionPerformed
        p1 = null;
        p2 = null;
        isLogged = false;
        engine.states.startState(GTC.IDLE);        
    }//GEN-LAST:event_mnStopActionPerformed

    private void mnStatGlobalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnStatGlobalActionPerformed
        Global global = new Global();
        global.setVisible(true);
    }//GEN-LAST:event_mnStatGlobalActionPerformed

    private void mnStatSessionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnStatSessionActionPerformed

        if(isLogged){        
            if (single){
                SessionSingle session = new SessionSingle(p1.getNickname());
                session.setVisible(true);
            }
            else{
                SessionMult session = new SessionMult(p1.getNickname(), p2.getNickname());
                session.setVisible(true);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Please log in to see the Session Statistics", 
                    "Session Statistics", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_mnStatSessionActionPerformed

    private void mnVolumeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnVolumeActionPerformed
        
    }//GEN-LAST:event_mnVolumeActionPerformed

    private void mnVolumeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnVolumeMouseClicked
        Volume volume = new Volume(engine.sounds);
        volume.setVisible(true);
    }//GEN-LAST:event_mnVolumeMouseClicked

    public void formSettings() {
        this.setTitle("GTC!");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(engine.renderer);
        this.pack();
        engine.start();
        
        State.addStateListener(new StateListener(){
            @Override
            public void started() {
               System.out.println("stated");
            }

            @Override
            public void ended() {
               System.out.println("ended");
               setStatistics();
            }

            @Override
            public void paused(boolean isPause) {
               System.out.println("isPause = "+isPause);
            }
        });
    }
    
    

    public void startDificulty() {
        Dificulty d = new Dificulty(this);
        d.setVisible(true);
    }

    public void setDificulty(String d) {
        if (single) {
            if ("EASY".equals(d)) {
                engine.states.getState(GTC.SINGLE).setDifficulty(GTC.EASY);
            } else if ("MEDIUM".equals(d)) {
                engine.states.getState(GTC.SINGLE).setDifficulty(GTC.MEDIUM);
            } else if ("HARD".equals(d)) {
                engine.states.getState(GTC.SINGLE).setDifficulty(GTC.HARD);
            }
            startSingle();
            isLogged = true;
        } else {
            
            if ("EASY".equals(d)) {
                engine.states.getState(GTC.MULT).setDifficulty(GTC.EASY);
            } else if ("MEDIUM".equals(d)) {
                engine.states.getState(GTC.MULT).setDifficulty(GTC.MEDIUM);
            } else if ("HARD".equals(d)) {
                engine.states.getState(GTC.MULT).setDifficulty(GTC.HARD);
            }            
            
            if(bot){
                startBot();
            }else{
                startMult();
            }  
            isLogged = true;
        }
    }

    public void startSingle() {
        engine.states.startState(GTC.SINGLE);
    }

    public void startMult() {
        engine.states.startState(GTC.MULT);
    }
    
    public void startBot(){
        engine.states.getState(GTC.MULT,Multiplayer.class).vsBot(true);
        engine.states.startState(GTC.MULT);        
    }
    
    public void setStatistics(){        
        Statistics stat = new Statistics(engine);
        
        if (single){
            if(engine.states.getState(GTC.SINGLE,Singleplayer.class).isOver()){
                stat.insertResults(1, p1, null); 
            }
        }
        else{ 
            if(engine.states.getState(GTC.MULT,Multiplayer.class).isOver()){
                stat.insertResults(0, p1, p2);
            }
        }          
    }
    
    
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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar MainMenu;
    private javax.swing.JMenu mnMatch;
    private javax.swing.JMenuItem mnMultiplayer;
    private javax.swing.JMenuItem mnPause;
    private javax.swing.JMenu mnPlay;
    private javax.swing.JMenuItem mnRestart;
    private javax.swing.JMenu mnSession;
    private javax.swing.JMenuItem mnSingleplayer;
    private javax.swing.JMenuItem mnStatGlobal;
    private javax.swing.JMenuItem mnStatSession;
    private javax.swing.JMenu mnStatistics;
    private javax.swing.JMenuItem mnStop;
    private javax.swing.JMenu mnVolume;
    // End of variables declaration//GEN-END:variables
}
