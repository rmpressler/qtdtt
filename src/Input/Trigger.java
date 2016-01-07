package Input;

import net.java.games.input.Component;

public class Trigger {
	private float val;
	private Component c;
	private boolean pulledRight;
	private boolean pulledLeft;
	
	public Trigger(Component c) {
		this.c = c;
		val = 0;
		pulledLeft = false;
		pulledRight = false;
	}
	
	public void update() {
		val = c.getPollData();
		if(val <= -0.8 && val >= -1.1) {
			if(!pulledRight) {
				pulledRight = true;
			}
		}
		else if(val > 0.8 && val < 1.1){
			if(!pulledLeft) {
				InputHandler.passInput(new Command(Action.FIRE));
				pulledLeft = true;
			}
		}
		else {
			pulledRight = false;
			pulledLeft = false;
		}
	}
}
