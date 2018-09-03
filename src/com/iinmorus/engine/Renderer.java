package com.iinmorus.engine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class Renderer extends JPanel{
    
    private boolean antialiasing;
    
    private Engine engine;
  
    protected Renderer(boolean antialiasing){
	this.antialiasing = antialiasing;
    }
    
    protected void setEngine(Engine engine){
	this.engine = engine;
    }

    @Override
    protected void paintComponent(Graphics g){
	super.paintComponent(g);

	Graphics2D g2d =(Graphics2D)g;
	if(antialiasing)
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	if(engine != null) engine.draw(g2d);
    }

    public void setAntialiasing(boolean antialiasing) {
	this.antialiasing = antialiasing;
    } 
    
}
