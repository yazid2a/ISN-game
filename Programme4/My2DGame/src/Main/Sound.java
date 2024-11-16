package Main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	Clip clip;
	URL soundURL[]=new URL[30];
	
	public Sound() {
		soundURL[0]= getClass().getResource("/sound/backgroundsong.wav"); //music background
		soundURL[1]= getClass().getResource("/sound/coin.wav"); // key sound 
		soundURL[2]= getClass().getResource("/sound/mario.wav"); // boots sound 
		soundURL[3]= getClass().getResource("/sound/unlock.wav"); // door unlocking
		soundURL[4]= getClass().getResource("/sound/fanfare.wav");
	}
	
	
	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip .open(ais);
		}catch(Exception e) {
			
		}
	}
	public void play() {
		clip.start();
		
	}
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		
	}
	public void stop() {
		clip.stop();
		
	}
}
