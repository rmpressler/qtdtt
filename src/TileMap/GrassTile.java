package TileMap;

public class GrassTile extends Tile{
	public GrassTile(int id) {
		super(id);
		
		this.setWalkable(true);
		this.setImg(TileImage.getImage("grass"));
	}
}
