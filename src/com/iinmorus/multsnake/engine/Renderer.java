package com.iinmorus.multsnake.engine;

import com.iinmorus.multsnake.objects.Drawable;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Renderer extends JPanel{

    private Color backgroundColor = Color.BLACK;
    private ArrayList<Drawable> toDraw;
    private int maxLayers;
    
    public Renderer(){
        this.setFocusable(true);
        this.requestFocus();
    }

    public void setDrawables(ArrayList<Drawable> toDraw){
	this.toDraw = toDraw;
	toDraw.stream().filter((object) -> (object.getLayer() > maxLayers)).forEach((object) -> {
	    this.maxLayers = object.getLayer();
	});
    }

    public void setBackgroundColor(Color backgroundColor) {
	this.backgroundColor = backgroundColor;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	g.setColor(backgroundColor);
	g.fillRect(0, 0, Config.WIDTH, Config.HEIGHT);
	
	if(toDraw != null){
	for(int l=0; l<=maxLayers; l++)
	    for(Drawable object : toDraw)
		if(object.getLayer() == l) object.draw(g);
	}
    }
 
}
