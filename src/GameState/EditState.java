package GameState;

import java.awt.Graphics2D;
import java.awt.Image;

import Game.Config;
import TileMap.TileFactory;
import TileMap.TileMap;

public class EditState extends GameState {
	TileMap tMap;
	int selection;
	int tileSize;
	int windowSize;
	int width;
	int height;
	
	public EditState(GameStateManager gsm) {
		super(gsm);
		
		width = 20;
		height = 20;
		
		windowSize = Config.VIEW_SIZE * Config.TILE_SIZE;
		tileSize = windowSize / width;
		
		tMap = new TileMap(width, height);
		
		for(int i = 0;i < tMap.getWidth();i++) {
			for(int j = 0;j < tMap.getHeight();j++) {
				tMap.setTile(i, j, TileFactory.GRASS);
			}
		}
	}
	
	public void init() {
		
	}
	
	public void update(){}
	public void draw(Graphics2D g) {
		for(int i = 0;i < tMap.getWidth();i++) {
			for(int j = 0;j < tMap.getHeight();j++) {
				Image img = tMap.getImg(i,  j);
				img = img.getScaledInstance(tileSize, 
						tileSize, 
						Image.SCALE_DEFAULT);
				g.drawImage(img, i * tileSize, j * tileSize, null);
			}
		}
		for(int i = 0; i < tMap.getWidth();i++) {
			g.drawLine(i * tileSize, 0,  i * tileSize, windowSize);
		}
		for(int i = 0; i < tMap.getHeight();i++) {
			g.drawLine(0, i * tileSize, windowSize,  i * tileSize);
		}
	}
}
