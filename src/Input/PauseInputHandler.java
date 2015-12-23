package Input;

import GameState.GameStateManager;
import GameState.PauseState;

public class PauseInputHandler {
	private static GameStateManager gsm;
	private static PauseState ps;
	
	public static void passInput(Command c) {
		boolean toggle = c.getToggle();
		switch(c.getAction()) {
		case CANCEL:
			break;
		case FIRE:
			break;
		case INTERACT:
			if(toggle) ps.optionExec();
			break;
		case MENU:
			gsm.setState(GameStateManager.PLAYSTATE);
			break;
		case LUP:
			if(toggle) ps.optionUp();
			break;
		case LDOWN:
			if(toggle) ps.optionDown();
			break;
		case LLEFT:
			break;
		case LRIGHT:
			break;
		case OPTION:
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
		PauseInputHandler.gsm = gsm;
	}
	
	public static void setStateReference(PauseState ps) {
		PauseInputHandler.ps = ps;
	}
}
