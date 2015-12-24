package Inventory;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Animation.SpriteImage;
import Camera.ImageData;
import Physics.HitBox;

public class Sword extends Weapon {
	public Sword(int width, int height) {
		super();
		
		id = 1;
		
		BufferedImage imgFile;
		try {
			imgFile = ImageIO.read(new File(".\\assets\\images\\sword.png"));
			img = SpriteImage.scale(imgFile, width, height);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setCoords(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public ImageData getImageData() {
		return new ImageData(img, x, y);
	}

	@Override
	public boolean isAlive() {
		return true;
	}

	@Override
	public void update() {
		
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public HitBox getHitBox() {
		return new HitBox(x, y, x + img.getWidth(), y + img.getHeight());
	}

	@Override
	public String getType() {
		return "sword";
	}

	@Override
	public void hit(int dmg) {
		
	}
}
