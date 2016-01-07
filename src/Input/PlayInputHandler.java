package Input;

import GameState.GameStateManager;
import World.World;
import static GameState.GameStateManager.*;

public class PlayInputHandler {
	public static World world;
	public static GameStateManager gsm;
	
	public static void passInput(Command c) {
		boolean toggle = c.getToggle();
		switch(c.getAction()) {
		case CANCEL:
			break;
		case FIRE:
			world.getHero().fire(world);
			break;
		case INTERACT:
			break;
		case MENU:
			gsm.setState(GameStateManager.PAUSESTATE);
			break;
		case LUP:
			if(toggle) world.getHero().startMove("up");
			else world.getHero().stopMove("up");
			break;
		case LDOWN:
			if(toggle) world.getHero().startMove("down");
			else world.getHero().stopMove("down");
			break;
		case LLEFT:
			if(toggle) world.getHero().startMove("left");
			else world.getHero().stopMove("left");
			break;
		case LRIGHT:
			if(toggle) world.getHero().startMove("right");
			else world.getHero().stopMove("right");
			break;
		case OPTION:
			if(toggle) gsm.setState(INVENTORYSTATE);
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
	
	public static void setWorld(World newWorld) {
		world = newWorld;
	}
	
	public static void setGSM(GameStateManager gsm) {
		PlayInputHandler.gsm = gsm;
	}
}
