package Actor;

import java.awt.event.KeyEvent;

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
	
	public void keyPressed(int key) {
		if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
			movingUp = true;
		}
		else if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
			movingDown = true;
		}
		else if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			movingLeft = true;
		}
		else if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			movingRight = true;
		}
	}
	
	public void keyReleased(int key) {
		if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
			movingUp = false;
		}
		else if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
			movingDown = false;
		}
		else if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			movingLeft = false;
		}
		else if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			movingRight = false;
		}
	}

	@Override
	public ImageData getImageData() {
		return new ImageData(img, x, y);
	}
}