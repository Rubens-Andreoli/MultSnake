package com.iinmorus.engine;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Engine extends Container implements Runnable {
    
    //engine configs
    public final Settings settings;
    
    //engine parts
    public final RenderBuffer renderBuffer;
    public final Input inputs;
    public final SoundManager sounds;
    public final StateManager states;

    //engine status
    private boolean running;
    private String firstState;

    public Engine(Settings settings){
	this.settings = settings;
	
	//initiate parts
	renderBuffer = new RenderBuffer(this);
	sounds = new SoundManager(settings.loadVolume);
	states = new StateManager(settings.loadBehaviour);
	inputs = new Input(states);
	
	//config parts
	renderBuffer.setAntialiasing(settings.antialiasing);
	sounds.setMute(settings.mute);
	if(settings.mouse) addMouseListener(inputs);
	if(settings.keyboard) addKeyListener(inputs);

	//config container
	Dimension dim = new Dimension(settings.width, settings.height);
	setPreferredSize(dim);
	setMaximumSize(dim);
	setLayout(new BorderLayout());
        add(renderBuffer, BorderLayout.CENTER);
        setFocusable(true);
        requestFocus();
    }
    
    public void registerStates(ArrayList<State> gameStates, String firstStateID){
	firstState = firstStateID;
	for(State state : gameStates){
	    states.registerState(state);
	}
    }
 
    @Override
    public void run(){
	running = true;
	states.startState(firstState);

	final double TICK_HERTZ = 1000000000 / settings.tickRate;
	long now;
	long after;

	while(running) {
	    now = System.nanoTime();
	    states.update();
	    renderBuffer.repaint();
	    after = System.nanoTime();
			
	    while(now - after < TICK_HERTZ){
		Thread.yield();
		try { Thread.sleep(1); } catch(Exception e) {}
		now = System.nanoTime();
	    }
	}
    }
    
    public void stop(){
	if(!running) return;
	running = false;
    }
    
    protected void render(Graphics2D g){
	states.draw(g);
	g.dispose();
    }

    public boolean isRunning(){return running;}    

    
}
