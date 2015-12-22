package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W: 
			InputHandler.passInput(new Command(Action.LUP, true)); 
			break;
		case KeyEvent.VK_S: 
			InputHandler.passInput(new Command(Action.LDOWN, true));
			break;
		case KeyEvent.VK_A: 
			InputHandler.passInput(new Command(Action.LLEFT, true));
			break;
		case KeyEvent.VK_D: 
			InputHandler.passInput(new Command(Action.LRIGHT, true));
			break;
		case KeyEvent.VK_SPACE:
			InputHandler.passInput(new Command(Action.FIRE, true));
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W: 
			InputHandler.passInput(new Command(Action.LUP, false)); 
			break;
		case KeyEvent.VK_S: 
			InputHandler.passInput(new Command(Action.LDOWN, false));
			break;
		case KeyEvent.VK_A: 
			InputHandler.passInput(new Command(Action.LLEFT, false));
			break;
		case KeyEvent.VK_D: 
			InputHandler.passInput(new Command(Action.LRIGHT, false));
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	
}
