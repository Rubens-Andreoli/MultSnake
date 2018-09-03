package br.unip.engine;

import static br.unip.engine.StateManager.LoadStrategy.REGISTER;
import static br.unip.engine.StateManager.LoadStrategy.START;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class StateManager{

    public enum LoadStrategy{
	MANUAL, REGISTER, START;
    }
    
    private final LoadStrategy loadStrategy;
    private final HashMap<String, State> states;
    private String currentState;

    protected StateManager(final LoadStrategy loadStrategy){
        states = new HashMap<String, State>();
        this.loadStrategy = loadStrategy;
    }

    public void loadResources(String stateID){
	if(!states.containsKey(stateID)) return;
	states.get(stateID).loadResources();
    }
    
    public void registerState(State state){
	String stateID = state.getStateID();
	if(!states.containsKey(stateID)){
	    states.put(stateID, state);
	    if(loadStrategy == REGISTER) loadResources(stateID);
	}else{
	    states.put(stateID, state);
	}
    }
    
    public void startState(String stateID){
	if(!states.containsKey(stateID)) return;
	if(loadStrategy == START) loadResources(stateID);
	if(currentState != null) states.get(currentState).setPaused(true);
	states.get(stateID).start();
	currentState = stateID;
    }
    
    public void readState(File file) throws IOException, ClassNotFoundException{
	if(!file.exists()) return;
	try(FileInputStream fileInput = new FileInputStream(file);
		ObjectInputStream objectInput = new ObjectInputStream(fileInput);) {
	    registerState((State)objectInput.readObject());
	}
    }

    public void resumeState(String stateID){
	if(!states.containsKey(stateID)) return;
	if(currentState != null) states.get(currentState).setPaused(true);
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
