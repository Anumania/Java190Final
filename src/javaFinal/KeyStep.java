package javaFinal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
/**
 * this class handles all keyboard inputs and then has methods to access the information without hassle, maybe the class i am most proud of in how i structured.
 * @author jonah
 *
 */
public class KeyStep extends JFrame implements ActionListener {
	int keys;
	boolean[] keyArray = new boolean[200];
	boolean[] lastFrame = new boolean[200];
	int[] keyTime = new int[200];
	String keyboard_string = ""; // im really used to another programming language that looks like this for
									// this exact term, this is not a mistake.
	
	/**
	 * sets up all keyListeners to modify an array once a key is pressed.
	 * @param gamer Main game, this is needed to attach the keyListener to some object
	 */
	public KeyStep(GameGame gamer) {// im a wizard i guessed how this worked and i was r i g h t.

		KeyListener listener = new KeyListener() {

			@Override
			public void keyPressed(KeyEvent event) {
				// System.out.println("you pressed");
				// System.out.print(event);
				// System.out.println(event.getKeyCode()); // use this to find keycodes if the
				// constants arent doing it for you
				keys = event.getKeyCode();
				keyArray[event.getKeyCode()] = true;
				// timeArray[event.getKeyCode()]++;

			}

			public void keyReleased(KeyEvent event) {
				keys = 0;
				keyArray[event.getKeyCode()] = false;
				// timeArray[event.getKeyCode()]=0;
			}

			public void keyTyped(KeyEvent event) { // keytyped automatically uses windows's environment variables for
													// typing preferences.(i think) also keyTyped has different keycodes
													// for some reason, likely these align with ascii or unicode rather
													// than vk codes
				if (event.getKeyChar() == (char) 8) {// keycode for backspace, for some reason getKeyCode doesnt work
														// correctly and returns 0 no matter the key pressed
					// TODO ask teacher if you can define what certain operators do, example:
					// keyboard_string -= event.getKeyChar();
					if (keyboard_string.length() > 0) {
						keyboard_string = keyboard_string.substring(0, keyboard_string.length() - 1);// backspace counts
																										// as a
																										// character, be
																										// careful
					}
				}
				else {
					keyboard_string += event.getKeyChar();
				}
			}
		};
		gamer.addKeyListener(listener);
	
	}
/**
 * this should never be called, if it does, then something is wrong
 * @param e actionEvent
 */
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(
				"keylistener called actionPerformed (this shouldnt happen(you should probably let me know how you did this))");
	}
	/**
	 * this is practically only used to debug, but this returns the whole key Array, which has values for each key on the keyboard
	 * @return key array
	 */
	public int getKeys() {
		return keys;
	}
	/**
	 * returns weather or not a key is being pressed this frame
	 * @param keyType use KeyEvent constants for this
	 * @return Weather or not the key is pressed this frame
	 */
	public boolean getKey(int keyType) {
		return keyArray[keyType];
	}
	/**
	 * returns weather or not a key was pressed this frame, only returns true the very first frame a key was pressed.
	 * @param keyType use KeyEvent constants for this
	 * @return Weather or not the key was pressed this frame.
	 */
	public boolean getKeyPressed(int keyType) {

		if (keyTime[keyType] == 1) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * this is to insure that {@link KeyStep#getKeyPressed(int)} works correctly
	 */
	public void frameCount() { // this runs every frame.,.,. or should at least
		for (int i = 0; i != keyArray.length; i++) {
			if (keyArray[i]) {
				keyTime[i]++;
			} else {
				keyTime[i] = 0;
			}
		}
	}
	/**
	 * an extra addon, this records what the user has typed, and can be used in the place of a textbox.
	 * @return a string with the chars the user has typed.
	 */
	public String getKeyboardString() { // getter and setter except you cant really set it (you can since its a public
										// variable but i cant think of any reason to atm)
		return keyboard_string;
	}
	/**
	 * if you want the keyboard string to be blank
	 */
	public void resetKeyboardString() {
		keyboard_string = "";
	}
}





