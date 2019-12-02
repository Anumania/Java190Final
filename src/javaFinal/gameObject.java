package javaFinal;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class gameObject {
	gameGame main;
	keyStep keyListen;
	int x;
	int y;
	int ysize;
	int xsize; // this is used for hitboxes
	boolean alive = false;
	BufferedImage objectImage;
	

	public gameObject() {
		x = 200;
		y = 200;
		try {
			objectImage = ImageIO.read(new File("./sprites/default.png"));
		} catch (IOException e) {
			System.out.println("read of default sprite failed, make new image called default.png please :)");
			e.printStackTrace();
		}
		xsize = 20;
		keyListen = gameGame.keyListen;
		main = gameGame.mainGame;
		main.steps(this); //place this object into the steps list, which is a list of every object in the game
		alive = true;
		create();
		

	}

	public gameObject(int _x, int _y) {// this is to allow gameObjects to be initialized at a certain place, the create
										// method is mainly supposed to be overridden, allowing gameObjects to
										// initialize variables inside an "event" rather than in their constructors,
										// which they have 2 of
		x = _x;
		y = _x;
		ysize = 20;
		xsize = 20;
		keyListen = gameGame.keyListen;
		main = gameGame.mainGame;
		main.steps(this); //place this object into the steps list, which is a list of every object in the game
		alive = true;
		// question: if i run super on a child, what will happen on this next line? even
		// if the child class overrides create?
		// answer: it executes the child class create, niiice
		create();
	}

	public void create() {
		System.out.println(
				"superclass executed create! you should override this by making a create method in your gameobject child");
	}

	public String getName() {
		return "gameObject";	
	}
	
	public void step() {
		if (keyListen.getKey(KeyEvent.VK_LEFT)) {
			x--;
		}
		if (keyListen.getKey(KeyEvent.VK_RIGHT)) {
			x++;
		}
		if (keyListen.getKey(KeyEvent.VK_UP)) {
			y--;
		}
		if (keyListen.getKey(KeyEvent.VK_DOWN)) {
			y++;
		}
	}

	public void paint(Graphics2D g2d) { // it is expected that all objects with step will have paint

		// System.out.println(wait); // yeah, you only print once

		// casting graphics to graphics2d to use graphics2d

		
		g2d.setColor(Color.BLACK);
		g2d.drawRect(x, y, xsize, ysize);

		// x += 0.01;

	}
	public void paintGUI(Graphics2D g2d) {
		
		
	}
	
	public static boolean checkCollision(gameObject obj1, gameObject obj2) {// check if object2 and object1 collide at
		boolean xCol = false;																	// any point.
		boolean yCol = false;

		if (obj1.x > obj2.x && obj1.x < obj2.x + obj2.xsize) {xCol = true;} 
		if (obj1.x + obj1.xsize > obj2.x && obj1.x < obj2.x) {xCol = true;}

		if (obj1.y > obj2.y && obj1.y < obj2.y + obj2.ysize) {yCol = true;}
		if (obj1.y + obj1.ysize > obj2.y && obj1.y < obj2.y) {yCol = true;}

		if(xCol && yCol) {
		return true;
		}
		return false;
	}
	public static boolean checkCollision(int targx, int targy,gameObject obj) { //check if you want a specific point if collisioned 
		boolean xCol = false;																	
		boolean yCol = false;

		if (targx > obj.x && targx < obj.x + obj.xsize) {xCol = true;}
		if (targy > obj.y && targy < obj.y + obj.ysize) {yCol = true;}


		if(xCol && yCol) {
		return true;
		}
		return false;
	
	}	
}

	


