package testGame;

public class Player extends gameObject {
	public Player(gameGame mainGame, keyStep _keyListen) {
		super(mainGame, _keyListen);
	}

	public void step() {
		System.out.println("libtard destroyed");
	}
}
