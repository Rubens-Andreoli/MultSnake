package com.unip.gtc.state;

import br.unip.engine.Engine;
import static br.unip.gtc.GTC.IDLE;
import com.unip.gtc.entity.Bot;
import com.unip.gtc.entity.Cherry;
import com.unip.gtc.entity.Snake;
import com.unip.gtc.entity.Walls;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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
	bot = new Bot(snake);
	bot.changeGoal(cherry.getLocation());
	walls = new Walls();
	engine.sounds.loop("idle", 600, engine.sounds.getFrames("idle") - 2000);
    }

    @Override
    public void update() {
        walls.update(cherry.getLocation());
	    
	ArrayList<Point> blacklist = new ArrayList<>();
	blacklist.addAll(snake.getSnakePoints());
	if(walls.isCollidable()) blacklist.addAll(walls.getWalls());
	
	if(lastClick!=null && snake.getHead().equals(lastClick)) 
	    bot.changeGoal(cherry.getLocation());
	   
	bot.control(blacklist);
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
	drawTitle(g);
    }
    
    private void drawTitle(Graphics2D g){
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
	if(e.getKeyCode() == KeyEvent.VK_EQUALS) engine.sounds.ajustMasterVolume(5F);
	if(e.getKeyCode() == KeyEvent.VK_MINUS) engine.sounds.ajustMasterVolume(-5F);
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

    @Override
    public void setDifficulty(int difficulty){}

}
