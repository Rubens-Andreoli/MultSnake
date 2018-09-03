package com.iinmorus.engine2d;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Input implements MouseListener, KeyListener{

    private final StateManager stateManager;
    
    protected Input(StateManager manager){
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
