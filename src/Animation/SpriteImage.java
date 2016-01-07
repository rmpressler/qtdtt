package Animation;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.HashMap;

import javax.imageio.ImageIO;

import Game.Config;
import TileMap.TileSheet;

public class SpriteImage {
	private HashMap<String, BufferedImage> images;
	private String spritePath = Config.SPRITE_PATH;
	private int width;
	private int height;
	
	public SpriteImage(int width, int height) {
		this.width = width;
		this.height = height;
		
		init();
	}
	
	private void init() {
		//*****GUIDELINES FOR ADDING SPRITES*****//
		/*
		 * 1. Every image must be in .png format
		 * 2. Every image must have an accompanying data file
		 *    with the EXACT SAME NAME.
		 *    
		 *    i.e. hero_sprite.png + hero_sprite.data
		 *    
		 *    DATA FILE RULES
		 *    
		 *    First line in format:
		 *    [width of sprite] [height of sprite] [number of frames]
		 *    Each remaining line should be the name of the next tile
		 *    
		 *    For a spritesheet of 64 x 64 sprites with 4 frames total:
		 *    
		 *    64 64 4
		 *    hero_walk_up
		 *    hero_walk_down
		 *    hero_walk_right
		 *    hero_walk_left
		 *    
		 *    The order of processing would be:
		 *    
		 *    0 1
		 *    2 3
		 */
		
		images = new HashMap<String, BufferedImage>();
		
		//Load all tilesheets in tileSheetPath
		String[] pngFileNames = getFileNames(spritePath, ".png");
		
		try {
			for(int i = 0;i < pngFileNames.length;i++) {
				String fileName = pngFileNames[i];
				
				//Get raw image data from .data file
				FileReader fileReader = 
		                new FileReader(spritePath + fileName.substring(0, fileName.length() - 4) + ".data");
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				
				//Get tilesheet stats from first line of .data file
				String[] dimensions = bufferedReader.readLine().split(" ");
				int tileWidth = Integer.parseInt(dimensions[0]);
				int tileHeight = Integer.parseInt(dimensions[1]);
				int numTiles = Integer.parseInt(dimensions[2]);
				
				//Load image
				TileSheet img = new TileSheet(
						ImageIO.read(new File(spritePath + fileName)),
						tileWidth,
						tileHeight,
						numTiles);

				//Add tiles to images
				for(int j = 0;j < numTiles;j++) {
					BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
					Graphics g = bi.getGraphics();
					g.drawImage(img.getTile().getScaledInstance(width, height, BufferedImage.SCALE_DEFAULT), 0, 0, null);
					String imgName = bufferedReader.readLine();
					images.put(imgName, bi);
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
	private String[] getFileNames(String path, String filterTxt) {
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
	
	public static BufferedImage scale(BufferedImage oldImage, int newWidth, int newHeight) {
		BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) newImage.getGraphics();
		Image temp = oldImage.getScaledInstance(newWidth, newHeight, Image.SCALE_AREA_AVERAGING);
		g.drawImage(temp,  0,  0,  null);
		g.dispose();
		return newImage;
	}
	
	public BufferedImage getImage(String imageName) {
		if(images == null) {
			init();
		}
		
		return images.get(imageName);
	}
}
