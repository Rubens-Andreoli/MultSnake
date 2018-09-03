package br.unip.engine;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class State implements Serializable{
    
    protected final Engine engine;
    private static ArrayList<StateListener> listeners;
    static{
	listeners = new ArrayList<>();
    }

    public State(final Engine engine){
	this.engine = engine;
    }
    
    public abstract String getStateID();
    
    protected abstract void loadResources();
    protected abstract void start();
    protected abstract void update();
    protected abstract void draw(Graphics2D g);
    public abstract void setPaused(boolean isPaused);
    public abstract boolean isPaused();
    public abstract void setDifficulty(int difficulty);
    
    protected abstract void keyPressed(KeyEvent e);
    protected abstract void keyReleased(KeyEvent e);
    protected abstract void mousePressed(MouseEvent e);
    
    public interface StateListener{
	void started();
	void ended();
	void paused(boolean isPause);
    } 
    
    public static void addStateListener(StateListener sl){listeners.add(sl);}
    public static void removeStateListener(StateListener sl){listeners.remove(sl);}
    
    protected void notifyEnd(){
	if(listeners.isEmpty()) return;
	for(StateListener sl : listeners)
	    sl.ended();
    }
    
    protected void notifyStart(){
	if(listeners.isEmpty()) return;
	for(StateListener sl : listeners)
	    sl.started();
    }
    
    protected void notifyPause(){
	if(listeners.isEmpty()) return;
	for(StateListener sl : listeners)
	    sl.paused(isPaused());
    }

}
