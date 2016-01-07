package GameState;

import java.awt.image.BufferedImage;

import Camera.Camera;
import Game.SaveFile;
import Input.Command;
import Input.PlayInputHandler;
import Physics.CollisionHandler;
import Player.Player;
import World.World;

public class PlayState extends GameState {
	private Camera view;
	private World world;
	private Player player;

	public PlayState(GameStateManager gsm) {
		super(gsm);
		
		init();
	}
	
	@Override
	public void init() {
		
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
	
	public void newGame() {
		world = new World(".\\assets\\maps\\sandbox.map");
		world.setNewData();
		view = new Camera(world, 10);
		player = new Player();
		PlayInputHandler.setWorld(world);
		PlayInputHandler.setGSM(gsm);
		CollisionHandler.setPlayer(player);
	}
	
	public void setData(SaveFile sf) {
		world = new World(".\\assets\\maps\\sandbox.map");
		world.setData(sf);
		view = new Camera(world, 10);
		player = new Player();
		player.setInventory(sf.getInventory());
		//template | player.getInventory().add(item)
		PlayInputHandler.setWorld(world);
		PlayInputHandler.setGSM(gsm);
		CollisionHandler.setPlayer(player);
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public World getWorld() {
		return world;
	}
}
