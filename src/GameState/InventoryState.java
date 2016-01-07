package GameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Game.GamePanel;

import static GameState.GameStateManager.*;

import Input.Command;
import Input.InventoryInputHandler;
import Inventory.Inventory;

public class InventoryState extends GameState {
	private int width, height;
	private Inventory inv;

	public InventoryState(GameStateManager gsm) {
		super(gsm);
		
		width = GamePanel.WINDOW_SIZE;
		height = GamePanel.WINDOW_SIZE;
		
		InventoryInputHandler.setGSM(gsm);
		InventoryInputHandler.setStateReference(this);
	}

	@Override
	public void init() {
		
	}

	@Override
	public void update() {
		inv = gsm.getPlayer().getInventory();
	}

	@Override
	public BufferedImage getScreen() {
		BufferedImage img = new BufferedImage(width, 
				height, 
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) img.getGraphics();
		
		g.drawImage(gsm.getState(PLAYSTATE).getScreen(),
				0, 0, null);
		
		g.setColor(new Color(0, 0, 0, 180));
		g.fillRect(0, 0, width, height);
		
		BufferedImage invbg = inv.getImg();
		
		int imgWidth = invbg.getWidth();
		int imgHeight = invbg.getHeight();
		
		g.drawImage(invbg, (width - imgWidth) / 2, (height - imgHeight) / 2, null);
		
		return img;
	}
	
	public void cursorRight() {
		inv.cursorRight();
	}
	
	public void cursorLeft() {
		inv.cursorLeft();
	}
	
	public void cursorUp() {
		inv.cursorUp();
	}
	
	public void cursorDown() {
		inv.cursorDown();
	}

	@Override
	public void passInput(Command command) {
		InventoryInputHandler.passInput(command);
	}

}
