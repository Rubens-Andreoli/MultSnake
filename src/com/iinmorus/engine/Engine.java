package com.iinmorus.engine;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Engine implements ActionListener{
    
    //engine configs
    private int fps;
    private int ups;
    private int tickRate;

    //engine status
    private long runTick;
    private boolean running;
    
    //engine parts
    private Timer timer;
    private Game game;

    public Engine(Game game, int fps, int ups){
	this.fps = fps;
	this.ups = ups;
	tickRate = 1000/ups;
        this.game = game;
	timer = new Timer(1000/fps, this);
    }
 
    public void start(){
	timer.start();
	running = true;
    }
    
    public void stop(){
	timer.stop();
	running = false;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        game.renderer.repaint();
	game.states.update();

	runTick++;
    }
    
    public void draw(Graphics2D g){
	game.states.draw(g);
    }

    public boolean isRunning() {
	return running;
    }

    public int getFps(){return fps;}
    public int getUps(){return ups;}
    public int getTickRate(){return tickRate;} 

}
