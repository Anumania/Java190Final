package javaFinal;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
/**
 * 
 * @author jonah
 * a cool idea i had, but did not have enough time to finish, will likely finish in the coming months.
 */
public class BeatMapMaker extends GameObject {
	/**
	 * {@inheritDoc}
	 */
	public BeatMapMaker() {
		super();
	}
	/**
	 * {@inheritDoc}
	 */
	public BeatMapMaker(int _x, int _y) {
		super(_x, _y);
	}
	/**
	 * empty create
	 */
	public void create() {

	}
	/**
	 * nothing much happens here
	 */
	public void step() {
		if (keyListen.getKeyPressed(KeyEvent.VK_F2)) {
			Editor.writeMapFile(GameGame.stepList);
		}
		if (keyListen.getKeyPressed(KeyEvent.VK_F1)) {
			GameGame.kill(this);

		}
	}
	/**
	 * {@inheritDoc}
	 */
	public String getName() {
		return "BeatMapMaker";
	}
	/**
	 * {@inheritDoc}
	 */
	public void paint(Graphics2D g2d) {

		g2d.setColor(Color.BLACK);
		// g2d.drawString("welcome to beatmap maker for testgame", 60, 60);

	}

}
