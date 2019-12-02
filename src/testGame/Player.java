package testGame;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends gameObject {
	BufferedImage sprite;
	
	

	public Player() {
		super();
		create();
	}
	public Player(int _x, int _y) {
		super(_x,_y);
		create();
	}
	public String getName() {
		return "Player";	
	}
	
	public void create() {
		File rootName = new File("./sprites/Dinklet/Dinklet1.png");
		try {
			sprite = ImageIO.read(rootName);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		if (keyListen.getKeyPressed(KeyEvent.VK_F1)) {

			new Overseer();
		}
	}

	public void paint(Graphics2D g2d) {
		//g2d.setColor(Color.BLUE);
		g2d.drawImage(sprite, (int) x, (int) y, main);
		//g2d.drawRect(x, y, xsize, ysize);

	}

}
