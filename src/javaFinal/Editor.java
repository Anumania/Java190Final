package javaFinal;

import java.awt.Color;

import java.awt.Graphics2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
/**
 * @author jonah
 * the crude beginnings of a level editor, maybe once i understand java more, this will work better, but at the moment it is a mess of cluttered ideas and different ways of doing the same thing: making a map that can be loaded at any point
 */
public class Editor extends GameObject {
	// new types should always be added at the end of this list, to not break
	// previous maps, as the maploader loads the objects by their index here.
	public final static Class gameObjectReference[] = { InvalidObject.class, GameObject.class, Overseer.class,
			Player.class,
			TemplateObject.class, Collision.class };
	/**
	 * just runs the superclass's constructor
	 */
	public Editor() {
		super();
	}
	/**
	 * no logic yet, there used to be stuff in here, but i removed it.
	 */
	public void step() {
		
	}
	/**
	 * works exactly the same as @see javaFinal.GameGame.getName()
	 * @deprecated
	 */
	public String getName() {
		return "Editor";	
	}
	/**
	 * does not do anything
	 * @param g2d see @see javaFinal.GameGame.paint(Graphics2D)
	 */
	public void paint(Graphics2D g2d) {
		//g2d.setColor(Color.BLUE);
		//g2d.drawRect(x, y, xsize, ysize);
		

	}
	/**
	 * also does not do anything
	 * @param g2d see @see javaFinal.GameGame.paint(Graphics2D)
	 */
	public void paintGUI(Graphics2D g2d) {
		
		g2d.setColor(Color.black);
		//g2d.drawString("test", 60, 60);
		//g2d.drawString("bruh bruh", 200, 200);
		
		// int a = conditional ? true : false; //this will return true or false depending on how the conditional goes
		//int a = (b == 0) ? 10 : 11 this is how this thing works im not actually sure what its called
	}
	/**
	 * incomplete, always returns true
	 * @return always returns true
	 */
	public boolean exportMap() {// returns true if works.

		return true;
	}

	static int index; // for nextChar
	/**
	 * reads map file and makes objects at the locations in the file, does not work correctly due to what i assume to be a math issue
	 * @param a file to use as map data
	 * @throws IOException when the map file does not exist or is broken in some way
	 */
	public static void readMapFile(File a) throws IOException {
		GameGame.resetStep();
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
	/**
	 * i used this when i was using a different IO library, now does nothing, but it is kept since i might use a different library later
	 * returns a char of a specified input, very useful for going through a file character by character.
	 * @param input the string
	 * @return the char at the location index specifies
	 */
	private static char nextChar(String input) {
		index++;
		return input.charAt(index - 1);

	}
	/**
	 * writes the map file that can be read from later, is static so any object can call it
	 * @param objList this is the stepList, it runs through the steplist and gets every object along with the x and y coordinates and then puts that information into a file, unfortunately i decided to do this in the worst way imaginable, but it was fun.
	 */
	public static void writeMapFile(GameObject[] objList) {
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
				System.out.println(objList[i].getName());
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
