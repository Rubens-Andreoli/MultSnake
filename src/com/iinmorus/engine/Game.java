package com.iinmorus.engine;

import java.util.List;

public class Game {
    
    public final String name;
    public final Settings settings;
    public final List<State> states;
    public final String startStateID;

    public Game(String name, Settings settings, List<State> states, String startStateID) {
	this.name = name;
	this.settings = settings;
	this.states = states;
	this.startStateID = startStateID;
    }
    
    
    
}
