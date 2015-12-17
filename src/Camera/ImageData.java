package Camera;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ImageData {
	private BufferedImage img;	//Image to draw
	private int x;				//x location in the world
	private int y;				//y location in the world
	
	public ImageData(BufferedImage img, int x, int y) {
		this.img = img;
		this.x = x;
		this.y = y;
	}
	
	public ImageData(Color color, int width, int height, int x, int y) {
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
		Graphics g = img.getGraphics();
		g.setColor(color);
		g.drawImage(img, 0, 0, null);
		g.dispose();
		
		this.x = x;
		this.y = y;
	}
	
	public BufferedImage getImg() {
		return img;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
