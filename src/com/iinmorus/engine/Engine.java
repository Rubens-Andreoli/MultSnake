package com.iinmorus.engine;

public class Engine implements Runnable{

    private final Game game;
    private boolean running;

    public Engine(Game game){
	this.game = game;
    }
        
    @Override
    public void run(){
        if(running || game == null) return;
        running = true;
        loop();
    }
    
    private void loop(){
	final double TICK_HERTZ = 1000000000 / game.settings.tickRate;
	long now;
	long after;

	while(running) {
	    now = System.nanoTime();
	    game.update();
            game.render();
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
    
    public boolean isRunning(){return running;}

}
