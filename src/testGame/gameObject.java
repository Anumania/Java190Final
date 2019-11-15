package testGame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class gameObject {
	gameGame main;
	keyStep keyListen;
	int x;
	int y;
	int ysize;;
	int xsize; // this is used for hitboxes
	

	public gameObject(gameGame mainGame, keyStep _keyListen) {
		x = 200;
		y = 200;
		ysize = 20;
		xsize = 20;
		keyListen = _keyListen;
		main = mainGame;
		main.steps(this);

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
	
	public static boolean checkCollision(gameObject obj1, gameObject obj2) {// check if object2 and object1 collide at
																			// any point.
		if(obj1.x < obj2.x ) {
			return true;
		}
		else {
			return false;
		}
	}
}

	


