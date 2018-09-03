package com.unip.gtc.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Walls extends Drawable{
        
    private final ArrayList<Point> walls;
    private final int amount;
    private boolean collidable;
    private long start;
    private final int respawnTime = 20000;
    private final int ghostTime = 2000;
    private final int baseWallAmount = 30;

    public Walls(){
	this(4);
    }
    
    public Walls(int difficulty) {
	color = Color.WHITE;
	walls = new ArrayList<>();
	amount = Math.round(baseWallAmount*difficulty*0.60F);
    }
       
    private void generate(Point pointsBlacklist){
	walls.clear();
	Random random = new Random();
	for(int w=0; w <= amount; w++){
	    Point wall;
	    do{
		wall = new Point(random.nextInt((width)-2)+1, random.nextInt(height));
	    }while(pointsBlacklist.equals(wall) || walls.contains(wall));
	    walls.add(wall);
	}
	collidable = false;
	start = System.currentTimeMillis();
    }
        
    public void update(Point pointBlacklist){
	long current = System.currentTimeMillis();
	if(current - start >= ghostTime) collidable = true;
	if(current - start >= respawnTime) this.generate(pointBlacklist);
    }

    @Override
    public void draw(Graphics2D g){
	g.setColor(collidable ? color : color.darker().darker());
	for(Point wall : walls)
	    g.fillRect(wall.x*scale, wall.y*scale, scale, scale); 
    }

    public ArrayList<Point> getWalls(){
	return walls;
    }

    public boolean isCollidable() {
	return collidable;
    } 

}
