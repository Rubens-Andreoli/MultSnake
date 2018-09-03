package com.iinmorus.multsnake.state;

import com.iinmorus.engine.StateManager;
import com.iinmorus.multsnake.bot.Bot;
import com.iinmorus.multsnake.bot.FastBot;
import com.iinmorus.engine.Renderer;
import com.iinmorus.engine.SoundManager;
import com.iinmorus.multsnake.entity.Cherry;
import com.iinmorus.multsnake.entity.Drawable;
import com.iinmorus.multsnake.entity.Snake;
import com.iinmorus.multsnake.entity.Walls;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Idle extends GameState{
    
    //entities
    private Snake snake;
    private Cherry cherry;
    private Bot bot;
    private Walls walls;
    
    //status
    private Point lastClick;
    
    @Override
    public void init(){
	snake = new Snake(0,0);
	snake.setBaseColor(new Color(255, 173, 51));
	cherry = new Cherry();
	bot = new FastBot(snake);
	bot.changeGoal(cherry.getLocation());
	baseWallAmount = 50;
	walls = new Walls(baseWallAmount);

	SoundManager.loop("idle", 600, SoundManager.getFrames("idle") - 2000);
    }

    @Override
    public void update() {
	stateTick++;
	
	if(stateTick%updateTick == 0){
	    walls.update(cherry.getLocation());
	    
	    if(lastClick!=null && snake.getHead().equals(lastClick)) 
		bot.changeGoal(cherry.getLocation());
	   
	    bot.control(walls.isCollidable()? walls.getWalls() : snake.getSnakePoints());
	    snake.move();
	    
	    if(snake.getHead().equals(cherry.getLocation())){
		cherry = new Cherry(walls.getWalls());
		bot.changeGoal(cherry.getLocation());
	    } 
	}
	    
    }

    @Override
    public void draw(Graphics2D g) {
	g.setColor(backgroung);
	g.fillRect(0, 0, Renderer.WIDTH, Renderer.HEIGHT);
	
	snake.draw(g);
	
	cherry.draw(g);
	
	walls.draw(g);
	
	g.setColor(titleColor);
	g.fillRect(0, 0, Renderer.WIDTH, 52);
	g.setColor(backgroung);
	g.setFont(titleFont);
	g.drawString(title, (Renderer.WIDTH/2-g.getFontMetrics(titleFont).stringWidth(title)/2), 48);
    }
    
    @Override
    public void mousePressed(MouseEvent e){
        Point point = new Point(e.getPoint().x/Drawable.SCALE, e.getPoint().y/Drawable.SCALE);
	lastClick = point;
	bot.changeGoal(point);
    }

    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_1){ //REMOVE: testing...
	    StateManager.startState(GameStateFactory.SINGLE);
	    SoundManager.stop("idle");
	}
	if(e.getKeyCode() == KeyEvent.VK_2){
	    StateManager.startState(GameStateFactory.MULT);
	    SoundManager.stop("idle");
	}
	if(e.getKeyCode() == KeyEvent.VK_EQUALS) SoundManager.ajustVolume("idle", 5F);
	if(e.getKeyCode() == KeyEvent.VK_MINUS) SoundManager.ajustVolume("idle", -5F);
    }
    
    @Override
    public void keyReleased(KeyEvent e){}

    @Override
    public void setPaused(boolean isPaused){}

    @Override
    public String getStateID() {
	return GameStateFactory.IDLE;
    }

    @Override
    public void setDifficulty(int difficulty) {}

}
