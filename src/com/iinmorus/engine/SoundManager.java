package com.iinmorus.engine;

import java.io.IOException;
import java.util.HashMap;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundManager {
    
    private final HashMap<String, Clip> clips;
    private final float loadVolume;
    private boolean mute;

    protected SoundManager(float loadVolume){
        clips = new HashMap<String, Clip>();
	this.loadVolume = loadVolume;
   }

    public void load(String filepath, String audioID){
	if(clips.get(audioID) != null) return;
	try {
	    AudioInputStream ais = AudioSystem.getAudioInputStream(SoundManager.class.getResourceAsStream(filepath));
	    AudioFormat baseFormat = ais.getFormat();
	    AudioFormat decodeFormat = new AudioFormat(
	        AudioFormat.Encoding.PCM_SIGNED,
	        baseFormat.getSampleRate(),
	        16,
	        baseFormat.getChannels(),
	        baseFormat.getChannels() * 2,
	        baseFormat.getSampleRate(),
	        false
	    );
	    AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);
	    Clip c = AudioSystem.getClip();
	    c.open(dais);
	    setVolume(c, loadVolume);
	    clips.put(audioID, c);
	} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
	    e.printStackTrace();
	}/*finally{
	    System.gc(); //workaround to close audio stream, ais.close doesn't free memory...
	}*/
    }
	
    public void play(String audioID) {
    	play(audioID, 0);
    }
	
    public void play(String audioID, int frame) {
	if(mute) return;
	Clip c = clips.get(audioID);
	if(c == null) return;
	if(c.isRunning()) c.stop();
	c.setFramePosition(frame);
	while(!c.isRunning()) c.start(); //workaround: sometimes sound won't start if java can't find an available channel...
    }
	
    public void stop(String audioID) {
	Clip c = clips.get(audioID);
	if(c == null) return;
	if(c.isRunning()) c.stop();
    }
    
    public void stopAll(){
	for(Clip clip : clips.values())
	    if(clip.isRunning()) clip.stop();
    }
	
    public void resume(String audioID) {
	if(mute) return;
	Clip c = clips.get(audioID);
	if(c == null) return;
	if(c.isRunning()) return;
	while(!c.isRunning()) c.start(); //workaround: sometimes sound won't start if java can't find an available channel...
    }
	
    public void loop(String audioID) {
    	loop(audioID, 0, 0, clips.get(audioID).getFrameLength() - 1);
    }
	
    public void loop(String audioID, int frame) {
    	loop(audioID, frame, 0, clips.get(audioID).getFrameLength() - 1);
    }
	
    public void loop(String audioID, int start, int end) {
	loop(audioID, 0, start, end);
    }
	
    public void loop(String audioID, int frame, int start, int end) {
	if(mute) return;
	Clip c = clips.get(audioID);
	if(c == null) return;
	stop(audioID);
	c.setLoopPoints(start, end);
	c.setFramePosition(frame); //FIX: error if pass frame end...
	while(!c.isRunning()) c.loop(Clip.LOOP_CONTINUOUSLY);
    }
	
    public void setPosition(String audioID, int frame) {
	Clip c = clips.get(audioID);
	if(c == null) return;
	c.setFramePosition(frame);
    }
	
    public int getFrames(String audioID) { 
	Clip c = clips.get(audioID);
	if(c == null) return -1;
	return c.getFrameLength(); 
    }
    
    public int getPosition(String audioID) { 
	Clip c = clips.get(audioID);
	if(c == null) return -1;
	return c.getFramePosition()%c.getFrameLength(); 
    }
	
    public void close(String audioID) {
	Clip c = clips.get(audioID);
	if(c == null) return;
	stop(audioID);
	c.drain();
	c.close();
    }
    
    private void setVolume(Clip clip, float volume){
    	FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	float dB = -80 - (-80*volume);
	if(dB > 0) dB = 0;
	if(dB < -80) dB = -80;
	gainControl.setValue(dB);
    }
    
    public void setClipVolume(String audioID, float volume){
	Clip c = clips.get(audioID);
	if(c == null) return;
	setVolume(c, volume);
    }
    
    public void setMasterVolume(float volume){
	for(Clip c : clips.values()) setVolume(c, volume);
    }
    
    private void ajustVolume(Clip clip, float ajust){
	FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	float dB = gainControl.getValue() + ajust;
	if(dB > 0) dB = 0;
	if(dB < -80) dB = -80;
	gainControl.setValue(dB);
    }
    
    public void ajustClipVolume(String audioID, float ajust){
    	Clip c = clips.get(audioID);
	if(c == null) return;
	ajustVolume(c, ajust);
    }
    
    public void ajustMasterVolume(float ajust){
	for(Clip c : clips.values()) ajustVolume(c, ajust);
    }

    public void setMute(boolean mute){
	this.mute = mute;
    }

}
