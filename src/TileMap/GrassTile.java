package TileMap;

public class GrassTile extends Tile{
	public GrassTile(int id) {
		super(id);
		
		this.setWalkable(true);
		int grassChoice = (int)(Math.random() * 10);
		if(grassChoice < 9) {
			this.setImg(TileImage.getImage("grass_2"));
		}
		else {
			this.setImg(TileImage.getImage("grass"));
		}
	}
}
