package com.iinmorus.engine2d;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class Renderer extends JPanel implements ActionListener{
    
    private final Engine engine;
    
    private boolean antialiasing;
  
    protected Renderer(Engine engine){
	this.engine = engine;
    }

    @Override
    protected void paintComponent(Graphics g){
	super.paintComponent(g);
	
	Graphics2D g2d =(Graphics2D)g;
	if(antialiasing)
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	if(engine != null) engine.render(g2d);
    }

    public void setAntialiasing(boolean antialiasing) {
	this.antialiasing = antialiasing;
    } 

    @Override
    public void actionPerformed(ActionEvent e) {
	repaint();
    }
    
}
