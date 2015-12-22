package Input;

import GameState.GameStateManager;

public class InputHandler {
	private static GameStateManager gsm;
	
	public static void setStateManager(GameStateManager newGsm) {
		gsm = newGsm;
	}
	
	public static void passInput(Command c) {
		gsm.passInput(c);
	}
}
