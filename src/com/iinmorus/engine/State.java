
package com.iinmorus.engine;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface State{

    String getStateID();
    
    void loadResources();
    void start();
    void update();
    void draw(Graphics2D g);
    void unload();
    
    void keyPressed(KeyEvent e);
    void keyReleased(KeyEvent e);
    void mousePressed(MouseEvent e);
    
    void setPaused(boolean isPaused);
}
