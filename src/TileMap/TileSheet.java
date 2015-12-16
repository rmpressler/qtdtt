package TileMap;

import java.awt.image.BufferedImage;

public class TileSheet {
	private BufferedImage tileSheet;
	
	private int tileWidth;
	private int tileHeight;
	private int currentX;
	private int currentY;
	
	public TileSheet(BufferedImage bi, int tileWidth, int tileHeight, int numTiles) {
		this.tileSheet = bi;
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		currentX = 0;
		currentY = 0;
	}
	
	public BufferedImage getTile() {
		BufferedImage tile = tileSheet.getSubimage(currentX,  currentY,  tileWidth,  tileHeight);
		if(currentX + tileWidth < tileSheet.getWidth()) {
			currentX += tileWidth;
		}
		else {
			currentX = 0;
			currentY += tileHeight;
		}
		return tile;
	}
}
