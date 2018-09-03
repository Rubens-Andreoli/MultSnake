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

public abstract class SoundManager {
    
    private static HashMap<String, Clip> clips;
    private static float masterVol = 0.7F;
    private static int gap = 0;
    private static boolean mute = false;
	
    public static void init() {
	clips = new HashMap<String, Clip>();
    }
	
    public static void loadMP3(String filepath, String audioID){
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
	    clips.put(audioID, clip);
	    setVolume(audioID, masterVol);
	}catch(UnsupportedAudioFileException | IOException | LineUnavailableException e) {
	    e.printStackTrace();
	}
    }
    
    public static void loadWAV(String filepath, String audioID){
	if(clips.get(audioID) != null) return;
	Clip clip;
	try {
	    AudioInputStream ais = AudioSystem.getAudioInputStream(SoundManager.class.getResourceAsStream(filepath));
	    clip = AudioSystem.getClip();
	    clip.open(ais);
	    clips.put(audioID, clip);
	    setVolume(audioID, masterVol);
	}catch(UnsupportedAudioFileException | IOException | LineUnavailableException e) {
	    e.printStackTrace();
	}
    }
	
    public static void play(String audioID) {
    	play(audioID, gap);
    }
	
    public static void play(String audioID, int frame) {
	if(mute) return;
	Clip c = clips.get(audioID);
	if(c == null) return;
	if(c.isRunning()) c.stop();
	c.setFramePosition(frame);
	while(!c.isRunning()) c.start();
    }
	
    public static void stop(String audioID) {
	if(clips.get(audioID) == null) return;
	if(clips.get(audioID).isRunning()) clips.get(audioID).stop();
    }
    
    public static void stopAll(){
	for(Clip clip : clips.values())
	    if(clip.isRunning()) clip.stop();
    }
	
    public static void resume(String audioID) {
	if(mute) return;
	if(clips.get(audioID).isRunning()) return;
	clips.get(audioID).start();
    }
	
    public static void loop(String audioID) {
    	loop(audioID, gap, gap, clips.get(audioID).getFrameLength() - 1);
    }
	
    public static void loop(String audioID, int frame) {
    	loop(audioID, frame, gap, clips.get(audioID).getFrameLength() - 1);
    }
	
    public static void loop(String audioID, int start, int end) {
	loop(audioID, gap, start, end);
    }
	
    public static void loop(String audioID, int frame, int start, int end) {
	stop(audioID);
	if(mute) return;
	clips.get(audioID).setLoopPoints(start, end);
	clips.get(audioID).setFramePosition(frame);
	clips.get(audioID).loop(Clip.LOOP_CONTINUOUSLY);
    }
	
    public static void setPosition(String audioID, int frame) {
	clips.get(audioID).setFramePosition(frame);
    }
	
    public static int getFrames(String audioID) { return clips.get(audioID).getFrameLength(); }
    public static int getPosition(String audioID) { return clips.get(audioID).getFramePosition(); }
	
    public static void close(String audioID) {
	stop(audioID);
	clips.get(audioID).close();
    }
    
    public static void setVolume(String audioID, float volume){
	FloatControl gainControl = (FloatControl) clips.get(audioID).getControl(FloatControl.Type.MASTER_GAIN);
	float dB = -80 - (-80*volume);
	if(dB < 0 || dB > -80)
	    gainControl.setValue(dB);
    }
    
    public static void ajustVolume(String audioID, float ajust){
    	FloatControl gainControl = (FloatControl) clips.get(audioID).getControl(FloatControl.Type.MASTER_GAIN);
	float dB = gainControl.getValue() + ajust;
	if(dB < 0 || dB > -80)
	    gainControl.setValue(dB);
    }
    
}
