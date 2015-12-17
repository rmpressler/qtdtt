package World;

import java.util.ArrayList;

import Actor.HeroActor;
import Camera.Renderable;
import TileMap.TileMap;

public class World {
	private TileMap tm;
	private HeroActor hero;
	
	private ArrayList<Renderable> renderables;
	
	public World(String location) {
		//Initialize fields
		renderables = new ArrayList<Renderable>();
		
		//Initialize tilemap
		tm = new TileMap(location);
		
		//Initialize actors
		hero = new HeroActor(400, 300, tm);
		renderables.add(hero);
	}
	
	public void update() {
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
}
