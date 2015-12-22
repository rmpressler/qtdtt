package Game;

public abstract class GameObject {
	private boolean renderable;
	private boolean collidable;
	
	public void setRenderable(boolean b) {
		renderable = b;
	}
	
	public void setCollidable(boolean b) {
		collidable = b;
	}
	
	public boolean isRenderable() {
		return renderable;
	}
	
	public boolean isCollidable() {
		return collidable;
	}
}
