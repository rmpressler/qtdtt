package Physics;

public interface Collidable {
	public HitBox getHitBox();				//Gets the object's hitbox
	public void collided(Collidable o);		//Handles what happens when unit collides with o
}
