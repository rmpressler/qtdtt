package AI;

import Actor.TestEnemyActor;
import Game.Config;
import World.World;

public class EnemyAI {
	private World world;
	
	private TestEnemyActor actor;
	private int destinationX;
	private int destinationY;
	
	private boolean moving;
	
	private long lastMovedTime;
	
	public EnemyAI(TestEnemyActor ea, World w) {
		this.actor = ea;
		this.world = w;
		
		moving = false;
		
		lastMovedTime = System.nanoTime() / 1000000;
	}
	
	private void stop() {
		actor.stopMove("all");
		lastMovedTime = System.nanoTime() / 1000000;
		moving = false;
	}
	
	public void update() {
		if(!moving && (System.nanoTime() / 1000000) - lastMovedTime >= 5000) {
			moving = true;
			boolean xPositive;
			boolean yPositive;
			
			//Set whether x will be positive
			if(Math.random() > 0.5) {
				xPositive = true;
			}
			else {
				xPositive = false;
			}
			
			//Set whether y will be positive
			if(Math.random() > 0.5) {
				yPositive = true;
			}
			else {
				yPositive = false;
			}
			
			//get random distance < 3 tiles worth of pixels
			int dx = (int)(Math.random() * (3 * Config.TILE_SIZE));
			int dy = (int)(Math.random() * (3 * Config.TILE_SIZE));
			
			//if either coord is meant to be negative, negate it
			if(!xPositive) {
				dx = -dx;
			}
			if(!yPositive) {
				dy = -dy;
			}
			
			//set destination
			destinationX = actor.getX() + dx;
			destinationY = actor.getY() + dy;
			
			String direction = World.getDirection(dx, dy);
			
			//set initial direction
			if(direction.indexOf('_') > -1) {
				//compound direction (direction_direction)
				String[] d = direction.split("_");
				actor.startMove(d[0]);
				actor.startMove(d[1]);
			}
			else {
				actor.startMove(direction);
			}
		}
		else if(moving) {
			if(actor.isStopped()) stop();
			
			if(actor.getX() <= 6) stop();
			if(actor.getY() <= 6) stop();
			if(actor.getX() >= (world.getTileMap().getWidth() * Config.TILE_SIZE) - actor.getWidth() - 6) stop();
			if(actor.getY() >= (world.getTileMap().getHeight() * Config.TILE_SIZE) - actor.getHeight() - 6) stop();
			
			int dx = -(actor.getX() - destinationX);	//-dx = left; +dx = right
			int dy = -(actor.getY() - destinationY);	//-dy = left; +dy = right
			
			//arrived
			if(Math.abs(dx) < 6 && Math.abs(dy) < 6) {
				stop();
				return;
			}
			
			//Get new direction
			String newDirection = World.getDirection(dx,  dy, 6);
			
			
			//If different direction than already moving, change direction
			if(newDirection != actor.getDirection()) {
				//Stop current movements
				if(actor.getDirection().indexOf('_') > -1) {
					String[] d = actor.getDirection().split("_");
					actor.stopMove(d[0]);
					actor.stopMove(d[1]);
					lastMovedTime = System.nanoTime() / 1000000;
				}
				else
					actor.stopMove(actor.getDirection());
				
				//Start new movements
				if(newDirection.indexOf('_') > -1) {
					//compound direction (direction_direction)
					String[] d = newDirection.split("_");
					actor.startMove(d[0]);
					actor.startMove(d[1]);
				}
				else {
					actor.startMove(newDirection);
				}
			}
		}
	}
}
