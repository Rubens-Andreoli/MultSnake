package com.iinmorus.multsnake.entity;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class Drawable {  
    
    public static final int SCALE = 10;
    protected Color baseColor;
    
    public abstract void draw(Graphics2D g);

    public void setBaseColor(Color color){
	this.baseColor = color;
    }

    public Color getBaseColor(){
	return baseColor;
    }
    
}
