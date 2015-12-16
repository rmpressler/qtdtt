package TileMap;

import java.awt.image.BufferedImage;

public abstract class Tile {
	private BufferedImage img;
	
	private int id;
	
	private boolean walkable;
	private boolean harvestable;
	
	protected Tile(int id) {
		this.id = id;
	}
	
	protected void setImg(BufferedImage img) {
		this.img = img;
	}
	
	public int getID() {
		return this.id;
	}
	
	public BufferedImage getImg() {
		return this.img;
	}
	
	protected void setWalkable(boolean walk) {
		walkable = walk;
	}
	
	public boolean isWalkable() {
		return this.walkable;
	}
	public boolean isHarvestable() {
		return this.harvestable;
	}
}
