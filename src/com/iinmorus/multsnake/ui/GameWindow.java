package com.iinmorus.multsnake.ui;

import com.iinmorus.engine.Engine;
import com.iinmorus.engine.StateManager;
import com.iinmorus.multsnake.state.GameStateFactory;
import com.iinmorus.multsnake.state.Multiplayer;
import com.iinmorus.multsnake.state.Singleplayer;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import com.iinmorus.engine.State;
import com.iinmorus.engine.TimedEvent;
import com.iinmorus.multsnake.state.GameState;

public class GameWindow extends JFrame{
    
    private final Engine engine;
    private final Thread gameThread;
    
    public GameWindow(){
	engine = new Engine(new GameStateFactory());
	gameThread = new Thread(engine);
	this.init();
    }
    
    private void init(){
	this.setTitle("GTC!");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//this.setSize(Renderer.WIDTH+6, Renderer.HEIGHT+28); 
	this.setResizable(false);
	//this.setLocationRelativeTo(null);
	this.setContentPane(engine.getRenderer());
	//this.add(engine.getRenderer());
	
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
	this.setVisible(true);
	
	/////EXEMPLOS:
//	StateManager.getState(GameStateFactory.SINGLE, Singleplayer.class).getScore();	    //Pega score da partida singleplayer
//	StateManager.startState(GameStateFactory.SINGLE);				    //Começa partida
//	StateManager.getState(GameStateFactory.SINGLE).setPaused(true);			    //Pausa partida
//	StateManager.getState(GameStateFactory.MULT, Multiplayer.class).vsBot(true);	    //Liga bot na partida multiplayer
//	StateManager.getState(GameStateFactory.SINGLE).setDifficulty(GameStates.HARD);	    //Muda dificuldade da partida
//	StateManager.getState(GameStateFactory.MULT, Multiplayer.class).getScore_P1();	    //Pega score do player 1 da partida multiplayer	    
//	StateManager.loadState(/*loaded state from file*/);				    //Carrega partida salva
        
	/////COMEÇA UMA PARTIDA EASY SINGLEPLAYER QUE COMEÇA PAUSADA:
//	State s = StateManager.getState(GameStateFactory.SINGLE);
//	s.setDifficulty(GameState.EASY);
//	StateManager.startState(GameStateFactory.SINGLE);
//	s.setPaused(true);

	/////COMEÇA UMA PARTIDA HARD MULTIPLAYER
//	Multiplayer m = StateManager.getState(GameStateFactory.MULT, Multiplayer.class);
//	m.setDifficulty(GameState.HARD);
//	m.vsBot(true);
//	StateManager.startState(GameStateFactory.MULT);

	////ADICIONA UM EVENTO QUE ACONTECE A CADA MINUTO:
//      TimedEvent te = new TimedEvent(){
//          @Override
//          public void doEvent() {
//              System.out.println("|TESTE| Every 1Min");
//          }
//      };
//      engine.EVERY_MINUTE.add(te);
    
    }
    
    public void startEngine(){
	gameThread.start();
    }
    
}
