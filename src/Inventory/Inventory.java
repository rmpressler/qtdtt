package Inventory;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Animation.SpriteImage;
import Game.GamePanel;

public class Inventory {
	private Item[][] items;
	//private int maxItems;
	private int cursorX;
	private int cursorY;
	private int boxX;
	private int boxY;
	private int boxWidth;
	private int boxHeight;
	private BufferedImage img;
	
	public Inventory() {
		//maxItems = 25;
		items = new Item[5][5];
		
		cursorX = 0;
		cursorY = 0;
		
		for(int i = 0;i < 5;i++) {
			for(int j = 0;j < 5; j++) {
				items[i][j] = null;
			}
		}
		
		try {
			BufferedImage bi = ImageIO.read(new File(".\\assets\\images\\inventorybg.png"));
			int panelWidth = (int)(GamePanel.WINDOW_SIZE * 0.75); 
			int panelHeight = (int)(panelWidth * 0.7);
			
			img = SpriteImage.scale(bi, panelWidth, panelHeight);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		boxX = (int)(img.getWidth() * 0.4);
		boxY = (int)(img.getHeight() / 7);
		
		boxWidth = img.getWidth() / 10;
		boxHeight = img.getHeight() / 7;
		
		add(new Sword(boxWidth, boxHeight));
		add(new Sword(boxWidth, boxHeight));
	}
	
	/**Adds an Item to the current Inventory.
	 * 
	 * @param item Item to add
	 * @return True if successful, false if inventory full.
	 */
	public boolean add(Item item) {
		for(int i = 0;i < 5;i++) {
			for(int j = 0;j < 5;j++) {
				if(items[i][j] == null) {
					items[j][i] = item;
					return true;
				}
			}
		}
		
		return false;
	}
	
	public void open() {
		cursorX = 0;
		cursorY = 0;
	}
	
	public void cursorRight() {
		if(cursorX < items.length - 1) cursorX++;
	}
	
	public void cursorLeft() {
		if(cursorX > 0) cursorX--;
	}
	
	public void cursorUp() {
		if(cursorY > 0) cursorY--;
	}
	
	public void cursorDown() {
		if(cursorY < items[0].length - 1) cursorY++;
	}
	
	public BufferedImage getImg() {
		BufferedImage bi = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) bi.getGraphics();
		
		g.drawImage(img,  0,  0,  null);
		
		g.drawRect(boxX + (boxWidth * cursorX) + 1,  boxY + (boxHeight * cursorY) + 2,  boxWidth,  boxY);
		
		for(int i = 0;i < items.length;i++) {
			for(int j = 0;j < items[0].length;j++) {
				if(items[i][j] != null) {
					g.drawImage(items[i][j].getImage(),
							boxX + (boxWidth * i),
							boxY + (boxHeight * j),
							null);
				}
			}
		}
		
		g.dispose();
		
		return bi;
	}
}
