package Actor;

import Animation.ActorAnimator;
import Camera.ImageData;
import Game.Config;
import TileMap.TileMap;

public class HeroActor extends Actor{
	
	public HeroActor(int x, int y, TileMap tm) {
		super(x, y, tm);
		
		height = (int)(0.9 * Config.TILE_SIZE);
		width = (int)(0.85 * Config.TILE_SIZE);
		animate = new ActorAnimator("hero", width, height);
		img = animate.getImage();
	}
	
	public void startMove(String direction) {
		switch(direction) {
			case "up":
				movingUp = true;
				break;
			case "down":
				movingDown = true;
				break;
			case "left":
				movingLeft = true;
				break;
			case "right":
				movingRight = true;
				break;
		}
	}
	
	public void stopMove(String direction) {
		switch(direction) {
			case "up":
				movingUp = false;
				break;
			case "down":
				movingDown = false;
				break;
			case "left":
				movingLeft = false;
				break;
			case "right":
				movingRight = false;
				break;
		}
	}

	@Override
	public ImageData getImageData() {
		return new ImageData(img, x, y);
	}
}