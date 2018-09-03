
package com.iinmorus.multsnake.state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.Serializable;

public abstract class State implements Serializable{
    
    protected StateManager sManager;
    protected long stateTick;
    
    public State(StateManager stateManager){
        sManager = stateManager;
    }
    
    public abstract void init();
    public abstract void update();
    public abstract void draw(Graphics g);
    public abstract void keyPressed(KeyEvent e);
    public abstract void keyReleased(KeyEvent e);
    public abstract void mousePressed(MouseEvent e);
    public abstract void setPaused(boolean isPaused);
    
}
