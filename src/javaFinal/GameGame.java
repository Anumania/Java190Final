package javaFinal;

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
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * 
 * @author Jonah Cathcart
 * 
 *         GameGame is the absolute beginning, this is where all magic happens,
 *         everything outside of GameGame interacts with it in some way
 */
public class GameGame extends JFrame implements ActionListener {
	double prevTime = 0;
	int fpsInProgress = 0;
	double timeUntilNextFPS = 0;
	double x;
	double y;
	double jumpVel = 0;
	private Timer timer;
	private int wait;
	Graphics offGraphics;
	Image offImage;
	int xDimension = 800;
	int yDimension = 600;
	static KeyStep keyListen; //all of these statics can and should be accessed by objects in order for the game to work correctly
	static GameObject stepList[] = new GameObject[20000]; // use this like depth yeah?
	static int stepListLength = 0;
	static GameGame mainGame; 
	static int camX = 0;
	static int camY = 0;
	boolean debug = false;
	boolean speedTest = true;
	int FPS; // usually finals are all caps, but FPS is usually like this
	int timeInFrames;
	static int BPM = 300;
	static int timeBetweenArrows = 60 / (BPM / 60); //
	static Instant lastTime = Instant.now();

	// 60/(bpm/60);
	
	


	/**
	 * a friend requested that i add the ability to change the BPM, and i tried to
	 * find a way to do it ingame and gave up, so if you give a number for a launch
	 * option, it takes it as a speed
	 * 
	 * @param args command line arguments, the first argument will be taken as game
	 *             speeds
	 */
	public static void main(String[] args) {
		if (args.length > 0) {
		BPM = Integer.parseInt(args[0]);
		timeBetweenArrows = 60 / (BPM / 60);
		}

		new GameGame();

	}

	/**
	 * The constructor passes the name of the game up to JFrame, and initializes
	 * everything that must work prior to the first frame
	 */
	public GameGame() {
		super("test game"); // this is the game title also this has to be first
		mainGame = this;
		

		// setIgnoreRepaint(true);
		x = 0.0;
		wait = 10;

		keyListen = new KeyStep(this);

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
		timer = new Timer(16, this); // this is what requires the need to implement actionlistener
		timer.start();


	}

