package com.iinmorus.multsnake.state;

import com.iinmorus.multsnake.engine.Engine;
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
import java.util.ArrayList;

public class Singleplayer extends State{
    private static final long serialVersionUID = 1;
    
    //ui
    private Color backgroung = Color.BLACK;
    private Color overColor = Color.RED;
    private Color pauseColor = Color.YELLOW;
    private String overMsg = "GAME OVER";
    private String pauseMsg = "PAUSED";
    private Font scoreFont = new Font(Font.MONOSPACED, Font.PLAIN, 20);
    private Font warningFont = new Font(Font.MONOSPACED, Font.BOLD, 100);
    
    //entities
    private Snake snake;
    private Cherry cherry;
    private Walls walls;
    
    //configs
    private int updateTick = 3;
    private int wallRefreshTick = 600;
    private int baseWallAmount = 30;
    private int baseScore = 5;
    private int difficulty = StateManager.EASY;
    
    //status
    private boolean isPaused, isOver;
    private int score, time, wallAmount;
    
    public Singleplayer(StateManager sManager){
	super(sManager);
    }

    @Override
    public void init(){
	stateTick = 0;
	time = 0;
	score = 0;
	isOver = false;
	isPaused = false;
        wallAmount = Math.round(baseWallAmount*difficulty*0.60F);
	
        snake = new Snake(0,0);
        cherry = new Cherry();
        walls = new Walls(wallAmount, cherry.getLocation());
    }

    @Override
    public void update() {
	if(!isOver && !isPaused){
	    stateTick++;
            
	    if(stateTick%updateTick == 0){
		ArrayList<Point> blacklist = new ArrayList<>();
		blacklist.addAll(walls.getWalls());
		blacklist.addAll(snake.getSnakePoints());
                
		if(!snake.isCollision(blacklist)){
                    snake.move();
                    if(snake.getHead().equals(cherry.getLocation())){
                        score += baseScore*difficulty;
			snake.grow();
                        cherry = new Cherry(blacklist);
                    }
                }else isOver = true;
            }

            if(stateTick%wallRefreshTick == 0)
                walls = new Walls(wallAmount, cherry.getLocation());
	    
	    if(stateTick%(1000/Engine.TICK_RATE) == 0)
		time++;
        }
    }

    @Override
    public void draw(Graphics g) {
	g.setColor(backgroung);
	g.fillRect(0, 0, Renderer.WIDTH, Renderer.HEIGHT);
	
	snake.draw(g);
	cherry.draw(g);
	walls.draw(g);
	
	g.setFont(scoreFont);
	g.setColor(snake.getBaseColor());
	g.drawString("Score: " + score, 10, 20);
	g.drawString("Length: " + snake.getTailLenght(), 10, 40);
	g.setColor(overColor);
	g.drawString("Time: " + time, 10, Renderer.HEIGHT-10);
	
	g.setFont(warningFont);
        if(isOver){
            g.setColor(overColor);
	    g.drawString(overMsg, Renderer.WIDTH/2-g.getFontMetrics(warningFont).stringWidth(overMsg)/2, 250);
        }else if(isPaused){
	    g.setColor(pauseColor);
	    g.drawString(pauseMsg, Renderer.WIDTH/2-g.getFontMetrics(warningFont).stringWidth(pauseMsg)/2, 250);
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
		if(!isOver)
		    isPaused = !isPaused;
		break;
	    case KeyEvent.VK_ESCAPE:
		this.init();
		isPaused = false;
		break;
	}
    }

    @Override
    public void keyReleased(KeyEvent e){}

    @Override
    public void mousePressed(MouseEvent e){}

    @Override
    public void setPaused(boolean isPaused){this.isPaused = isPaused;}
    
    public void setDifficulty(int difficulty){this.difficulty = difficulty;}
    
    public int getScore(){return score;}
    public int getTime(){return time;}

}
