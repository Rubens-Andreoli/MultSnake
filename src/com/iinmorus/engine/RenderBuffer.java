package com.iinmorus.engine;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class RenderBuffer extends JPanel{
    
    private final Engine engine;
    
    private Graphics2D g2d;
    private boolean antialiasing;
  
    protected RenderBuffer(Engine engine){
	this.engine = engine;
	setFocusable(true);
	setPreferredSize(new Dimension(engine.game.settings.width, engine.settings.height));
    }

    @Override
    protected void paintComponent(Graphics g){
	super.paintComponent(g);
	g2d =(Graphics2D)g;

	if(antialiasing)
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	if(engine != null) engine.render(g2d);
    }

    public void setAntialiasing(boolean antialiasing) {
	this.antialiasing = antialiasing;
    }

}
