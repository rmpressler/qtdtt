package Animation;

import java.awt.image.BufferedImage;

public abstract class Animator {
	public abstract void move(String direction);
	public abstract BufferedImage getImage();
}
