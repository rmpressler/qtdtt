package TileMap;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

import javax.swing.JFileChooser;

public class TileMap {
	private Tile[][] tiles;
	
	public int getWidth() {
		if(tiles == null) {
			return -1;
		}
		
		return tiles.length;
	}
	
	public int getHeight() {
		if(tiles == null) {
			return -1;
		}
		
		return tiles[0].length;
	}
	
	public BufferedImage getImg(int x, int y) {
		return tiles[x][y].getImg();
	}
	
	public void setTile(int x, int y, int id) {
		tiles[x][y] = TileFactory.createTile(id);
	}
	
	public void loadMap() {
		
		//Prompt user to choose location to open from
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.showOpenDialog(null);
		
		FileReader fileReader;
		try {
			//fileReader = new FileReader(fileChooser.getSelectedFile());
			fileReader = new FileReader(new File(".\\assets\\maps\\sandbox.map"));
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			//read dimensions from first line
			String[] dimensions = bufferedReader.readLine().split(" ");
			int width = Integer.parseInt(dimensions[0]);
			int height = Integer.parseInt(dimensions[1]);
			
			tiles = new Tile[width][height];
			
			for(int i = 0;i < height;i++) {		//for every row
				//get line in file
				String line = bufferedReader.readLine();
				String[] sIds = line.split(" ");
				for(int j = 0;j < sIds.length;j++) {
					tiles[j][i] = TileFactory.createTile(Integer.parseInt(sIds[j]));
				}
			}
			
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean isWalkable(int x, int y) {
		return tiles[x][y].isWalkable();
	}
	
	public void saveMap() {
		//Prompt user to choose location to save to
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.showOpenDialog(null);
		
		//Open file and write the dimensions in the first line,
		//then each tile's type on a new line.
		try {
			PrintWriter writer = new PrintWriter(fileChooser.getSelectedFile());
			writer.println(getWidth() + " " + getHeight());
			for(int i = 0; i < tiles.length; i++) {
				for(int j = 0; j < tiles[i].length; j++) {
					writer.print(tiles[i][j].getID());
					if(j != tiles[i].length - 1) writer.print(" ");
				}
				if(i != tiles.length - 1) writer.println();
			}
			writer.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public TileMap() {
		loadMap();
	}
	
	public TileMap(int width, int height) {
		tiles = new Tile[width][height];
	}
}
