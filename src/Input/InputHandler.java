package Input;

import GameState.GameStateManager;

public class InputHandler {
	private static GameStateManager gsm;
	
	public static void setStateManager(GameStateManager newGsm) {
		gsm = newGsm;
	}
	
	public static void setToggle1(boolean toggle) {
		if(gsm.getState() == GameStateManager.PLAYSTATE) {
			PlayInputHandler.setToggle1(toggle);
		}
	}
	
	public static void setToggle2(boolean toggle) {
		if(gsm.getState() == GameStateManager.PLAYSTATE) {
			PlayInputHandler.setToggle2(toggle);
		}
	}
	
	public static void setToggle3(boolean toggle) {
		if(gsm.getState() == GameStateManager.PLAYSTATE) {
			PlayInputHandler.setToggle3(toggle);
		}
	}
	
	public static void setToggle4(boolean toggle) {
		if(gsm.getState() == GameStateManager.PLAYSTATE) {
			PlayInputHandler.setToggle4(toggle);
		}
	}
	
	public static void activateAction0() {
		if(gsm.getState() == GameStateManager.PLAYSTATE) {
			PlayInputHandler.activateAction0();
		}
	}
	
	public static void activateAction1() {
		if(gsm.getState() == GameStateManager.PLAYSTATE) {
			//
		}
	}
}
