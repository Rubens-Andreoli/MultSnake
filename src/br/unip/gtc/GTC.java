package br.unip.gtc;

import br.unip.engine.Engine;
import br.unip.engine.Game;
import br.unip.engine.Settings;
import br.unip.engine.State;
import com.unip.gtc.state.Idle;
import com.unip.gtc.state.Multiplayer;
import com.unip.gtc.state.Singleplayer;
import java.util.ArrayList;
import java.util.List;

public class GTC implements Game{

    public static final String IDLE="idle", SINGLE="single", MULT="mult";
    public static final int EASY=1, MEDIUM=2, HARD=3;
    
    public static final Settings SETTINGS;
    static{
	SETTINGS = new Settings.Builder().create();
    }
    
    @Override
    public String getName() {
	return "GTC";
    }

    @Override
    public List<State> generateStateList(Engine engine) {
	ArrayList<State> states = new ArrayList<State>();
	states.add(new Idle(engine));
	states.add(new Singleplayer(engine));
	states.add(new Multiplayer(engine));
	return states;
    }

    @Override
    public String firstStateID() {
	return IDLE;
    }

    @Override
    public Settings getSettings() {
	return SETTINGS;
    }
    
}
