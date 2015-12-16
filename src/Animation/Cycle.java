package Animation;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Cycle {
	private HashMap<String, Integer> cycles;
	private long lastFrameTime;
	private SpriteImage sprites;
	
	public Cycle(int width, int height) {
		cycles = new HashMap<String, Integer>();
		lastFrameTime = System.nanoTime();
		sprites = new SpriteImage(width, height);
	}
	
	public BufferedImage getFrame(String cycleName) {		
		if(cycles.get(cycleName) == null) {
			cycles.put(cycleName, 1);
			BufferedImage img = sprites.getImage(cycleName + "_" + 1);
			cycles.replace(cycleName, 2);
			return img;
		}
		
		int frameNum = cycles.get(cycleName);
		
		if((System.nanoTime() - lastFrameTime) / 1000000 < 300) {
			return sprites.getImage(cycleName + "_" + frameNum);
		}
		
		BufferedImage img = sprites.getImage(cycleName + "_" + frameNum);
		if(frameNum == 2) cycles.replace(cycleName, 1);
		else cycles.replace(cycleName, cycles.get(cycleName) + 1);
		lastFrameTime = System.nanoTime();
		return img;
	}
}
