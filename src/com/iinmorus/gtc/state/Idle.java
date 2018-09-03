package com.iinmorus.gtc.state;

import com.iinmorus.engine.Engine;
import static com.iinmorus.gtc.GTC.IDLE;
import static com.iinmorus.gtc.GTC.MULT;
import static com.iinmorus.gtc.GTC.SINGLE;
import com.iinmorus.gtc.bot.Bot;
import com.iinmorus.gtc.bot.FastBot;
import com.iinmorus.gtc.entity.Cherry;
import com.iinmorus.gtc.entity.Snake;
import com.iinmorus.gtc.entity.Walls;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Idle extends GTCState{
    
    //entities
    private Snake snake;
    private Cherry cherry;
    private Bot bot;
    private Walls walls;
    
    //status
    private Point lastClick;

    public Idle(Engine engine) {
	super(engine);
    }
    
    @Override
    public void start(){
	snake = new Snake(0,0);
	snake.setColor(new Color(255, 173, 51));
	cherry = new Cherry();
	bot = new FastBot(snake);
	bot.changeGoal(cherry.getLocation());
	baseWallAmount = 50;
	walls = new Walls(baseWallAmount);

	engine.sounds.loop("idle", 600, engine.sounds.getFrames("idle") - 2000);
        //System.out.println(engine.sounds.getFrames("idle")); 1283328
    }

    @Override
    public void update() {
        System.out.println(engine.sounds.getPosition("idle"));
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
	snake.draw(g);
	
	cherry.draw(g);
	
	walls.draw(g);
	
	g.setColor(titleColor);
	g.fillRect(0, 0, width, 52);
	g.setColor(background);
	g.setFont(titleFont);
	g.drawString(title, (width/2-g.getFontMetrics(titleFont).stringWidth(title)/2), 48);
    }
    
    @Override
    public void mousePressed(MouseEvent e){
        Point point = new Point(e.getPoint().x/scale, e.getPoint().y/scale);
	lastClick = point;
	bot.changeGoal(point);
    }

    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_1){engine.states.startState(SINGLE);} //REMOVE: testing...
	if(e.getKeyCode() == KeyEvent.VK_2){engine.states.startState(MULT);} //REMOVE: testing...
	if(e.getKeyCode() == KeyEvent.VK_EQUALS) engine.sounds.ajustClipVolume("idle", 5F);
	if(e.getKeyCode() == KeyEvent.VK_MINUS) engine.sounds.ajustClipVolume("idle", -5F);
    }
    
    @Override
    public void keyReleased(KeyEvent e){}

    @Override
    public String getStateID() {
	return IDLE;
    }

    @Override
    public void setPaused(boolean isPaused) {
	if(isPaused) engine.sounds.stop("idle");
	else engine.sounds.loop(
		"idle", 
		engine.sounds.getPosition("idle"), 
		600, 
		engine.sounds.getFrames("idle") - 2000
	    );
    }

}
