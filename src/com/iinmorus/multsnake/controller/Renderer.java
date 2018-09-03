package com.iinmorus.multsnake.controller;

import com.iinmorus.multsnake.model.Cherry;
import com.iinmorus.multsnake.model.Snake;
import com.iinmorus.multsnake.view.Window;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Renderer extends JPanel{

    public static final int SCALE = 10;
    private static Color backgroundColor = Color.BLACK;
    private Snake snake;
    private Cherry cherry; 
    
    public void addPlayer(Snake snake){
	this.snake = snake;
    }
    
    public void addCherry(Cherry cherry){
	this.cherry = cherry;
    }

    @Override
    protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	g.setColor(backgroundColor);
	g.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);
	
	if(snake != null){
	    g.setColor(snake.getHeadColor());
	    g.fillRect(snake.getHead().x*SCALE, snake.getHead().y*SCALE, SCALE, SCALE);
	    
	    g.setColor(snake.getSnakeColor());
	    snake.getSnakeParts().stream().forEach((point) -> {
		g.fillRect(point.x*SCALE, point.y*SCALE, SCALE, SCALE);
	    });
	}
	
	if(cherry != null){
	    g.setColor(cherry.getColor());
	    g.fillRect(cherry.getLocation().x*SCALE, cherry.getLocation().y*SCALE, SCALE, SCALE);
	}
		
//	String string = "Score: " + snake.score + "   -   Length: " + snake.tailLenght + "   -   Time: "+ snake.time/2/20;
//	g.setColor(Color.WHITE);
//	g.drawString(string, 10, 20);
    }
 
}
