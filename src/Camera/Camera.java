package Camera;

import java.awt.Graphics2D;

import Actor.HeroActor;
import Game.Config;
import TileMap.TileMap;
import World.World;

public class Camera {
	private int x;
	private int y;
	private int width;
	private int height;

	private HeroActor hero;		//Actor to sync to
	private TileMap tm;			//TileMap to paint
	private World world;
	
	public Camera(World world, int size) {
		this.world = world;
		this.tm = world.getTileMap();
		this.hero = world.getHero();
		this.x = hero.getX();				//x location on tilemap in pixels
		this.y = hero.getY();				//y location on tilemap in pixels
		this.width = size;
		this.height = size;
	}
	
	public void update() {
		int actorX = hero.getX();
		int actorY = hero.getY();
		
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
		//g.drawImage(hero.getImg(), hero.getX() - x, hero.getY() - y, null);
		Renderable[] renderables = world.getRenderables();
		for(Renderable r : renderables) {			
			//Get image data
			ImageData iData = r.getImageData();
			
			//Create local copies of location/dimensions to reduce calls to getters
			int imgX1 = iData.getX() - x;						//left edge (on screen)
			int imgY1 = iData.getY() - y;						//top edge (on screen)
			int imgX2 = imgX1 + iData.getImg().getWidth() - x;	//right edge (on screen)
			int imgY2 = imgY1 + iData.getImg().getHeight() - y;	//bottom edge (on screen)
			
			//If image is off screen, skip
			if(imgX1 < 0 || imgY1 < 0
					|| imgX2 > x + (width * Config.TILE_SIZE)
					|| imgY2 > y + (height * Config.TILE_SIZE)) continue;
			
			//Paint image to screen
			g.drawImage(iData.getImg(), imgX1, imgY1, null);
		}
	}
}
