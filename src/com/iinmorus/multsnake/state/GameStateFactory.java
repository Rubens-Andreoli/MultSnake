package com.iinmorus.multsnake.state;

import com.iinmorus.engine.State;
import com.iinmorus.engine.StateFactory;
import java.util.HashSet;
import java.util.Set;

public class GameStateFactory implements StateFactory{

    public static final String IDLE="idle", SINGLE="sigle", MULT="mult";
    
    @Override
    public State createState(String stateID) {
	switch (stateID){
	    case IDLE:
		return new Idle();
	    case SINGLE:
		return new Singleplayer();
	    case MULT:
		return new Multiplayer();
	    default:
		return null;
	}
    }

    @Override
    public Set<String> getStateSet() {
	HashSet<String> states = new HashSet<>();
	states.add(IDLE);
	states.add(SINGLE);
	states.add(MULT);
	return states;
    }

    @Override
    public String getStartStateID() {
	return IDLE;
    }

    
}
