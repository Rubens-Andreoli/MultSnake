package com.unip.gtc.state;

import br.unip.engine.Engine;
import static br.unip.gtc.GTC.SINGLE;
import com.unip.gtc.entity.Cherry;
import com.unip.gtc.entity.Snake;
import com.unip.gtc.entity.Walls;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Singleplayer extends GTCState{
      
    //entities
    private Snake snake;
    private Cherry cherry;
    private Walls walls;
    
    //status
    private int score;

    public Singleplayer(Engine engine) {
	super(engine);
    }

    @Override
    public void start(){
	stateTick = 0;
	time = 0;
	score = 0;
	isOver = false;
	isPaused = false;
	
        snake = new Snake(0,0);
        cherry = new Cherry();
        walls = new Walls(difficulty);
	
	engine.sounds.loop("match", 600, engine.sounds.getFrames("match") - 2000);
    }

    @Override
    public void update() {
	if(!isOver && !isPaused){
	    stateTick++;
	    if(stateTick%engine.settings.tickRate == 0) time++;
	    
	    walls.update(cherry.getLocation());
		
	    ArrayList<Point> blacklist = new ArrayList<>();
	    blacklist.addAll(walls.getWalls());
	    blacklist.addAll(snake.getSnakePoints());
                
	    if(!snake.isCollision(walls.isCollidable()? blacklist: snake.getSnakePoints())){
	        snake.move();
                if(snake.getHead().equals(cherry.getLocation())){
                    engine.sounds.play("cherry");
		    score += baseScore*difficulty;
		    snake.grow();
                    cherry = new Cherry(blacklist);
                }
            }else{
		score -= baseScore*difficulty+((time+1)*0.2F)+1;
	        engine.sounds.stop("match");
	        engine.sounds.play("hit");
	        isOver = true;
	    }
	}
    }

    @Override
    public void draw(Graphics2D g) {
	snake.draw(g);
	cherry.draw(g);
	walls.draw(g);
	
	drawScore(g);
	
        if(isOver){
            super.drawOver(g);
        }else if(isPaused){
	    super.drawPaused(g);
	}
    }
    
    private void drawScore(Graphics2D g){
	g.setFont(scoreFont);
	g.setColor(snake.getColor());
	g.drawString("Score: " + score, 10, 20);
	g.drawString("Length: " + snake.getTailLenght(), 10, 40);
	g.setColor(overColor);
	g.drawString("Time: " + time, 10, height-10); 
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
		start();
		setPaused(false);
		break;
	    case KeyEvent.VK_MINUS:
		engine.sounds.ajustMasterVolume(-5F);
		break;
	    case KeyEvent.VK_EQUALS:
		engine.sounds.ajustMasterVolume(5F);
		break;
	}
    }
    
    @Override
    public void setDifficulty(int difficulty){this.difficulty = difficulty;}
    
    public int getScore(){return score;}
    public int getTime(){return time;}

    @Override
    public String getStateID(){
	return SINGLE;
    }

    @Override
    public void setPaused(boolean isPaused){
	this.isPaused = isPaused;
	if(isPaused) engine.sounds.stop("match");
	else engine.sounds.loop(
		"match", 
		engine.sounds.getPosition("match"), 
		600, 
		engine.sounds.getFrames("match") - 2000
	    );
    }
    
    @Override
    public void keyReleased(KeyEvent e){}

    @Override
    public void mousePressed(MouseEvent e){}

}
