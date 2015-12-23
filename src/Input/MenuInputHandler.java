package Input;

import GameState.MenuState;

public class MenuInputHandler {
	private static MenuState ms;
	
	public static void passInput(Command c) {
		boolean toggle = c.getToggle();
		switch(c.getAction()) {
		case CANCEL:
			break;
		case FIRE:
			break;
		case INTERACT:
			if(toggle) ms.optionExec();
			break;
		case MENU:
			break;
		case LUP:
			if(toggle) ms.optionUp();
			break;
		case LDOWN:
			if(toggle) ms.optionDown();
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
	
	public static void setStateReference(MenuState ms) {
		MenuInputHandler.ms = ms;
	}
}
