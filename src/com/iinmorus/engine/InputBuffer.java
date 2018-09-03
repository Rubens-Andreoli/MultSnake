package com.iinmorus.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InputBuffer implements MouseListener, KeyListener{

    private final Game game;
    
    protected InputBuffer(Game game){
	this.game = game;
    }
    
    @Override
    public void mousePressed(MouseEvent e){
	if(game.engine.isRunning())
	    game.states.getCurrentState().mousePressed(e);
    }

    @Override
    public void keyPressed(KeyEvent e){
	if(game.engine.isRunning())
	    game.states.getCurrentState().keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e){
	if(game.engine.isRunning())
	    game.states.getCurrentState().keyReleased(e);
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
