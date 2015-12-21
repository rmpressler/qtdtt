package Physics;

public interface Collidable {
	public HitBox getHitBox();				//Gets the object's hitbox
	public String getType();
	public int getDmg();
	public void hit(int dmg);
}
