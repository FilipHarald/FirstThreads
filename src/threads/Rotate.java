package threads;

import controller.Controller;

public class Rotate implements Runnable {
	
	private Controller c; 
	
	/**Updates the GUIFrame via the controller
	 * @param c the controller used to access the GUI
	 */
	public Rotate(Controller c){
		this.c = c;
	}

	@Override
	public void run() {
		while(!Thread.interrupted()){
			System.out.println("runRotateImage");
			c.rotateImage();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				break;
			}
		}
	}

}
