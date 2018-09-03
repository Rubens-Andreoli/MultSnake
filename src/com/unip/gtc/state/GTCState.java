package com.unip.gtc.state;

import br.unip.engine.Engine;
import br.unip.engine.State;
import static br.unip.gtc.GTC.EASY;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;

public abstract class GTCState extends State{

    //ui
    protected Color background = Color.BLACK;
    protected Color overColor = Color.RED;
    protected Color pauseColor = Color.YELLOW;
    protected Color titleColor = Color.RED;
    protected String overMsg = "GAME OVER";
    protected String pauseMsg = "PAUSED";
    protected String title = "GET THAT CHERRY!";
    protected String msg = "!Congratulations!";
    protected Font scoreFont;
    protected Font warningFont;
    protected Font msgFont;
    protected Font titleFont;
    
    //configs
    protected final int width;
    protected final int height;
    protected final int scale;
    protected int baseScore = 5;
    protected int difficulty = EASY;
    
    //status
    protected long stateTick;
    protected int time;
    protected boolean isPaused, isOver;

    public GTCState(Engine engine) {
	super(engine);
	scale = engine.settings.scale;
	width = engine.settings.width*scale;
	height = engine.settings.height*scale;
    }

    @Override
    protected void loadResources() {
	//Font
	try {
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/manaspc.ttf")));
	    titleFont = new Font("Manaspace", Font.PLAIN, 60);
	    scoreFont = new Font("Manaspace", Font.PLAIN, 20);
	    warningFont = new Font("Manaspace", Font.BOLD, 100);
	    msgFont = new Font("Manaspace", Font.PLAIN, 40); 
	} catch (Exception e) {
	    titleFont = new Font(Font.MONOSPACED, Font.BOLD, 60);
	    scoreFont = new Font(Font.MONOSPACED, Font.PLAIN, 20);
	    warningFont = new Font(Font.MONOSPACED, Font.BOLD, 100);
	    msgFont = new Font(Font.MONOSPACED, Font.BOLD, 40);
	}
	
	//Music
	engine.sounds.load("/sounds/idle.mp3", "idle");
	engine.sounds.load("/sounds/match.mp3", "match");
	
	//SFX
	engine.sounds.load("/sounds/hit.mp3", "hit");
	engine.sounds.load("/sounds/cherry.mp3", "cherry");
    }
    
    protected void drawPaused(Graphics2D g){
	g.setFont(warningFont);
	g.setColor(pauseColor);
	g.drawString(pauseMsg, width/2-g.getFontMetrics(warningFont).stringWidth(pauseMsg)/2, height/2);
    }
    
    protected void drawOver(Graphics2D g){
	g.setFont(warningFont);
	g.setColor(overColor);
        g.drawString(overMsg, width/2-g.getFontMetrics(warningFont).stringWidth(overMsg)/2, height/2);
    }

    @Override
    public boolean isPaused() {
	return isPaused;
    }
    
}
