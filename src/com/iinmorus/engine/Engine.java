package com.iinmorus.engine;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Timer;

public class Engine implements Runnable, ActionListener{
    
    //timed events
    private static final HashMap<Integer, ArrayList<TimedEvent>> EVENTS = new HashMap<>();
    
    //engine configs
    public static final int FPS = 60;
    public static final int TICK_RATE = 1000/FPS;
    private final Timer timer;
    private long runTick;
    private boolean running;
    
    //engine parts
    private final Renderer renderer;
    private final InputListener input;

    public Engine(){
	timer = new Timer(TICK_RATE, this);
	renderer = new Renderer(this);
	input = new InputListener();
	SoundManager.init();
	StateManager.init();
    }

    @Override
    public void run(){
	renderer.addMouseListener(input);
	renderer.addKeyListener(input);
	timer.start();
	running = true;
    }
    
    public void stop(){
	timer.stop();
	running = false;
    }
    
    public static void addEvent(TimedEvent event){
	int interval = event.getInterval();
	if(EVENTS.containsKey(interval))
	    EVENTS.get(interval).add(event);
	else{
	    ArrayList<TimedEvent> eventList = new ArrayList<>();
	    eventList.add(event);
	    EVENTS.put(interval, eventList);
	}
    }
    
    public static void removeEvent(TimedEvent event){
	int interval = event.getInterval();
	EVENTS.get(interval).remove(event);
	if(EVENTS.get(interval).isEmpty())
	    EVENTS.remove(interval);
    }

    @Override
    public void actionPerformed(ActionEvent e){
	renderer.repaint();
	
	StateManager.update();

	for(Map.Entry entry : EVENTS.entrySet()){
	    if(runTick%((int)entry.getKey()/TICK_RATE) == 0){
		for(TimedEvent te : (ArrayList<TimedEvent>)entry.getValue())
		    te.doEvent();
	    }
	}
	
	runTick++;
    }
    
    public void draw(Graphics2D g){
	StateManager.draw(g);
    }

    public Renderer getRenderer(){
	return renderer;
    }

    public boolean isRunning() {
	return running;
    }

}
