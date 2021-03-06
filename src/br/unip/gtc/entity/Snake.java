package br.unip.gtc.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Snake extends Drawable{
    
    public static final int UP=0, DOWN=1, LEFT=2, RIGHT=3;
    
    private Point head;
    private ArrayList<Point> snakeParts;
    private int minTailLenght = 3;
    private int tailLenght = 5;
    private int direction = DOWN; 

    public Snake(int startX, int startY) {
	color = new Color(51, 102, 255);
	snakeParts = new ArrayList<>();
	
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
    
    
    public boolean isCollisionAhead(int direction, int ahead, ArrayList<Point> obstacles){
	switch (direction) {
	    case UP:
		if(head.y -ahead < 0 || obstacles.contains(new Point(head.x, head.y-ahead)))  
		    return true;
		break;
	    case DOWN:
		if(head.y +ahead >= height || obstacles.contains(new Point(head.x, head.y+ahead)))  
		    return true;
		break;
	    case LEFT:
		if(head.x -ahead < 0 || obstacles.contains(new Point(head.x-ahead, head.y)))  
		    return true;
		break;
	    case RIGHT:
		if(head.x +ahead >= width || obstacles.contains(new Point(head.x+ahead, head.y)))  
		    return true;
		break;
	    }
	return false;
    }
    
    public boolean isCollision(ArrayList<Point> obstacles){
        return this.isCollisionAhead(this.direction, 1, obstacles);
    }

    public void changeDirection(int newDirection){
	if(newDirection == LEFT && direction != RIGHT) direction = LEFT;
	if(newDirection == RIGHT && direction != LEFT) direction = RIGHT;
	if(newDirection == UP && direction != DOWN) direction = UP;
	if(newDirection == DOWN && direction != UP) direction = DOWN;
    }
    
    public void grow(){
	tailLenght++;
    }
    
    public void shrink(){
	if(tailLenght > minTailLenght) tailLenght--;
    }
    
    public ArrayList<Point> getSnakePoints(){
	ArrayList<Point> snake = (ArrayList<Point>) this.snakeParts.clone();
	snake.add(head);
	return snake;
    }

    @Override
    public void draw(Graphics2D g){
	g.setColor(color.darker());
	g.fillRect(head.x*scale, head.y*scale, scale, scale);

	for(Point point : snakeParts){
	    g.setColor(new Random().nextBoolean() ? color.brighter().brighter() : color.brighter());
	    g.fillRect(point.x*scale, point.y*scale, scale, scale);
	}
    }

    public Point getHead(){
	return head;
    }

    public int getDirection(){
	return direction;
    }

    public int getTailLenght(){
	return tailLenght;
    }  

}
