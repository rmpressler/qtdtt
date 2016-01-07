package GameState;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Game.SaveFile;
import Input.Command;
import Player.Player;
import World.World;

public class GameStateManager {
	private ArrayList<GameState> states;
	
	private int currentState;
	
	public static final int PLAYSTATE = 0;
	public static final int PAUSESTATE = 1;
	public static final int MENUSTATE = 2;
	public static final int LOADSTATE = 3;
	public static final int INVENTORYSTATE = 4;
	
	public GameStateManager() {
		// Initialize State list
		states = new ArrayList<GameState>();
		
		// Build list of states
		states.add(new PlayState(this));		// 0 PlayState
		states.add(new PauseState(this));		// 1 PauseState
		states.add(new MenuState(this)); 		// 2 MenuState
		states.add(new LoadState(this)); 		// 3 LoadState
		states.add(new InventoryState(this)); 	// 4 InventoryState
		
		// Start on Menu
		currentState = 2;
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

	public GameState getState(int state) {
		return states.get(state);
	}
	
	public void setState(int state) {
		currentState = state;
	}
	
	public void setData(SaveFile sf) {
		PlayState ps = (PlayState)states.get(PLAYSTATE);
		ps.setData(sf);
	}
	
	public void startNewGame() {
		PlayState ps = (PlayState)states.get(PLAYSTATE);
		ps.newGame();
		
		currentState = PLAYSTATE;
	}
	
	public Player getPlayer() {
		PlayState ps = (PlayState)states.get(PLAYSTATE);
		return ps.getPlayer();
	}
	
	public World getWorld() {
		PlayState ps = (PlayState)states.get(PLAYSTATE);
		return ps.getWorld();
	}
}
