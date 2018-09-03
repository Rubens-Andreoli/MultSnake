package com.iinmorus.gtc.entity;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class Drawable {  
    
    public static final int SIZE = 10;
    protected Color color;
    
    public abstract void draw(Graphics2D g);

    public void setColor(Color color){
	this.color = color;
    }

    public Color getColor(){
	return color;
    }
    
}