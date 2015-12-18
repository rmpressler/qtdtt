package World;

import java.util.ArrayList;

import AI.EnemyAI;
import Actor.HeroActor;
import Actor.TestEnemyActor;
import Camera.Renderable;
import TileMap.TileMap;

public class World {
	private TileMap tm;
	private HeroActor hero;
	
	private ArrayList<Renderable> renderables;
	private ArrayList<EnemyAI> aiControllers;
	
	public World(String location) {
		//Initialize fields
		renderables = new ArrayList<Renderable>();
		aiControllers = new ArrayList<EnemyAI>();
		
		//Initialize tilemap
		tm = new TileMap(location);
		
		//Initialize actors
		hero = new HeroActor(tm);
		hero.setStart(200, 400);
		renderables.add(hero);
		
		//Create 10 ai-controlled enemies
		for(int i = 0;i < 10; i++) {
			TestEnemyActor tea = new TestEnemyActor(tm);
			EnemyAI eai = new EnemyAI(tea, this);
			aiControllers.add(eai);
			renderables.add(tea);
		}
	}
	
	public void update() {
		for(int i = 0;i < aiControllers.size();i++) {
			aiControllers.get(i).update();
		}
		hero.update();
	}
	
	public TileMap getTileMap() {
		return tm;
	}
	
	public HeroActor getHero() {
		return hero;
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
}
