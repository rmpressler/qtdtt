package Camera;

public interface Renderable {
	public ImageData getImageData();
	public boolean isAlive();
	public void update();
	public int getX();
	public int getY();
}