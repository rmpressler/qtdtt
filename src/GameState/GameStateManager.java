package GameState;

import java.awt.Graphics2D;
import java.util.ArrayList;

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
	
	public void update() {
		states.get(currentState).update();
	}
	
	public void draw(Graphics2D g) {
		states.get(currentState).draw(g);
	}
}
