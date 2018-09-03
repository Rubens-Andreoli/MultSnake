package com.iinmorus.engine;

import java.awt.Graphics2D;
import java.util.HashMap;

public abstract class StateManager{

    private static HashMap<String, State> states;
    private static String currentState;
    
    private static StateFactory stateFactory;

    public static void init(StateFactory factory){
	states = new HashMap<>();
	stateFactory = factory;
	
	factory.getStateSet().stream().forEach((stateID) -> {
	    State state = factory.createState(stateID);
	    state.loadResources();
	    states.put(stateID, state);
	});
	
	if(!states.isEmpty()){
	    states.get(factory.getStartStateID()).init();
	    currentState = factory.getStartStateID();
	}
    }
    
    public static void startState(String stateID){
	assert(states.containsKey(stateID)): "State "+stateID+" not loaded";
	states.get(stateID).init();
	currentState = stateID;
    }

    public static void resumeState(String stateID){
	assert(states.containsKey(stateID)): "State "+stateID+" not loaded";
	currentState = stateID;
    }
    
    public static void loadState(State state){
	String stateID = state.getStateID();
	state.loadResources();
	states.put(stateID, state);
	currentState = stateID;
    }
    
    public static void resetState(String stateID){
	State state = stateFactory.createState(stateID);
	assert(state != null) : "State "+stateID+" can't be created by loaded StateFactory";
	states.replace(stateID, state);
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
