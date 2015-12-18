
package Input;

import net.java.games.input.Component;

public abstract class Stick {
	public enum StickLocation {
		UP,
		DOWN,
		LEFT,
		RIGHT,
		CENTER
	}
	
	protected Component comp;
	protected StickLocation location;
	
	public Stick(Component comp) {
		this.comp = comp;
		this.location = getLocation();
	}
	
	public abstract StickLocation getLocation();
	public abstract void update();
}
