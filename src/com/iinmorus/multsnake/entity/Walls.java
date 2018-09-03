package com.iinmorus.multsnake.entity;

import com.iinmorus.multsnake.engine.Renderer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Walls extends Drawable{
        
    private final ArrayList<Point> walls;
    private boolean collidable;
    private int i;

    private ArrayList<Point> pointsBlackList;
    
    public Walls(int amount, ArrayList<Point> pointsBlackList){
	baseColor = Color.WHITE;
	walls = new ArrayList<>();
	this.pointsBlackList = pointsBlackList;
	this.generate(amount);
    }
    
    public Walls(int amount, Point pointBlackList){
	baseColor = Color.WHITE;
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
		wall = new Point(random.nextInt((Renderer.WIDTH/Renderer.SCALE)-2)+1, random.nextInt(Renderer.HEIGHT/Renderer.SCALE));
	    }while(pointsBlackList.contains(wall) || walls.contains(wall));
	    walls.add(wall);
	}
    
    }

    @Override
    public void draw(Graphics g){
	g.setColor(collidable ? baseColor : baseColor.darker());
	walls.stream().forEach((wall) -> {
		g.fillRect(wall.x*Renderer.SCALE, wall.y*Renderer.SCALE, Renderer.SCALE, Renderer.SCALE); 
	});
    }

    public void setCollidable(boolean collidable){
	this.collidable = collidable;
    }
  
    public ArrayList<Point> getWalls(){
	return walls;
    }

    public boolean isCollidable() {
	return collidable;
    } 

}
