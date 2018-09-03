package com.iinmorus.multsnake.ui;

import com.iinmorus.multsnake.bot.Bot;
import com.iinmorus.multsnake.engine.Engine;
import com.iinmorus.multsnake.engine.Renderer;
import com.iinmorus.multsnake.state.Multiplayer;
import com.iinmorus.multsnake.state.Singleplayer;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GameWindow extends JFrame{
    
    private final Engine engine;
    private final Thread gameThread;
    
    public GameWindow(){
	engine = new Engine();
	gameThread = new Thread(engine);
	this.init();
    }
    
    private void init(){
	this.setTitle("GTC");
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
	//engine.getStateManager().getState(1, Singleplayer.class).getScore();
	//engine.getStateManager().setState(1);
	//engine.getStateManager().getState(1, Singleplayer.class).setPaused(true);
	//engine.getStateManager().getState(2, Multiplayer.class).vsBot(Bot.EASY);
	//engine.getStateManager().getState(2, Multiplayer.class).getScore_P1();
	//engine.getStateManager().loadState(/*load state from file*/);
    }
    
    public void startEngine(){
	gameThread.start();
    }
    
}
