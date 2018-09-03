package com.iinmorus.engine;

import java.awt.Graphics2D;
import java.util.HashMap;

public abstract class StateManager{

    private static HashMap<String, State> states;
    private static String currentState;
    
    public static void init(){
	states = new HashMap<>();
    }
    
    public static void startState(String stateID){
	if(!states.containsKey(stateID)) return;
	if(currentState != null) states.get(currentState).setPaused(true);
	states.get(stateID).start();
	currentState = stateID;
    }

    public static void resumeState(String stateID){
	if(!states.containsKey(stateID)) return;
	if(currentState != null) states.get(currentState).setPaused(true);
	currentState = stateID;
    }
    
    public static void loadState(State state){
	String stateID = state.getStateID();
	state.loadResources();
	states.put(stateID, state);
    }
    
    public static void removeState(String stateID){
	if(!states.containsKey(stateID)) return;
	states.remove(stateID);
    }
    
    public static <T extends State> T getState(String stateID, Class<T> type){
	assert(states.get(stateID).getClass().isInstance(type)): "State "+stateID+" must be instance of class "+type;
	return type.cast(states.get(stateID));
    }
    
    public static State getState(String stateID){
	return states.get(stateID);
    }
    
    public static <T extends State> T getCurrentState(Class<T> type){
	assert(states.get(currentState).getClass().isInstance(type)): "Current state must be instance of class "+type;
	return type.cast(states.get(currentState));
    }
    
    public static State getCurrentState(){
	return states.get(currentState);
    }
    
    public static void update(){
	if(currentState != null) states.get(currentState).update();
    }
    
    public static void draw(Graphics2D g){
	if(currentState != null) states.get(currentState).draw(g);
    }
    
}
