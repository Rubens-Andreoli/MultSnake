package br.unip.engine;

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
    public abstract void start();
    protected abstract void update();
    protected abstract void draw(Graphics2D g);
    public abstract void setPaused(boolean isPaused);
    public abstract boolean isPaused();
    public abstract void setDifficulty(int difficulty);
    
    protected abstract void keyPressed(KeyEvent e);
    protected abstract void keyReleased(KeyEvent e);
    protected abstract void mousePressed(MouseEvent e);

}
