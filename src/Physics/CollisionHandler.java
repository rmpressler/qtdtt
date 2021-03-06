package Physics;

import Actor.TestEnemyActor;
import Camera.Renderable;
import Game.GameObject;
import Inventory.Item;
import Inventory.Sword;
import World.World;
import Player.Player;

public class CollisionHandler {
	private static World world;
	private static Player player;
	
	public static void setWorld(World world) {
		CollisionHandler.world = world;
	}
	
	public static void handleCollision(Collidable a, Collidable b) {
		if((a.getType() == "fireball" && b.getType() == "enemy")) {
			b.hit(a.getDmg());
			if(!b.isAlive()) {
				System.out.println("Enemy died!");
				TestEnemyActor t = (TestEnemyActor)b;
				Item drop = t.getDrop(player.getInventory().getBoxWidth(),
						player.getInventory().getBoxHeight());
				if(drop != null) {
					drop.setCoords(b.getHitBox().getX1(),
							b.getHitBox().getY1());
					world.addCollidableObject(drop);
					world.addRenderableObject(drop);
				}
			}
			world.removeCollidableObject(a);
			world.removeRenderableObject((Renderable)a);
		}
		else if(b.getType() == "fireball" && a.getType() == "enemy") {
			a.hit(b.getDmg());
			if(!a.isAlive()) {
				System.out.println("Enemy died!");
				TestEnemyActor t = (TestEnemyActor)a;
				Item drop = t.getDrop(player.getInventory().getBoxWidth(),
						player.getInventory().getBoxHeight());
				if(drop != null) {
					drop.setCoords(a.getHitBox().getX1(),
							a.getHitBox().getY1());
					world.addCollidableObject(drop);
					world.addRenderableObject(drop);
				}
			}
			world.removeCollidableObject(b);
			world.removeRenderableObject((Renderable)b);
		}
		else if(b.getType() == "sword" && a.getType() == "hero") {
			world.removeCollidableObject(b);
			world.removeRenderableObject((Renderable)b);
			
			player.getInventory().add((Sword)b);
		}
		
		else if(a.getType() == "sword" && b.getType() == "hero") {
			world.removeCollidableObject(a);
			world.removeRenderableObject((Renderable)a);
			
			player.getInventory().add((Sword)a);
		}
	}

	public static void setPlayer(Player player) {
		CollisionHandler.player = player;
	}
}
