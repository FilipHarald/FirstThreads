package threads;

import java.io.File;

import controller.Controller;


public class ThreadRunner {
	private Display runnableDisplay;
	private Rotate runnableRotate;
	private Music runnableMusic;
	private PlayThread playThread;
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
			playThread = new PlayThread(runnableMusic);
			playThread.start();
		}
		
	}

	public void stopMusic() {
		if (playThread.isAlive()) {
			playThread.interrupt();
			playThread = null;
		}
	}
	
	private class PlayThread extends Thread{
		private Music m;
		
		public PlayThread(Music m){
			super(m);
			this.m = m;
		}

		@Override
		public void interrupt() {
			m.stopPlaying();
			super.interrupt();
		}
	}
}
