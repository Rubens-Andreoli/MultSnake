package com.iinmorus.gtc.state;

import com.iinmorus.gtc.bot.Bot;
import com.iinmorus.gtc.bot.FastBot;
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

public class Idle extends GameState{
    
    //entities
    private Snake snake;
    private Cherry cherry;
    private Bot bot;
    private Walls walls;
    
    //status
    private Point lastClick;
    
    @Override
    public void start(){
	snake = new Snake(0,0);
	snake.setBaseColor(new Color(255, 173, 51));
	cherry = new Cherry();
	bot = new FastBot(snake);
	bot.changeGoal(cherry.getLocation());
	baseWallAmount = 50;
	walls = new Walls(baseWallAmount);

	GAME.sounds.loop("idle", 600, GAME.sounds.getFrames("idle") - 2000);
    }

    @Override
    public void update() {
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

    @Override
    public void draw(Graphics2D g) {
	g.setColor(backgroung);
	g.fillRect(0, 0, GAME.settings.width, GAME.settings.height);
	
	snake.draw(g);
	
	cherry.draw(g);
	
	walls.draw(g);
	
	g.setColor(titleColor);
	g.fillRect(0, 0, GAME.settings.width, 52);
	g.setColor(backgroung);
	g.setFont(titleFont);
	g.drawString(title, (GAME.settings.width/2-g.getFontMetrics(titleFont).stringWidth(title)/2), 48);
    }
    
    @Override
    public void mousePressed(MouseEvent e){
        Point point = new Point(e.getPoint().x/Drawable.SCALE, e.getPoint().y/Drawable.SCALE);
	lastClick = point;
	bot.changeGoal(point);
    }

    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_1){GAME.states.startState(SINGLE);} //REMOVE: testing...
	if(e.getKeyCode() == KeyEvent.VK_2){GAME.states.startState(MULT);}
	if(e.getKeyCode() == KeyEvent.VK_EQUALS) GAME.sounds.ajustVolume("idle", 5F);
	if(e.getKeyCode() == KeyEvent.VK_MINUS) GAME.sounds.ajustVolume("idle", -5F);
    }
    
    @Override
    public void keyReleased(KeyEvent e){}

    @Override
    public void setPaused(boolean isPaused){
	GAME.sounds.stop("idle");
    }

    @Override
    public String getStateID() {
	return IDLE;
    }

    @Override
    public void setDifficulty(int difficulty) {}

}
