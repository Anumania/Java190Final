package testGame;

import java.awt.Color;
import java.awt.Graphics2D;

public class Editor extends gameObject {
	// new types should always be added at the end of this list, to not break
	// previous maps.
	public final String gameObjectReference[] = {"gameObject", "Overseer", "Player", "TemplateObject"};

	// public final Object gameObjectObject[] =
	public Editor() {
		super();
	}

	public void step() {
		
	}
	public String getName() {
		return "Editor";	
	}
	public void paint(Graphics2D g2d) {
		//g2d.setColor(Color.BLUE);
		//g2d.drawRect(x, y, xsize, ysize);
		

	}
	public void paintGUI(Graphics2D g2d) {
		
		g2d.setColor(Color.black);
		//g2d.drawString("test", 60, 60);
		//g2d.drawString("bruh bruh", 200, 200);
		
		// int a = conditional ? true : false; //this will return true or false depending on how the conditional goes
		//int a = (b == 0) ? 10 : 11 this is how this thing works im not actually sure what its called
	}

	public boolean exportMap() {// returns true if works.
		return true;
	}

}
