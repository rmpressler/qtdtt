package TileMap;

public class DirtTile extends Tile{
	public DirtTile(int id) {
		super(id);
		
		this.setImg(TileImage.getImage("dirt"));
		this.setWalkable(false);
	}
}
