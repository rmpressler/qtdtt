package GameState;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GameStateManager {
	private ArrayList<GameState> states;
	
	private int currentState;
	
	public GameStateManager() {
		states = new ArrayList<GameState>();
		
		//List of states
		states.add(new PlayState(this));	// 0 PlayState
		states.add(new EditState(this));	// 1 EditState
		
		currentState = 0;
	}
	
	public void update() {
		states.get(currentState).update();
	}
	
	public void draw(Graphics2D g) {
		states.get(currentState).draw(g);
	}
	
	public void keyPressed(int key) {
		states.get(currentState).keyPressed(key);
	}
	
	public void keyReleased(int key) {
		states.get(currentState).keyReleased(key);
	}
	
	public void mousePressed(MouseEvent e) {
		states.get(currentState).mousePressed(e);
	}
	
	public void mouseReleased(MouseEvent e) {
		states.get(currentState).mouseReleased(e);
	}

	public void mouseDragged(MouseEvent e) {
		states.get(currentState).mouseDragged(e);
	}
}
