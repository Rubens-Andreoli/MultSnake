package com.iinmorus.engine;

import com.iinmorus.engine.StateManager.LoadBehavior;
import java.awt.Dimension;
import java.awt.Toolkit;

public class Settings {
    public final int tickRate;

    public final int height;
    public final int width;
    public final boolean antialiasing;
	
    public final float loadVolume;
    public final boolean mute;
    
    public final boolean listenMouse;
    public final boolean listenKey;
    
    public final LoadBehavior loadBehavior;
    
    private Settings(Builder builder){
	this.tickRate = builder.tickRate;
	this.height = builder.height;
	this.width = builder.width;
	this.antialiasing = builder.antialiasing;
	this.loadVolume = builder.loadVolume;
	this.mute = builder.mute;
	this.listenMouse = builder.mouse;
	this.listenKey = builder.keyboard;
	this.loadBehavior = builder.loadBehavior;
    }
 
    public static class Builder{
	private int tickRate = 15;
	
	private int height = 600;
	private int width = 700;
	private boolean antialiasing = true;
	
	private float loadVolume = 0.7F;
	private boolean mute = false;
	
	private boolean mouse = true;
	private boolean keyboard = true;
	
	private LoadBehavior loadBehavior = LoadBehavior.REGISTER;


	public Builder setTickRate(int tickRate){
	    if(tickRate > 0) this.tickRate = tickRate;
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

	public Builder isAntialiasing(boolean b){
	    antialiasing = b;
	    return this;
	}
	
	public Builder setLoadVolume(float volume){
	    loadVolume = volume;
	    return this;
	}
	
	public Builder isMute(boolean b){
	    mute = b;
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
	
	public Builder setLoadResources(LoadBehavior loadBehavior){
	    this.loadBehavior = loadBehavior;
	    return this;
	}
	
	public Settings create(){
	    return new Settings(this);
	}
	
    }
    
}
