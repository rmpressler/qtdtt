package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W: 
			InputHandler.setToggle1(true); 
			break;
		case KeyEvent.VK_S: 
			InputHandler.setToggle2(true); 
			break;
		case KeyEvent.VK_A: 
			InputHandler.setToggle3(true); 
			break;
		case KeyEvent.VK_D: 
			InputHandler.setToggle4(true); 
			break;
		case KeyEvent.VK_SPACE:
			InputHandler.activateAction0();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W: 
			InputHandler.setToggle1(false); 
			break;
		case KeyEvent.VK_S: 
			InputHandler.setToggle2(false); 
			break;
		case KeyEvent.VK_A: 
			InputHandler.setToggle3(false); 
			break;
		case KeyEvent.VK_D: 
			InputHandler.setToggle4(false); 
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	
}
