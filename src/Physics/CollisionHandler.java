package Physics;

import Camera.Renderable;
import World.World;

public class CollisionHandler {
	private static World world;
	
	public static void setWorld(World world) {
		CollisionHandler.world = world;
	}
	
	public static void handleCollision(Collidable a, Collidable b) {
		if((a.getType() == "fireball" || b.getType() == "fireball")
				&& (a.getType() == "enemy" || b.getType() == "enemy")) {
			world.removeRenderableObject((Renderable)a);
			world.removeCollidableObject(a);
		}
	}
}
