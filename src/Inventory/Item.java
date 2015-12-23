package Inventory;

import java.awt.image.BufferedImage;

import Camera.Renderable;

public abstract class Item implements Renderable{
	protected int id;
	protected ItemType type;
	protected BufferedImage img;
	
	public BufferedImage getImage() {
		return img;
	}
	public ItemType getType() {
		return type;
	}
}
