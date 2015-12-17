package Input;

import net.java.games.input.*;

public class Joystick {
	private Controller c;
	
	public Joystick() {
		Controller[] found = ControllerEnvironment.getDefaultEnvironment().getControllers();
		
		for(int i = 0; i < found.length;i++) {
			if(found[i].getType() == Controller.Type.GAMEPAD) c = found[i];
		}
		
		System.out.println("Connected to " + c.getName() + "!");
		
		Component[] comps = c.getComponents();
		for(Component comp : comps) {
			System.out.println(comp.getName() + " (" + comp.getIdentifier() + "): " + comp.getPollData());
		}
		
		float val = c.getComponent(Component.Identifier.Button._1).getPollData();
		while(true) {
			if(c.getComponent(Component.Identifier.Button._1).getPollData() != val) {
				System.out.println("Changed! HOLY SHIT!");
			}
		}
	}
	
	public void printX() {
		Component comp = c.getComponent(Component.Identifier.Axis.X);
		System.out.println(comp.getPollData());
	}
}
