package GameState;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Game.GamePanel;
import Input.Command;
import Input.MenuInputHandler;
import Option.*;

public class MenuState extends GameState {
	Menu menu;

	public MenuState(GameStateManager gsm) {
		super(gsm);
		
		menu = new Menu();
		
		menu.addOption(new NewOption(gsm));
		menu.addOption(new LoadOption(gsm));
		
		menu.init();
		
		MenuInputHandler.setStateReference(this);
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
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(".\\assets\\images\\menubg.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedImage bi = new BufferedImage(GamePanel.WINDOW_SIZE, GamePanel.WINDOW_SIZE, BufferedImage.TYPE_INT_RGB);
		Graphics g = bi.getGraphics();
		g.drawImage(img.getScaledInstance(GamePanel.WINDOW_SIZE, GamePanel.WINDOW_SIZE, BufferedImage.SCALE_DEFAULT), 0, 0, null);
		
		BufferedImage menuImage = menu.getImage();
		int wSize = GamePanel.WINDOW_SIZE;
		
		g.drawImage(menuImage, 
				(wSize - menuImage.getWidth()) / 2,
				(wSize - menuImage.getHeight()) / 2,
				null);
		
		return bi;
	}

	@Override
	public void passInput(Command command) {
		MenuInputHandler.passInput(command);
	}

}
