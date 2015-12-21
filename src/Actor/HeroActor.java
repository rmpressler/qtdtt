package Actor;

import Animation.ActorAnimator;
import Camera.ImageData;
import Game.Config;
import Physics.HitBox;
import Spells.Fireball;
import TileMap.TileMap;
import World.World;

public class HeroActor extends Actor{
	
	public HeroActor(TileMap tm) {
		super(tm);
		
		height = (int)(0.9 * Config.TILE_SIZE);
		width = (int)(0.85 * Config.TILE_SIZE);
		animate = new ActorAnimator("hero", width, height);
		img = animate.getImage();
		hp = 100;
	}
	
	public void fire(World w) {
		int xOffset = 0;
		int yOffset = 0;
		
		if(World.isCompoundDirection(getDirection())) {
			String[] directions = World.Direction2to1(getDirection());
			if(directions[0].compareTo("up") == 0)
				yOffset -= 25;
			if(directions[0].compareTo("down") == 0)
				yOffset += 25;
			if(directions[1].compareTo("left") == 0)
				xOffset -= 25;
			if(directions[1].compareTo("right") == 0)
				xOffset += 25;
		}
		else {
			if(getDirection() == "up")
				yOffset -= 25;
			if(getDirection() == "down")
				yOffset += 25;
			if(getDirection() == "left")
				xOffset -= 25;
			if(getDirection() == "right")
				xOffset += 25;
		}
		Fireball fb = new Fireball(this.getDirection(), x + (width / 4) - 5 + xOffset, y + (height / 4) - 5 + yOffset);
		w.addRenderableObject(fb);
		w.addCollidableObject(fb);
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
		return "hero";
	}

	@Override
	public int getDmg() {
		return dmg;
	}
}