package javaFinal;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends gameObject {
	BufferedImage sprite;
	double angle = 0;
	double targetAngle = 0;
	double scale = 1;
	static Player me; // this is to allow for other objects to interact with player without foreaching
						// through the entire steplist

	public Player() {
		super();
		create();
	}

	public Player(int _x, int _y) {
		super(_x,_y);
		create();
	}

	public String getName() {
		return "Player";	
	}
	
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
	}
	
	
	public void step() {

		if (keyListen.getKey(KeyEvent.VK_LEFT)) {
			targetAngle = 270.0;
		}
		if (keyListen.getKey(KeyEvent.VK_RIGHT)) {
			targetAngle = 90.0;
		}
		if (keyListen.getKey(KeyEvent.VK_DOWN)) {
			targetAngle = 180.0;
		}
		if (keyListen.getKey(KeyEvent.VK_UP)) {
			if (targetAngle == 270.0) {
				targetAngle = 360.0;
			} 
			else {
			targetAngle = 0.0;
			}
		}
		if (keyListen.getKeyPressed(KeyEvent.VK_F1)) {

			new Overseer(20, 20);
		}
		if (keyListen.getKeyPressed(KeyEvent.VK_F2)) {
			Editor.writeMapFile(gameGame.stepList);
		}
		if (keyListen.getKeyPressed(KeyEvent.VK_F3)) {
			try {
				Editor.readMapFile(new File("./maps/map1.map"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (keyListen.getKey(KeyEvent.VK_Z)) {
			// TODO add the actual game part
			scale++;
		}

		angle += util.betterAngle(targetAngle, angle) * 30.0;
		angle %= 360;
		if (angle < 0.0) {
			angle += 360;
		}
		
		scale -= 0.1;
		scale = util.clamp(scale, 1.0, 1.5);
		if (main.timeInFrames % 60 == 3) {
			new Arrow((int) (Math.random() * 4), 5.0);

		}

	}

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

}
