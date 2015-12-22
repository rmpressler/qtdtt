package GameState;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Input.Command;

public class GameStateManager {
	private ArrayList<GameState> states;
	
	private int currentState;
	
	public static final int PLAYSTATE = 0;
	public static final int EDITSTATE = 1;
	
	public GameStateManager() {
		states = new ArrayList<GameState>();
		
		//List of states
		states.add(new PlayState(this));	// 0 PlayState
		states.add(new EditState(this));	// 1 EditState
		
		currentState = 0;
	}
	
	public int getCurrentState() {
		return currentState;
	}
	
	public void passInput(Command c) {
		states.get(currentState).passInput(c);
	}
	
	public void update() {
		states.get(currentState).update();
	}
	
	public BufferedImage getScreen() {
		return states.get(currentState).getScreen();
	}
}
