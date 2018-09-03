package com.iinmorus.engine;

import java.util.Set;

public interface StateFactory {
    
    State createState(String stateID);
    
    Set<String> getStateSet();
    String getStartStateID();
    
}
