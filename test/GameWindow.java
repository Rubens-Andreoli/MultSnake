import br.unip.engine.Engine;
import br.unip.gtc.GTC;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GameWindow extends JFrame implements KeyListener{

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
	engine.renderer.addKeyListener(this);
	engine.start();

	this.setVisible(true);
    
    }

    @Override
    public void keyTyped(KeyEvent e){}

    @Override
    public void keyPressed(KeyEvent e) {
	int code = e.getKeyCode();
	switch(code){
	    case KeyEvent.VK_1:
		engine.states.startState(GTC.SINGLE);
		break;
	    case KeyEvent.VK_2:
		engine.states.startState(GTC.MULT);
		break;
	    case KeyEvent.VK_0:
		engine.states.startState(GTC.IDLE);
		break;
	    case KeyEvent.VK_SPACE:
		engine.states.togglePauseCurrentState();
		break;
	    case KeyEvent.VK_ESCAPE:
		engine.states.restartCurrentState();
		break;
	}
    }

    @Override
    public void keyReleased(KeyEvent e){}
    
}
