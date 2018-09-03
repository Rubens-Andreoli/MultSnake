package com.iinmorus.multsnake.engine;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Renderer extends JPanel{
    
    public static final int HEIGHT = 600, WIDTH = 700;
    public static final int SCALE = 10;
        
    private final Engine engine;
  
    public Renderer(Engine engine){
	this.engine = engine;
        this.configRenderer();
    }
    
    private void configRenderer(){
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	this.setFocusable(true);
        this.requestFocus();
    }
    
    @Override
    protected void paintComponent(Graphics g){
	super.paintComponent(g);
	engine.draw(g);
    }
    
}
