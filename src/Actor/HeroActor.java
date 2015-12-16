package Actor;

import java.awt.event.KeyEvent;

import Animation.ActorAnimator;
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
		if(key == KeyEvent.VK_UP) {
			movingUp = true;
		}
		else if(key == KeyEvent.VK_DOWN) {
			movingDown = true;
		}
		else if(key == KeyEvent.VK_LEFT) {
			movingLeft = true;
		}
		else if(key == KeyEvent.VK_RIGHT) {
			movingRight = true;
		}
	}
	
	public void keyReleased(int key) {
		if(key == KeyEvent.VK_UP) {
			movingUp = false;
		}
		else if(key == KeyEvent.VK_DOWN) {
			movingDown = false;
		}
		else if(key == KeyEvent.VK_LEFT) {
			movingLeft = false;
		}
		else if(key == KeyEvent.VK_RIGHT) {
			movingRight = false;
		}
	}
}