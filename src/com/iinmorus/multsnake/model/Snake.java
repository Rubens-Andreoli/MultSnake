package com.iinmorus.multsnake.model;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Snake {
    
    private Point head;
    private Color headColor = Color.WHITE;
    private ArrayList<Point> snakeParts;
    private Color snakeColor = Color.GRAY;
    private int direction = DOWN; 
    private int tailLenght = 5;
        
    public static final int UP=0, DOWN=1, LEFT=2, RIGHT=3;
    
    public Snake(){
	this.snakeParts = new ArrayList<>();
	head = new Point(0,-1);
	for(int i=0; i < tailLenght; i++){
	    snakeParts.add(new Point(head.x, head.y));
	}
    }
    
    public void move(){
	snakeParts.add(new Point(head.x, head.y));
	    switch (direction) {
	    	case UP:
		    if(head.y -1 >= 0 && checkTailAt(head.x, head.y-1))  
			head = new Point(head.x, head.y-1);
		    break;
	    	case DOWN:
		    if(head.y +1 < 67 && checkTailAt(head.x, head.y+1))  
			head = new Point(head.x, head.y+1);
		    break;
	    	case LEFT:
		    if(head.x -1 >= 0 && checkTailAt(head.x-1, head.y))  
			head = new Point(head.x-1, head.y);
		    break;
	    	case RIGHT:
		    if(head.x +1 < 80 && checkTailAt(head.x+1, head.y))  
			head = new Point(head.x+1, head.y);
		    break;
	    }
	    
	    if(snakeParts.size() > tailLenght) snakeParts.remove(0);
    }
    
    public void changeDirection(int key){
	if(key == KeyEvent.VK_LEFT && direction != RIGHT) direction = LEFT;
	if(key == KeyEvent.VK_RIGHT && direction != LEFT) direction = RIGHT;
	if(key == KeyEvent.VK_UP && direction != DOWN) direction = UP;
	if(key == KeyEvent.VK_DOWN && direction != UP) direction = DOWN;
    }
    
    public boolean checkCollisionAhead(int direction, int pxAhead){
	switch (direction) {
	    	case UP:
		    if(head.y -pxAhead < 0 || !checkTailAt(head.x, head.y-pxAhead))  return true;
		    break;
	    	case DOWN:
		    if(head.y +pxAhead >= 67 || !checkTailAt(head.x, head.y+pxAhead))  return true;
		    break;
	    	case LEFT:
		    if(head.x -pxAhead < 0 || !checkTailAt(head.x-pxAhead, head.y))  return true;
		    break;
	    	case RIGHT:
		    if(head.x +pxAhead >= 80 || !checkTailAt(head.x+pxAhead, head.y))  return true;
		    break;
	    }
	return false;
    }
    
    public boolean checkCollision(){
	return this.checkCollisionAhead(direction, 1);
    }
    
    private boolean checkTailAt(int headX, int headY) {
	for(Point point: snakeParts){
	    if(point.equals(new Point(headX, headY))){
		return false;
	    }
	}
	return true;
    }
    
    public void grow(){
	//tailLenght+=3;
    }

    public ArrayList<Point> getSnakeParts() {
	return snakeParts;
    }

    public Point getHead() {
	return head;
    }

    public int getDirection() {
	return direction;
    }

    public Color getHeadColor() {
	return headColor;
    }

    public Color getSnakeColor() {
	return snakeColor;
    }

}
