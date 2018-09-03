
package com.iinmorus.engine;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface State{

    void loadResources();
    void start();
    void update();
    void draw(Graphics2D g);
    
    void keyPressed(KeyEvent e);
    void keyReleased(KeyEvent e);
    void mousePressed(MouseEvent e);
    
    String getStateID();
    
    void setPaused(boolean isPaused);
    void setDifficulty(int difficulty);
    
}
