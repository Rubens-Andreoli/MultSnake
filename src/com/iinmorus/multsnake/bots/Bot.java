package com.iinmorus.multsnake.bots;

import com.iinmorus.multsnake.objects.Cherry;
import com.iinmorus.multsnake.objects.Snake;
import java.awt.Point;
import java.util.ArrayList;

public abstract class Bot {
    
    protected Point point;
    protected final Snake snake;
    protected int blindness = 0;
    
    public Bot(Snake snake){
	this.snake = snake;
    }
    
    public void setBlindness(int blindness) {
	this.blindness = blindness;
    }
    
    public void changeGoal(Point point){
	this.point = point;
    }
    
    public abstract void control(ArrayList<Point> avoidPoints);
    
}
