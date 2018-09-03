package com.iinmorus.frame;

import com.iinmorus.engine.Engine;
import com.iinmorus.engine.Game;
import com.iinmorus.engine.Settings;
import com.iinmorus.engine.State;
import com.iinmorus.gtc.GTC;
import com.iinmorus.gtc.state.GTCState;
import com.iinmorus.gtc.state.Singleplayer;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GameWindow extends JFrame{

    private Engine engine;
    
    public GameWindow(){
	engine = new Engine(new GTC());

	this.init();
    }
    
    private void init(){
	this.setTitle("GTC!");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setResizable(false);
	this.setContentPane(engine.renderer);

	JMenuBar menu = new JMenuBar();
	    JMenu game = new JMenu("Game");
		JMenu start = new JMenu("Start");
		    JMenuItem single = new JMenuItem("Single Player");
		    JMenuItem bot = new JMenuItem("Player vs Bot");
		    JMenuItem multi = new JMenuItem("Player vs Player");
		    start.add(single);
		    start.addSeparator();
		    start.add(bot);
		    start.add(multi);
		JMenuItem pause = new JMenuItem("Pause");
		JMenuItem load = new JMenuItem("Load");
		load.setEnabled(false);
		JMenuItem save = new JMenuItem("Save");
		save.setEnabled(false);
		game.add(start);
		game.add(pause);
		game.addSeparator();
		game.add(load);
		game.add(save);
	menu.add(game);
	
	this.setJMenuBar(menu);

	this.pack();
	
	engine.start();

	/////EXEMPLOS:
//	GAME.states.getState(GameState.SINGLE, Singleplayer.class).getScore();		    //Pega score da partida singleplayer
//	GAME.states.startState(GameState.SINGLE);					    //Começa partida
//	GAME.states.getState(GameState.SINGLE).setPaused(true);				    //Pausa partida
//	GAME.states.getState(GameState.MULT, Multiplayer.class).vsBot(true);		    //Liga bot na partida multiplayer
//	GAME.states.getState(GameState.SINGLE).setDifficulty(GameState.HARD);		    //Muda dificuldade da partida
//	GAME.states.getState(GameState.MULT, Multiplayer.class).getScore_P1();		    //Pega score do player 1 da partida multiplayer	    
//	GAME.states.loadState(/*loaded state from file*/);				    //Carrega partida salva
        
	/////COMEÇA UMA PARTIDA EASY SINGLEPLAYER QUE COMEÇA PAUSADA:
//	State s = engine.states.getState(GTC.SINGLE);
//	s.setDifficulty(GTCState.EASY);
//	engine.states.startState(GTC.SINGLE);
//	s.setPaused(true);

	/////COMEÇA UMA PARTIDA MEDIUM SINGLEPLAYER
//	engine.states.getState(GTC.SINGLE).setDifficulty(GTCState.MEDIUM);
//	engine.states.startState(GTC.SINGLE);

	/////COMEÇA UMA PARTIDA HARD MULTIPLAYER VS BOT
//	Multiplayer m = GAME.states.getState(GameState.MULT, Multiplayer.class);
//	m.setDifficulty(GameState.HARD);
//	m.vsBot(true);
//	GAME.states.startState(GameState.MULT);

	this.setVisible(true);
    
    }

    
}
