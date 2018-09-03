package com.iinmorus.multsnake.ui;

import com.iinmorus.multsnake.engine.Config;
import com.iinmorus.multsnake.engine.Engine;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GameWindow extends JFrame {

    private final Engine engine;
    private final Thread gameThread;
    
    public GameWindow(){
	this.engine = new Engine();
	this.gameThread = new Thread(engine);
	this.configFrame();
    }
    
    private void configFrame(){
    	this.setTitle("Snake");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setSize(Config.WIDTH+5, Config.HEIGHT+50);
	this.setResizable(false);
	this.setLocationRelativeTo(null);
	this.addMouseListener(engine);
	this.add(engine.getRenderer());
	//this.addKeyListener(engine);
	
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
	
	this.setVisible(true);
    }

    public void startEngine(){
	gameThread.start();
    }
    
}
