package Input;


import net.java.games.input.*;

public class Joystick {
	private Controller c;
	
	private boolean connected;
	
	private Stick xStick;
	private Stick yStick;
	private Trigger trigger;
	
	public Joystick() {
		Controller[] found = ControllerEnvironment.getDefaultEnvironment().getControllers();
		
		for(int i = 0; i < found.length;i++) {
			if(found[i].getType() == Controller.Type.GAMEPAD) c = found[i];
		}
		
		if(c == null) {
			connected = false;
			return;
		}
		
		connected = true;
		
		System.out.println("Connected to " + c.getName() + "!");
		
		c.poll();
		
		xStick = new StickX(c.getComponent(Component.Identifier.Axis.X));
		yStick = new StickY(c.getComponent(Component.Identifier.Axis.Y));
		trigger = new Trigger(c.getComponent(Component.Identifier.Axis.Z));
	}
	
	public boolean isConnected() {
		return connected;
	}
	
	public void update() {
		c.poll();
		
		xStick.update();
		yStick.update();
		trigger.update();
	}
}
