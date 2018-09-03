package com.iinmorus.engine;

import com.iinmorus.engine.StateManager.LoadStrategy;
import java.awt.Color;

public class Settings {
    public final int tickRate;

    public final int height;
    public final int width;
    public final int scale;
    public final Color backgroundColor;
    public final boolean antialiasing;
	
    public final float loadVolume;
    
    public final boolean listenMouse;
    public final boolean listenKey;
    
    public final LoadStrategy loadStrategy;
    
    private Settings(Builder builder){
	this.tickRate = builder.tickRate;
	this.height = builder.height;
	this.width = builder.width;
	this.scale = builder.scale;
	this.backgroundColor = builder.backgroundColor;
	this.antialiasing = builder.antialiasing;
	this.loadVolume = builder.loadVolume;
	this.listenMouse = builder.mouse;
	this.listenKey = builder.keyboard;
	this.loadStrategy = builder.loadBehavior;
    }

    public static class Builder{
	private int tickRate = 15;
	
	private int height = 60;
	private int width = 70;
	private int scale = 10;
	private Color backgroundColor = Color.BLACK;
	private boolean antialiasing = true;
	
	private float loadVolume = 0.7F;
	
	private boolean mouse = true;
	private boolean keyboard = true;
	
	private LoadStrategy loadBehavior = LoadStrategy.REGISTER;


	public Builder setTickRate(int tickRate){
	    if(tickRate > 0) this.tickRate = tickRate;
	    else tickRate = 1;
	    return this;
	}
	
	public Builder setHeight(int height){
	    this.height = height;
	    return this;
	}
	
	public Builder setWidth(int width){
	    this.width = width;
	    return this;
	}
	
	public Builder setScale(int scale){
	    if(scale > 0) this.scale = scale;
	    else scale = 1;
	    return this;
	}
	
	public Builder setBackgroundColor(Color color){
	    backgroundColor = color;
	    return this;
	}

	public Builder isAntialiasing(boolean b){
	    antialiasing = b;
	    return this;
	}
	
	public Builder setLoadVolume(float volume){
	    loadVolume = volume;
	    return this;
	}
	
	public Builder isMouse(boolean b){
	    mouse = b;
	    return this;
	}
	
	public Builder isKeyboard(boolean b){
	    keyboard = b;
	    return this;
	}
	
	public Builder setLoadResources(LoadStrategy loadBehavior){
	    this.loadBehavior = loadBehavior;
	    return this;
	}
	
	public Settings create(){
	    return new Settings(this);
	}
	
    }
    
}
