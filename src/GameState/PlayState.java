package GameState;

import java.awt.Graphics2D;

import Camera.Camera;
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
	public void draw(Graphics2D g) {
		view.draw(g);
	}
}
