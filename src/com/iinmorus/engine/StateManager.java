package com.iinmorus.engine;

import static com.iinmorus.engine.StateManager.LoadBehavior.REGISTER;
import static com.iinmorus.engine.StateManager.LoadBehavior.START;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class StateManager{

    public static enum LoadBehavior{
	MANUAL, REGISTER, START;
    }

    private LoadBehavior loadBehavior;
    private final HashMap<String, State> states;
    private String currentState;
 
    public StateManager(Engine engine){
	states = new HashMap<>();
	this.loadBehavior = engine.settings.loadBehavior;
    }

    public void loadResources(String stateID){
	if(!states.containsKey(stateID)) return;
	states.get(stateID).loadResources();
    }
    
    public void registerState(State state){
	String stateID = state.getStateID();
	if(!states.containsKey(stateID)){
	    states.put(stateID, state);
	    if(loadBehavior == REGISTER) loadResources(stateID);
	}
    }
    
    public void startState(String stateID){
	if(!states.containsKey(stateID)) return;
	if(loadBehavior == START) loadResources(stateID);
	if(currentState != null) states.get(currentState).unload();
	states.get(stateID).start();
	currentState = stateID;
    }
    
    public void readState(File file) throws IOException, ClassNotFoundException{
	if(!file.exists()) return;
	try(FileInputStream fileInput = new FileInputStream(file);
		ObjectInputStream objectInput = new ObjectInputStream(fileInput);) {
	    State state = (State)objectInput.readObject();
	    String stateID = state.getStateID();
	    if(!states.containsKey(stateID)){
		registerState(state);
	    }else{
		states.put(stateID, state);
	    }
	}
    }

    public void resumeState(String stateID){
	if(!states.containsKey(stateID)) return;
	if(currentState != null) states.get(currentState).unload();
	currentState = stateID;
    }
    
    public void removeState(String stateID){
	if(!states.containsKey(stateID)) return;
	if(currentState.equals(stateID)) return;
	states.remove(stateID);
    }
    
    public <T extends State> T getState(String stateID, Class<T> type){
	assert(states.get(stateID).getClass().isInstance(type)): "State "+stateID+" must be instance of class "+type;
	return type.cast(states.get(stateID));
    }
    
    public State getState(String stateID){
	return states.get(stateID);
    }
    
    public <T extends State> T getCurrentState(Class<T> type){
	assert(states.get(currentState).getClass().isInstance(type)): "Current state must be instance of class "+type;
	return type.cast(states.get(currentState));
    }
    
    public State getCurrentState(){
	return states.get(currentState);
    }
    
    protected void update(){
	if(currentState != null) states.get(currentState).update();
    }
    
    protected void draw(Graphics2D g){
	if(currentState != null) states.get(currentState).draw(g);
    }
    
}
