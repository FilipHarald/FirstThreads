package controller;

import java.io.File;

import guis.GUIFrame;
import threads.ThreadRunner;

/**Connects the ThreadRunner and GUIFrame
 * @author Filip
 *
 */
/**
 * @author Filip
 *
 */
/**
 * @author Filip
 *
 */
public class Controller {
	private ThreadRunner threadRunner;
	private GUIFrame gF;
	
	/**Constructor
	 * @param gF The GUIFrame
	 * @param displayMaxX Max value for the displayPanels height
	 * @param displayMaxY Max value for the displayPanels widht
	 */
	public Controller(GUIFrame gF, int displayMaxX, int displayMaxY){
		this.gF = gF;
		threadRunner = new ThreadRunner(this, displayMaxX, displayMaxX);
	}

	/**
	 * Starts the display thread
	 */
	public void startDisplay() {
		threadRunner.startDisplay();
	}
	/**
	 * Stops the display thread
	 */
	public void stopDisplay() {
		threadRunner.stopDisplay();
	}

	/**Sets the new value to the display in the GUIFrame
	 * @param x
	 * @param y
	 */
	public void setDisplayPos(int x, int y) {
		gF.setDisplayPos(x, y);
		
	}
	/**
	 * Rotates the image in GUIFrame one step(the step value is set in GUIFrame)
	 */
	public void rotateImage() {
		gF.rotateImage();
	}
	/**
	 * Starts the rotate thread
	 */
	public void startRotate() {
		threadRunner.startRotate();
	}
	/**
	 * Stops the rotate thread
	 */
	public void stopRotate() {
		threadRunner.stopRotate();
	}
	/**Sets the music file in Music-class
	 * @param file
	 */
	public void setMusicFile(File file) {
		threadRunner.setMusicFile(file);
	}
	/**
	 * Starts the music thread
	 */
	public void playMusic(){
		threadRunner.startMusic();
	}

	/**
	 * Stops the music thread
	 */
	public void stopMusic() {
		threadRunner.stopMusic();
	}
}
