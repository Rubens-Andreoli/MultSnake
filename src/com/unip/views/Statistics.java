package com.unip.views;

import br.unip.engine.Engine;
import br.unip.gtc.GTC;
import com.unip.conection.Player;
import com.unip.conection.PlayerDAO;
import com.unip.gtc.state.Multiplayer;
import com.unip.gtc.state.Singleplayer;

public class Statistics {
    
    PlayerDAO pdao;
    private Engine engine;
    
    public Statistics(Engine engine){
        this.engine = engine;
    }
    
    private int getSingleScore(){
        return engine.states.getState(GTC.SINGLE, Singleplayer.class).getScore();
    }    
    private int getMultScoreP1(){
        return engine.states.getState(GTC.MULT, Multiplayer.class).getScore_P1();
    }    
    private int getMultScoreP2(){
        return engine.states.getState(GTC.MULT, Multiplayer.class).getScore_P2();        
    }    
    private int getSingleTime(){
        return engine.states.getState(GTC.SINGLE, Singleplayer.class).getTime();
    }    
    private int getMultTime(){
        return engine.states.getState(GTC.MULT, Multiplayer.class).getTime();
    }    
    private int getSingleLength(){
        return engine.states.getState(GTC.SINGLE, Singleplayer.class).getLength();
    }    
    private int getMultLengthP1(){
        return engine.states.getState(GTC.MULT, Multiplayer.class).getLength_P1();
    }    
    private int getMultLengthP2(){
        return engine.states.getState(GTC.MULT, Multiplayer.class).getLength_P2();
    }    
    private String getSingleDifficulty(){
        int dificulty = engine.states.getState(GTC.SINGLE, Singleplayer.class).getDifficulty();
        switch(dificulty){
            case 1:
                return "EASY";
            
            case 2:
                return "MEDIUM";
            
            case 3:
                return "HARD";
                
            default:
                return null;
        }
    }    
    private String getMultDifficulty(){
        int dificulty = engine.states.getState(GTC.MULT, Multiplayer.class).getDifficulty();
        switch(dificulty){
            case 1:
                return "EASY";
            
            case 2:
                return "MEDIUM";
            
            case 3:
                return "HARD";
                
            default:
                return null;
        }
    }    
    
    public void insertResults(int single, Player p1, Player p2){
        pdao = new PlayerDAO();
        
        int score;
        int time;
        int length;
        String dif;
            
        System.out.println("Single switch: " +  single);
        switch(single){
            
            // multiplayer
            case 0:         
                int score_p1 = getMultScoreP1();
                time = getMultTime();
                int length_p1 = getMultLengthP1();
                dif = getMultDifficulty();
                int score_p2 = getMultScoreP2();
                int length_p2 = getMultLengthP2();
                
                pdao.statisticsMult(time, dif, "MULT", p1.getNickname(), score_p1, length_p1, 
                        p2.getNickname(), score_p2, length_p2);
            break;
            
            // singleplayer
            case 1:               
                score = getSingleScore();
                time = getSingleTime();
                length = getSingleLength();
                dif = getSingleDifficulty();
                
                pdao.statisticsSingle(time, dif, "SINGLE", p1.getNickname(), score, length);   
            break;
        }
        
        
    }
    
    
  
    
}
