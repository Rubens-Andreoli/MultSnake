package com.iinmorus.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InputListener implements MouseListener, KeyListener{

    private final StateManager stateManager;
    
    protected InputListener(StateManager manager){
	stateManager = manager;
    }
    
    @Override
    public void mousePressed(MouseEvent e){
	 stateManager.getCurrentState().mousePressed(e);
    }

    @Override
    public void keyPressed(KeyEvent e){
	stateManager.getCurrentState().keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e){
	stateManager.getCurrentState().keyReleased(e);
    }


    public void mouseClicked(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void keyTyped(KeyEvent e){}
    
}
