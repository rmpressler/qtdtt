package Option;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Camera.ImageData;
import Camera.Renderable;
import GameState.GameStateManager;

public abstract class Option implements Renderable{
	protected int x, y;
	protected String text;
	protected boolean selected;
	protected GameStateManager gsm;
	public abstract void execute();
	
	public Option(GameStateManager gsm) {
		this.gsm = gsm;
	}
	
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Option() {
		selected = false;
	}
	
	public void setSelected(boolean s) {
		selected = s;
	}
	
	@Override
	public void update() {}
	
	@Override
	public int getX() { return x; }
	
	@Override
	public int getY() { return y; }
	
	@Override
	public boolean isAlive() { return true; }
	
	@Override
	public ImageData getImageData() {
		BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) img.getGraphics();
		g.setFont(new Font("Verdana", Font.PLAIN, 12));
		int width = g.getFontMetrics().stringWidth(text);
		int height = g.getFontMetrics().getHeight() + 5;
		g.dispose();
		img = null;
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		g = (Graphics2D) img.getGraphics();
		g.setFont(new Font("Verdana", Font.PLAIN, 12));
		if(selected) g.setColor(Color.YELLOW);
		else g.setColor(Color.WHITE);
		g.drawString(text, 0, height - 5);
		g.dispose();
		return new ImageData(img, x, y);
	}
}
