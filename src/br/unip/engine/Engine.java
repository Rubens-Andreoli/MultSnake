package br.unip.engine;

import java.awt.Graphics2D;
import java.util.List;

public class Engine{
    
    //settings
    public final Settings settings;
    
    //parts
    private final Game game;
    private final Thread thread;
    public final Renderer renderer;
    public final Input input;
    public final SoundManager sounds;
    public final StateManager states;

    //status
    private boolean running;

    public Engine(Game game){
	this.settings = game.getSettings();
	this.game = game;
	
	//initiate parts
	thread = new Thread(new Runnable(){
	    @Override
	    public void run() {
		loop();
	    }
	});
	sounds = new SoundManager(settings.masterVolume);
	states = new StateManager(settings.loadStrategy);
	renderer = new Renderer(this);
	input = new Input(states);
    }
    
    public void start(){
	List<State> stateTemp = game.generateStateList(this);
	if(stateTemp == null) return;
	
	for(State state : stateTemp)
	    states.registerState(state);
	states.startState(game.firstStateID());

	thread.setName(game.getName());
	if(settings.listenMouse) renderer.addMouseListener(input);
	if(settings.listenKey) renderer.addKeyListener(input);
	renderer.requestFocus();
	thread.start();
    }
    
    protected void loop(){
	final double TICK_HERTZ = 1000000000 / settings.tickRate;
	long now;
	long after;
	
	running = true;

	while(running) {
	    now = System.nanoTime();
	    states.update();
	    renderer.repaint();
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
	sounds.stopAll();
	running = false;
    }
    
    protected void render(Graphics2D g){
	states.draw(g);
	g.dispose();
    }

    public boolean isRunning(){return running;}

}
