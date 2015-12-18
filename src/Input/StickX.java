package Input;

import net.java.games.input.Component;

public class StickX extends Stick{
	public StickX(Component comp) {
		super(comp);
	}
	
	public StickLocation getLocation() {
		if(comp.getPollData() < -0.5) {
			return StickLocation.LEFT;
		}
		else if(comp.getPollData() > 0.5) {
			return StickLocation.RIGHT;
		}
		else return StickLocation.CENTER;
	}
	
	public void update() {
		//Stick is in center
		if(getLocation() == Stick.StickLocation.CENTER) {
			//Left was released
			if(location == StickLocation.LEFT) {
				location = StickLocation.CENTER;
				InputHandler.setToggle3(false);
			}
			//Right was released
			else if(location == StickLocation.RIGHT) {
				location = StickLocation.CENTER;
				InputHandler.setToggle4(false);
			}
		}
		//Stick is left
		else if(getLocation() == StickLocation.LEFT) {
			//Just began going left
			if(location == StickLocation.CENTER) {
				location = StickLocation.LEFT;
				InputHandler.setToggle3(true);
			}
			//Switched from right
			else if(location == StickLocation.RIGHT) {
				location = StickLocation.LEFT;
				InputHandler.setToggle3(true);
				InputHandler.setToggle4(false);
			}
		}
		else if(getLocation() == StickLocation.RIGHT) {
			//Just began going right
			if(location == StickLocation.CENTER) {
				location = StickLocation.RIGHT;
				InputHandler.setToggle4(true);
			}
			//Switched from left
			else if(location == StickLocation.LEFT) {
				location = StickLocation.RIGHT;
				InputHandler.setToggle4(true);
				InputHandler.setToggle3(false);
			}
		}
	}
}
