package threads;

import java.io.File;

import controller.Controller;


/**A class used for running the runnables on threads
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
/**
 * @author Filip
 *
 */
public class ThreadRunner {
	private Display runnableDisplay;
	private Rotate runnableRotate;
	private Music runnableMusic;
	private PlayThread playThread;
	private Thread moveThread;
	private Thread rotateThread;

	/**
	 * @param c the controller used to access the GUIFrame
	 * @param displayMaxX max x value for the display panel
	 * @param displayMaxY max y value for the display panel
	 */
	public ThreadRunner(Controller c, int displayMaxX, int displayMaxY) {
		runnableDisplay = new Display(c, displayMaxX, displayMaxY);
		runnableRotate = new Rotate(c);
		runnableMusic = new Music();
	}

	/**
	 * Starts the display thread
	 */
	public void startDisplay() {
		if (moveThread == null) {
			moveThread = new Thread(runnableDisplay);
			moveThread.start();
		}
	}

	/**
	 * Stops the display thread
	 */
	public void stopDisplay() {
		if (moveThread.isAlive()) {
			moveThread.interrupt();
			moveThread = null;
		}
	}

	/**
	 * Starts the rotate thread
	 */
	public void startRotate() {
		if (rotateThread == null) {
			rotateThread = new Thread(runnableRotate);
			rotateThread.start();
		}
	}

	/**
	 * Stops the rotate thread
	 */
	public void stopRotate() {
		if (rotateThread.isAlive()) {
			rotateThread.interrupt();
			rotateThread = null;
		}
	}
	
	/**Sets the music file for the runnable music object
	 * @param file the music file intended to play
	 */
	public void setMusicFile(File file){
		runnableMusic.setFile(file);
	}

	/**
	 * Starts the music thread
	 */
	public void startMusic() {
		if (playThread == null) {
			playThread = new PlayThread(runnableMusic);
			playThread.start();
		}
		
	}

	/**
	 * Stops the music thread
	 */
	public void stopMusic() {
		if (playThread.isAlive()) {
			playThread.interrupt();
			playThread = null;
		}
	}
	
	/**Used for the special case of stopping the music on the music thread
	 * @author Filip
	 *
	 */
	private class PlayThread extends Thread{
		private Music m;
		
		/**The class needs to have reference to the music obj to stop music.
		 * @param m
		 */
		public PlayThread(Music m){
			super(m);
			this.m = m;
		}
		
		@Override
		public void interrupt() {
			//Stops the music before interrupting thread
			m.stopPlaying();
			super.interrupt();
		}
	}
}
