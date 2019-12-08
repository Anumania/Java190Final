package javaFinal;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class BeatMapMaker extends GameObject {
	public BeatMapMaker() {
		super();
	}

	public BeatMapMaker(int _x, int _y) {
		super(_x, _y);
	}
	public void create() {

	}
	public void step() {
		if (keyListen.getKeyPressed(KeyEvent.VK_F2)) {
			Editor.writeMapFile(GameGame.stepList);
		}
		if (keyListen.getKeyPressed(KeyEvent.VK_F1)) {
			GameGame.kill(this);

		}
	}
	public String getName() {
		return "BeatMapMaker";
	}
	public void paint(Graphics2D g2d) {

		g2d.setColor(Color.BLACK);
		// g2d.drawString("welcome to beatmap maker for testgame", 60, 60);

	}

}
