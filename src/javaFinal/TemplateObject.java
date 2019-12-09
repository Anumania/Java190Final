package javaFinal;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/*
 * template object to copy for new objects
 */
public class TemplateObject extends GameObject {
	/*
	 * constructor which just runs super
	 */
	public TemplateObject() {
		super();
	}

	/*
	 * constructor
	 */
	public TemplateObject(int _x, int _y) {
		super(_x, _y);
	}
	public void create() {
		try {
			objectImage = ImageIO.read(new File("./sprites/default.png"));
		} catch (IOException e) {
			System.out.println("read of default sprite failed, make new image called default.png please :)");
			e.printStackTrace();
		}
	}
	public void step() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javaFinal.GameObject#getName() deprecated in the place of a better
	 * system,
	 */
	@Deprecated
	public String getName() {
		return "TemplateObject";	
	}

	/*
	 * @see javaFinal.GameObject#paint()
	 * 
	 */
	public void paint(Graphics2D g2d) {

		g2d.setColor(Color.BLUE);
		g2d.drawImage(objectImage, x, y, main);

	}

}
