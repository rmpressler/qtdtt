package Camera;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Actor.HeroActor;
import Game.Config;
import TileMap.TileMap;
import World.World;

public class Camera {
	private int x;				// x coord in pixels
	private int y;				// y coord in pixels
	private int width;			// width in tiles
	private int height;			// height in tiles

	private HeroActor hero;		// Reference to Actor to sync to
	private TileMap tm;			// Reference to TileMap to paint
	private World world;		// Reference to World object
	
	public Camera(World world, int size) {
		this.world = world;
		this.tm = world.getTileMap();
		this.hero = world.getHero();
		this.x = hero.getX();
		this.y = hero.getY();
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
	
	public BufferedImage getScreen() {
		// Get context of BufferedImage
		BufferedImage img = new BufferedImage(Config.VIEW_SIZE * Config.TILE_SIZE,
				Config.VIEW_SIZE * Config.TILE_SIZE,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) img.getGraphics();
		
		// Convert world coords to tile coords
		int xTile = x / Config.TILE_SIZE;
		int yTile = y / Config.TILE_SIZE;
		
		// Get offset within tile
		int xOffset = x % Config.TILE_SIZE;
		int yOffset = y % Config.TILE_SIZE;
		
		// Iterate through tiles within the view
		for(int i = 0;i <= width;i++) {
			
			for(int j = 0;j <= height;j++) {
				
				// If an attempt is made to render off-screen tiles that don't exist, skip
				if(i + xTile >= tm.getWidth() || j + yTile >= tm.getHeight()) {
					continue;
				}
				
				// Add tile image to BufferedImage
				g.drawImage(tm.getImg(i + xTile,  j + yTile), 
						(i * Config.TILE_SIZE) - xOffset,
						(j * Config.TILE_SIZE) - yOffset,
						null);
			}
		}
		
		// Draw any visible actors
		Renderable[] renderables = world.getRenderables();
		for(Renderable r : renderables) {			
			// Get image data
			ImageData iData = r.getImageData();
			
			// Create local copies of location/dimensions to reduce calls to getters
			int imgX1 = iData.getX() - x;						//left edge (on screen)
			int imgY1 = iData.getY() - y;						//top edge (on screen)
			if(iData.getImg() == null) {
				System.out.println("iData.getImg() is null");
			}
			int imgX2 = imgX1 + iData.getImg().getWidth() - x;	//right edge (on screen)
			int imgY2 = imgY1 + iData.getImg().getHeight() - y;	//bottom edge (on screen)
			
			// If image is out of camera, skip
			if(imgX1 < 0 || imgY1 < 0
					|| imgX2 > x + (width * Config.TILE_SIZE)
					|| imgY2 > y + (height * Config.TILE_SIZE)) continue;
			
			// Paint image to BufferedImage
			g.drawImage(iData.getImg(), imgX1, imgY1, null);
		}
		
		return img;
	}
}
