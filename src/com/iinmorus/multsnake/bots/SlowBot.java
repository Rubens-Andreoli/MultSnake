package com.iinmorus.multsnake.bots;

import com.iinmorus.multsnake.objects.Snake;
import static com.iinmorus.multsnake.objects.Snake.DOWN;
import static com.iinmorus.multsnake.objects.Snake.LEFT;
import static com.iinmorus.multsnake.objects.Snake.RIGHT;
import static com.iinmorus.multsnake.objects.Snake.UP;
import java.awt.Point;
import java.util.ArrayList;

public class SlowBot extends Bot {

    public SlowBot(Snake snake) {
	super(snake);
    }
      
    @Override
    public void control(ArrayList<Point> avoidPoints){
	switch(snake.getDirection()){
	    case DOWN:	
	    case UP:
		if(point.getLocation().y+blindness == snake.getHead().y){
		    if(point.getLocation().x < snake.getHead().x && !snake.isCollisionAhead(LEFT, 1, avoidPoints))
			snake.changeDirection(Snake.LEFT);
		    else if(point.getLocation().x > snake.getHead().x && !snake.isCollisionAhead(RIGHT, 1, avoidPoints))
			snake.changeDirection(Snake.RIGHT);
		}
		
		if(snake.isCollision(avoidPoints)){
		    if(point.getLocation().x > snake.getHead().x){
			if (snake.isCollisionAhead(RIGHT, 1, avoidPoints)) {
			    snake.changeDirection(Snake.LEFT);
			} else {
			    snake.changeDirection(Snake.RIGHT);
			}
		    }else{
			if (snake.isCollisionAhead(LEFT, 1, avoidPoints)) {
			snake.changeDirection(Snake.RIGHT);
		    } else {
			snake.changeDirection(Snake.LEFT);
		    }
		    }
		}
		break;
	    case LEFT:
	    case RIGHT:
		if(point.getLocation().x+blindness == snake.getHead().x){
		    if(point.getLocation().y < snake.getHead().y && !snake.isCollisionAhead(UP, 1, avoidPoints))
			snake.changeDirection(Snake.UP);
		    else if(point.getLocation().y > snake.getHead().y && !snake.isCollisionAhead(DOWN, 1, avoidPoints))
			snake.changeDirection(Snake.DOWN);
		}
		
		if(snake.isCollision(avoidPoints)){
		    if(point.getLocation().y < snake.getHead().y){
			if (snake.isCollisionAhead(UP, 1, avoidPoints)) {
			    snake.changeDirection(Snake.DOWN);
			} else {
			    snake.changeDirection(Snake.UP);
			}
		    }else{
			if (snake.isCollisionAhead(DOWN, 1, avoidPoints)) {
			    snake.changeDirection(Snake.UP);
			} else {
			    snake.changeDirection(Snake.DOWN);
			}
		    }
		}
		break;
	}
    
    }
  
}
