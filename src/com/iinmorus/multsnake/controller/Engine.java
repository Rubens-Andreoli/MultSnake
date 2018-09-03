package com.iinmorus.multsnake.controller;

import com.iinmorus.multsnake.controller.Match;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Engine implements ActionListener{
  
    private final Timer timer = new Timer(20, this);
    private Renderer renderer;
    
    private Match match;
    
    public Engine(){
	this.renderer = new Renderer();
	match = new Match();
	renderer.addPlayer(match.getSnake());
	renderer.addCherry(match.getCherry());
	timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	//System.out.println("RUNNING TICK: "+ ticks);
	renderer.repaint();
	match.toLoop();
	if(match.isChangeCherry()) 
	    renderer.addCherry(match.getCherry());
    }
    
    public void applyCommand(int command){
	match.getSnake().changeDirection(command);
	//if(key == KeyEvent.VK_ESCAPE && over) startGame();
	//if(key == KeyEvent.VK_SPACE && !over) paused = !paused;
	
    }

    public void setRenderer(Renderer renderer) {
	this.renderer = renderer;
    }

    public Renderer getRenderer() {
	return renderer;
    }

}
