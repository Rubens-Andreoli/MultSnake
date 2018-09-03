package com.iinmorus.engine2d;

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

    public SoundManager(float loadVolume) {
	clips = new HashMap<String, Clip>();
	this.loadVolume = loadVolume;
    }
    
    public void loadMP3(String filepath, String audioID){
	if(clips.get(audioID) != null) return;
	Clip clip;
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
	    clip = AudioSystem.getClip();
	    clip.open(dais);
	    setVolume(clip, loadVolume);
	    clips.put(audioID, clip);
	}catch(UnsupportedAudioFileException | IOException | LineUnavailableException e) {
	    e.printStackTrace();
	}
    }
    
    public void loadWAV(String filepath, String audioID){
	if(clips.get(audioID) != null) return;
	Clip clip;
	try {
	    AudioInputStream ais = AudioSystem.getAudioInputStream(SoundManager.class.getResourceAsStream(filepath));
	    clip = AudioSystem.getClip();
	    clip.open(ais);
	    setVolume(clip, loadVolume);
	    clips.put(audioID, clip);
	}catch(UnsupportedAudioFileException | IOException | LineUnavailableException e) {
	    e.printStackTrace();
	}
    }
	
    public void play(String audioID) {
    	play(audioID, 1);
    }
	
    public void play(String audioID, int frame) {
	if(mute) return;
	Clip c = clips.get(audioID);
	if(c == null) return;
	if(c.isRunning()) c.stop();
	c.setFramePosition(frame);
	c.start();
    }
	
    public void stop(String audioID) {
	if(clips.get(audioID) == null) return;
	if(clips.get(audioID).isRunning()) clips.get(audioID).stop();
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
	c.start();
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
	Clip c = clips.get(audioID);
	if(c == null) return;
	stop(audioID);
	if(mute) return;
	c.setLoopPoints(start, end);
	c.setFramePosition(frame);
	c.loop(Clip.LOOP_CONTINUOUSLY);
    }
	
    public void setPosition(String audioID, int frame) {
	clips.get(audioID).setFramePosition(frame);
    }
	
    public int getFrames(String audioID) { return clips.get(audioID).getFrameLength(); }
    public int getPosition(String audioID) { return clips.get(audioID).getFramePosition(); }
	
    public void close(String audioID) {
	Clip c = clips.get(audioID);
	if(c == null) return;
	stop(audioID);
	c.close();
    }
    
    private void setVolume(Clip clip, float volume){
    	FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	float dB = -80 - (-80*volume);
	if(dB < 0 || dB > -80)
	    gainControl.setValue(dB);
    }
    
    public void setVolume(String audioID, float volume){
	Clip c = clips.get(audioID);
	if(c == null) return;
	setVolume(c, volume);
    }
    
    public void setVolume(float volume){
	for(Clip c : clips.values()) setVolume(c, volume);
    }
    
    private void ajustVolume(Clip clip, float ajust){
	FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	float dB = gainControl.getValue() + ajust;
	if(dB < 0 || dB > -80)
	    gainControl.setValue(dB);
    }
    
    public void ajustVolume(String audioID, float ajust){
    	Clip c = clips.get(audioID);
	if(c == null) return;
	ajustVolume(c, ajust);
    }
    
    public void ajustVolume(float ajust){
	for(Clip c : clips.values()) ajustVolume(c, ajust);
    }

    public void setMute(boolean mute){
	this.mute = mute;
    }

    
}
