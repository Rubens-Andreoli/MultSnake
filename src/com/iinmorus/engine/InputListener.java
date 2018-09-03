package com.iinmorus.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InputListener implements MouseListener, KeyListener{

    private StateManager stateM;
    
    protected InputListener(StateManager manager){
	stateM = manager;
    }
    
    @Override
    public void mousePressed(MouseEvent e){
	 stateM.getCurrentState().mousePressed(e);
    }

    @Override
    public void keyPressed(KeyEvent e){
	stateM.getCurrentState().keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e){
	stateM.getCurrentState().keyReleased(e);
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
