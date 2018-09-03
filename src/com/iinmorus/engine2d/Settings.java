package com.iinmorus.engine2d;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Settings {
    public final int fps;
    public final int tickRate;
    public final int updateTick;
    public final int updateRate;

    public final int height;
    public final int width;
    public final boolean fullscreen;
    public final boolean antialiasing;
	
    public final float loadVolume;
    public final boolean mute;
    
    public final boolean mouse;
    public final boolean keyboard;
    
    public final int loadBehaviour;
    
    private Settings(Builder builder){
	this.fps = builder.fps;
	this.tickRate = (1000/builder.fps);
	this.updateTick = builder.updateTick;
	this.updateRate = this.tickRate*builder.updateTick;

	this.fullscreen = builder.fullscreen;
	if(!builder.fullscreen){
	    this.height = builder.height;
	    this.width = builder.width;
	}else{
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    width = (int) screenSize.getWidth();
	    height = (int) screenSize.getHeight();
	}
	this.antialiasing = builder.antialiasing;
	
	this.loadVolume = builder.loadVolume;
	this.mute = builder.mute;
	this.mouse = builder.mouse;
	
	this.keyboard = builder.keyboard;
	
	this.loadBehaviour = builder.loadBehaviour;
    }
 
    public static class Builder{
	private int fps = 60;
	private int updateTick = 4;
	
	private int height = 600;
	private int width = 700;
	private boolean fullscreen = false;
	private boolean antialiasing = true;
	
	private float loadVolume = 0.7F;
	private boolean mute = false;
	
	private boolean mouse = true;
	private boolean keyboard = true;
	
	private int loadBehaviour = StateManager.REGISTER_LOAD;
	
	public Builder setFPS(int fps){
	    if(fps > 0 && fps < 120) this.fps = fps;
	    return this;
	}
	
	public Builder setUpdateTick(int updateTick){
	    if(updateTick > 0) this.updateTick = updateTick;
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
	
	public Builder isFullscreen(boolean b){
	    fullscreen = b;
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
	
	public Builder setLoadResources(int loadBehaviour){
	    this.loadBehaviour = loadBehaviour;
	    return this;
	}
	
	public Settings create(){
	    return new Settings(this);
	}
	
    }
    
}
