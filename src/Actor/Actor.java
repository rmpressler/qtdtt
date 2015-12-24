package Actor;

import java.awt.image.BufferedImage;

import Animation.ActorAnimator;
import Camera.ImageData;
import Camera.Renderable;
import Game.Config;
import Game.GameObject;
import Physics.Collidable;
import TileMap.TileMap;
import World.World;

public abstract class Actor extends GameObject implements Renderable,
									Collidable{
	protected int x;		//location in pixels relative to top left of level
	protected int y;		//location in pixels relative to top left of level
	protected int width;	//width of sprite
	protected int height;	//height of sprite
	protected int speed;	//speed at which sprite moves ( pixels / frame )
	protected int hp;		//hit points
	protected int dmg;		//damage done by attack
	
	private boolean moving;		//true when walking. Controls walking animation
	private boolean attacking;	//true when attacking. Controls attacking animation
	private boolean isAlive;	//false when hp falls to 0
	private boolean isStopped;	//Used in preventing units from walking on tiles they shouldn't
	
	// Booleans to keep track of which movement directions are active
	
	protected boolean movingUp;		
	protected boolean movingDown;
	protected boolean movingLeft;
	protected boolean movingRight;
	
	private String direction;	//Keeps track of current facing/moving direction
	
	protected BufferedImage img;		//Stores current image of unit
	protected ActorAnimator animate;	//Animator that fetches correct frames
	protected TileMap tm;				//Tile map for positioning
	
	public Actor(TileMap tm) {
		this.speed = 3;
		this.tm = tm;
		
		direction = "down";
		moving = false;
		movingUp = false;
		movingDown = false;
		movingLeft = false;
		movingRight = false;
		isStopped = false;
		isAlive = true;
		
		setRenderable(true);
		setCollidable(true);
	}
	
	/** Sets the initial location for top left corner of sprite in pixels.
	 * 
	 * @param x x coordinate in pixels
	 * @param y y coordinate in pixels
	 * 
	 */
	public void setStart(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/** Sets actor movement flag to true for desired direction. Valid 
	 * strings are:
	 * "up"
	 * "down"
	 * "left"
	 * "right"
	 * 
	 * @param direction String representation of direction
	 * 
	 */
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
	
	/** Set movement flag for direction to false
	 * 
	 * Valid directions:
	 * "up"
	 * "down"
	 * "left"
	 * "right"
	 * 
	 * @param direction Direction of movement to turn off
	 * 
	 */
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
	
	/** Boolean getter for the moving flag
	 * 
	 * @return true if actor moving, false if not
	 */
	public boolean isMoving() {
		return moving;
	}
	
	/** Boolean getter for the attacking flag
	 * 
	 * @return true if attacking, false if not
	 */
	protected boolean isAttacking() {
		return attacking;
	}
	
	/** Boolean getter for the stopped flag
	 * Mainly used for pathing and ensuring that 
	 * actor is not moving onto an unwalkable
	 * tile
	 * 
	 * @return true if an unwalkable tile is found and the actor is stopped short
	 */
	public boolean isStopped() {
		if(isStopped) {
			isStopped = false;
			return true;
		}
		return false;
	}
	
	/** Getter for width (in pixels)
	 * 
	 * @return Width in pixels
	 */
	public int getWidth() {
		return width;
	}
	
	/** Getter for height (in pixels)
	 * 
	 * @return height in pixels
	 */
	public int getHeight() {
		return height;
	}
	
	/** Getter for x coordinate (in pixels)
	 * 
	 * @return x coordinate in pixels
	 */
	public int getX() {
		return x;
	}
	
	/** Getter for y coordinate (in pixels)
	 * 
	 * @return y coordinate in pixels
	 */
	public int getY() {
		return y;
	}
	
	/** Returns current image fed by animator.
	 * 
	 * Required by Renderable
	 * 
	 * @return BufferedImage to be displayed for actor
	 */
	public BufferedImage getImg() {
		return img;
	}
	
	/** Getter for direction.
	 * 
	 * @return String representation of direction facing/moving
	 */
	public String getDirection() {
		return direction;
	}
	
	/** Updater method for actors.
	 * 
	 * Process:
	 * 
	 * - Set alive status
	 * - Determine movement including direction
	 * - Check for valid movement
	 * - Step animation
	 */
	public void update() {
		// Set up delta vars
		int dx = 0;
		int dy = 0;
		
		// Set to not alive when HP falls to 0
		if(hp <= 0) {
			setAlive(false);
			return;
		}
		
		// establish collision box coordinates
		int x1 = x;
		int x2 = x + width;
		int y1 = y + (height / 2);
		int y2 = y + height;
		
		// Operate on delta vars
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
		
		// If at least one delta var is changed, set direction and begin moving
		if(dx != 0 || dy != 0) {
			direction = World.getDirection(dx, dy);
			moving = true;
		}
		else moving = false;
		
		// moving left, check for walkable
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
		
		// moving up, check for walkable
		if(dy < 0) {
			// left of collision box
			int tileX = x1 / Config.TILE_SIZE;
			int tileY = (y1 + dy) / Config.TILE_SIZE;
			if(tileX < tm.getWidth() && tileY < tm.getHeight()) {
				if(!tm.isWalkable(tileX, tileY)){
					dy = 0;
					isStopped = true;
				}
				
				// right of collision box
				tileX = x2 / Config.TILE_SIZE;
				if(tileX < tm.getWidth())
					if(!tm.isWalkable(tileX, tileY)){
						dy = 0;
						isStopped = true;
					}
			}
		}
		else if(dy > 0) {
			// left of collision box
			int tileX = x1 / Config.TILE_SIZE;
			int tileY = (y2 + dy) / Config.TILE_SIZE;
			if(tileX < tm.getWidth() && tileY < tm.getHeight()) {
				if(!tm.isWalkable(tileX, tileY)){
					dy = 0;
					isStopped = true;
				}
			
				// right of collision box
				tileX = x2 / Config.TILE_SIZE;
				if(tileX < tm.getWidth())
					if(!tm.isWalkable(tileX, tileY)){
						dy = 0;
						isStopped = true;
					}
			}
		}		
		
		// move coords
		x += dx;
		y += dy;
		
		// Check map bounds
		if(x < 0) x = 0;
		if(y < 0) y = 0;
		if(x > (tm.getWidth() * Config.TILE_SIZE) - this.width) x = (tm.getWidth() * Config.TILE_SIZE) - this.width;
		if(y > (tm.getHeight() * Config.TILE_SIZE) - this.height) y = (tm.getHeight() * Config.TILE_SIZE) - this.height;
		
		// reset vars
		dx = 0;
		dy = 0;
		
		// move animation, if needed
		if(moving) animate.move(direction);
		img = animate.getImage();
	}
	
	/** Getter for alive status for use in renderer and collider
	 * 
	 * @return True if alive, false if not
	 * 
	 * @see Camera.Renderable#isAlive()
	 */
	public boolean isAlive() {
		return isAlive;
	}
	
	/** Setter for alive flag - should not be set by any other object.
	 * 
	 * @param alive Should be false to indicate unit died
	 */
	protected void setAlive(boolean alive) {
		isAlive = alive;
	}
	
	/** Returns image and coordinates encapsulated in
	 * ImageData object. Used by renderer.
	 * 
	 * @return ImageData object containing image and coordinates
	 * 
	 * @see Camera.Renderable#getImageData()
	 */
	@Override
	public ImageData getImageData() {
		return new ImageData(img, x, y);
	}
	
	/** Reduces actor's HP by dmg. Used by collider.
	 * 
	 * @param dmg Amount to reduce HP by.
	 * 
	 * @see Physics.Collidable#hit(int)
	 */
	public void hit(int dmg) {
		hp -= dmg;
		if(hp <= 0) {
			isAlive = false;
		}
	}
}
