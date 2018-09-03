package com.iinmorus.gtc;

import com.iinmorus.engine.Engine;
import com.iinmorus.engine.StateManager;
import com.iinmorus.gtc.state.Idle;
import com.iinmorus.gtc.state.Multiplayer;
import com.iinmorus.gtc.state.Singleplayer;
import java.awt.Container;

public class Game {
    
    public static final String IDLE="idle", SINGLE="single", MULT="mult"; 
    
    private final Engine engine;
    private final Thread gameThread;
    
    public Game(){
	engine = new Engine();
	gameThread = new Thread(engine);
	
	StateManager.loadState(new Idle());
	StateManager.loadState(new Singleplayer());
	StateManager.loadState(new Multiplayer());
	StateManager.startState("idle");
    }
    
    public void start(){
	gameThread.start();
    }
    
    public Container getContainer(){
	return engine.getRenderer();
    }
    
}
