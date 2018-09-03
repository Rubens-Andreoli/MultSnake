package br.unip.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Input implements MouseListener, KeyListener{

    private final StateManager states;
    
    protected Input(StateManager states){
	this.states = states;
    }
    
    @Override
    public void mousePressed(MouseEvent e){
	states.getCurrentState().mousePressed(e);
    }

    @Override
    public void keyPressed(KeyEvent e){
	states.getCurrentState().keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e){
	states.getCurrentState().keyReleased(e);
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
