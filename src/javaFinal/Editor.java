package javaFinal;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class Editor extends gameObject {
	// new types should always be added at the end of this list, to not break
	// previous maps, as the maploader loads the objects by their index here.
	public final String gameObjectReference[] = { "InvalidObject", "gameObject", "Overseer", "Player", "TemplateObject",
			"Collision" };

	/// asdas
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

	public static void readMapFile(File a) throws IOException {
		Scanner fileIn = new Scanner(a);
		while (fileIn.hasNextLine()) {

		}
	}

	public static void writeMapFile(gameObject[] objList) {
		String finalOut = "";
		for (int i = 0; i < objList.length; i++) {
			if (objList[i] != null) {
				if (objList[i].getName().equals("gameObject")) {
					finalOut += (char) 1;
				} else if (objList[i].getName().equals("Overseer")) {
					finalOut += (char) 2;
				} else if (objList[i].getName().equals("Player")) {
					finalOut += (char) 3;
				} else if (objList[i].getName().equals("TemplateObject")) {
					finalOut += (char) 4;
				} else if (objList[i].getName().equals("Collision")) {
					finalOut += (char) 5;
				} else {
					finalOut += (char) 0;// invalid object, wont crash at startup
				}
				finalOut += (char) (int) (Math.floor(objList[i].x / 256.0));
				finalOut += (char) (int) (objList[i].x % 256.0);
				finalOut += (char) (int) (Math.floor(objList[i].y / 256.0));
				finalOut += (char) (int) (objList[i].y % 256.0);
				// split the x coord into 2 bytes, this allows for x values larger than 256

			}
		}
		System.out.println(finalOut);
	}

}
