package Input;

import net.java.games.input.Component;

public class StickY extends Stick{
	public StickY(Component comp) {
		super(comp);
	}
	
	public StickLocation getLocation() {
		if(comp.getPollData() < -0.5) {
			return StickLocation.UP;
		}
		else if(comp.getPollData() > 0.5) {
			return StickLocation.DOWN;
		}
		else return StickLocation.CENTER;
	}
	
	public void update() {
		//Stick is in center
		if(getLocation() == Stick.StickLocation.CENTER) {
			//Left was released
			if(location == StickLocation.UP) {
				location = StickLocation.CENTER;
				InputHandler.setToggle1(false);
			}
			//Right was released
			else if(location == StickLocation.DOWN) {
				location = StickLocation.CENTER;
				InputHandler.setToggle2(false);
			}
		}
		//Stick is left
		else if(getLocation() == StickLocation.UP) {
			//Just began going left
			if(location == StickLocation.CENTER) {
				location = StickLocation.UP;
				InputHandler.setToggle1(true);
			}
			//Switched from right
			else if(location == StickLocation.DOWN) {
				location = StickLocation.UP;
				InputHandler.setToggle1(true);
				InputHandler.setToggle2(false);
			}
		}
		else if(getLocation() == StickLocation.DOWN) {
			//Just began going right
			if(location == StickLocation.CENTER) {
				location = StickLocation.DOWN;
				InputHandler.setToggle2(true);
			}
			//Switched from left
			else if(location == StickLocation.UP) {
				location = StickLocation.DOWN;
				InputHandler.setToggle2(true);
				InputHandler.setToggle1(false);
			}
		}
	}
}
