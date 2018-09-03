package com.iinmorus.engine;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

public class Engine extends Container implements Runnable, ActionListener{
    
    //engine configs
    public final Settings settings;
    
    //engine parts
    private final Timer timer;
    public final Renderer renderer;
    public final SoundManager sounds;
    public final StateManager states;
    public final InputListener inputs;

    //engine status
    private long renderTick;
    private long updateTick;
    private boolean running;

    public Engine(Settings settings){
	this.settings = settings;
	
	//parts
	timer = new Timer(settings.renderRate, this);
	renderer = new Renderer(this);
	renderer.setAntialiasing(settings.antialiasing);
	sounds = new SoundManager(settings.loadVolume);
	sounds.setMute(settings.mute);
	states = new StateManager(settings.loadBehaviour);
	inputs = new InputListener(states);
	if(settings.mouse) addMouseListener(inputs);
	if(settings.keyboard) addKeyListener(inputs);
	
	//container
	setPreferredSize(new Dimension(settings.width, settings.height));
	setLayout(new BorderLayout());
        add(renderer, BorderLayout.CENTER);
        setFocusable(true);
        requestFocus();
    }
    
    public void registerStates(ArrayList<State> gameStates, String firstStateID){
	for(State state : gameStates){
	    states.registerState(state);
	}
	states.startState(firstStateID);
    }
 
    @Override
    public void run(){
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

	if(renderTick%(settings.fps/settings.ups) == 0){
	    states.update();
	    updateTick++;
	}
	
	renderTick++;
    }
    
    protected void draw(Graphics2D g){
	states.draw(g);
    }

    public boolean isRunning(){return running;}    
    public long getRenderTick(){return renderTick;}
    public long getUpdateTick(){return updateTick;}   
    
}
