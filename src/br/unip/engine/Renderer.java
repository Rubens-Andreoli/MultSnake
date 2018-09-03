package br.unip.engine;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class Renderer extends JPanel{
    
    private final Engine engine;
    
    private Graphics2D g2d;
    private boolean antialiasing;
  
    protected Renderer(Engine engine){
	this.engine = engine;
	setFocusable(true);
	setPreferredSize(new Dimension(
		engine.settings.width*engine.settings.scale, 
		engine.settings.height*engine.settings.scale
	));
    }

    @Override
    protected void paintComponent(Graphics g){
	super.paintComponent(g);
	g2d =(Graphics2D)g;
	g2d.setColor(engine.settings.backgroundColor);
	g2d.fillRect(
		0, 
		0, 
		engine.settings.width*engine.settings.scale, 
		engine.settings.height*engine.settings.scale
	);
	if(antialiasing)
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    engine.render(g2d);
    }

}
