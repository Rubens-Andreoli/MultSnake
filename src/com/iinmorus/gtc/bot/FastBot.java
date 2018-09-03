package com.iinmorus.gtc.bot;

import com.iinmorus.gtc.entity.Snake;
import static com.iinmorus.gtc.entity.Snake.DOWN;
import static com.iinmorus.gtc.entity.Snake.LEFT;
import static com.iinmorus.gtc.entity.Snake.RIGHT;
import static com.iinmorus.gtc.entity.Snake.UP;
import java.awt.Point;
import java.util.ArrayList;

public class FastBot extends Bot{
   
    public FastBot(Snake snake) {
	super(snake);
    }

    @Override
    public void control(ArrayList<Point> avoidPoints){
	switch(snake.getDirection()){
	    case DOWN:
	    case UP:
		if(goal.x < snake.getHead().x && !snake.isCollisionAhead(LEFT, 1, avoidPoints))
		    snake.changeDirection(Snake.LEFT);
		else if(goal.x > snake.getHead().x && !snake.isCollisionAhead(RIGHT, 1, avoidPoints))
		    snake.changeDirection(Snake.RIGHT);
		
		if(snake.isCollision(avoidPoints)){
		    if (snake.isCollisionAhead(LEFT, 1, avoidPoints) || snake.isCollisionAhead(LEFT, 2, avoidPoints)) {
			snake.changeDirection(Snake.RIGHT);
		    } else {
			snake.changeDirection(Snake.LEFT);
		    }
		}
		break;
	    case LEFT:
	    case RIGHT:
		if(goal.y < snake.getHead().y && !snake.isCollisionAhead(UP, 1, avoidPoints))
		    snake.changeDirection(Snake.UP);
		else if(goal.y > snake.getHead().y && !snake.isCollisionAhead(DOWN, 1, avoidPoints)) 
		    snake.changeDirection(Snake.DOWN);
		
		if (snake.isCollision(avoidPoints)) {
		    if (snake.isCollisionAhead(DOWN, 1, avoidPoints) || snake.isCollisionAhead(DOWN, 2, avoidPoints)) {
			snake.changeDirection(Snake.UP);
		    } else {
			snake.changeDirection(Snake.DOWN);
		    }
		}
		break;
	}
    
    }
    
}
