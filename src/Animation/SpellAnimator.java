package Animation;

import java.awt.image.BufferedImage;

public class SpellAnimator extends Animator {
	private SpriteImage sprites;
	private String direction;
	private String prefix;
	
	public SpellAnimator(String prefix, String direction, int width, int height) {
		sprites = new SpriteImage(width, height);
		this.direction = direction;
		this.prefix = prefix;
	}

	@Override
	public void move(String direction) {
		this.direction = direction;
	}

	@Override
	public BufferedImage getImage() {
		return sprites.getImage(prefix + "_" + direction);
	}
	
	
}
