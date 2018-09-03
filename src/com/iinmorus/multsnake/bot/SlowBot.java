package com.iinmorus.multsnake.bot;


import com.iinmorus.multsnake.entity.Snake;
import static com.iinmorus.multsnake.entity.Snake.DOWN;
import static com.iinmorus.multsnake.entity.Snake.LEFT;
import static com.iinmorus.multsnake.entity.Snake.RIGHT;
import static com.iinmorus.multsnake.entity.Snake.UP;
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
		if(goal.getLocation().y+blindness == snake.getHead().y){
		    if(goal.getLocation().x < snake.getHead().x && !snake.isCollisionAhead(LEFT, 1, avoidPoints))
			snake.changeDirection(Snake.LEFT);
		    else if(goal.getLocation().x+blindness > snake.getHead().x && !snake.isCollisionAhead(RIGHT, 1, avoidPoints))
			snake.changeDirection(Snake.RIGHT);
		}
		
		if(snake.isCollision(avoidPoints)){
		    if(goal.getLocation().x > snake.getHead().x){
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
		if(goal.getLocation().x+blindness == snake.getHead().x){
		    if(goal.getLocation().y < snake.getHead().y && !snake.isCollisionAhead(UP, 1, avoidPoints))
			snake.changeDirection(Snake.UP);
		    else if(goal.getLocation().y+blindness > snake.getHead().y && !snake.isCollisionAhead(DOWN, 1, avoidPoints))
			snake.changeDirection(Snake.DOWN);
		}
		
		if(snake.isCollision(avoidPoints)){
		    if(goal.getLocation().y < snake.getHead().y){
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