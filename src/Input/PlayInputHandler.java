package Input;

import World.World;

public class PlayInputHandler {
	public static World world;
	
	public static void setWorld(World newWorld) {
		world = newWorld;
	}
	
	public static void setToggle1(boolean toggle) {
		if(toggle) world.getHero().startMove("up");
		else world.getHero().stopMove("up");
	}
	
	public static void setToggle2(boolean toggle) {
		if(toggle) world.getHero().startMove("down");
		else world.getHero().stopMove("down");
	}
	
	public static void setToggle3(boolean toggle) {
		if(toggle) world.getHero().startMove("left");
		else world.getHero().stopMove("left");
	}
	
	public static void setToggle4(boolean toggle) {
		if(toggle) world.getHero().startMove("right");
		else world.getHero().stopMove("right");
	}
	
	public static void activateAction0() {
		world.getHero().fire(world);
	}
}
