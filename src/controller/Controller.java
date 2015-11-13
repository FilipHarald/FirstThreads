package controller;

import java.io.File;

import guis.GUIFrame;
import threads.ThreadRunner;

public class Controller {
	private ThreadRunner threadRunner;
	private GUIFrame gF;
	
	public Controller(GUIFrame gF, int displayMaxX, int displayMaxY){
		this.gF = gF;
		threadRunner = new ThreadRunner(this, displayMaxX, displayMaxX);
	}

	public void startDisplay() {
		threadRunner.startDisplay();
	}

	public void stopDisplay() {
		threadRunner.stopDisplay();
	}

	public void setDisplayPos(int x, int y) {
		gF.setDisplayPos(x, y);
		
	}

	public void rotateImage() {
		gF.rotateImage();
	}

	public void startRotate() {
		threadRunner.startRotate();
	}

	public void stopRotate() {
		threadRunner.stopRotate();
	}

	public void setMusicFile(File file) {
		threadRunner.setMusicFile(file);
	}
	
	public void playMusic(){
		threadRunner.startMusic();
	}

	public void stopMusic() {
		threadRunner.stopMusic();
	}
}
