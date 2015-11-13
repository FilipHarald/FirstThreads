package threads;

import java.io.File;

import controller.Controller;


public class ThreadRunner {
	private Display runnableDisplay;
	private Rotate runnableRotate;
	private Music runnableMusic;
	private Thread playThread;
	private Thread moveThread;
	private Thread rotateThread;

	public ThreadRunner(Controller c, int displayMaxX, int displayMaxY) {
		runnableDisplay = new Display(c, displayMaxX, displayMaxY);
		runnableRotate = new Rotate(c);
		runnableMusic = new Music();
	}

	public void startDisplay() {
		if (moveThread == null) {
			moveThread = new Thread(runnableDisplay);
			moveThread.start();
		}
	}

	public void stopDisplay() {
		if (moveThread.isAlive()) {
			moveThread.interrupt();
			moveThread = null;
		}
	}

	public void startRotate() {
		if (rotateThread == null) {
			rotateThread = new Thread(runnableRotate);
			rotateThread.start();
		}
	}

	public void stopRotate() {
		if (rotateThread.isAlive()) {
			rotateThread.interrupt();
			rotateThread = null;
		}
	}
	
	public void setMusicFile(File file){
		runnableMusic.setFile(file);
	}

	public void startMusic() {
		if (playThread == null) {
			playThread = new Thread(runnableMusic);
			playThread.start();
		}
		
	}

	public void stopMusic() {
		if (playThread.isAlive()) {
			runnableMusic.stopMusic();
			playThread.interrupt();
			playThread = null;
		}
	}

}
