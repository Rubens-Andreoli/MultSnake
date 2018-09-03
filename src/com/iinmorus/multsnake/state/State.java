
package com.iinmorus.multsnake.state;

import com.iinmorus.multsnake.engine.StateManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.Serializable;

public abstract class State implements Serializable{
    
    protected StateManager sManager;
    protected long stateTick;
    
    //ui
    protected Color backgroung = Color.BLACK;
    protected Color overColor = Color.RED;
    protected Color pauseColor = Color.YELLOW;
    protected String overMsg = "GAME OVER";
    protected String pauseMsg = "PAUSED";
    protected Font scoreFont = new Font(Font.MONOSPACED, Font.PLAIN, 20);
    protected Font warningFont = new Font(Font.MONOSPACED, Font.BOLD, 100);
    
    //configs
    protected int updateTick = 3;
    protected int wallRefreshTick = 600;
    protected int baseWallAmount = 30;
    protected int wallFormationTick = 100;
    protected int baseScore = 5;
    protected int difficulty = StateManager.EASY;
    
    //status
    protected boolean isPaused, isOver;
    
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
