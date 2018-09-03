package com.iinmorus.multsnake.engine;

import com.iinmorus.multsnake.bots.Bot;
import com.iinmorus.multsnake.bots.FastBot;
import com.iinmorus.multsnake.bots.SlowBot;
import com.iinmorus.multsnake.objects.Cherry;
import com.iinmorus.multsnake.objects.Drawable;
import com.iinmorus.multsnake.objects.Snake;
import com.iinmorus.multsnake.objects.Walls;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

public class Engine implements Runnable, ActionListener, KeyListener, MouseListener{
  
    private final Timer timer = new Timer(20, this);
    private Random random = new Random();
    private long ticks;
    
    private Renderer renderer;
    
    private ArrayList<Drawable> objects;
    private Walls walls;
    private Cherry cherry;
    private Snake snake_01;
    private Snake snake_02;
    
    private Bot bot_01;
    private Bot bot_02;
    
    public Engine(){
	this.renderer = new Renderer();
	this.objects = new ArrayList<>();
    }

    @Override
    public void run() {
        renderer.addKeyListener(this);
        renderer.addMouseListener(this);
        
	snake_01 = new Snake(0, 0);
	snake_02 = new Snake(Config.WIDTH/Config.SCALE-1, 0);
	cherry = new Cherry();
	walls = new Walls(40, cherry.getLocation());
	
	snake_02.setBaseColor(new Color(112, 219, 112));
	//snake_03.setBaseColor(new Color(255, 173, 51));
	
	bot_01 = new FastBot(snake_01);
	bot_01.changeGoal(cherry.getLocation());
	bot_02 = new FastBot(snake_02);
	bot_02.changeGoal(cherry.getLocation());
	
	objects.add(snake_01);
	objects.add(snake_02);
	objects.add(cherry);
	objects.add(walls);
	
	renderer.setDrawables(objects);

	timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	ticks++;
//	System.out.println("RUNNING TICK: "+ ticks);
	
//	if(ticks%50 == 0){
//	    bot_01.setBlindness(random.nextInt(3));
//	}

	if(ticks%500 == 0){
	    this.objects.remove(walls);
	    walls = new Walls(40, cherry.getLocation());
	    objects.add(walls);
	}
	
	//TODO: testing FastBot control every tick, seems more natural:
//	ArrayList<Point> blackList = new ArrayList<>();
//	blackList.addAll(walls.getWalls());
//	blackList.addAll(snake_01.getSnakePoints());
//	blackList.addAll(snake_02.getSnakePoints());
//	bot_01.control(blackList);
	    
	if(ticks%2 == 0){
	    
	    ArrayList<Point> blackList = new ArrayList<>();
	    blackList.addAll(walls.getWalls());
	    blackList.addAll(snake_01.getSnakePoints());
	    blackList.addAll(snake_02.getSnakePoints());

	    bot_01.control(blackList);
	    bot_02.control(blackList);
            bot_02.control(blackList);

	    if(snake_01.isCollision(walls.getWalls()) || snake_01.isCollision(snake_02.getSnakePoints())){ //TODO: if not collision then do stuff...
		System.out.println("COLLISION SNAKE 01");
//		timer.stop();
	    }else{
                snake_01.move();
            }
	    
	    if(snake_02.isCollision(walls.getWalls()) || snake_02.isCollision(snake_01.getSnakePoints())){
		System.out.println("COLLISION SNAKE 02");
//		timer.stop();
	    }else{
                snake_02.move();
            }
	    
	    if(cherry != null){
		if(snake_01.getHead().equals(cherry.getLocation())){
		    this.objects.remove(cherry);
		    switch(cherry.getEffect()){
			case Cherry.GROW:
			    snake_01.grow();
			    break;
			case Cherry.SHRINK:
			    snake_01.shrink();
			    break;  
		    }  
		    cherry = new Cherry();
		    //bot_01.changeGoal(cherry);
		    bot_02.changeGoal(cherry.getLocation());
		    objects.add(cherry);
		    System.out.println("CHERRY - "+cherry.getLocation().x+":"+cherry.getLocation().y);
		}
		if(snake_02.getHead().equals(cherry.getLocation())){
		    this.objects.remove(cherry);
		    switch(cherry.getEffect()){
			case Cherry.GROW:
			    snake_02.grow();
			    break;
			case Cherry.SHRINK:
			    snake_02.shrink();
			    break;  
		    }  
		    cherry = new Cherry();
		    //bot_01.changeGoal(cherry);
		    bot_02.changeGoal(cherry.getLocation());
		    objects.add(cherry);
		    System.out.println("CHERRY - "+cherry.getLocation().x+":"+cherry.getLocation().y);
		}
	    }
            
	}
	
	renderer.setDrawables(objects);

	renderer.repaint();
    }

    public Renderer getRenderer() {
	return renderer;
    }

    @Override
    public void keyPressed(KeyEvent e) {
	int key = e.getKeyCode();
	
	switch (key) {
	    case KeyEvent.VK_LEFT:
		snake_01.changeDirection(Snake.LEFT);
		break;
	    case KeyEvent.VK_RIGHT:
		snake_01.changeDirection(Snake.RIGHT);
		break;
	    case KeyEvent.VK_UP:
		snake_01.changeDirection(Snake.UP);
		break;
	    case KeyEvent.VK_DOWN:
		snake_01.changeDirection(Snake.DOWN);
		break;
	}
	
	switch (key) {
	    case KeyEvent.VK_A:
		snake_02.changeDirection(Snake.LEFT);
		break;
	    case KeyEvent.VK_D:
		snake_02.changeDirection(Snake.RIGHT);
		break;
	    case KeyEvent.VK_W:
		snake_02.changeDirection(Snake.UP);
		break;
	    case KeyEvent.VK_S:
		snake_02.changeDirection(Snake.DOWN);
		break;
	}
	
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point point = new Point(e.getPoint().x/Config.SCALE, e.getPoint().y/Config.SCALE);
        System.out.println(point.x+":"+point.y);
        bot_01.changeGoal(point);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
