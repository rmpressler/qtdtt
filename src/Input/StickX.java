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
				InputHandler.passInput(new Command(Action.LLEFT, false));
			}
			//Right was released
			else if(location == StickLocation.RIGHT) {
				location = StickLocation.CENTER;
				InputHandler.passInput(new Command(Action.LRIGHT, false));
			}
		}
		//Stick is left
		else if(getLocation() == StickLocation.LEFT) {
			//Just began going left
			if(location == StickLocation.CENTER) {
				location = StickLocation.LEFT;
				InputHandler.passInput(new Command(Action.LLEFT, true));
			}
			//Switched from right
			else if(location == StickLocation.RIGHT) {
				location = StickLocation.LEFT;
				InputHandler.passInput(new Command(Action.LRIGHT, false));
				InputHandler.passInput(new Command(Action.LLEFT, true));
			}
		}
		else if(getLocation() == StickLocation.RIGHT) {
			//Just began going right
			if(location == StickLocation.CENTER) {
				location = StickLocation.RIGHT;
				InputHandler.passInput(new Command(Action.LRIGHT, true));
			}
			//Switched from left
			else if(location == StickLocation.LEFT) {
				location = StickLocation.RIGHT;
				InputHandler.passInput(new Command(Action.LLEFT, false));
				InputHandler.passInput(new Command(Action.LRIGHT, true));
			}
		}
	}
}
