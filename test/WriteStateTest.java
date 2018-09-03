

import com.iinmorus.multsnake.state.*;
import com.iinmorus.multsnake.engine.Renderer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class WriteStateTest extends State{

    StringBuilder sb;

    public WriteStateTest(StateManager sManager) {
	super(sManager);
	sb = new StringBuilder(" ");
    }

    @Override
    public void init() {
	
    }

    @Override
    public void update() {
	
    }

    @Override
    public void draw(Graphics g) {
	g.setColor(Color.BLACK);
	g.fillRect(0, 0, Renderer.WIDTH, Renderer.HEIGHT);
	g.setColor(Color.WHITE);
	g.drawString(sb.toString(), 100, 100);
    }

    @Override
    public void keyPressed(KeyEvent e) {
	if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
	    sb = new StringBuilder("");
	else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
	    sb.deleteCharAt(sb.length()-1);
	else
	    sb.append(e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e){}

    @Override
    public void mousePressed(MouseEvent e){}

    @Override
    public void setPaused(boolean isPaused){}
    
    
    
}
