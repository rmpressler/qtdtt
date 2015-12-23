package Inventory;

public abstract class Weapon extends Item {
	protected int dmg;
	
	public Weapon() {
		type = ItemType.WEAPON;
	}
	
	public int getDmg() {
		return dmg;
	}
}
