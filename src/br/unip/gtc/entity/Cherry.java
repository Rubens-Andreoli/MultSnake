package br.unip.gtc.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Cherry extends Drawable{
    
    public static final int GROW=1, SHRINK=2;
    
    private Point location;
    private final int effect;

    public Cherry() {
	this(null);
    }

    public Cherry(ArrayList<Point> pointsBlackList){
	color = new Color(222, 49, 99);
	Random random = new Random();
	location = new Point(random.nextInt(width), random.nextInt(height));
	if(pointsBlackList != null){
	    while(pointsBlackList.contains(location)){
		location = new Point(random.nextInt(width), random.nextInt(height));
	    }
	}
	effect = random.nextInt(2)+1;
    }

    @Override
    public void draw(Graphics2D g){
	g.setColor(color);
	g.fillRect(location.x*scale, location.y*scale, scale, scale);
    }

    public int getEffect(){
	return effect;
    }

    public Point getLocation(){
	return location;
    }
    
}
