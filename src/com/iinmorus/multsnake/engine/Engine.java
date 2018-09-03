package com.iinmorus.multsnake.engine;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

public class Engine implements Runnable, ActionListener{
    
    //timed events
    public final ArrayList<TimedEvent> EVERY_MINUTE = new ArrayList<>();
    public final ArrayList<TimedEvent> EVERY_30SEC = new ArrayList<>();
    
    //engine configs
    public static final int FPS = 60;
    public static final int TICK_RATE = 1000/FPS;
    private final Timer timer;
    private long runTick;
    
    //engine parts
    private final Renderer renderer;
    private final StateManager stateM;

    public Engine(){
	timer = new Timer(TICK_RATE, this);
	renderer = new Renderer(this);
	stateM = new StateManager();
    }
    
    @Override
    public void run(){
	renderer.addMouseListener(stateM);
	renderer.addKeyListener(stateM);
	timer.start();
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
	renderer.repaint();
	
	stateM.update();

        if(runTick%(30000/TICK_RATE) == 0){
	    EVERY_30SEC.stream().forEach((te) -> {
		te.doEvent();
	    });
	}

	if(runTick%(60000/TICK_RATE) == 0){
	    EVERY_MINUTE.stream().forEach((te) -> {
		te.doEvent();
	    });
	}
	
	runTick++;
    }
    
    public void draw(Graphics g){
	stateM.draw(g);
    }

    public Renderer getRenderer(){
	return renderer;
    }

    public StateManager getStateManager(){
	return stateM;
    }

}
