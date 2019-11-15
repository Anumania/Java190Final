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
	keyStep keyListen;
	gameObject stepList[] = new gameObject[200];
	int stepListLength = 0;
	gameObject bronky;
	gameObject testobj;

	// keyStep keyInput = new keyStep();
	public static void main(String[] args) {

		new gameGame();

	}

	public gameGame() {
		super("test game"); // this is the game title also this has to be first
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

		timer = new Timer(wait, this); // this is what requires the need to implement actionlistener
		timer.start();

	}

	public void step() { // use keyListen.getKey(KeyEvent.(your key here(usually starts with VK_))
							// followed by whatever you want to happen
		// for (gameObject i : stepList) {

		if (stepListLength != 0) {

			for (int i = 0; i < stepListLength - 1; i++) {
				stepList[i].step();

			}

		}

		if (keyListen.getKeyPressed(KeyEvent.VK_UP)) { // use this, other option sucks more
			jumpVel -= 10;
		}
		if (keyListen.getKey(KeyEvent.VK_LEFT)) { // use this, other option sucks more
			x -= 3;
		}
		if (keyListen.getKey(KeyEvent.VK_RIGHT)) { // use this, other option sucks more
			x += 3;
			// System.out.print(keyListen.keyDownLength(KeyEvent.VK_RIGHT));
		}
		if (keyListen.getKeyPressed(KeyEvent.VK_F1)) {
			gameObject testobj = new gameObject(this, keyListen);
		}
		if (keyListen.getKeyPressed(KeyEvent.VK_F2)) {
			gameObject bronky = new gameObject(this, keyListen);
		}
		if (keyListen.getKey(KeyEvent.VK_F3)) {
			// if (gameObject.checkCollision(bronky, testobj)) {
				Player player = new Player(this, keyListen);
			// }
		}
		if (y + 200 >= yDimension && jumpVel > 0) {
			jumpVel = 0;
			y = yDimension - 200;
		} else {
			jumpVel += 1;
		}
		y += jumpVel;
	}


	public void paint(Graphics g) { // the graphics object originates here, cant make your own, also this is called

		// automagically :)
		offImage = createImage(xDimension, yDimension);
		offGraphics = offImage.getGraphics();

		Graphics2D g2d = (Graphics2D) offGraphics; // draw everything onto a seperate canvas
		g2d.setColor(Color.white);
		g2d.fillRect(0, 0, xDimension, yDimension); // wipe the previous screen

		if (stepListLength != 0) {

			for (int i = 0; i < stepListLength - 1; i++) {
				stepList[i].paint(g2d);

			}

		}

		// repaint(); // this line makes it paint many times :))) very good
		g.drawImage(offImage, 0, 0, this); // draw the seperate canvas onto the screen, removing flickering.
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

		wait--;
		wait = Math.max(wait, 2);
		timer.setDelay(16); // this is roughly 60 fps


	}

	public void steps(gameObject l) {

		stepList[stepListLength] = l;
		stepListLength++;

	}

}