package Actor;

import java.awt.image.BufferedImage;

import Animation.ActorAnimator;
import Camera.ImageData;
import Camera.Renderable;
import Game.Config;
import Physics.Collidable;
import TileMap.TileMap;
import World.World;

public abstract class Actor implements Renderable,
									Collidable{
	protected int x;		//location in pixels relative to top left of level
	protected int y;		//location in pixels relative to top left of level
	protected int width;	//width of sprite
	protected int height;	//height of sprite
	protected int dx;		//movement on the x axis this update
	protected int dy;		//movement on the x axis this update
	protected int speed;	//speed at which sprite moves ( pixels / frame )
	protected int hp;
	protected int dmg;
	
	private boolean moving;		//true when walking. Controls walking animation
	private boolean attacking;	//true when attacking. Controls attacking animation
	private boolean isAlive;
	
	protected boolean movingUp;
	protected boolean movingDown;
	protected boolean movingLeft;
	protected boolean movingRight;
	
	private boolean isStopped;
	
	private String direction;
	
	protected BufferedImage img;
	protected ActorAnimator animate;
	protected TileMap tm;
	
	public Actor(TileMap tm) {
		this.speed = 3;
		this.tm = tm;
		
		dx = 0;
		dy = 0;
		
		direction = "down";
		moving = false;
		movingUp = false;
		movingDown = false;
		movingLeft = false;
		movingRight = false;
		isStopped = false;
		isAlive = true;
	}
	
	public void setStart(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void startMove(String direction) {
		switch(direction) {
			case "up":
				movingUp = true;
				break;
			case "down":
				movingDown = true;
				break;
			case "left":
				movingLeft = true;
				break;
			case "right":
				movingRight = true;
				break;
		}
	}
	
	public void stopMove(String direction) {
		switch(direction) {
			case "up":
				movingUp = false;
				break;
			case "down":
				movingDown = false;
				break;
			case "left":
				movingLeft = false;
				break;
			case "right":
				movingRight = false;
				break;
			case "all":
				movingUp = false;
				movingDown = false;
				movingLeft = false;
				movingRight = false;
		}
	}
	
	public boolean isMoving() {
		return moving;
	}
	
	protected boolean isAttacking() {
		return attacking;
	}
	
	
	public boolean isStopped() {
		if(isStopped) {
			isStopped = false;
			return true;
		}
		return false;
	}
	
	public void update() {
		
		if(hp <= 0) {
			setAlive(false);
			return;
		}
		
		//establish collision box coordinates
		int x1 = x;
		int x2 = x + width;
		int y1 = y + (height / 2);
		int y2 = y + height;
		
		//Operate on delta vars
		if(movingRight) {
			dx += speed;
		}
		if(movingLeft) {
			dx -= speed;
		}
		if(movingUp) {
			dy -= speed;
		}
		if(movingDown) {
			dy += speed;
		}
		
		if(dx != 0 || dy != 0) {
			direction = World.getDirection(dx, dy);
			moving = true;
		}
		else moving = false;
		
		//moving left, check for walkable
		if(dx < 0) {
			//top of collision box
			int tileX = (x1 + dx) / Config.TILE_SIZE;
			int tileY = y1 / Config.TILE_SIZE;
			if(tileX < tm.getWidth() && tileY < tm.getHeight()) {
				if(!tm.isWalkable(tileX, tileY)) {
					dx = 0;
					isStopped = true;
				}
				
				//bottom of collision box
				tileY = y2 / Config.TILE_SIZE;
				if(tileY < tm.getHeight())
					if(!tm.isWalkable(tileX, tileY)){
						dx = 0;
						isStopped = true;
					}
			}
		}
		//moving right, check for walkable
		else if(dx > 0) {
			//top of collision box
			int tileX = (x2 + dx) / Config.TILE_SIZE;
			int tileY = y1 / Config.TILE_SIZE;
			if(tileX < tm.getWidth() && tileY < tm.getHeight()) {
				if(!tm.isWalkable(tileX, tileY)) {
					dx = 0;
					isStopped = true;
				}
			
				//bottom of collision box
				tileY = y2 / Config.TILE_SIZE;
				if(tileY < tm.getHeight())
					if(!tm.isWalkable(tileX, tileY)){
						dx = 0;
						isStopped = true;
					}
			}
		}
		
		//moving up, check for walkable
		if(dy < 0) {
			//left of collision box
			int tileX = x1 / Config.TILE_SIZE;
			int tileY = (y1 + dy) / Config.TILE_SIZE;
			if(tileX < tm.getWidth() && tileY < tm.getHeight()) {
				if(!tm.isWalkable(tileX, tileY)){
					dy = 0;
					isStopped = true;
				}
				
				//right of collision box
				tileX = x2 / Config.TILE_SIZE;
				if(tileX < tm.getWidth())
					if(!tm.isWalkable(tileX, tileY)){
						dy = 0;
						isStopped = true;
					}
			}
		}
		else if(dy > 0) {
			//left of collision box
			int tileX = x1 / Config.TILE_SIZE;
			int tileY = (y2 + dy) / Config.TILE_SIZE;
			if(tileX < tm.getWidth() && tileY < tm.getHeight()) {
				if(!tm.isWalkable(tileX, tileY)){
					dy = 0;
					isStopped = true;
				}
			
				//right of collision box
				tileX = x2 / Config.TILE_SIZE;
				if(tileX < tm.getWidth())
					if(!tm.isWalkable(tileX, tileY)){
						dy = 0;
						isStopped = true;
					}
			}
		}		
		
		//move coords
		x += dx;
		y += dy;
		
		//Check map bounds
		if(x < 0) x = 0;
		if(y < 0) y = 0;
		if(x > (tm.getWidth() * Config.TILE_SIZE) - this.width) x = (tm.getWidth() * Config.TILE_SIZE) - this.width;
		if(y > (tm.getHeight() * Config.TILE_SIZE) - this.height) y = (tm.getHeight() * Config.TILE_SIZE) - this.height;
		
		//reset vars
		dx = 0;
		dy = 0;
		
		//move animation, if needed
		if(moving) animate.move(direction);
		img = animate.getImage();
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public BufferedImage getImg() {
		return img;
	}
	
	public String getDirection() {
		return direction;
	}
	
	public boolean isAlive() {
		return isAlive;
	}
	
	protected void setAlive(boolean alive) {
		isAlive = alive;
	}
	
	@Override
	public ImageData getImageData() {
		return new ImageData(img, x, y);
	}
	
	public void hit(int dmg) {
		hp -= dmg;
	}
}
