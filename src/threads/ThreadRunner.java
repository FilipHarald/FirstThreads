package threads;

import controller.Controller;

public class ThreadRunner {
	private Display runnableDisplay;
	private Rotate runnableRotate;
	private Thread playThread;
	private Thread moveThread;
	private Thread rotateThread;

	public ThreadRunner(Controller c, int displayMaxX, int displayMaxY) {
		runnableDisplay = new Display(c, displayMaxX, displayMaxY);
		runnableRotate = new Rotate(c);
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

}
