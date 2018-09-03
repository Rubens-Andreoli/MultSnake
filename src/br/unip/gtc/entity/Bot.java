package br.unip.gtc.entity;

import br.unip.gtc.GTC;
import static br.unip.gtc.entity.Snake.DOWN;
import static br.unip.gtc.entity.Snake.LEFT;
import static br.unip.gtc.entity.Snake.RIGHT;
import static br.unip.gtc.entity.Snake.UP;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Bot {
    
    private Point goal;
    private final Snake snake;

    private float avoidChance;
    private float persueChance;
    
    public Bot(Snake snake){
	this(snake, 100);
    }
    
    public Bot(Snake snake, int difficulty){
	this.snake = snake;
	switch(difficulty){
	    case GTC.EASY:
		persueChance = 0.3F;
		avoidChance = 0.92F;
		break;
	    case GTC.MEDIUM:
		persueChance = 0.45F;
		avoidChance = 0.96F;
		break;
	    case GTC.HARD:
		persueChance = 0.6F;
		avoidChance = 0.98F;
		break;
	    default:
		persueChance = 1F;
		avoidChance = 1F;
		break;	
	}
	
    }

    public void changeGoal(Point goal){
	this.goal = goal;
    }

    public void control(ArrayList<Point> avoidPoints){
	switch(snake.getDirection()){
	    case DOWN:
	    case UP:
		if(goal.x < snake.getHead().x && !snake.isCollisionAhead(LEFT, 1, avoidPoints)){
		    if(roll(persueChance)) snake.changeDirection(Snake.LEFT);
		}else if(goal.x > snake.getHead().x && !snake.isCollisionAhead(RIGHT, 1, avoidPoints)){
		    if(roll(persueChance)) snake.changeDirection(Snake.RIGHT);
		}
		
		if(snake.isCollision(avoidPoints)){
		    if(snake.isCollisionAhead(LEFT, 1, avoidPoints) || snake.isCollisionAhead(LEFT, 2, avoidPoints)){
			if(roll(avoidChance)) snake.changeDirection(Snake.RIGHT);
		    }else{
			if(roll(avoidChance)) snake.changeDirection(Snake.LEFT);
		    }
		}
		break;
	    case LEFT:
	    case RIGHT:
		if(goal.y < snake.getHead().y && !snake.isCollisionAhead(UP, 1, avoidPoints)){
		    if(roll(persueChance)) snake.changeDirection(Snake.UP);
		}else if(goal.y > snake.getHead().y && !snake.isCollisionAhead(DOWN, 1, avoidPoints)){
		    if(roll(persueChance)) snake.changeDirection(Snake.DOWN);
		}
		
		if(snake.isCollision(avoidPoints)){
		    if(snake.isCollisionAhead(DOWN, 1, avoidPoints) || snake.isCollisionAhead(DOWN, 2, avoidPoints)){
			if(roll(avoidChance)) snake.changeDirection(Snake.UP);
		    }else{
			if(roll(avoidChance)) snake.changeDirection(Snake.DOWN);
		    }
		}
		break;
	}
    }
    
    private Random random = new Random();
    private boolean roll(float chance){
	return random.nextInt(101)+1 <= chance*100;
    }

}
