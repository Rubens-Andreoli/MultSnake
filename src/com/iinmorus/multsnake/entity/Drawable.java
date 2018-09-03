package com.iinmorus.multsnake.entity;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Drawable {  
    
    protected Color baseColor;
    
    public abstract void draw(Graphics g);

    public void setBaseColor(Color color){
	this.baseColor = color;
    }

    public Color getBaseColor(){
	return baseColor;
    }
    
}
