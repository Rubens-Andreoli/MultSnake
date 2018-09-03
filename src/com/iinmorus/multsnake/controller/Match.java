package com.iinmorus.multsnake.controller;

import com.iinmorus.multsnake.model.Cherry;
import com.iinmorus.multsnake.model.Snake;
import java.awt.Point;

public class Match {
    
    private Renderer renderer;
    
    private int ticks, time;
    private boolean over, paused;
    private int score;
    
    private Snake snake;
    private Cherry cherry;
    
    private FastBot bot;
    
    private boolean changeCherry;

    public Match(){
	
	over = false;
	paused = false;
	ticks = 0;
	time = 0;
	score = 0;
	
	snake = new Snake();
	cherry = new Cherry();
	bot = new FastBot(snake);
	bot.changeGoal(cherry);
    }
    
    public void toLoop(){
	ticks++;
	if(!over) time++;
	if(ticks%2 == 0 && !over && !paused){
	    bot.control();
	    //System.out.println("COLISION: "+ snake.checkCollision());
	    if(!snake.checkCollision()) snake.move();
	    else over = true;
	    
	    if(cherry != null){
		if(snake.getHead().x == cherry.getLocation().x && snake.getHead().y == cherry.getLocation().y){
		    score+=10;
		    snake.grow();
		    do{
			cherry = new Cherry();
		    }while(!checkCherry());
		    bot.changeGoal(cherry);
		    changeCherry = true;
		}else{
		    changeCherry = false;
		}
	    }
	}
    }
    
    private boolean checkCherry(){
	for (Point point : snake.getSnakeParts()){
	    if (point.x == cherry.getLocation().x && point.y == cherry.getLocation().y)
		return false;
	}
	return true;
    }

    public boolean isChangeCherry() {
	return changeCherry;
    }

    public void addScore(){
	this.score += 10;
    }

    public Snake getSnake() {
	return snake;
    }

    public Cherry getCherry() {
	return cherry;
    }

    public int getTime() {
	return time;
    }

}
