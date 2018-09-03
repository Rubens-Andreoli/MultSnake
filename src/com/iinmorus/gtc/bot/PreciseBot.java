package com.iinmorus.gtc.bot;

import com.iinmorus.gtc.entity.Snake;
import java.awt.Point;
import java.util.ArrayList;

public class PreciseBot extends FastBot{
    
    public PreciseBot(Snake snake) {
	super(snake);
    }

    @Override
    public void control(ArrayList<Point> avoidPoints) {
	super.control(avoidPoints);
	super.control(avoidPoints);
    }
    
    
    
}
