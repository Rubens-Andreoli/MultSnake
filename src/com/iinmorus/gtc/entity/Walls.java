package com.iinmorus.gtc.entity;

import com.iinmorus.engine.Renderer;
import static com.iinmorus.gtc.ui.GameWindow.GAME;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Walls extends Drawable{
        
    private ArrayList<Point> walls;
    private boolean collidable;
    private long start;
    private int amount;
    private int respawnTime = 20000;
    private int ghostTime = 2000;

    
    public Walls(int amount){
	baseColor = Color.WHITE;
	walls = new ArrayList<>();
	this.amount = amount;
    }
       
    private void generate(Point pointsBlacklist){
	walls.clear();
	Random random = new Random();
	for(int w=0; w <= amount; w++){
	    Point wall;
	    do{
		wall = new Point(random.nextInt((GAME.settings.width/SCALE)-2)+1, random.nextInt(GAME.settings.height/SCALE));
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
	g.setColor(collidable ? baseColor : baseColor.darker());
	walls.stream().forEach((wall) -> {
		g.fillRect(wall.x*SCALE, wall.y*SCALE, SCALE, SCALE); 
	});
    }

    public ArrayList<Point> getWalls(){
	return walls;
    }

    public boolean isCollidable() {
	return collidable;
    } 

}
