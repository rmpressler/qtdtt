package Player;

import Inventory.Inventory;

public class Player {
	private Inventory inv;
	private int level;
	
	public Player() {
		inv = new Inventory();
		level = 1;
	}
	
	public Inventory getInventory() {
		return inv;
	}
	
	public void setInventory(Inventory inv) {
		if(inv == null) {
			this.inv = inv;
		}
	}
}
