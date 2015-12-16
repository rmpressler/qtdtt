package Camera;

import java.awt.Graphics2D;

import Actor.HeroActor;
import Game.Config;
import TileMap.TileMap;

public class Camera {
	private int x;
	private int y;
	private int width;
	private int height;
	
	private HeroActor hero;
	private TileMap tm;
	private int actorX;
	private int actorY;
	
	public Camera(TileMap tm, HeroActor hero, int size) {
		this.tm = tm;
		this.hero = hero;
		this.x = hero.getX();				//x location on tilemap in pixels
		this.y = hero.getY();				//y location on tilemap in pixels
		this.width = size;
		this.height = size;
	}
	
	public void update() {
		actorX = hero.getX();
		actorY = hero.getY();
		
		x = actorX - ((width * Config.TILE_SIZE) / 2);
		y = actorY - ((height * Config.TILE_SIZE) / 2);
		
		//If x or y are out of bounds, force them back in
		if(x < 0) x = 0;
		if(y < 0) y = 0;
		if(x > (tm.getWidth() - width) * Config.TILE_SIZE) {
			x = (tm.getWidth() - width) * Config.TILE_SIZE;
		}
		if(y > (tm.getHeight() - height) * Config.TILE_SIZE) {
			y = (tm.getHeight() - height) * Config.TILE_SIZE;
		}
	}
	
	public void draw(Graphics2D g) {
		//Draw the visible tiles
		int xTile = x / Config.TILE_SIZE;
		int yTile = y / Config.TILE_SIZE;
		
		int xOffset = x % Config.TILE_SIZE;
		int yOffset = y % Config.TILE_SIZE;
		
		for(int i = 0;i <= width;i++) {
			for(int j = 0;j <= height;j++) {
				//If an attempt is made to render off-screen tiles that don't exist, skip
				if(i + xTile >= tm.getWidth() || j + yTile >= tm.getHeight()) {
					continue;
				}
				
				g.drawImage(tm.getImg(i + xTile,  j + yTile), 
						(i * Config.TILE_SIZE) - xOffset,
						(j * Config.TILE_SIZE) - yOffset,
						null);
			}
		}
		
		//Draw any visible actors
		g.drawImage(hero.getImg(), hero.getX() - x, hero.getY() - y, null);
	}
}
