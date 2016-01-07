package TileMap;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.HashMap;
import Game.Config;

import javax.imageio.ImageIO;

public class TileImage {
	private static HashMap<String, BufferedImage> images;
	private static String tileSheetPath = Config.TS_PATH;
	
	private static void init() {
		//*****GUIDELINES FOR ADDING TILESHEETS*****//
		/*
		 * 1. Every image must be in .png format
		 * 2. Every image must have an accompanying data file
		 *    with the EXACT SAME NAME.
		 *    
		 *    i.e. grass_set.png + grass_set.data
		 *    
		 *    DATA FILE RULES
		 *    
		 *    First line in format:
		 *    [width of tile] [height of tile] [number of tiles]
		 *    Each remaining line should be the name of the next tile
		 *    
		 *    For a tilesheet of 64 x 64 tiles with 9 tiles total:
		 *    
		 *    64 64 9
		 *    grass_top_left
		 *    grass_top
		 *    grass_topgrass_top_right
		 *    grass_topgrass_left
		 *    grass_topgrass
		 *    grass_topgrass_right
		 *    grass_topgrass_bottom_left
		 *    grass_topgrass_bottom
		 *    grass_topgrass_bottom_right
		 *    
		 *    The order of processing would be:
		 *    
		 *    0 1 2
		 *    3 4 5
		 *    6 7 8
		 */
		
		images = new HashMap<String, BufferedImage>();
		
		//Load all tilesheets in tileSheetPath
		String[] pngFileNames = getFileNames(tileSheetPath, ".png");
		try {
			for(int i = 0;i < pngFileNames.length;i++) {
				String fileName = pngFileNames[i];
				
				//Get raw image data from .data file
				FileReader fileReader = 
		                new FileReader(tileSheetPath + fileName.substring(0, fileName.length() - 4) + ".data");
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				
				//Get tilesheet stats from first line of .data file
				String[] dimensions = bufferedReader.readLine().split(" ");
				int tileWidth = Integer.parseInt(dimensions[0]);
				int tileHeight = Integer.parseInt(dimensions[1]);
				int numTiles = Integer.parseInt(dimensions[2]);
				
				//Load image
				TileSheet img = new TileSheet(
						ImageIO.read(new File(tileSheetPath + fileName)),
						tileWidth,
						tileHeight,
						numTiles);
				
				//Add tiles to images
				for(int j = 0;j < numTiles;j++) {
					BufferedImage bi = new BufferedImage(Config.TILE_SIZE, Config.TILE_SIZE, BufferedImage.TYPE_INT_RGB);
					Graphics g = bi.getGraphics();
					g.drawImage(img.getTile().getScaledInstance(Config.TILE_SIZE, Config.TILE_SIZE, BufferedImage.SCALE_DEFAULT), 0, 0, null);
					images.put(bufferedReader.readLine(), bi);
				}
				
				bufferedReader.close();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/* Returns an array of String that represents the filename of every
	 * file in the path argument. The path arg can be relative or absolute.
	 * Only returns file with names containing the text in the filterText 
	 * argument.
	 * 
	 * @param	path 		Directory to search for files
	 * @param	filterTxt	Text to match in files
	 * @return				Array of String file names
	 */
	private static String[] getFileNames(String path, String filterTxt) {
		File directory = new File(path);
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				String lowercaseName = name.toLowerCase();
//				if (lowercaseName.endsWith(filterTxt)) {
				if (lowercaseName.endsWith(".png")) {
					return true;
				} else {
					return false;
				}
			}
		};
		return directory.list(filter);
	}

	public static BufferedImage getImage(String imageName) {
		if(images == null) {
			init();
		}
		
		return images.get(imageName);
	}
}
