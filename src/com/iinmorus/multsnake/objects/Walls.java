package com.iinmorus.multsnake.objects;

import com.iinmorus.multsnake.engine.Config;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Walls implements Drawable{
    
    private static final int LAYER = 1;
    
    private final ArrayList<Point> walls;
    private Color baseColor = Color.WHITE;
    private boolean collidable = true;

    private ArrayList<Point> pointsBlackList;
    
    public Walls(int amount, ArrayList<Point> pointsBlackList){
	this.walls = new ArrayList<>();
	pointsBlackList = new ArrayList<>();
	this.generate(amount);
    }
    
    public Walls(int amount, Point pointBlackList){
	this.walls = new ArrayList<>();
	pointsBlackList = new ArrayList<>();
	pointsBlackList.add(pointBlackList);
	this.generate(amount);
    }
    
    private void generate(int amount){
	Random random = new Random();
	for(int w=0; w <= amount; w++){
	    Point wall;
	    do{
		wall = new Point(random.nextInt(Config.WIDTH/Config.SCALE), random.nextInt(Config.HEIGHT/Config.SCALE));
	    }while(this.pointsBlackList.contains(wall) && walls.contains(wall));
	    walls.add(wall);
	}
    
    }
    

    @Override
    public int getLayer() {
	return Walls.LAYER;
    }

    @Override
    public void draw(Graphics g) {
	g.setColor(collidable ? baseColor : baseColor.brighter().brighter());
	walls.stream().forEach((wall) -> {
		g.fillRect(wall.x*Config.SCALE, wall.y*Config.SCALE, Config.SCALE, Config.SCALE); 
	});
    }

    public void setCollidable(boolean collidable) {
	this.collidable = collidable;
    }
  
    public ArrayList<Point> getWalls() {
	return walls;
    }

    public void setBaseColor(Color baseColor) {
	this.baseColor = baseColor;
    } 

}
