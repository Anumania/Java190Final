package javaFinal;

import java.awt.Color;
import java.awt.Graphics2D;

public class InvalidObject extends gameObject {
	public InvalidObject() {
		super();
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
