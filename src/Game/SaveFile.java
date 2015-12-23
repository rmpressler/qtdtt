package Game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**Handles all load/save operations of save files.
 * 
 * Save file format:
 * 
 * (Object Title)
 * (Data)
 * 
 * (Object Title)
 * (Data)
 * 
 * e.x.
 * PLAYER_X
 * 150
 * 
 * represents the player x location of 150
 * 
 * @author r.pressler
 *
 */

public class SaveFile {
	public static final String SAVE_NAME = "SAVE_NAME";
	public static final String PLAYER_X = "PLAYER_X";
	public static final String PLAYER_Y = "PLAYER_Y";
	
	private HashMap<String, String> saveData; 
	private FileReader fileReader;
	
	public SaveFile() {
		saveData = new HashMap<String, String>();
	}
	
	public void save(File file) {
		FileWriter fw;
		try {
			fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write("My Save Game\n");
			
			String[] keys = new String[saveData.size()];
			saveData.keySet().toArray(keys);
			for(String key : keys) {
				bw.write("\n" + key + "\n" + saveData.get(key) + "\n");
			}
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void load(File file) {
		try {
			fileReader = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		String line;
		try {
			// First line is name of save
			line = bufferedReader.readLine();
			saveData.put("Save Name", line);
			line = bufferedReader.readLine();
			while(line != null) {
				while(line == "") {
					line = bufferedReader.readLine();
				}
				
				String key = bufferedReader.readLine();
				String val = bufferedReader.readLine();
				
				saveData.put(key,  val);
				
				line = bufferedReader.readLine();
				
			}
			
			String[] keys = new String[saveData.size()];
			saveData.keySet().toArray(keys);
			for(String key : keys) {
				System.out.println(key + ": " + saveData.get(key));
			}
			
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getInt(String key) {
		return Integer.parseInt(saveData.get(key));
	}
	
	public String getStr(String key) {
		return saveData.get(key);
	}
	
	public void setData(String key, String data) {
		saveData.put(key,  data);
	}
	
	public void setData(String key, int data) {
		saveData.put(key,  Integer.toString(data));
	}
}
