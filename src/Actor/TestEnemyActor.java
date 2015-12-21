package Actor;

import Animation.ActorAnimator;
import Camera.ImageData;
import Game.Config;
import Physics.HitBox;
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
		hp = 20;
	}

	@Override
	public ImageData getImageData() {
		return new ImageData(img, x, y);
	}

	@Override
	public HitBox getHitBox() {
		return new HitBox(x, y, x + width, y + height);
	}

	@Override
	public String getType() {
		return "enemy";
	}

	@Override
	public int getDmg() {
		// TODO Auto-generated method stub
		return 0;
	}
}