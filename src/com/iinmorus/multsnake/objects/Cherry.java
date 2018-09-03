package com.iinmorus.multsnake.objects;

import com.iinmorus.multsnake.engine.Config;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

public class Cherry implements Drawable{
    
    private static final int LAYER = 0;
    
    public static final int GROW=1, SHRINK=2, SPRINT=3, IMUNE=4;
    
    private Color color = Color.RED;
    private final Point location;
    private final int effect;


    public Cherry() {
	Random random = new Random();
	location = new Point(random.nextInt(Config.WIDTH/Config.SCALE), random.nextInt(Config.HEIGHT/Config.SCALE));
	effect = random.nextInt(5);
    }

    @Override
    public void draw(Graphics g) {
	g.setColor(color);
	g.fillRect(location.x*Config.SCALE, location.y*Config.SCALE, Config.SCALE, Config.SCALE);
    }

    @Override
    public int getLayer() {
	return LAYER;
    }

    public Point getLocation() {
	return location;
    }

    public void setColor(Color color) {
	this.color = color;
    }

    public int getEffect() {
	return effect;
    }

}
