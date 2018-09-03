package com.iinmorus.gtc.ui;

import com.iinmorus.engine.Game;
import com.iinmorus.engine.Settings;
import com.iinmorus.engine.State;
import com.iinmorus.gtc.state.GameState;
import com.iinmorus.gtc.state.Idle;
import com.iinmorus.gtc.state.Multiplayer;
import com.iinmorus.gtc.state.Singleplayer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GameWindow extends JFrame{
    
    public static final Game GAME;
    static{
	Settings s = new Settings.Builder().create();
	ArrayList<State> states = new ArrayList<>();
	states.add(new Idle());
	states.add(new Singleplayer());
	states.add(new Multiplayer());
	GAME = new Game(s, states);
    }

    private Thread gameThread;
    
    public GameWindow(){
	gameThread = new Thread(GAME);
	this.init();
    }
    
    private void init(){
	this.setTitle("GTC!");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//this.setSize(GAME.settings.width+6, GAME.settings.height+28); 
	this.setResizable(false);
	//this.setLocationRelativeTo(null);
	this.setContentPane(GAME);
	//this.add(engine.getRenderer());
	//this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	//this.setUndecorated(true);
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
	
	gameThread.start();
	try {
	    gameThread.join();
	} catch (InterruptedException ex) {
	    Logger.getLogger(GameWindow.class.getName()).log(Level.SEVERE, null, ex);
	}
	
	/////EXEMPLOS:
//	StateManager.getState(Game.SINGLE, Singleplayer.class).getScore();		    //Pega score da partida singleplayer
//	StateManager.startState(Game.SINGLE);						    //Começa partida
//	StateManager.getState(Game.SINGLE).setPaused(true);				    //Pausa partida
//	StateManager.getState(Game.MULT, Multiplayer.class).vsBot(true);		    //Liga bot na partida multiplayer
//	StateManager.getState(Game.SINGLE).setDifficulty(GameState.HARD);		    //Muda dificuldade da partida
//	StateManager.getState(Game.MULT, Multiplayer.class).getScore_P1();		    //Pega score do player 1 da partida multiplayer	    
//	StateManager.loadState(/*loaded state from file*/);				    //Carrega partida salva
        
	/////COMEÇA UMA PARTIDA EASY SINGLEPLAYER QUE COMEÇA PAUSADA:
//	State s = StateManager.getState(Game.SINGLE);
//	s.setDifficulty(GameState.EASY);
//	StateManager.startState(Game.SINGLE);
//	s.setPaused(true);

	/////COMEÇA UMA PARTIDA MEDIUM SINGLEPLAYER
//	StateManager.getState(Game.SINGLE).setDifficulty(GameState.MEDIUM);
//	StateManager.startState(Game.SINGLE);

	/////COMEÇA UMA PARTIDA HARD MULTIPLAYER VS BOT
//	Multiplayer m = StateManager.getState(Game.MULT, Multiplayer.class);
//	m.setDifficulty(GameState.HARD);
//	m.vsBot(true);
//	StateManager.startState(Game.MULT);

	this.setVisible(true);
    
    }

    
}
