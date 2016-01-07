package GameState;

import java.awt.image.BufferedImage;

import Input.Command;

public abstract class GameState {
	protected GameStateManager gsm;
	
	public GameState(GameStateManager gsm) {
		this.gsm = gsm;
	}
	
	public abstract void init();
	public abstract void update();
	public abstract BufferedImage getScreen();
	public abstract void passInput(Command command);
}
