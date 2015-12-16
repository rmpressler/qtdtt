package Animation;

import java.awt.image.BufferedImage;

public class ActorAnimator extends Animator {
	private Cycle cycles;
	private BufferedImage currentImg;
	private String prefix;
	
	public ActorAnimator(String prefix, int width, int height) {
		this.prefix = prefix;
		cycles = new Cycle(width, height);
		move("down");
	}

	@Override
	public void move(String direction) {
		String cycleName = prefix + "_" + direction;
		currentImg = cycles.getFrame(cycleName);
	}

	@Override
	public BufferedImage getImage() {
		return currentImg;
	}
	
	
}
