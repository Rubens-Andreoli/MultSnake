package com.iinmorus.gtc.state;

import com.iinmorus.gtc.entity.Cherry;
import com.iinmorus.gtc.entity.Snake;
import com.iinmorus.gtc.entity.Walls;
import static com.iinmorus.gtc.ui.GameWindow.GAME;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Singleplayer extends GameState{
    private static final long serialVersionUID = 2;
      
    //entities
    private Snake snake;
    private Cherry cherry;
    private Walls walls;
    
    //status
    private int score;

    @Override
    public void start(){
	stateTick = 0;
	time = 0;
	score = 0;
	isOver = false;
	isPaused = false;
	
        snake = new Snake(0,0);
        cherry = new Cherry();
        walls = new Walls(Math.round(baseWallAmount*difficulty*0.60F));
	
	GAME.sounds.loop("match", 600, GAME.sounds.getFrames("match") - 2000);
    }

    @Override
    public void update() {
	if(!isOver && !isPaused){
	    stateTick++;
	    
	    walls.update(cherry.getLocation());
		
	    ArrayList<Point> blacklist = new ArrayList<>();
	    blacklist.addAll(walls.getWalls());
	    blacklist.addAll(snake.getSnakePoints());
                
	    if(!snake.isCollision(walls.isCollidable()? blacklist: snake.getSnakePoints())){
	        snake.move();
                if(snake.getHead().equals(cherry.getLocation())){
                    GAME.sounds.play("cherry");
		    score += baseScore*difficulty;
		    snake.grow();
                    cherry = new Cherry(blacklist);
                }
            }else{
	        GAME.sounds.stop("match");
	        GAME.sounds.play("hit");
	        isOver = true;
	    }
	    	    
	    if(stateTick%(1000/GAME.settings.updateRate) == 0) time++;
	}
    }

    @Override
    public void draw(Graphics2D g) {
	g.setColor(backgroung);
	g.fillRect(0, 0, GAME.settings.width, GAME.settings.height);
	
	snake.draw(g);
	cherry.draw(g);
	walls.draw(g);
	
	g.setFont(scoreFont);
	g.setColor(snake.getBaseColor());
	g.drawString("Score: " + score, 10, 20);
	g.drawString("Length: " + snake.getTailLenght(), 10, 40);
	g.setColor(overColor);
	g.drawString("Time: " + time, 10, GAME.settings.height-10);
	
	g.setFont(warningFont);
        if(isOver){
            g.setColor(overColor);
	    g.drawString(overMsg, GAME.settings.width/2-g.getFontMetrics(warningFont).stringWidth(overMsg)/2, GAME.settings.height/2);
        }else if(isPaused){
	    g.setColor(pauseColor);
	    g.drawString(pauseMsg, GAME.settings.width/2-g.getFontMetrics(warningFont).stringWidth(pauseMsg)/2, GAME.settings.height/2);
	}
    }

    @Override
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
	switch (key) {
	    case KeyEvent.VK_UP:
		snake.changeDirection(Snake.UP);
		break;
	    case KeyEvent.VK_DOWN:
		snake.changeDirection(Snake.DOWN);
		break;
	    case KeyEvent.VK_LEFT:
		snake.changeDirection(Snake.LEFT);
		break;
	    case KeyEvent.VK_RIGHT:
		snake.changeDirection(Snake.RIGHT);
		break;
	    case KeyEvent.VK_SPACE:
		if(!isOver) setPaused(!isPaused);
		break;
	    case KeyEvent.VK_ESCAPE:
		this.start();
		isPaused = false;
		break;
	}
    }

    @Override
    public void keyReleased(KeyEvent e){}

    @Override
    public void mousePressed(MouseEvent e){}

    @Override
    public void setPaused(boolean isPaused){
	this.isPaused = isPaused;
	if(isPaused) GAME.sounds.stop("match");
	else{
	    GAME.sounds.loop("match", GAME.sounds.getPosition("match"), 600, GAME.sounds.getFrames("match") - 2000);
	}
    }
    
    @Override
    public void setDifficulty(int difficulty){this.difficulty = difficulty;}
    
    public int getScore(){return score;}
    public int getTime(){return time;}

    @Override
    public String getStateID() {
	return SINGLE;
    }

}
