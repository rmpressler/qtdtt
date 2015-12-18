package Spells;

import java.awt.image.BufferedImage;

import Camera.ImageData;
import Camera.Renderable;

public class Fireball implements Renderable {
	private int x;
	private int y;
	private String direction;
	private BufferedImage img;
	
	public Fireball(String direction, int x, int y) {
		
	}

	@Override
	public ImageData getImageData() {
		return new ImageData(img, x, y);
	}
	
}
