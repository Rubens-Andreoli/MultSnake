package com.iinmorus.engine;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.Serializable;

public abstract class State implements Serializable{
    
    protected final Engine engine;
    
    public State(final Engine engine){
	this.engine = engine;
    }
    
    public abstract String getStateID();
    
    protected abstract void loadResources();
    protected abstract void start();
    protected abstract void update();
    protected abstract void draw(Graphics2D g);
    protected abstract void setPaused(boolean isPaused);
    
    protected abstract void keyPressed(KeyEvent e);
    protected abstract void keyReleased(KeyEvent e);
    protected abstract void mousePressed(MouseEvent e);

}
