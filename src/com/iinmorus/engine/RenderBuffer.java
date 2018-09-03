package com.iinmorus.engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class RenderBuffer extends JPanel{
    
    private final Game game;
    
    private Graphics2D g2d;
    private boolean antialiasing;
  
    protected RenderBuffer(Game game){
	this.game = game;
	setFocusable(true);
	setPreferredSize(new Dimension(game.settings.width, game.settings.height));
    }

    @Override
    protected void paintComponent(Graphics g){
	super.paintComponent(g);
	g2d =(Graphics2D)g;
                g.setColor(Color.BLACK);
        g.drawRect(0, 0, game.settings.width, game.settings.height);
        
	if(antialiasing)
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	if(game != null) game.draw(g2d);
    }

    public void setAntialiasing(boolean antialiasing) {
	this.antialiasing = antialiasing;
    }

}
