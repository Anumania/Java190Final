package javaFinal;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
public class Editor extends gameObject {
	// new types should always be added at the end of this list, to not break
	// previous maps, as the maploader loads the objects by their index here.
	public final static Class gameObjectReference[] = { InvalidObject.class, gameObject.class, Overseer.class,
			Player.class,
			TemplateObject.class, Collision.class };

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

	static int index; // for nextChar
	public static void readMapFile(File a) throws IOException {
		gameGame.resetStep();
		index = 0;
		InputStream fileIn = new FileInputStream(a);
		int objType;
		int max = fileIn.read();
		for (int i = 0; i <= max / 5; i++) {
			objType = fileIn.read();
			System.out.println(objType);
			int objX = (fileIn.read() * 256) + fileIn.read();
			int objY = (fileIn.read() * 256) + fileIn.read();
			System.out.println(objX + " " + objY);
			try {
				gameObjectReference[objType].newInstance();
			} catch (InstantiationException e) {
				System.out.println("cant create class");
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				System.out.println("error:accessed incorrectly");
				e.printStackTrace();
			}

		}
	}

	private static char nextChar(String input) {
		index++;
		return input.charAt(index - 1);

	}

	public static void writeMapFile(gameObject[] objList) {
		File outFile = new File("./maps/map1.map");
		FileWriter fWriter = null;
		PrintWriter pWriter;
		try {
			fWriter = new FileWriter(outFile);
		} catch (IOException e) {
			System.out.println("error occured when finding write map file");
			e.printStackTrace();
		}

		pWriter = new PrintWriter(fWriter);
		int quantity = 0;
		String finalOut = "";
		for (int i = 0; i < objList.length; i++) {
			if (objList[i] != null) {
				quantity++;
				for (int j = 0; j < gameObjectReference.length; j++) {
					if (gameObjectReference[j] == objList[i].getClass()) {
						finalOut += (char) j;
					}
				} 
				System.out.println(objList[i].x + " " + objList[i].y);
				finalOut += (char) (int) (Math.floor(objList[i].x / 256.0));
				finalOut += (char) (int) (objList[i].x % 256.0);
				finalOut += (char) (int) (Math.floor(objList[i].y / 256.0));
				finalOut += (char) (int) (objList[i].y % 256.0);
				// split the x coord into 2 bytes, this allows for x values larger than 256

			}
		}
		pWriter.print((char) quantity); // this lets the writer know how many entries are in
		pWriter.print(finalOut);
		pWriter.close();
	}

}
