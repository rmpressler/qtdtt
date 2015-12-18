package Spells;

import java.awt.image.BufferedImage;

import Animation.SpellAnimator;
import Camera.ImageData;
import Camera.Renderable;
import World.World;

public class Fireball implements Renderable {
	private int x;
	private int y;
	private int speed;
	private String direction;
	private BufferedImage img;
	private SpellAnimator sa;
	
	public Fireball(String direction, int x, int y) {
		this.direction = direction;
		this.x = x;
		this.y = y;
		this.speed = 5;
		this.sa = new SpellAnimator("fireball", direction, 30, 30);
		this.img = sa.getImage();
	}
	
	public void update() {
		int dx = 0;
		int dy = 0;
		
		if(World.isCompoundDirection(direction)) {
			String[] directions = World.Direction2to1(direction);
			if(directions[0].compareTo("up") == 0)
				dy -= speed;
			if(directions[0].compareTo("down") == 0)
				dy += speed;
			if(directions[1].compareTo("left") == 0)
				dx -= speed;
			if(directions[1].compareTo("right") == 0)
				dx += speed;
		}
		else {
			if(direction == "up")
				dy -= speed;
			if(direction == "down")
				dy += speed;
			if(direction == "left")
				dx -= speed;
			if(direction == "right")
				dx += speed;
		}
		
		//String newDirection = World.getDirection(dx, dy);
		
		x += dx;
		y += dy;
		
		//For homing - implement later?
//		if(newDirection != direction) {
//			direction = newDirection;
//		}
	}

	@Override
	public ImageData getImageData() {
		return new ImageData(img, x, y);
	}
	
}
