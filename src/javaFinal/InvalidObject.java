package javaFinal;

import java.awt.Color;
import java.awt.Graphics2D;
/**
 * object that {@link Editor} can load and it wont break anything if the object index is incorrect
 * @author jonah
 *
 */
public class InvalidObject extends GameObject {
	/**
	 * {@inheritDoc}
	 */
	public InvalidObject() {
		super();
	}
	/**
	 * {@inheritDoc}
	 */
	public InvalidObject(int _x, int _y) {
		super(_x, _y);
	}
	/**
	 * {@inheritDoc}
	 */
	public void step() {
	}
	/**
	 * {@inheritDoc}
	 */
	public String getName() {
		return "InvalidObject";
	}
	/**
	 * {@inheritDoc}
	 * and it draws a red x so that i know something went wrong during loading.
	 */
	public void paint(Graphics2D g2d) {

		g2d.setColor(Color.RED);
		g2d.drawLine(x, y, xsize, ysize);
		g2d.drawLine(x, y + ysize, x + xsize, y);

	}

}
