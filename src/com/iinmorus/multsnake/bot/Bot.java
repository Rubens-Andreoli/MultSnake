package com.iinmorus.multsnake.bot;


import com.iinmorus.multsnake.entity.Snake;
import java.awt.Point;
import java.util.ArrayList;

public abstract class Bot {
    
    protected Point goal;
    
    protected final Snake snake;
    protected int blindness = 0;
    
    public Bot(Snake snake){
	this.snake = snake;
    }
    
    public void setBlindness(int blindness){
	this.blindness = blindness;
    }
    
    public void changeGoal(Point goal){
	this.goal = goal;
    }
    
    public abstract void control(ArrayList<Point> avoidPoints);
    
    
}
