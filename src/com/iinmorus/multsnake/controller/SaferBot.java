package com.iinmorus.multsnake.controller;

import com.iinmorus.multsnake.model.Cherry;
import com.iinmorus.multsnake.model.Snake;
import static com.iinmorus.multsnake.model.Snake.DOWN;
import static com.iinmorus.multsnake.model.Snake.LEFT;
import static com.iinmorus.multsnake.model.Snake.RIGHT;
import static com.iinmorus.multsnake.model.Snake.UP;
import java.awt.event.KeyEvent;

public class SaferBot {
    
    private Cherry cherry;
    private Snake snake;
    
    public SaferBot(Snake snake){
	this.snake = snake;
    }
    
    public void changeGoal(Cherry cherry){
	this.cherry = cherry;
    }
    
    public void control(){
	int ahead = 1;
	switch(snake.getDirection()){
	    case DOWN:	
		if(cherry.getLocation().y == snake.getHead().y){
		    if(cherry.getLocation().x < snake.getHead().x && !snake.checkCollisionAhead(LEFT, ahead))
			snake.changeDirection(KeyEvent.VK_LEFT);
		    else if(cherry.getLocation().x > snake.getHead().x && !snake.checkCollisionAhead(RIGHT, ahead))
			snake.changeDirection(KeyEvent.VK_RIGHT);
		}
		
		if(snake.checkCollisionAhead(DOWN, 1)){
		    if(cherry.getLocation().x > snake.getHead().x){
			if (snake.checkCollisionAhead(RIGHT, 1) || snake.checkCollisionAhead(RIGHT, 2)) {
			    snake.changeDirection(KeyEvent.VK_LEFT);
			} else {
			    snake.changeDirection(KeyEvent.VK_RIGHT);
			}
		    }else{
			if (snake.checkCollisionAhead(LEFT, 1) || snake.checkCollisionAhead(LEFT, 2)) {
			snake.changeDirection(KeyEvent.VK_RIGHT);
		    } else {
			snake.changeDirection(KeyEvent.VK_LEFT);
		    }
		    }
		}
		break;
	    case UP:
		if(cherry.getLocation().y == snake.getHead().y){
		    if(cherry.getLocation().x < snake.getHead().x && !snake.checkCollisionAhead(LEFT, ahead))
			snake.changeDirection(KeyEvent.VK_LEFT);
		    else if(cherry.getLocation().x > snake.getHead().x && !snake.checkCollisionAhead(RIGHT, ahead))
			snake.changeDirection(KeyEvent.VK_RIGHT);
		}
		
		if(snake.checkCollisionAhead(UP, 1)){
		    if(cherry.getLocation().x > snake.getHead().x){
			if (snake.checkCollisionAhead(RIGHT, 1) || snake.checkCollisionAhead(RIGHT, 2)) {
			    snake.changeDirection(KeyEvent.VK_LEFT);
			} else {
			    snake.changeDirection(KeyEvent.VK_RIGHT);
			}
		    }else{
			if (snake.checkCollisionAhead(LEFT, 1) || snake.checkCollisionAhead(LEFT, 2)) {
			    snake.changeDirection(KeyEvent.VK_RIGHT);
			} else {
			    snake.changeDirection(KeyEvent.VK_LEFT);
			}
		    }
		}
		break;
	    case LEFT:
		if(cherry.getLocation().x == snake.getHead().x){
		    if(cherry.getLocation().y < snake.getHead().y && !snake.checkCollisionAhead(UP, ahead))
			snake.changeDirection(KeyEvent.VK_UP);
		    else if(cherry.getLocation().y > snake.getHead().y && !snake.checkCollisionAhead(DOWN, ahead))
			snake.changeDirection(KeyEvent.VK_DOWN);
		}
		
		if(snake.checkCollisionAhead(LEFT, 1)){
		    if(cherry.getLocation().y < snake.getHead().y){
			if (snake.checkCollisionAhead(UP, 1) || snake.checkCollisionAhead(UP, 2)) {
			    snake.changeDirection(KeyEvent.VK_DOWN);
			} else {
			    snake.changeDirection(KeyEvent.VK_UP);
			}
		    }else{
			if (snake.checkCollisionAhead(DOWN, 1) || snake.checkCollisionAhead(DOWN, 2)) {
			    snake.changeDirection(KeyEvent.VK_UP);
			} else {
			    snake.changeDirection(KeyEvent.VK_DOWN);
			}
		    }
		}
		break;
	    case RIGHT:
		if(cherry.getLocation().x == snake.getHead().x){
		    if(cherry.getLocation().y < snake.getHead().y && !snake.checkCollisionAhead(UP, ahead))
			snake.changeDirection(KeyEvent.VK_UP);
		    else if(cherry.getLocation().y > snake.getHead().y && !snake.checkCollisionAhead(DOWN, ahead)) 
			snake.changeDirection(KeyEvent.VK_DOWN);
		}
		
		if (snake.checkCollisionAhead(RIGHT, 1)) {
		    if(cherry.getLocation().y < snake.getHead().y){
			if (snake.checkCollisionAhead(UP, 1) || snake.checkCollisionAhead(UP, 2)) {
			    snake.changeDirection(KeyEvent.VK_DOWN);
			} else {
			    snake.changeDirection(KeyEvent.VK_UP);
			}
		    }else{
			if (snake.checkCollisionAhead(DOWN, 1) || snake.checkCollisionAhead(DOWN, 2)) {
			    snake.changeDirection(KeyEvent.VK_UP);
			} else {
			    snake.changeDirection(KeyEvent.VK_DOWN);
			}
		    }
		}
		break;
	}
    
    }
    
    
    
    
}
