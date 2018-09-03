

import com.iinmorus.multsnake.objects.Snake;
import static com.iinmorus.multsnake.objects.Snake.DOWN;
import static com.iinmorus.multsnake.objects.Snake.LEFT;
import static com.iinmorus.multsnake.objects.Snake.RIGHT;
import static com.iinmorus.multsnake.objects.Snake.UP;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class BlindBot extends Bot {

    private Random random;
    private int blindness = 3;
    
    public BlindBot(Snake snake) {
	super(snake);
	this.random = new Random();
    }
      
    @Override
    public void control(ArrayList<Point> avoidPoints){
	switch(snake.getDirection()){
	    case DOWN:	
	    case UP:
		if(cherry.getLocation().y+random.nextInt(blindness) == snake.getHead().y){
		    if(cherry.getLocation().x < snake.getHead().x && !snake.isCollisionAhead(LEFT, 1, avoidPoints))
			snake.changeDirection(Snake.LEFT);
		    else if(cherry.getLocation().x > snake.getHead().x && !snake.isCollisionAhead(RIGHT, 1, avoidPoints))
			snake.changeDirection(Snake.RIGHT);
		}
		
		if(snake.isCollision(avoidPoints)){
		    if(cherry.getLocation().x > snake.getHead().x){
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
		if(cherry.getLocation().x+random.nextInt(blindness) == snake.getHead().x){
		    if(cherry.getLocation().y+random.nextInt(blindness) < snake.getHead().y && !snake.isCollisionAhead(UP, 1, avoidPoints))
			snake.changeDirection(Snake.UP);
		    else if(cherry.getLocation().y > snake.getHead().y && !snake.isCollisionAhead(DOWN, 1, avoidPoints))
			snake.changeDirection(Snake.DOWN);
		}
		
		if(snake.isCollision(avoidPoints)){
		    if(cherry.getLocation().y < snake.getHead().y){
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
