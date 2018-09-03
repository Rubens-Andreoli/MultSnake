package br.unip.gtc.entity;

import static br.unip.gtc.GTC.SETTINGS;
import java.awt.Color;
import java.awt.Graphics2D;

public abstract class Drawable {
    
    protected final int width;
    protected final int height;
    protected final int scale;
    
    protected Color color;

    public Drawable() {
	width = SETTINGS.width;
	height = SETTINGS.height;
	scale = SETTINGS.scale;
    }

    public abstract void draw(Graphics2D g);

    public void setColor(Color color){
	this.color = color;
    }

    public Color getColor(){
	return color;
    }
    
}