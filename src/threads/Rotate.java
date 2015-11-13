package threads;

import controller.Controller;

public class Rotate implements Runnable {
	
	private Controller c; 
	
	public Rotate(Controller c){
		this.c = c;
	}

	@Override
	public void run() {
		while(!Thread.interrupted()){
			System.out.println("runRotateImage");
			c.rotateImage();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				break;
			}
		}
	}

}
