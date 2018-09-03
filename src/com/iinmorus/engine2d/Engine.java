package com.iinmorus.engine2d;

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
    private Timer timer;
    public final RenderBuffer renderBuffer;
    public final Input inputs;
    public final SoundManager sounds;
    public final StateManager states;

    //engine status
    private long tick;
    private boolean running;

    public Engine(Settings settings){
	this.settings = settings;
	
	//parts
	renderBuffer = new RenderBuffer(this);
	renderBuffer.setAntialiasing(settings.antialiasing);
	timer = new Timer(settings.tickRate, this);
	sounds = new SoundManager(settings.loadVolume);
	sounds.setMute(settings.mute);
	states = new StateManager(settings.loadBehaviour);
	inputs = new Input(states);
	if(settings.mouse) addMouseListener(inputs);
	if(settings.keyboard) addKeyListener(inputs);
	
	//container
	Dimension dim = new Dimension(settings.width, settings.height);
	setPreferredSize(dim);
	setMaximumSize(dim);
	setLayout(new BorderLayout());
        add(renderBuffer, BorderLayout.CENTER);
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
	if(running) return;
	timer.start();
	running = true;
    }
    
    public void stop(){
	if(!running) return;
	timer.stop();
	running = false;
    }

    @Override
    public void actionPerformed(ActionEvent e){
	renderBuffer.repaint();
	if(tick%settings.updateTick == 0){
	    states.update();
	}
	tick++;
    }
    
    protected void render(Graphics2D g){
	states.draw(g);
    }

    public boolean isRunning(){return running;}    

    
}
