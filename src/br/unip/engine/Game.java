package br.unip.engine;

import java.util.List;

public interface Game {
    
    public String getName();
    public List<State> generateStateList(Engine engine);
    public String firstStateID();
    public Settings getSettings();
    
}
