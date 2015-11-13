package threads;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Music implements Runnable{
	AudioInputStream audioInputStream;
	Clip clip;
	
	public Music(){
	}
	
	public void setFile(File file){
		try {
			audioInputStream =	AudioSystem.getAudioInputStream(file);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void stopMusic(){
		clip.stop();
	}
	
	@Override
	public void run() {
		while(!Thread.interrupted()){
			try{
				clip = AudioSystem.getClip();
				clip.open(audioInputStream);
//				clip.start();
			}
			catch(Exception ex){
			}		
		}
	}

}
