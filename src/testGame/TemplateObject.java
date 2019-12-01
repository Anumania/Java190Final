package testGame;

import java.awt.Color;
import java.awt.Graphics2D;

public class TemplateObject extends gameObject {
	public TemplateObject() {
		super();
	}

	public void step() {
	}
	public String getName() {
		return "TemplateObject";	
	}
	public void paint(Graphics2D g2d) {

		g2d.setColor(Color.BLUE);
		g2d.drawRect(x, y, xsize, ysize);

	}

}
