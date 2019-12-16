package javaFinal;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * the player of the beatgame, he cant move, but is supposed to be the first object to be created so he draws first.
 * @author jonah
 *
 */
public class Player extends GameObject {
	BufferedImage sprite;
	double angle = 0;
	double direction = 0;
	double scale = 1;
	static int timeSinceAction = 0;
	static int combo;
	static double comboMult = 1.0;
	static int score = 0;
	boolean hardmode = false;
	static Player me; // this is to allow for other objects to interact with player without foreaching
						// through the entire steplist
	private boolean changeBPM = false;
	/**
	 * {@inheritDoc}
	 */
	public Player() {
		super();
		create();
	}
	/**
	 * {@inheritDoc}
	 */
	public Player(int _x, int _y) {
		super(_x,_y);
		create();
	}
	/**
	 * {@inheritDoc}
	 */
	public String getName() {
		return "Player";	
	}
	/**
	 * places the player at the center of the screen, sets his sprite, and creates the combo counter object
	 */
	public void create() {
		me = this;
		xsize = 32;
		ysize = 32;
		y = main.yDimension / 2;
		x = main.xDimension / 2;

		File rootName = new File("./sprites/Player.png");
		try {
			sprite = ImageIO.read(rootName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		new ComboCounter(550,50);
	}
	
	/**
	 * handles all input for the player with some extra inputs in there for debugging.
	 */
	public void step() {
		if (score == Integer.MAX_VALUE) {
			System.exit(69);
		}

		if (keyListen.getKeyPressed(KeyEvent.VK_J)) {
			direction = 270.0;
			if (hardmode) {
				timeSinceAction = 0;
				scale += 3;
			}
		}
		if (keyListen.getKeyPressed(KeyEvent.VK_L)) {
			direction = 90.0;
			if (hardmode) {
				timeSinceAction = 0;
				scale += 3;
			}
		}
		if (keyListen.getKeyPressed(KeyEvent.VK_K)) {
			direction = 180.0;
			if (hardmode) {
				timeSinceAction = 0;
				scale += 3;
			}
		}
		if (keyListen.getKeyPressed(KeyEvent.VK_I)) {
				direction = 0.0;
			if (hardmode) {
				timeSinceAction = 0;
				scale += 3;
			}

		}
		if (keyListen.getKeyPressed(KeyEvent.VK_F1)) {

			new Overseer(20, 20);
		}
		if (keyListen.getKeyPressed(KeyEvent.VK_F2)) {
			Editor.writeMapFile(GameGame.stepList);
		}
		if (keyListen.getKeyPressed(KeyEvent.VK_F3)) {
			try {
				Editor.readMapFile(new File("./maps/map1.map"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(keyListen.getKeyPressed(KeyEvent.VK_F12)) {
			if(!changeBPM) {
				keyListen.resetKeyboardString();
				changeBPM = true;
			}
			else {
				main.BPM = Integer.parseInt(keyListen.keyboard_string);
				changeBPM = false;
			}
		}
		timeSinceAction++;
		if (keyListen.getKeyPressed(KeyEvent.VK_Z) || keyListen.getKeyPressed(KeyEvent.VK_SPACE)) {
			// TODO add the actual game part
			if (!hardmode) {
			timeSinceAction = 0;
			scale += 3;
			}
		}
		if (keyListen.getKeyPressed(KeyEvent.VK_F5)) {
			/*
			 * GameGame.resetStep(); new BeatMapMaker();
			 */
		}
		angle += Util.betterAngle(direction, angle) * 30.0;
		angle %= 360;
		if (angle < 0.0) {
			angle += 360;
		}
		
		scale -= 0.1;
		scale = Util.clamp(scale, 1.0, 1.5);
		if (main.timeInFrames % GameGame.timeBetweenArrows == 0) {
			int result = (int) (Math.random() * 11);
			if (result < 11) {
				new Arrow((int) (Math.random() * 4), 5.0);
			}
			else if (result < 11) {
				new BounceArrow((int) (Math.random() * 4), 5.0);
			}
		}
	}
	/**
	 * {@inheritDoc}
	 */
	public void paint(Graphics2D g2d, BufferedImage imageLayer) {
		//g2d.setColor(Color.BLUE);
		// source: https://stackoverflow.com/questions/8639567/java-rotating-images
		
		AffineTransform a = new AffineTransform(); // AffineTransform.getRotateInstance(angle, x + xsize / 2, y + ysize
													// / 2);
		a.translate(x + xsize / 2, y + ysize / 2);
		a.rotate(Math.toRadians(angle)); // S2: rotate around anchor
		a.translate(-(x + xsize / 2), -(y + ysize / 2));
		a.translate(x + xsize / 2.0, y + ysize / 4.0);
		a.scale(scale, scale);
		a.translate(-(x + xsize / 2), -(y + ysize / 4));


		// origin

		g2d.setTransform(a);
		g2d.drawImage(sprite, (int) x, (int) y, main);
		g2d.setTransform(new AffineTransform());
		// g2d.setColor(Color.black);
		// g2d.drawString(scale + "", 60, 60);


		// g2d.setTransform(null);
		// AffineTransform testy = new AffineTransform();
		//g2d.drawRect(x, y, xsize, ysize);

	}
	/**
	 * {@inheritDoc}
	 */
	public void paintGUI(Graphics2D g2d) {
		g2d.setColor(Color.BLACK);
		g2d.drawString(main.FPS+"", 60, 60);
		g2d.drawString(main.BPM + "", 30, 60);

		// g2d.setFont(Font.);
		g2d.drawString(score + "", 600, 500);
		g2d.drawString(removeFloatingPointError(comboMult) + "x", 600, 530);
		g2d.drawString(
				"youre " + 100 * removeFloatingPointError((double) (score) / (double) Integer.MAX_VALUE) + "% there",
				600, 560);

	}
	/**
	 * when the combo is increased, so is the multiplier, and the comboCounter also does a bit of a hop to indicate the combo increasing
	 * @param precision how good did you hit? max 500 min 100
	 */
	public static void increaseCombo(double precision) {
		combo++;
		ComboCounter.scale = 1.7;
		score += (precision * comboMult);
		comboMult *= 1.03;
	}
	public static double removeFloatingPointError(double a) {
		a *= 10000;
		a = Math.ceil(a);
		a /= 10000;
		return a;
	}

}
