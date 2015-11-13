
package guis;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.*;
import javax.imageio.ImageIO;

import controller.Controller;

/**
 * The GUI for assignment 1
 */
public class GUIFrame
{
	/**
	 * These are the components you need to handle.
	 * You have to add listeners and/or code
	 */
	private JFrame frame;		// The Main window
	private JButton btnOpen;	// Open sound file button
	private JButton btnPlay;	// Play selected file button
	private JButton btnStop;	// Stop music button
	private JButton btnDisplay;	// Start thread moving display
	private JButton btnDStop;	// Stop moving display thread
	private JButton btnTriangle;// Start moving graphics thread
	private JButton btnTStop;	// Stop moving graphics thread
	private JLabel lblPlaying;	// Hidden, shown after start of music
	private JLabel lblPlayURL;	// The sound file path
	private JLabel lblMove;     // The label to move around
	private RotateLabel lblRotateImage;//The image to rotate
	private JPanel pnlMove;		// The panel to move display in
	private JPanel pnlRotate;	// The panel to move graphics in
	
	private JFileChooser fc;
	private Controller controller; //The controller

	/**
	 * Constructor
	 */
	public GUIFrame()
	{
	}
	
	/**
	 * Starts the application
	 */
	public void Start()
	{
		frame = new JFrame();
		frame.setBounds(0, 0, 494, 437);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setTitle("Multiple Thread Demonstrator");
		readImage();
		initializeGUI();					// Fill in components
		frame.setVisible(true);
		frame.setResizable(false);			// Prevent user from change size
		frame.setLocationRelativeTo(null);	// Start middle screen
		fc = new JFileChooser();
	}
	
	private void readImage() {
		try {
//			bi = ;
			lblRotateImage = new RotateLabel(ImageIO.read(new File("images/itunes.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sets up the GUI with components
	 */
	private void initializeGUI()
	{
		//The button listener
		ButtonListener btnListener = new ButtonListener();
		
		// The play panel
		JPanel pnlSound = new JPanel();
		Border b1 = BorderFactory.createTitledBorder("Music Player");
		pnlSound.setBorder(b1);
		pnlSound.setBounds(12, 12, 450, 100);
		pnlSound.setLayout(null);
		
		// Add the buttons and labels to this panel
		btnOpen = new JButton("Open");
		btnOpen.setBounds(6, 71, 75, 23);
		btnOpen.addActionListener(btnListener);
		pnlSound.add(btnOpen);
		
		btnPlay = new JButton("Play");
		btnPlay.setBounds(88, 71, 75, 23);
		btnPlay.addActionListener(btnListener);
		pnlSound.add(btnPlay);
		
		btnStop = new JButton("Stop");
		btnStop.setBounds(169, 71, 75, 23);
		btnStop.addActionListener(btnListener);
		pnlSound.add(btnStop);
		
		lblPlaying = new JLabel("Now Playing...",JLabel.CENTER);
		lblPlaying.setFont(new Font("Serif", Font.BOLD, 20));
		lblPlaying.setBounds(128, 16, 120, 30);
		pnlSound.add(lblPlaying);
		
		lblPlayURL = new JLabel("Music url goes here");
		lblPlayURL.setBounds(10, 44, 115, 13);
		pnlSound.add(lblPlayURL);
		// Then add this to main window
		frame.add(pnlSound);
		
		// The moving display outer panel
		JPanel pnlDisplay = new JPanel();
		Border b2 = BorderFactory.createTitledBorder("Display Thread");
		pnlDisplay.setBorder(b2);
		pnlDisplay.setBounds(12, 118, 222, 269);
		pnlDisplay.setLayout(null);
		
		// Add buttons and drawing panel to this panel
		btnDisplay = new JButton("Start Display");
		btnDisplay.setBounds(10, 226, 121, 23);
		btnDisplay.addActionListener(btnListener);		
		pnlDisplay.add(btnDisplay);
		
		btnDStop = new JButton("Stop");
		btnDStop.setBounds(135, 226, 75, 23);
		btnDStop.addActionListener(btnListener);
		pnlDisplay.add(btnDStop);
		
		
		pnlMove = new JPanel();
		pnlMove.setBounds(10,  19,  200,  200);
		Border b21 = BorderFactory.createLineBorder(Color.black);
		pnlMove.setBorder(b21);
		pnlDisplay.add(pnlMove);
		//Then add JLabel
		lblMove = new JLabel("You spin me right round!");  //-----------------------------------------------Moving Display-----------------------------------
		pnlMove.add(lblMove);
		
		// Then add this to main window
		frame.add(pnlDisplay);
		
		// The moving graphics outer panel
		JPanel pnlTriangle = new JPanel();
		Border b3 = BorderFactory.createTitledBorder("Triangle Thread");
		pnlTriangle.setBorder(b3);
		pnlTriangle.setBounds(240, 118, 222, 269);
		pnlTriangle.setLayout(null);
		
		// Add buttons and drawing panel to this panel
		btnTriangle = new JButton("Start Rotate");
		btnTriangle.setBounds(10, 226, 121, 23);
		btnTriangle.addActionListener(btnListener);
		pnlTriangle.add(btnTriangle);
		
		btnTStop = new JButton("Stop");
		btnTStop.setBounds(135, 226, 75, 23);
		btnTStop.addActionListener(btnListener);
		pnlTriangle.add(btnTStop);
		
		pnlRotate = new JPanel();
		pnlRotate.setBounds(10,  19,  200,  200);
		Border b31 = BorderFactory.createLineBorder(Color.black);
		pnlRotate.setBorder(b31);
		pnlTriangle.add(pnlRotate);
		pnlRotate.add(lblRotateImage);			//-------------------------------Spinning Image----------------------------------------
//		lblRotateImage.setLocation(pnlRotate.getWidth()/2, pnlRotate.getHeight()/2);
		// Add this to main window
		frame.add(pnlTriangle);
		
		//Initialize the controller
		controller = new Controller(this, pnlMove.getWidth(), pnlMove.getHeight());
	}
	
	public void setDisplayPos(int x, int y){
		lblMove.setLocation(x, y);
	}
	
	public void rotateImage() {
		pnlRotate.repaint();
	}
	
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnOpen){
				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
				int returnVal = fc.showOpenDialog(null);
		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = fc.getSelectedFile();
		            System.out.println(file);
		            controller.setMusicFile(file);
		        } else {
		        	System.out.println("Open command cancelled by user.");
		        }
			}else if(e.getSource() == btnPlay){
				controller.playMusic();
			}else if(e.getSource() == btnStop){
				controller.stopMusic();
			}else if(e.getSource() == btnDisplay){
				controller.startDisplay();
			}else if(e.getSource() == btnDStop){
				controller.stopDisplay();
			}else if(e.getSource() == btnTriangle){
				controller.startRotate();
			}else if(e.getSource() == btnTStop){
				controller.stopRotate();
			}
		}

	}
	private class RotateLabel extends JPanel{
		private BufferedImage bi;
		private int rotation;
		
		RotateLabel(BufferedImage bi){
			super();
			rotation = 100;
			this.bi = bi;
		}
		
		@Override
        public Dimension getPreferredSize() {
            return new Dimension(bi.getWidth(), bi.getHeight());
        }
		
		@Override
		public void paintComponent(Graphics g){
			System.out.println("paint comp");
			super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.rotate((rotation+=100), bi.getWidth() / 2, bi.getHeight() / 2);
            g2.drawImage(bi, 0, 0, null);
		}
		
	}


}
