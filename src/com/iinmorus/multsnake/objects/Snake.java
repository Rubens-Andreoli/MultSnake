package com.iinmorus.multsnake.objects;

import com.iinmorus.multsnake.engine.Config;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Snake implements Drawable{
    
    private static final int LAYER = 0;
    
    public static final int UP=0, DOWN=1, LEFT=2, RIGHT=3;
    
    private Point head;
    private Color baseColor = new Color(51, 102, 255);
    private ArrayList<Point> snakeParts;
    private int tailLenght = 5;
    
    private int direction = DOWN; 
    
    public Snake(int startX, int startY){
	this.snakeParts = new ArrayList<>();
	
	head = new Point(startX, startY);
	for(int i=0; i < tailLenght; i++){
	    snakeParts.add(new Point(head.x, head.y));
	}
    }
    
    public void move(){
	snakeParts.add(new Point(head.x, head.y));
	    switch (direction) {
	    	case UP:
		    head = new Point(head.x, head.y-1);
		    break;
	    	case DOWN:
		    head = new Point(head.x, head.y+1);
		    break;
	    	case LEFT:
		    head = new Point(head.x-1, head.y);
		    break;
	    	case RIGHT:
		    head = new Point(head.x+1, head.y);
		    break;
	    }
	    while(snakeParts.size() > tailLenght) snakeParts.remove(0);
    }
    
    
    public boolean isCollisionAhead(int direction, int ahead, ArrayList<Point> obstacles){ //TODO: use map?
	switch (direction) {
	    case UP:
		if(head.y -ahead < 0 || obstacles.contains(new Point(head.x, head.y-ahead))/*isObstableCollision(head.x, head.y-ahead, obstacles)*/)  
		    return true;
		break;
	    case DOWN:
		if(head.y +ahead >= Config.HEIGHT/Config.SCALE || obstacles.contains(new Point(head.x, head.y+ahead))/*isObstableCollision(head.x, head.y+ahead, obstacles)*/)  
		    return true;
		break;
	    case LEFT:
		if(head.x -ahead < 0 || obstacles.contains(new Point(head.x-ahead, head.y))/*isObstableCollision(head.x-ahead, head.y, obstacles)*/)  
		    return true;
		break;
	    case RIGHT:
		if(head.x +ahead >= Config.WIDTH/Config.SCALE || obstacles.contains(new Point(head.x+ahead, head.y))/*isObstableCollision(head.x+ahead, head.y, obstacles)*/)  
		    return true;
		break;
	    }
	return false;
    }
    
    public boolean isCollision(ArrayList<Point> obstacles){
	return this.isCollisionAhead(this.direction, 1, obstacles);
    }
    
//    private static boolean isObstableCollision(int headX, int headY, ArrayList<Point> pointsBlackList){
//	Point head = new Point(headX, headY);
//	if (pointsBlackList.stream().anyMatch((point) -> (head.equals(point)))) {
//	    return true;
//	}
//	return false;
//    }
    
    
    public void changeDirection(int newDirection){
	if(newDirection == LEFT && direction != RIGHT) direction = LEFT;
	else if(newDirection == RIGHT && direction != LEFT) direction = RIGHT;
	else if(newDirection == UP && direction != DOWN) direction = UP;
	else if(newDirection == DOWN && direction != UP) direction = DOWN;
    }
    
    public void grow(){
	tailLenght++;
    }
    
    public void shrink(){
	if(tailLenght > 2) tailLenght--;
    }
    
    public ArrayList<Point> getSnakePoints(){
	ArrayList<Point> snake = (ArrayList<Point>) this.snakeParts.clone();
	snake.add(head);
	return snake;
    }

    @Override
    public void draw(Graphics g) {
	g.setColor(baseColor.darker());
	g.fillRect(head.x*Config.SCALE, head.y*Config.SCALE, Config.SCALE, Config.SCALE);
	    
	snakeParts.stream().forEach((point) -> {
	    g.setColor(new Random().nextBoolean() ? baseColor.brighter().brighter() : baseColor.brighter());
	    g.fillRect(point.x*Config.SCALE, point.y*Config.SCALE, Config.SCALE, Config.SCALE);
	});
    }
    

    @Override
    public int getLayer() {
	return LAYER;
    }

    public Point getHead() {
	return head;
    }

    public int getDirection() {
	return direction;
    }

    public void setBaseColor(Color baseColor) {
	this.baseColor = baseColor;
    }

}
