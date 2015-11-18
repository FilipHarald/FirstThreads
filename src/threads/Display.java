package threads;

import java.util.Random;

import controller.Controller;

/**A class for randoming new int-values inside the diplay height and width
 * @author Filip
 *
 */
public class Display implements Runnable{
	private int maxX;
	private int maxY;
	private Controller c; 
	private Random rand;

	
	/**
	 * @param c the controller, used for accessing the GUIFrame
	 * @param maxX the max x-value
	 * @param maxY the max y-value
	 */
	public Display(Controller c, int maxX, int maxY) {
		this.maxX = maxX;
		this.maxY = maxY;
		rand = new Random();
		this.c = c; 
	}
	
	@Override
	public void run() {
		while(!Thread.interrupted()){			
			try {
				System.out.println("runDisplay");
				c.setDisplayPos(rand.nextInt(maxX),rand.nextInt(maxX));
				Thread.sleep(300);
			} catch (InterruptedException e) {
				break;
			}
		}
	}

}
