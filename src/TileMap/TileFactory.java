package TileMap;

public class TileFactory {
	public static final int GRASS = 0;
	public static final int DIRT = 1;
	
	public static Tile createTile(int id) {
		switch(id) {
			case GRASS:
				return new GrassTile(id);
			case DIRT:
				return new DirtTile(id);
			default:
				return null;
		}
	}
}
