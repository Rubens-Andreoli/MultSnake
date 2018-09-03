package com.iinmorus.engine;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Engine implements Runnable, ActionListener{
    
    //engine configs
    public static final int FPS = 60;
    public static final int TICK_RATE = 1000/FPS;
    private final Timer timer;
    private long runTick;
    private boolean running;
    
    //engine parts
    private final Renderer renderer;
    private final InputListener input;

    public Engine(){
	timer = new Timer(TICK_RATE, this);
	renderer = new Renderer(this);
	input = new InputListener();
    }

    @Override
    public void run(){
	renderer.addMouseListener(input);
	renderer.addKeyListener(input);
	timer.start();
	running = true;
    }
    
    public void stop(){
	timer.stop();
	running = false;
    }

    @Override
    public void actionPerformed(ActionEvent e){
	renderer.repaint();
	
	StateManager.update();
	
	runTick++;
    }
    
    public void draw(Graphics2D g){
	StateManager.draw(g);
    }

    public Renderer getRenderer(){
	return renderer;
    }

    public boolean isRunning() {
	return running;
    }

}
