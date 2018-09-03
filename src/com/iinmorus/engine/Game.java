package com.iinmorus.engine;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;

public class Game extends Container implements Runnable{

    public final Settings settings;
    public final StateManager states;
    public final SoundManager sounds;
    public final InputListener inputs;
    public final Renderer renderer;
    public final Engine engine;
    
    private ArrayList<State> stateList;
    
    public Game(Settings settings, ArrayList<State> stateList){
        this.settings = settings;
	renderer = new Renderer(settings.antialiasing);
        engine = new Engine(this, settings.fps, settings.ups);
        renderer.setEngine(engine);
        states = new StateManager(settings.loadBehaviour);
	this.stateList = stateList;
	sounds = new SoundManager();
	sounds.setMute(settings.mute);
	inputs = new InputListener(this.states);   
        if(settings.mouse)
	    this.addMouseListener(inputs);
	if(settings.keyboard)
	    this.addKeyListener(inputs);
        this.setPreferredSize(new Dimension(settings.width, settings.height));
        this.setLayout(new BorderLayout());
        this.add(renderer, BorderLayout.CENTER);
        this.setFocusable(true);
        this.requestFocus();
    }

    @Override
    public void run() {
	for(State state : stateList){
	    states.registerState(state);
	}
	states.startState("idle");
	engine.start();
    }
    
}
