package GameState;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import Actor.HeroActor;
import Camera.Camera;
import TileMap.TileMap;

public class PlayState extends GameState {
	private Camera view;
	private TileMap tm;
	private HeroActor hero;

	public PlayState(GameStateManager gsm) {
		super(gsm);
		
		init();
	}
	
	@Override
	public void init() {
		tm = new TileMap();
		hero = new HeroActor(400, 200, tm);
		view = new Camera(tm, hero, 10);
	}

	@Override
	public void update() {
		hero.update();
		view.update();
	}
	
	@Override
	public void draw(Graphics2D g) {
		view.draw(g);
	}
	
	@Override
	public void keyPressed(int key) {
		hero.keyPressed(key);
	}
	@Override
	public void keyReleased(int key) {
		hero.keyReleased(key);
	}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseDragged(MouseEvent e) {}
}
