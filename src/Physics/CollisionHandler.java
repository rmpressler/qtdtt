package Physics;

import Camera.Renderable;
import World.World;

public class CollisionHandler {
	private static World world;
	
	public static void setWorld(World world) {
		CollisionHandler.world = world;
	}
	
	public static void handleCollision(Collidable a, Collidable b) {
		if((a.getType() == "fireball" && b.getType() == "enemy")) {
			b.hit(a.getDmg());
			world.removeCollidableObject(a);
			world.removeRenderableObject((Renderable)a);
		}
		else if(b.getType() == "fireball" && a.getType() == "enemy") {
			a.hit(b.getDmg());
			world.removeCollidableObject(b);
			world.removeRenderableObject((Renderable)b);
		}
	}
}
