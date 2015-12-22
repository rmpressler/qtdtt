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
			//Up was released
			if(location == StickLocation.UP) {
				location = StickLocation.CENTER;
				InputHandler.passInput(new Command(Action.LUP, false));
			}
			//Down was released
			else if(location == StickLocation.DOWN) {
				location = StickLocation.CENTER;
				InputHandler.passInput(new Command(Action.LDOWN, false));
			}
		}
		//Stick is up
		else if(getLocation() == StickLocation.UP) {
			//Just began going up
			if(location == StickLocation.CENTER) {
				location = StickLocation.UP;
				InputHandler.passInput(new Command(Action.LUP, true));
			}
			//Switched from down
			else if(location == StickLocation.DOWN) {
				location = StickLocation.UP;
				InputHandler.passInput(new Command(Action.LDOWN, false));
				InputHandler.passInput(new Command(Action.LUP, true));
			}
		}
		//Stick is down
		else if(getLocation() == StickLocation.DOWN) {
			//Just began going down
			if(location == StickLocation.CENTER) {
				location = StickLocation.DOWN;
				InputHandler.passInput(new Command(Action.LDOWN, true));
			}
			//Switched from up
			else if(location == StickLocation.UP) {
				location = StickLocation.DOWN;
				InputHandler.passInput(new Command(Action.LDOWN, true));
				InputHandler.passInput(new Command(Action.LUP, false));
			}
		}
	}
}
