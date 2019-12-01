package testGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.Timer;

public class gameGame extends JFrame implements ActionListener {
	double prevTime = 0;
	int bruh = 0;
	double skruh = 0;
	double x;
	double y;
	double jumpVel = 0;
	private Timer timer;
	private int wait;
	Graphics offGraphics;
	Image offImage;
	int xDimension = 800;
	int yDimension = 600;
	static keyStep keyListen; //all of these statics can and should be accessed by objects in order for the game to work correctly
	static gameObject stepList[] = new gameObject[20000]; //use this like depth yeah?
	static int stepListLength = 0;
	static gameGame mainGame; 
	static int camX;
	static int camY;
	gameObject bronky;
	gameObject testobj;
	
	


	// keyStep keyInput = new keyStep();
	public static void main(String[] args) {

		new gameGame();

	}

	public gameGame() {
		super("test game"); // this is the game title also this has to be first
		mainGame = this;
		File rootName = new File("./sprites/test.jpg");

		try {
			System.out.println(rootName.getCanonicalPath());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		

		// setIgnoreRepaint(true);
		x = 0.0;
		wait = 10;

		keyListen = new keyStep(this);

		setSize(xDimension, yDimension); // this is the window size

		setVisible(true); // this makes the window show
		
		// these lines is wizardry and i do not expect to know how it works yet
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				System.exit(0);
			}
		});
		new Player();
		timer = new Timer(wait, this); // this is what requires the need to implement actionlistener
		timer.start();

	}

	public void step() { 
		if(false) {
			for(int i = 0; i < stepList.length;i++) {	
				if(i != 0) {
				
				}
			}
		}
			
//		if (keyListen.getKey(KeyEvent.VK_A)) {
//			camX-=5;
//		}
//		if (keyListen.getKey(KeyEvent.VK_D)) {
//			camX+=5;
//		}
//		if (keyListen.getKey(KeyEvent.VK_S)) {
//			camY+=5;
//		}
//		if (keyListen.getKey(KeyEvent.VK_W)) {
//			camY-=5;
//		}
		
		if (stepListLength != 0){ for (int i = 0; i < stepListLength; i++) {stepList[i].step();}}
	}


	public void paint(Graphics g) { // the graphics object originates here, cant make your own, also this is called

		// automagically :)
		offImage = createImage(xDimension, yDimension);
		offGraphics = offImage.getGraphics();
		
		Graphics2D g2d = (Graphics2D) offGraphics; // draw everything onto a seperate canvas
		g2d.setColor(Color.white);
		g2d.fillRect(0, 0, xDimension, yDimension); // wipe the previous screen

		if (stepListLength != 0) {
			for (int i = 0; i < stepListLength; i++) {
				stepList[i].x-= gameGame.camX; stepList[i].y-= gameGame.camY; //camera movement
				// TODO dont draw things outside of the screen border
				stepList[i].paint(g2d);
				stepList[i].x+= gameGame.camX; stepList[i].y+= gameGame.camY;
			}
		}

		g.drawImage(offImage, (int) x, (int) y, this);
		// repaint(); // this line makes it paint many times :))) very good
		 // draw the seperate canvas onto the screen, removing flickering.
		
		// http://journals.ecs.soton.ac.uk/java/tutorial/ui/drawing/doubleBuffer.html
		// thanks
	}

	@Override
	public void actionPerformed(ActionEvent arg0) { // this runs when timer is done
		keyListen.frameCount();
		double i = arg0.getWhen() - prevTime;
		prevTime = arg0.getWhen();
		skruh += i;

		bruh++;
		if (skruh >= 1000) {
			System.out.println(bruh);
			bruh = 0;
			skruh = 0;
		}
		// keyListen.
		step();
		repaint(); // if you do not allow the frame to finish rendering, you will get artifacts, we
		// use something called double buffering
		// to fix these artifacts, by drawing to an entirely different canvas, and
		// drawing the canvas for the previous frame
		// this forces the computer to draw a frame that is complete instead of a
		// possibly incomplete one.

		timer.setDelay(16); // this is roughly 60 fps


	}

	public void steps(gameObject l) {
		stepList[stepListLength] = l;
		stepListLength++;

	}
	public void steps(gameObject l,int k) {
		System.out.println("created " + l.getName());
		
		stepList[k] = l;
		stepListLength++;

	}

	public void importFiles() {
		BufferedImage test;
		File rootName = new File("./sprites.jpg");
		try {
			test = ImageIO.read(rootName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * File name = new File("./");
		 */
	}

}
