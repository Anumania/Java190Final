package javaFinal;
//oversees everything
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
/**
 * the overseer should be alive at all times, and keeps track of many global variables, and also handles the console which i forgot about until right now
 * @author jonah
 *
 */
public class Overseer extends GameObject {
	boolean console;
	/**
	 * {@inheritDoc}
	 */
	public Overseer() {
		super();
	}
	/**
	 * {@inheritDoc}
	 */
	public Overseer(int hruhx, int hruhy) {
		super(hruhx,hruhy);
	}
	/**
	 * {@inheritDoc}
	 */
	public void create() {

	}
	
	/**
	 * {@inheritDoc}
	 * enables the console if tilde is pressed and has a fancy tenerary operator
	 */
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
	/**
	 * {@inheritDoc}
	 */
	public String getName() {
		return "Overseer";
	}
	/**
	 * {@inheritDoc}
	 */
	public void paint(Graphics2D g2d) {


	}
	/**
	 * draws fps and shows the console if the console is open
	 */
	public void paintGUI(Graphics2D g2d) {
		g2d.setColor(Color.BLACK);
		g2d.drawString(main.FPS + "", 20, 60);
		if (console) {
			g2d.drawString(keyListen.getKeyboardString(), 20, 90); // be careful, this draws from some weird coordinates
																	// :/

		}
	}
	/**
	 * handles all console commands as easily as i could make them, the only one that exists right now is editor, which doesnt do anything except smile at you
	 * how this is supposed to work is the parameter command will have the command itself, no arguments, while the args array would have all arguments.
	 * an example would be "console 3", where command would have "console" and args would have "3"
	 * @param command String of console input
	 * @param args all arguments
	 */
	public void consoleCommands(String command, String[] args) {
		if (command.contentEquals("editor")) {
			System.out.println("editor :)");
		}
		System.out.println(command);
		System.out.println("editor");
	}

}
