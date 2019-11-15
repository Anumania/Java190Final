package testGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class keyStep extends JFrame implements ActionListener {
	int keys;
	boolean[] keyArray = new boolean[200];
	boolean[] lastFrame = new boolean[200];
	int[] keyTime = new int[200];
	

	public keyStep(gameGame gamer) {// im a goddamn wizard i guessed how this worked and i was r i g h t.

		KeyListener listener = new KeyListener() {

			@Override
			public void keyPressed(KeyEvent event) {
				// System.out.println("you pressed");
				// System.out.print(event);
				// System.out.println(event.getKeyCode());
				keys = event.getKeyCode();
				keyArray[event.getKeyCode()] = true;
				// timeArray[event.getKeyCode()]++;

			}

			public void keyReleased(KeyEvent event) {
				keys = 0;
				keyArray[event.getKeyCode()] = false;
				// timeArray[event.getKeyCode()]=0;
			}

			public void keyTyped(KeyEvent event) {
				// System.out.println("you typed");
				// int e = KeyEvent.VK_A; //in order to turn the ints that this returns into
				// input, use KeyEvent.VK_A, the int that this returns is likely like 20 or
				// something but use these
				// event.getKeyChar();
				//keys = event.getKeyInt()

				// System.out.println(event.getKeyCode());
				// System.out.println(event.getKeyChar());


			}
		};
		gamer.addKeyListener(listener);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(
				"keylistener called actionPerformed (this shouldnt happen(you should probably let me know how you did this))");

	}
	
	public int getKeys() {
		return keys;
	}

	public boolean getKey(int keyType) {
		return keyArray[keyType];
	}

	public boolean getKeyPressed(int keyType) {

		if (keyTime[keyType] == 1) {
			return true;
		}
		else {
			return false;
		}
	}


	public void frameCount() { // this runs every frame.,.,. or should at least
		for (int i = 0; i != keyArray.length; i++) {
			if (keyArray[i]) {
				keyTime[i]++;
			} else {
				keyTime[i] = 0;
			}
		}
	}
}





