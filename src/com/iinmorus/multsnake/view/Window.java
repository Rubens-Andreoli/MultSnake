package com.iinmorus.multsnake.view;

import com.iinmorus.multsnake.controller.Engine;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class Window extends JFrame implements KeyListener{

    private final Engine engine;

    public static final int HEIGHT = 698, WIDTH = 805;
    
    public Window(Engine engine){
	this.engine = engine;
	this.setFrame();
    }

    private void setFrame(){
	this.setTitle("Snake");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setSize(WIDTH, HEIGHT);
	this.setResizable(false);
	this.setLocationRelativeTo(null);
	this.add(engine.getRenderer());
	this.addKeyListener(this);
	
	this.setVisible(true);
	
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
	engine.applyCommand(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    
}
