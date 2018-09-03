package com.iinmorus.gtc.state;

import com.iinmorus.gtc.bot.Bot;
import com.iinmorus.gtc.bot.FastBot;
import com.iinmorus.gtc.bot.PreciseBot;
import com.iinmorus.gtc.bot.SlowBot;
import com.iinmorus.engine.Engine;
import com.iinmorus.engine.Renderer;
import com.iinmorus.engine.SoundManager;
import com.iinmorus.gtc.entity.Cherry;
import com.iinmorus.gtc.entity.Drawable;
import com.iinmorus.gtc.entity.Snake;
import com.iinmorus.gtc.entity.Walls;
import static com.iinmorus.gtc.ui.GameWindow.GAME;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Multiplayer extends GameState{
    private static final long serialVersionUID = 2;
    
    //entities
    private Snake snake_P2;
    private Snake snake_P1;
    private Cherry cherry;
    private Walls walls;
    private Bot bot;
       
    //status
    private boolean isBot;
    private int score_P1, score_P2;

    @Override
    public void start(){
	isBot = true; //REMOVE: testing...
	
	stateTick = 0;
	time = 0;
	score_P1 = 0;
	score_P2 = 0;
	isOver = false;
	isPaused = false;
	
        snake_P1 = new Snake(0,0);
	snake_P2 = new Snake(GAME.settings.width/Drawable.SCALE-1, 0);
	snake_P2.setBaseColor(new Color(112, 219, 112));
        cherry = new Cherry();
        walls = new Walls(Math.round(baseWallAmount*difficulty*0.60F));
	
	if(isBot){
	    switch(difficulty){
		case EASY:
		    bot = new SlowBot(snake_P2);
		    break;
		case MEDIUM:
		    bot = new PreciseBot(snake_P2);
		    break;
		case HARD:
		    bot = new FastBot(snake_P2);
		    break;
	    }
	    bot.changeGoal(cherry.getLocation());
	}
	
	GAME.sounds.loop("match", 600, GAME.sounds.getFrames("match") - 2000);
    }

    @Override
    public void update(){
	if(!isOver && !isPaused){
	    stateTick++;
            
	    if(stateTick%updateTick == 0){
		walls.update(cherry.getLocation());
		
		ArrayList<Point> blacklist = new ArrayList<>();
		if(walls.isCollidable()) blacklist.addAll(walls.getWalls());
		blacklist.addAll(snake_P1.getSnakePoints());
		blacklist.addAll(snake_P2.getSnakePoints());
                
		if(isBot) bot.control(blacklist);

		if(!snake_P1.isCollision(blacklist) && !snake_P2.isCollision(blacklist)){
                    snake_P1.move();
		    snake_P2.move();
                    
		    boolean isP1 = false;
		    if((isP1 = snake_P1.getHead().equals(cherry.getLocation())) || snake_P2.getHead().equals(cherry.getLocation())){
			GAME.sounds.play("cherry");
			if(isP1){
			    score_P1 += baseScore*difficulty;
			    this.applyEffect(snake_P1);
			}else{
			    score_P2 += baseScore*difficulty;
			    this.applyEffect(snake_P2);
			}
                        cherry = new Cherry(blacklist);
			if(isBot) bot.changeGoal(cherry.getLocation());
                    }
		    
                }else{
		    GAME.sounds.stop("match");
		    GAME.sounds.play("hit");
		    isOver = true;
		}
            }
	    
	    if(stateTick%(1000/GAME.engine.getTickRate()) == 0)
		time++;
        }
    }

    @Override
    public void draw(Graphics2D g){
	g.setColor(backgroung);
	g.fillRect(0, 0, GAME.settings.width, GAME.settings.height);
	
	snake_P1.draw(g);
	snake_P2.draw(g);
	cherry.draw(g);
	walls.draw(g);
	
	g.setFont(scoreFont);
	g.setColor(snake_P1.getBaseColor());
        g.drawString("Score: " + score_P1, 10, 20);
	g.setColor(snake_P2.getBaseColor());
	g.drawString("Score: " + score_P2, 10, 40);
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
		snake_P1.changeDirection(Snake.UP);
		break;
	    case KeyEvent.VK_DOWN:
		snake_P1.changeDirection(Snake.DOWN);
		break;
	    case KeyEvent.VK_LEFT:
		snake_P1.changeDirection(Snake.LEFT);
		break;
	    case KeyEvent.VK_RIGHT:
		snake_P1.changeDirection(Snake.RIGHT);
		break;
	    case KeyEvent.VK_SPACE:
		if(!isOver) this.setPaused(!isPaused);
		break;
	    case KeyEvent.VK_ESCAPE:
		this.start();
		this.setPaused(false);
		break;
	}
	
	if(!isBot){
	    switch(key){
		case KeyEvent.VK_W:
		    snake_P2.changeDirection(Snake.UP);
		    break;
		case KeyEvent.VK_S:
		    snake_P2.changeDirection(Snake.DOWN);
		    break;
		case KeyEvent.VK_A:
		    snake_P2.changeDirection(Snake.LEFT);
		    break;
		case KeyEvent.VK_D:
		    snake_P2.changeDirection(Snake.RIGHT);
		    break;
	    }
	}
    }
    
    private void applyEffect(Snake snake){
	switch(cherry.getEffect()){
	    case Cherry.GROW:
		snake.grow();
		break;
	    case Cherry.SHRINK:
		snake.shrink();
		break;
	}
    }

    @Override
    public void mousePressed(MouseEvent e){}
    
    @Override
    public void keyReleased(KeyEvent e){}

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
    
    public void vsBot(boolean isBot){this.isBot = isBot;}
    
    public int getScore_P1(){return score_P1;}
    public int getScore_P2(){return score_P2;}
    public int getTime(){return time;}  

    @Override
    public String getStateID() {
	return MULT;
    }

}
