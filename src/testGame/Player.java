package testGame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Player extends gameObject {
	public Player(gameGame mainGame, keyStep _keyListen) {
		super(mainGame, _keyListen);
	}

	public void step() {
		if (keyListen.getKey(KeyEvent.VK_LEFT)) {
			x--;
		}
		if (keyListen.getKey(KeyEvent.VK_RIGHT)) {
			x++;
		}
		if (keyListen.getKey(KeyEvent.VK_DOWN)) {
			y++;
		}
		if (keyListen.getKey(KeyEvent.VK_UP)) {
			y--;
		}
	}

	public void paint(Graphics2D g2d) {

		g2d.setColor(Color.BLUE);
		g2d.drawRect(x, y, xsize, ysize);

	}

}
