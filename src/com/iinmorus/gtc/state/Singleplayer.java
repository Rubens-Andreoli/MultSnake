package com.iinmorus.gtc.state;

import com.iinmorus.gtc.entity.Cherry;
import com.iinmorus.gtc.entity.Snake;
import com.iinmorus.gtc.entity.Walls;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import static com.iinmorus.gtc.ui.GameWindow.ENGINE;

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
	
	ENGINE.sounds.loop("match", 600, ENGINE.sounds.getFrames("match") - 2000);
    }

    @Override
    public void update() {
	if(!isOver && !isPaused){
	    stateTick++;
	    if(stateTick%ENGINE.settings.tickRate == 0) time++;
	    
	    walls.update(cherry.getLocation());
		
	    ArrayList<Point> blacklist = new ArrayList<>();
	    blacklist.addAll(walls.getWalls());
	    blacklist.addAll(snake.getSnakePoints());
                
	    if(!snake.isCollision(walls.isCollidable()? blacklist: snake.getSnakePoints())){
	        snake.move();
                if(snake.getHead().equals(cherry.getLocation())){
                    ENGINE.sounds.play("cherry");
		    score += baseScore*difficulty;
		    snake.grow();
                    cherry = new Cherry(blacklist);
                }
            }else{
	        ENGINE.sounds.stop("match");
	        ENGINE.sounds.play("hit");
	        isOver = true;
	    }
	}
    }

    @Override
    public void draw(Graphics2D g) {
	g.setColor(background);
	g.fillRect(0, 0, ENGINE.settings.width, ENGINE.settings.height);
	
	snake.draw(g);
	cherry.draw(g);
	walls.draw(g);
	
	g.setFont(scoreFont);
	g.setColor(snake.getColor());
	g.drawString("Score: " + score, 10, 20);
	g.drawString("Length: " + snake.getTailLenght(), 10, 40);
	g.setColor(overColor);
	g.drawString("Time: " + time, 10, ENGINE.settings.height-10);
	
	g.setFont(warningFont);
        if(isOver){
            g.setColor(overColor);
	    g.drawString(overMsg, ENGINE.settings.width/2-g.getFontMetrics(warningFont).stringWidth(overMsg)/2, ENGINE.settings.height/2);
        }else if(isPaused){
	    g.setColor(pauseColor);
	    g.drawString(pauseMsg, ENGINE.settings.width/2-g.getFontMetrics(warningFont).stringWidth(pauseMsg)/2, ENGINE.settings.height/2);
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
	if(isPaused) ENGINE.sounds.stop("match");
	else{
	    ENGINE.sounds.loop("match", ENGINE.sounds.getPosition("match"), 600, ENGINE.sounds.getFrames("match") - 2000);
	}
    }
    
    public void setDifficulty(int difficulty){this.difficulty = difficulty;}
    
    public int getScore(){return score;}
    public int getTime(){return time;}

    @Override
    public String getStateID() {
	return SINGLE;
    }

    @Override
    public void unload() {
	
    }

}
