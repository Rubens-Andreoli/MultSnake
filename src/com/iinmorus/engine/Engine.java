package com.iinmorus.engine;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class Engine{

    //engine settings
    private final Game game;
    
    //engine parts
    private final Thread gameThread;
    public final RenderBuffer renderBuffer;
    public final InputBuffer inputBuffer;
    public final SoundManager sounds;
    public final StateManager states;

    //engine status
    private boolean running;
    private String firstState;

    public Engine(Game game){
	this.game = game;
	
	//initiate parts
	gameThread = new Thread(new Runnable(){
	    @Override
	    public void run() {
		loop();
	    }
	});
	renderBuffer = new RenderBuffer(this);
	sounds = new SoundManager(this);
	states = new StateManager(this);
	inputBuffer = new InputBuffer(this);

	//configure parts
	gameThread.setName("ARCADIA ENGINE v1.0");
	if(game.settings.listenMouse) renderBuffer.addMouseListener(inputBuffer);
	if(game.settings.listenKey) renderBuffer.addKeyListener(inputBuffer);
    }
    
    public void start(ArrayList<State> gameStates, String firstStateID){
	if(gameStates == null || firstStateID == null) return;
	firstState = firstStateID;
	for(State state : gameStates)
	    states.registerState(state);
	states.startState(firstState);
        renderBuffer.requestFocus();
	gameThread.start();
    }
    
    protected void loop(){
	final double TICK_HERTZ = 1000000000 / game.settings.tickRate;
	long now;
	long after;
	
	running = true;

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
