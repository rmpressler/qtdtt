package Input;

import GameState.GameStateManager;
import GameState.InventoryState;

public class InventoryInputHandler {
	private static GameStateManager gsm;
	private static InventoryState is;
	
	public static void passInput(Command c) {
		boolean toggle = c.getToggle();
		switch(c.getAction()) {
		case CANCEL:
			gsm.setState(GameStateManager.PLAYSTATE);
			break;
		case FIRE:
			break;
		case INTERACT:
			break;
		case MENU:
			gsm.setState(GameStateManager.PLAYSTATE);
			break;
		case LUP:
			if(toggle) is.cursorUp();
			break;
		case LDOWN:
			if(toggle) is.cursorDown();
			break;
		case LLEFT:
			if(toggle) is.cursorLeft();
			break;
		case LRIGHT:
			if(toggle) is.cursorRight();
			break;
		case OPTION:
			gsm.setState(GameStateManager.PLAYSTATE);
			break;
		case RDOWN:
			break;
		case RLEFT:
			break;
		case RRIGHT:
			break;
		case RUP:
			break;
		default:
			break;
		}
	}
	
	public static void setGSM(GameStateManager gsm) {
		InventoryInputHandler.gsm = gsm;
	}
	
	public static void setStateReference(InventoryState is) {
		InventoryInputHandler.is = is;
	}
}
