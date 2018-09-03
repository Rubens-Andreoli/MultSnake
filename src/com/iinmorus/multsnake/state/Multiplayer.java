package com.iinmorus.multsnake.state;

import com.iinmorus.multsnake.bot.Bot;
import com.iinmorus.multsnake.bot.FastBot;
import com.iinmorus.multsnake.bot.PreciseBot;
import com.iinmorus.multsnake.bot.SlowBot;
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

public class Multiplayer extends State{
    
    //ui
    private Color backgroung = Color.BLACK;
    private Color overColor = Color.RED;
    private Color pauseColor = Color.YELLOW;
    private Font scoreFont = new Font(Font.MONOSPACED, Font.PLAIN, 20);
    private String overMsg = "GAME OVER";
    private String pauseMsg = "PAUSED";
    private Font warningFont = new Font(Font.MONOSPACED, Font.BOLD, 100);
    
    //entities
    private Snake snake_P2;
    private Snake snake_P1;
    private Cherry cherry;
    private Walls walls;
    private Bot bot;
    
    //configs
    private int wallAmount = 30;
    private int updateTick = 3;
    private int wallRefreshTick = 600;
    private int botDifficulty = 0;
    
    //status
    private boolean isPaused, isOver, isBot;
    private int score_P1, score_P2, time;

    public Multiplayer(StateManager sManager){
	super(sManager);
    }

    @Override
    public void init(){
	isBot = true; //REMOVE: testing...
	botDifficulty = 1;
	
	isOver = false;
	stateTick = 0;
	time = 0;
	isPaused = false;
	score_P1 = 0;
	score_P2 = 0;
	
        snake_P1 = new Snake(0,0);
	snake_P2 = new Snake(Renderer.WIDTH/Renderer.SCALE-1, 0);
	snake_P2.setBaseColor(new Color(112, 219, 112));
        cherry = new Cherry();
        walls = new Walls(wallAmount, cherry.getLocation());
	
	if(isBot){
	    switch(botDifficulty){
		case Bot.EASY:
		    bot = new SlowBot(snake_P2);
		    break;
		case Bot.MEDIUM:
		    bot = new PreciseBot(snake_P2);
		    break;
		case Bot.HARD:
		    bot = new FastBot(snake_P2);
		    break;
	    }
	    bot.changeGoal(cherry.getLocation());
	}
    }

    @Override
    public void update(){
	if(!isOver && !isPaused){
	    stateTick++;
            
	    if(stateTick%updateTick == 0){
		ArrayList<Point> blacklist = new ArrayList<>();
		blacklist.addAll(walls.getWalls());
		blacklist.addAll(snake_P1.getSnakePoints());
		blacklist.addAll(snake_P2.getSnakePoints());
                
		if(isBot) bot.control(blacklist);
		
//		if(snake_P1.isCollision(blacklist)){
//		    if(score_P1 - 5 <= 0) isOver = true;
//		    else score_P1-=5;
//		}
//		
//		if(snake_P2.isCollision(blacklist)){
//		    if(score_P2 - 5 <= 0) isOver = true;
//		    else score_P2-=5;
//		}
		
//		if(!isOver){
		if(!snake_P1.isCollision(blacklist) && !snake_P2.isCollision(blacklist)){
                    snake_P1.move();
		    snake_P2.move();
                    
		    boolean isP1 = false;
		    if((isP1 = snake_P1.getHead().equals(cherry.getLocation())) || snake_P2.getHead().equals(cherry.getLocation())){
			if(isP1){
			    score_P1+=10;
			    this.applyEffect(snake_P1);
			}else{
			    score_P2+=10;
			    this.applyEffect(snake_P2);
			}
                        cherry = new Cherry(blacklist);
			if(isBot) bot.changeGoal(cherry.getLocation());
                    }
		    
                }else isOver = true;
            }

            if(stateTick%wallRefreshTick == 0)
                walls = new Walls(wallAmount, cherry.getLocation());
	    
	    if(stateTick%(1000/Engine.FPS) == 0)
		time++;
        }
    }

    @Override
    public void draw(Graphics g){
	g.setColor(backgroung);
	g.fillRect(0, 0, Renderer.WIDTH, Renderer.HEIGHT);
	
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
	g.drawString("Time: " + time, 10, Renderer.HEIGHT-10);
        
	g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 100));
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
		if(!isOver)
		    isPaused = !isPaused;
		break;
	    case KeyEvent.VK_ESCAPE:
		this.init();
		isPaused = false;
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
    public void setPaused(boolean isPaused){this.isPaused = isPaused;}

    public void vsBot(int dificulty){
	isBot = true;
	botDifficulty = dificulty;
    }
    
    public int getScore_P1(){return score_P1;}
    public int getScore_P2(){return score_P2;}
    public int getTime(){return time;}  

}
