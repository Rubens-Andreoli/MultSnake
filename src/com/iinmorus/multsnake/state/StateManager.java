package com.iinmorus.multsnake.state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class StateManager implements MouseListener, KeyListener{

    private ArrayList<State> states;
    private int currentState;
    
    public static final int EASY=1, MEDIUM=2, HARD=3;
    
    public static final int IDLE_STATE = 0;
    public static final int SINGLE_STATE = 1;
    public static final int MULTI_STATE = 2;
    public static final int LOAD_STATE = 3;

    
    public StateManager(){
	states = new ArrayList<>();
	currentState = IDLE_STATE;
	
	states.add(new Idle(this));
	states.add(new Singleplayer(this));
	states.add(new Multiplayer(this));
    }
    
    public void setState(int state) {
	currentState = state;
	states.get(currentState).init();
    }
    
    public void loadState(State state){
	currentState = LOAD_STATE;
	states.set(LOAD_STATE, state);
    }
    
    public <T extends State> T getState(int state, Class<T> type){
	return type.cast(states.get(state));
    }
    
    public void update(){
	states.get(currentState).update();
    }
    
    public void draw(Graphics g){
	states.get(currentState).draw(g);
    }
    
    @Override
    public void mousePressed(MouseEvent e){
	states.get(currentState).mousePressed(e);
    }

    @Override
    public void keyPressed(KeyEvent e){
	states.get(currentState).keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e){
	states.get(currentState).keyReleased(e);
    }

    @Override
    public void mouseClicked(MouseEvent e){}

    @Override
    public void mouseReleased(MouseEvent e){}

    @Override
    public void mouseEntered(MouseEvent e){}

    @Override
    public void mouseExited(MouseEvent e){}

    @Override
    public void keyTyped(KeyEvent e){}
    
}
