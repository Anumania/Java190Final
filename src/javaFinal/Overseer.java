package javaFinal;
//oversees everything
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Overseer extends GameObject {
	boolean console;
	public Overseer() {
		super();
	}
	public Overseer(int hruhx, int hruhy) {
		super(hruhx,hruhy);
	}

	public void create() {

	}
	

	public void step() {
		System.out.println("test");
		if (keyListen.getKeyPressed(192)) {
			console = Util.toggle(console);
			keyListen.resetKeyboardString(); // dont even need to reset on true, will work either way
			System.out.println("console is " + (console ? "on" : "off")); // ternary operator, super advanced cool
																			// stuff, absolutely radical
		}
		if (keyListen.getKeyPressed(KeyEvent.VK_ENTER)) {
			keyListen.keyboard_string = keyListen.keyboard_string.substring(0, keyListen.keyboard_string.length() - 1);
			consoleCommands(keyListen.getKeyboardString(), null);
		}
	}
	public String getName() {
		return "Overseer";
	}
	public void paint(Graphics2D g2d) {


	}

	public void paintGUI(Graphics2D g2d) {
		g2d.setColor(Color.BLACK);
		g2d.drawString(main.FPS + "", 20, 60);
		if (console) {
			g2d.drawString(keyListen.getKeyboardString(), 20, 90); // be careful, this draws from some weird coordinates
																	// :/

		}
	}

	public void consoleCommands(String command, String[] args) {
		if (command.contentEquals("editor")) {
			System.out.println("editor :)");
		}
		System.out.println(command);
		System.out.println("editor");
	}

}
