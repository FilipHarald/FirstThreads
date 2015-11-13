package threads;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Music implements Runnable{
	Player player;
	File file;
	
	public Music(){
	}
	
	public void setFile(File file){
		this.file = file;
	}
	
	@Override
	public void run() {
			try {
				player = new Player(new BufferedInputStream(new FileInputStream(file)));
				player.play();
			} catch (JavaLayerException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	}

	public void stopPlaying() {
		player.close();
	}
}
