package Inventory;

import java.awt.image.BufferedImage;

import Camera.Renderable;
import Physics.Collidable;

public abstract class Item implements Renderable, Collidable{
	protected int id;
	protected ItemType type;
	protected BufferedImage img;
	protected int x;
	protected int y;
	
	public BufferedImage getImage() {
		return img;
	}
	public ItemType getItemType() {
		return type;
	}
	
	public void setCoords(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
