package Inventory;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Animation.SpriteImage;
import Camera.ImageData;

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

	@Override
	public ImageData getImageData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}
}
