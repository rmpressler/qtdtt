package GameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Game.GamePanel;
import Input.Command;
import Input.PauseInputHandler;
import Option.*;

public class PauseState extends GameState {
	private int width, height;
	private Menu menu;

	public PauseState(GameStateManager gsm) {
		super(gsm);
		
		PauseInputHandler.setGSM(gsm);
		PauseInputHandler.setStateReference(this);
		
		menu = new Menu();
		
		width = GamePanel.WINDOW_SIZE;
		height = GamePanel.WINDOW_SIZE;
		
		menu.addOption(new ResumeOption(gsm));
		menu.addOption(new SaveOption(gsm));
		menu.addOption(new QuitOption(gsm));
		
		menu.init();
	}

	@Override
	public void init() {
		
	}

	@Override
	public void update() {
		
	}
	
	public void optionUp() {
		menu.optionUp();
	}
	
	public void optionDown() {
		menu.optionDown();
	}
	
	public void optionExec() {
		menu.optionExec();
	}

	@Override
	public BufferedImage getScreen() {
		BufferedImage img = new BufferedImage(width, 
				height, 
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) img.getGraphics();
		
		g.drawImage(gsm.getState(GameStateManager.PLAYSTATE).getScreen(),
				0, 0, null);
		
		g.setColor(new Color(0, 0, 0, 180));
		g.fillRect(0, 0, width, height);
		
		BufferedImage menuImage = menu.getImage();
		
		g.drawImage(menuImage, 
				(width - menuImage.getWidth()) / 2, 
				(height - menuImage.getHeight()) / 2, 
				null);
		
		g.dispose();
		return img;
	}

	@Override
	public void passInput(Command command) {
		PauseInputHandler.passInput(command);
	}

}
