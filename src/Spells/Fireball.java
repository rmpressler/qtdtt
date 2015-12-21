package Spells;

import java.awt.image.BufferedImage;

import Animation.SpellAnimator;
import Camera.ImageData;
import Camera.Renderable;
import Game.Config;
import Physics.Collidable;
import Physics.HitBox;
import World.World;

public class Fireball implements Renderable,
								Collidable{
	private int x;
	private int y;
	private int width;
	private int height;
	private int speed;
	private int dmg;
	private String direction;
	private BufferedImage img;
	private SpellAnimator sa;
	private boolean isAlive;
	
	public Fireball(String direction, int x, int y) {
		this.direction = direction;
		this.x = x;
		this.y = y;
		this.width = (int)(Config.TILE_SIZE * 0.666);
		this.height = (int)(Config.TILE_SIZE * 0.666);
		this.speed = 5;
		this.sa = new SpellAnimator("fireball", direction, width, height);
		this.img = sa.getImage();
		this.isAlive = true;
		this.dmg = 5;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
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

	@Override
	public HitBox getHitBox() {
		return new HitBox(x, y, x + width, y + height);
	}
	
	public int getDmg() {
		return dmg;
	}

	@Override
	public String getType() {
		return "fireball";
	}

	@Override
	public boolean isAlive() {
		return isAlive;
	}

	@Override
	public void hit(int dmg) {
		// TODO Auto-generated method stub
		
	}
}
