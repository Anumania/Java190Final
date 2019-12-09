package javaFinal;

import java.awt.Color;
import java.awt.Graphics2D;

/*
 * this is spawned when the game incorrectly loads a map, does nothing but is a big red box to show that something messed up
 * @see javaFinal.GameObject for information on all of these methods
 */
public class InvalidObject extends GameObject {
	public InvalidObject() {
		super();
	}

	public InvalidObject(int _x, int _y) {
		super(_x, _y);
	}

	public void step() {
	}
	public String getName() {
		return "InvalidObject";
	}
	public void paint(Graphics2D g2d) {

		g2d.setColor(Color.RED);
		g2d.drawLine(x, y, xsize, ysize);
		g2d.drawLine(x, y + ysize, x + xsize, y);

	}

}
