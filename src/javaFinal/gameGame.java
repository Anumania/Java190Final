package javaFinal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	boolean debug = false;
	boolean speedTest = false;
	int FPS; // usually finals are all caps, but FPS is usually like this
	int timeInFrames;
	
	


	// keyStep keyInput = new keyStep();
	public static void main(String[] args) {

		new gameGame();

	}

	public gameGame() {
		super("test game"); // this is the game title also this has to be first
		mainGame = this;
		

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
		if(debug) {
			for(int i = 0; i < stepList.length;i++) {	
				if(stepList[i] != null) {
					System.out.print(i + " " +stepList[i].getName() + " ");
				}
			}
			System.out.println();
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
		gameGame main = gameGame.mainGame;
		offImage = createImage(xDimension, yDimension);
		offGraphics = offImage.getGraphics();
		BufferedImage imageLayer = new BufferedImage(main.xDimension, main.yDimension, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g2d = (Graphics2D) offGraphics; // draw everything onto a seperate canvas
		// g2d.setColor(Color.blue);
		// g2d.setComposite(AlphaComposite.Clear);
		// g2d.fillRect(0, 0, xDimension, yDimension); // wipe the previous screen
		g2d.setBackground(new Color(255, 0, 0, 100));
		if (stepListLength != 0) {
			for (int i = 0; i < stepListLength; i++) {
				stepList[i].x-= gameGame.camX; stepList[i].y-= gameGame.camY; //camera movement
				// TODO dont draw things outside of the screen border
				stepList[i].paint(g2d, imageLayer);
				stepList[i].x+= gameGame.camX; stepList[i].y+= gameGame.camY;
			}
		}
		
		if (stepListLength != 0) {
			for (int i = 0; i < stepListLength; i++) { //camera movement
				// TODO dont draw things outside of the screen border
				stepList[i].paintGUI(g2d);
			}
		}
		offGraphics.drawImage(imageLayer, 0, 0, this);
		g.drawImage(offImage, (int) x, (int) y, this);
		// repaint(); // this line makes it paint many times :))) very good
		 // draw the seperate canvas onto the screen, removing flickering.
		
		// http://journals.ecs.soton.ac.uk/java/tutorial/ui/drawing/doubleBuffer.html
		// thanks
	}

	@Override
	public void actionPerformed(ActionEvent arg0) { // this runs when timer is done
		timeInFrames++;
		keyListen.frameCount();
		double i = arg0.getWhen() - prevTime;
		prevTime = arg0.getWhen();
		skruh += i;

		bruh++;
		if (skruh >= 1000) {
			System.out.println("fps: " + bruh);
			FPS = bruh;
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

		 // this is roughly 60 fps
		if(speedTest) {
			timer.setDelay(0);
		}
		else { 
			timer.setDelay(16);
		}
		

	}

	public void steps(gameObject l) {
		stepList[stepListLength] = l;
		stepListLength++;

	}
	public void steps(gameObject l,int k) {
		if(stepList[k] != null) {
			System.out.println("created " + l.getName());
		}
		stepList[k] = l;
		stepListLength++;

	}
	public void changeDepth(gameObject l, int k) {
		for(int i = 0;i != stepList.length;i++) {
			if(stepList[i].equals(l)) {
				stepList[i] = null;
				break;
			}
		}
		
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

	public static void resetStep() {
		System.out.println("i got here");
		stepList = new gameObject[20000];
		stepListLength = 0;
	}

}
