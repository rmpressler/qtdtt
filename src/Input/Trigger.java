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
				InputHandler.activateAction1();
				pulledRight = true;
			}
		}
		else if(val > 0.8 && val < 1.1){
			if(!pulledLeft) {
				InputHandler.activateAction0();
				pulledLeft = true;
			}
		}
		else {
			pulledRight = false;
			pulledLeft = false;
		}
	}
}
