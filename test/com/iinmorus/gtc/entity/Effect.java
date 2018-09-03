package com.iinmorus.gtc.entity;



import com.iinmorus.gtc.entity.*;

public enum Effect {
    GROW(1, 4), SHRINK(5, 8), IMUNE(8, 10);

    public static final int dice = 10;
    public final int min;
    public final int max;

    Effect(int min, int max) {
	this.min = min;
	this.max = max;
    }

    private static Effect getEffect(int roll) {
	for (Effect e : Effect.values())
	    if (roll > e.min && roll <= e.max)
		return e;
	return GROW;
    }
    
    public static void applyEffect(Snake snake, Cherry cherry){
	switch(getEffect(cherry.getEffect())){
	    case GROW:
		snake.grow();
		break;
	    case SHRINK:
		snake.shrink();
		break;
	    case IMUNE:
		//snake.imune();
		break;
	}
    }
}
