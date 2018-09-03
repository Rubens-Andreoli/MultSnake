package com.iinmorus.gtc.state;

import static com.iinmorus.frame.GameWindow.ENGINE;
import static com.iinmorus.frame.GameWindow.SETTINGS;
import com.iinmorus.gtc.bot.Bot;
import com.iinmorus.gtc.bot.FastBot;
import com.iinmorus.gtc.entity.Cherry;
import com.iinmorus.gtc.entity.Drawable;
import com.iinmorus.gtc.entity.Snake;
import com.iinmorus.gtc.entity.Walls;
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
	snake.setColor(new Color(255, 173, 51));
	cherry = new Cherry();
	bot = new FastBot(snake);
	bot.changeGoal(cherry.getLocation());
	baseWallAmount = 50;
	walls = new Walls(baseWallAmount);

	ENGINE.sounds.loop("idle", 600, ENGINE.sounds.getFrames("idle") - 2000);
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
	g.setColor(background);
	g.fillRect(0, 0, SETTINGS.width, SETTINGS.height);
	
	snake.draw(g);
	
	cherry.draw(g);
	
	walls.draw(g);
	
	g.setColor(titleColor);
	g.fillRect(0, 0, SETTINGS.width, 52);
	g.setColor(background);
	g.setFont(titleFont);
	g.drawString(title, (SETTINGS.width/2-g.getFontMetrics(titleFont).stringWidth(title)/2), 48);
    }
    
    @Override
    public void mousePressed(MouseEvent e){
        Point point = new Point(e.getPoint().x/Drawable.SIZE, e.getPoint().y/Drawable.SIZE);
	lastClick = point;
	bot.changeGoal(point);
    }

    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_1){ENGINE.states.startState(SINGLE);} //REMOVE: testing...
	if(e.getKeyCode() == KeyEvent.VK_2){ENGINE.states.startState(MULT);}
	if(e.getKeyCode() == KeyEvent.VK_EQUALS) ENGINE.sounds.ajustClipVolume("idle", 5F);
	if(e.getKeyCode() == KeyEvent.VK_MINUS) ENGINE.sounds.ajustClipVolume("idle", -5F);
    }
    
    @Override
    public void keyReleased(KeyEvent e){}

    @Override
    public void setPaused(boolean isPaused){}

    @Override
    public String getStateID() {
	return IDLE;
    }

    @Override
    public void unload() {
	snake = null;
	cherry = null;
	bot = null;
	walls = null;
	ENGINE.sounds.close("idle");
    }

}
