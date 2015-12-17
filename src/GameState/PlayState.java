package GameState;

import java.awt.Graphics2D;

import Camera.Camera;
import Input.PlayInputHandler;
import World.World;
import net.java.games.input.*;

public class PlayState extends GameState {
	private Camera view;
	private World world;

	public PlayState(GameStateManager gsm) {
		super(gsm);
		
		init();
		
		System.out.println("JInput version: " + Version.getVersion());
		ControllerEnvironment ce = ControllerEnvironment.getDefaultEnvironment();
		Controller[] cs = ce.getControllers();
		for (int i = 0; i < cs.length; i++)
			System.out.println(i + ". " + cs[i].getName() + ", " + cs[i].getType() );
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
