package GameState;

import java.awt.image.BufferedImage;

import Camera.Camera;
import Input.Command;
import Input.PlayInputHandler;
import World.World;

public class PlayState extends GameState {
	private Camera view;
	private World world;

	public PlayState(GameStateManager gsm) {
		super(gsm);
		
		init();
	}
	
	@Override
	public void init() {
		world = new World(".\\assets\\maps\\sandbox.map");
		view = new Camera(world, 10);
		PlayInputHandler.setWorld(world);
	}

	@Override
	public void update() {
		world.update();
		view.update();
	}
	
	@Override
	public BufferedImage getScreen() {
		return view.getScreen();
	}

	@Override
	public void passInput(Command command) {
		PlayInputHandler.passInput(command);
	}
}
