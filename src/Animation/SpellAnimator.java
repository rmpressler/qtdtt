package Animation;

import java.awt.image.BufferedImage;

public class SpellAnimator extends Animator {
	private Cycle cycles;
	private BufferedImage currentImg;
	
	public SpellAnimator(String direction, int width, int height) {
		cycles = new Cycle(width, height);
		move(direction);
	}

	@Override
	public void move(String direction) {
		currentImg = cycles.getFrame(direction);
	}

	@Override
	public BufferedImage getImage() {
		return currentImg;
	}
	
	
}