	/**
	 * this system is highly inspired by another game engine that i work with, each
	 * game object has a method called: step, create, paint, and paintGUI. At the
	 * moment, each game object is placed on a massive array, and each frame, the
	 * array is looped through and it runs the step method on every object
	 */
	public void step() { 
		if (keyListen.getKey(KeyEvent.VK_RIGHT)) {
			camX++;
		}
		if (keyListen.getKey(KeyEvent.VK_LEFT)) {
			camX--;
		}
		timeBetweenArrows = 60 / (BPM / 60);
		if(debug) {
			for(int i = 0; i < stepList.length;i++) {	
				if(stepList[i] != null) {
					System.out.print(i + " " +stepList[i].getName() + " ");
				}
			}
			System.out.println();
		}
			
//			camX-=5;
		if (stepListLength != 0) {
			for (int i = 0; i < stepListLength; i++) {
				if (stepList[i] != null) {
					stepList[i].step();
				}
			}
		}
		
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Window#paint(java.awt.Graphics) my paint and Jframe paint work
	 *      identically, especially in GameObjects, this works like step but g2d is
	 *      passed to each object to draw onto, along with another layer for other
	 *      reasons. Also, every object, before they draw, have their x and y
	 *      positions shifted relative to the camera, this is in order to give the
	 *      illusion of camera movement.
	 */
	public void paint(Graphics g) { // the graphics object originates here, cant make your own, also this is called
		
		// automagically :)
		GameGame main = GameGame.mainGame;
		offImage = createImage(xDimension, yDimension);
		offGraphics = offImage.getGraphics();
		BufferedImage imageLayer = new BufferedImage(main.xDimension, main.yDimension, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g2d = (Graphics2D) offGraphics; // draw everything onto a seperate canvas
		g2d.setColor(new Color(0, 0, 0, Util.clamp(Player.combo * 10, 0, 255)));
		// g2d.setComposite(AlphaComposite.Clear);
		g2d.fillRect(0, 0, xDimension, yDimension); // wipe the previous screen
	
		//g2d.setBackground();
		if (stepListLength != 0) {
			for (int i = 0; i < stepListLength; i++) {
				if(stepList[i] != null) {
				stepList[i].x-= GameGame.camX; stepList[i].y-= GameGame.camY; //camera movement
				// TODO dont draw things outside of the screen border
				stepList[i].paint(g2d, imageLayer);
				stepList[i].x+= GameGame.camX; stepList[i].y+= GameGame.camY;
				}
			}
		}
		// paintGUI does not take camera movement into account and also draws ontop of
		// paint elements
		if (stepListLength != 0) {
			for (int i = 0; i < stepListLength; i++) { //camera movement
				{
					if (stepList[i] != null) {

				// TODO dont draw things outside of the screen border
				stepList[i].paintGUI(g2d);
					}
				}
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
	/**
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 *      actionPerformed is from Timer, which i only barely use. I could go
	 *      without this, but it could break something to remove right now
	 * 
	 *      Timing is done at ~6 lines down from here instead of using the setTimer
	 *      method because its too imprecise.
	 */
	public void actionPerformed(ActionEvent arg0) { // this runs when timer is done

		// while (lastTime.plus((long) 0.160,
		// ChronoUnit.MILLIS).isBefore(Instant.now())) {

		// }
		// lastTime-Instant.now();
		// Instant.now().un
		// long howLong = lastTime.until(Instant.now(), ChronoUnit.MILLIS);
		// this finds out how long until the next frame should play
		long howLong = Instant.now().until(lastTime.plus((long) (1000.0f / 60.0f), ChronoUnit.MILLIS),
				ChronoUnit.MILLIS);
		//System.out.println(Instant.now());
		//System.out.println(lastTime);
		howLong = Math.max(howLong, 0); // makes sure its not negative
		//System.out.println(howLong);
		//System.out.println(lastTime.until(Instant.now(), ChronoUnit.MILLIS));
		try {
			//Thread.sleep(5000);
			Thread.sleep(howLong); // waits for however long
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		lastTime = Instant.now();
		// System.out.println(Instant.now().compareTo(lastTime));
		// lastTime = Instant.now();
		// System.out.println(Instant.now().getNano());
		timeInFrames++;
		keyListen.frameCount();
		double i = arg0.getWhen() - prevTime;
		prevTime = arg0.getWhen();
		timeUntilNextFPS += i;

		fpsInProgress++;
		if (timeUntilNextFPS >= 1000) {
			System.out.println("fps: " + fpsInProgress);
			FPS = fpsInProgress;
			fpsInProgress = 0;
			timeUntilNextFPS = 0;

			
		}
		// calling the step and paint methods, which in turn call every other object's
		// step and paint methods
		step();
		repaint();

		 // this is roughly 60 fps
		if(speedTest) {
			timer.setDelay(0); // this used to read 16, but that would cause the game to run at 63 fps, and 17
								// would cause 57 fps.
		}

		

	}

	/**
	 * despite this incredibly poorly worded method looking simmilar to @see #step ,
	 * its job is to add whatever object is in the parameter to the stepList, which
	 * is then used by this class to run each event
	 * 
	 * @param object GameObject to add to the stepList
	 * @return the index that the object was placed, this is remembered by all
	 *         GameObjects so that they can use it to remove themselves from the
	 *         stepist.
	 */
	public int steps(GameObject object) {
		stepList[stepListLength] = object;
		stepListLength++;
		return stepListLength;
	}

	/**
	 * this method seems cool, being able to choose what order things are drawn in
	 * is definitely something important, but because of how the system works right
	 * now, it does not work correctly
	 * 
	 * @param l DONT USE
	 * 
	 * @param k THIS METHOD
	 */
	public void steps(GameObject l,int k) {
		if(stepList[k] != null) {
			System.out.println("created " + l.getName());
		}
		stepList[k] = l;
		stepListLength++;

	}

	/**
	 * same as @see #steps(GameObject,int), doesnt work for the same reason
	 * 
	 * @param l object to change depth of
	 * @param k index to change the depth to
	 */
	public void changeDepth(GameObject l, int k) {
		for(int i = 0;i != stepList.length;i++) {
			if(stepList[i].equals(l)) {
				stepList[i] = null;
				break;
			}
		}
		
	}

	/**
	 * i had an idea where i would make image "packs", but i see no advantage to
	 * that, so this sits here until i do see a reason to make an image pack
	 */
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

	/**
	 * reset the stepList, no objects, completely empty
	 */
	public static void resetStep() {
		System.out.println("everything has been reset");
		stepList = new GameObject[20000];
		stepListLength = 0;

	}

	/**
	 * removes the reference to the specified object from the stepList
	 * 
	 * @param object to remove from steplist
	 */
	public static void kill(GameObject object) {
		// System.out.println(stepList[1244]);
		stepList[object.stepNum - 1] = null;
	}

}
