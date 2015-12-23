package Option;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Menu {
	private ArrayList<Option> ops;
	private int selection;
	
	public Menu() {
		ops = new ArrayList<Option>();
		selection = 0;
	}
	
	public void init() {
		ops.get(selection).setSelected(true);
	}
	
	public void addOption(Option o) {
		ops.add(o);
	}
	
	public void optionUp() {
		if(selection > 0) {
			ops.get(selection).setSelected(false);
			selection--;
			ops.get(selection).setSelected(true);
		}
	}
	
	public void optionDown() {
		if(selection < ops.size() - 1) {
			ops.get(selection).setSelected(false);
			selection++;
			ops.get(selection).setSelected(true);
		}
	}
	
	public void optionExec() {
		ops.get(selection).execute();
	}
	
	public BufferedImage getImage() {
		// Set box width as longest String width
		int width = 0;
		for(int i = 0;i < ops.size();i++) {
			int thisWidth = ops.get(i).getImageData().getImg().getWidth();
			if(thisWidth > width) {
				width = thisWidth; 
			}
		}
		
		// Set line height to font height
		int lineHeight = ops.get(0).getImageData().getImg().getHeight() + 5;
		
		// Get number of options total
		int numOptions = ops.size();
		
		// Height of all options and spaces
		int boxHeight = (int) (lineHeight * (numOptions * 1.75));
		
		// Start first line at lineHeight since the 0, 0 coord for String is bottom left corner
		int y = lineHeight;
		
		// Create BufferedImage and get graphics context
		BufferedImage bi = new BufferedImage(width, boxHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) bi.getGraphics();
		
		for(Option o: ops) {
			int lineWidth = o.getImageData().getImg().getWidth();
			int x = (width - lineWidth) / 2;
			
			g.drawImage(o.getImageData().getImg(), x, y, null);
			y += (int)(lineHeight);
		}
		g.dispose();
		return bi;
	}
}
