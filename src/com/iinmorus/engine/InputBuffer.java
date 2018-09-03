package com.iinmorus.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InputBuffer implements MouseListener, KeyListener{

    private final Engine engine;
    
    protected InputBuffer(Engine engine){
	this.engine = engine;
    }
    
    @Override
    public void mousePressed(MouseEvent e){
	if(engine.isRunning())
	    engine.states.getCurrentState().mousePressed(e);
    }

    @Override
    public void keyPressed(KeyEvent e){
	if(engine.isRunning())
	    engine.states.getCurrentState().keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e){
	if(engine.isRunning())
	    engine.states.getCurrentState().keyReleased(e);
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
