package com.iinmorus.multsnake.entity;

import com.iinmorus.multsnake.engine.Renderer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Cherry extends Drawable{
    
    private Point location;

    public static final int GROW=1, SHRINK=2, SPRINT=3, IMUNE=4;
    private int effect;

    public Cherry() {
	this(null);
    }
    
    public Cherry(ArrayList<Point> pointsBlackList){
	baseColor = new Color(222, 49, 99);
	Random random = new Random();
	location = new Point(random.nextInt(Renderer.WIDTH/Renderer.SCALE), random.nextInt(Renderer.HEIGHT/Renderer.SCALE));
	if(pointsBlackList != null){
	    while(pointsBlackList.contains(location)){
		location = new Point(random.nextInt(Renderer.WIDTH/Renderer.SCALE), random.nextInt(Renderer.HEIGHT/Renderer.SCALE));
	    }
	}
	effect = random.nextInt(5);
    }

    @Override
    public void draw(Graphics g){
	Color color = baseColor.darker();
	for(int i=0; i< effect; i++){
	    color.darker();
	}
	g.setColor(color);
	g.fillRect(location.x*Renderer.SCALE, location.y*Renderer.SCALE, Renderer.SCALE, Renderer.SCALE);
    }

    public int getEffect(){
	return effect;
    }

    public Point getLocation(){
	return location;
    }

}
