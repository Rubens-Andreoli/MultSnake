package com.iinmorus.engine;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

public class Engine implements Runnable, ActionListener{
    
    //timed events
    public final ArrayList<TimedEvent> EVERY_05MIN = new ArrayList<>();
    public final ArrayList<TimedEvent> EVERY_01MIN = new ArrayList<>();
    public final ArrayList<TimedEvent> EVERY_30SEC = new ArrayList<>();
    
    //engine configs
    public static final int FPS = 60;
    public static final int TICK_RATE = 1000/FPS;
    private final Timer timer;
    private long runTick;
    
    //engine parts
    private final Renderer renderer;
    private final InputListener input;

    public Engine(StateFactory factory){
	timer = new Timer(TICK_RATE, this);
	renderer = new Renderer(this);
	input = new InputListener();
	SoundManager.init();
	StateManager.init(factory);
    }

    @Override
    public void run(){
	renderer.addMouseListener(input);
	renderer.addKeyListener(input);
	timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e){
	renderer.repaint();
	
	StateManager.update();

        if(runTick%(30000/TICK_RATE) == 0){
	    EVERY_30SEC.stream().forEach((te) -> {
		te.doEvent();
	    });
	}

	if(runTick%(60000/TICK_RATE) == 0){
	    EVERY_01MIN.stream().forEach((te) -> {
		te.doEvent();
	    });
	}
	
	if(runTick%(300000/TICK_RATE) == 0){
	    EVERY_30SEC.stream().forEach((te) -> {
		te.doEvent();
	    });
	}
	
	runTick++;
    }
    
    public void draw(Graphics2D g){
	StateManager.draw(g);
    }

    public Renderer getRenderer(){
	return renderer;
    }

}
