package com.iinmorus.multsnake.model;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

public class Cherry {
    
    private Color color = Color.RED;
    private final Point location;

    public Cherry() {
	Random random = new Random();
	location = new Point(random.nextInt(78), random.nextInt(65));
    }

    public Point getLocation() {
	return location;
    }

    public Color getColor() {
	return color;
    }
    
}
