package com.iinmorus.multsnake.state;

import com.iinmorus.multsnake.engine.StateManager;
import com.iinmorus.multsnake.bot.Bot;
import com.iinmorus.multsnake.bot.FastBot;
import com.iinmorus.multsnake.engine.Renderer;
import com.iinmorus.multsnake.entity.Cherry;
import com.iinmorus.multsnake.entity.Snake;
import com.iinmorus.multsnake.entity.Walls;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Idle extends State{

    //ui
    private Font titleFont = new Font(Font.MONOSPACED, Font.BOLD, 60);
    private Color titleColor = Color.RED;
    private String title = "GET THAT CHERRY";
    private Color snakeColor = new Color(255, 173, 51);
    
    //entities
    private Snake snake;
    private Cherry cherry;
    private Bot bot;
    private Walls walls;
    
    //status
    private Point lastClick;
    
    public Idle(StateManager sManager) {
	super(sManager);
	this.init();
    }

    @Override
    public void init(){
	snake = new Snake(0,0);
	snake.setBaseColor(snakeColor);
	cherry = new Cherry();
	bot = new FastBot(snake);
	bot.changeGoal(cherry.getLocation());
	baseWallAmount = 50;
	walls = new Walls(baseWallAmount, cherry.getLocation());
    }

    @Override
    public void update() {
	stateTick++;
	
	if(stateTick%updateTick == 0){
	    if(lastClick!=null && snake.getHead().equals(lastClick)) 
		bot.changeGoal(cherry.getLocation());
	   
	    bot.control(walls.isCollidable()? walls.getWalls() : snake.getSnakePoints());
	    snake.move();
	    
	    if(snake.getHead().equals(cherry.getLocation())){
		cherry = new Cherry(walls.getWalls());
		bot.changeGoal(cherry.getLocation());
	    } 
	}
	
	if(stateTick%wallRefreshTick == 0)
	    walls = new Walls(baseWallAmount, cherry.getLocation());
	
	if(stateTick%wallRefreshTick-wallFormationTick == 0)
                walls.setCollidable(true);
	    
    }

    @Override
    public void draw(Graphics g){
	g.setColor(backgroung);
	g.fillRect(0, 0, Renderer.WIDTH, Renderer.HEIGHT);
	
	snake.draw(g);
	
	cherry.draw(g);
	
	walls.draw(g);
	
	g.setColor(titleColor);
	g.fillRect(0, 0, Renderer.WIDTH, 50);
	g.setColor(backgroung);
	g.setFont(titleFont);
	g.drawString(title, (Renderer.WIDTH/2-g.getFontMetrics(titleFont).stringWidth(title)/2), 43);
    }
    
    @Override
    public void mousePressed(MouseEvent e){
        Point point = new Point(e.getPoint().x/Renderer.SCALE, e.getPoint().y/Renderer.SCALE);
	lastClick = point;
	bot.changeGoal(point);
    }

    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_1) sManager.setState(StateManager.SINGLE_STATE); //REMOVE: testing...
	if(e.getKeyCode() == KeyEvent.VK_2) sManager.setState(StateManager.MULTI_STATE);
    }
    
    @Override
    public void keyReleased(KeyEvent e){}

    @Override
    public void setPaused(boolean isPaused){}
}
