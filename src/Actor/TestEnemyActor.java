package Actor;

import Animation.ActorAnimator;
import Camera.ImageData;
import Game.Config;
import TileMap.TileMap;

public class TestEnemyActor extends Actor{
	
	public TestEnemyActor(TileMap tm) {
		super(tm);
		
		height = (int)(0.9 * Config.TILE_SIZE);
		width = (int)(0.85 * Config.TILE_SIZE);
		
		int boundsX = tm.getWidth() * Config.TILE_SIZE;
		int boundsY = tm.getHeight() * Config.TILE_SIZE;
		
		this.x = (int)((Math.random() * boundsX) - width);
		this.y = (int)((Math.random() * boundsY) - height);
	
		animate = new ActorAnimator("test_enemy", width, height);
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
		
		update();
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