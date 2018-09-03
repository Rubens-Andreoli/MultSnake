

import com.iinmorus.engine.*;
import java.awt.Container;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.List;

public abstract class GameTest {
    
    private String name = "ARCADIA v1.0";
    public final Settings settings;
    public final HashMap<String, State> stateMap;
    public final String startStateID;
    
    public final Engine engine;
    private final Thread gameThread;
    public final RenderBuffer renderBuffer;
    public final InputBuffer inputBuffer;
    public final SoundManager sounds;
    public final StateManager states;

    public GameTest(Settings settings, HashMap<String, State> stateMap, String startStateID) {
	this.settings = settings;
        this.stateMap = stateMap;
        this.startStateID = startStateID;
        
        //initiate parts
        engine = new Engine(this);   
	gameThread = new Thread(engine);
        renderBuffer = new RenderBuffer(this);
	inputBuffer = new InputBuffer(this);
        sounds = new SoundManager(this);
        states = new StateManager(this);

	//configure parts
	gameThread.setName(name);
	if(settings.listenMouse) renderBuffer.addMouseListener(inputBuffer);
	if(settings.listenKey) renderBuffer.addKeyListener(inputBuffer);
    }

    protected void update(){
        states.update();
    }
    
    protected void render(){
        renderBuffer.repaint();
    }
    
    protected void draw(Graphics2D g){
	states.draw(g);
	g.dispose();
    }
    
    public Container getGameContainer(){return renderBuffer;}
    
}
