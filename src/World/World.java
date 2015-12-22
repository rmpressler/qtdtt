package World;

import java.util.ArrayList;

import AI.EnemyAI;
import Actor.HeroActor;
import Actor.TestEnemyActor;
import Camera.Renderable;
import Game.Config;
import Game.GameObject;
import Physics.Collidable;
import Physics.CollisionDetect;
import Physics.CollisionHandler;
import TileMap.TileMap;

public class World {
	private TileMap tm;
	private HeroActor hero;
	
	private ArrayList<Renderable> renderables;
	private CollisionDetect collider;
	private ArrayList<EnemyAI> aiControllers;
	
	public World(String location) {
		// Initialize fields
		renderables = new ArrayList<Renderable>();
		aiControllers = new ArrayList<EnemyAI>();
		collider = new CollisionDetect();
		CollisionHandler.setWorld(this);
		
		// Initialize tilemap
		tm = new TileMap(location);
		
		// Initialize actors
		hero = new HeroActor(tm);
		hero.setStart(200, 400);
		add(hero);
		
		// Create 10 ai-controlled enemies
		for(int i = 0;i < 10; i++) {
			TestEnemyActor tea = new TestEnemyActor(tm);
			EnemyAI eai = new EnemyAI(tea, this);
			aiControllers.add(eai);
			add(tea);
		}
	}
	
	public void update() {
		for(int i = 0;i < aiControllers.size();i++) {
			aiControllers.get(i).update();
		}
		for(int i = 0; i < renderables.size();i++) {
			Renderable r = renderables.get(i);
			if(r.getX() < 0 || r.getY() < 0 ||
					r.getX() > tm.getWidth() * Config.TILE_SIZE ||
					r.getY() > tm.getHeight() * Config.TILE_SIZE ||
					!r.isAlive()) {
				remove((GameObject)r);
				System.out.println("Removing object");
			}
			else r.update();
		}
		collider.update();
	}
	
	public TileMap getTileMap() {
		return tm;
	}
	
	public HeroActor getHero() {
		return hero;
	}
	
	public void add(GameObject o) {
		if(o.isRenderable()) {
			renderables.add((Renderable)o);
		}
		
		if(o.isCollidable()) {
			collider.add((Collidable)o);
		}
	}
	
	public void remove(GameObject o) {
		if(o.isRenderable()) {
			renderables.remove(o);
		}
		
		if(o.isCollidable()) {
			collider.remove((Collidable)o);
		}
	}
	
	public Renderable[] getRenderables() {
		Renderable[] renderArray = new Renderable[renderables.size()];
		for(int i = 0;i < renderables.size();i++) {
			renderArray[i] = renderables.get(i);
		}
		
		return renderArray;
	}
	
	public static String getDirection(int dx, int dy) {
		if(dx > 0 && dy == 0) {
			return "right";
		}
		else if(dx < 0 && dy == 0) {
			return "left";
		}
		else if(dy > 0 && dx == 0) {
			return "down";
		}
		else if(dy < 0 && dx == 0) {
			return "up";
		}
		else if(dx > 0 && dy > 0) {
			return "down_right";
		}
		else if(dx < 0 && dy > 0) {
			return "down_left";
		}
		else if(dx > 0 && dy < 0) {
			return "up_right";
		}
		else if(dx < 0 && dy < 0) {
			return "up_left";
		}
		else return null;
	}
	
	public static boolean isCompoundDirection(String direction) {
		if(direction.indexOf('_') > -1) {
			return true;
		}
		return false;
	}
	
	public static String[] Direction2to1(String direction) {
		if(isCompoundDirection(direction)) {
			return direction.split("_");
		}
		return null;
	}
	
	public static String getDirection(int dx, int dy, int tolerance) {
		if(dx > 0 && Math.abs(dy) < tolerance) {
			return "right";
		}
		else if(dx < 0 && Math.abs(dy) < tolerance) {
			return "left";
		}
		else if(dy > 0 && Math.abs(dx) < tolerance) {
			return "down";
		}
		else if(dy < 0 && Math.abs(dx) < tolerance) {
			return "up";
		}
		else if(dx > 0 && dy > 0) {
			return "down_right";
		}
		else if(dx < 0 && dy > 0) {
			return "down_left";
		}
		else if(dx > 0 && dy < 0) {
			return "up_right";
		}
		else if(dx < 0 && dy < 0) {
			return "up_left";
		}
		else return null;
	}

	public void addRenderableObject(Renderable r) {
		renderables.add(r);
	}
	
	public void addCollidableObject(Collidable c) {
		collider.add(c);
	}

	public void removeRenderableObject(Renderable r) {
		renderables.remove(r);
	}

	public void removeCollidableObject(Collidable c) {
		collider.remove(c);
	}
}
