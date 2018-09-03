package com.iinmorus.engine;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class Renderer extends JPanel{
    
    public static final int HEIGHT = 600, WIDTH = 700;
    public static final boolean ANTIALIASING = true;
        
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
	Graphics2D g2d =(Graphics2D)g;
	if(ANTIALIASING)
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	engine.draw(g2d);
    }
    
}
