package GameState;

import java.awt.image.BufferedImage;

import javax.swing.JFileChooser;

import Game.SaveFile;
import Input.Command;

public class LoadState extends GameState {
	private JFileChooser fc;

	public LoadState(GameStateManager gsm) {
		super(gsm);
		init();
	}

	@Override
	public void init() {}

	@Override
	public void update() {
		if(fc == null) {
			fc = new JFileChooser(".\\save");
			fc.showOpenDialog(null);
		}
		
		SaveFile sf = new SaveFile();
		sf.load(fc.getSelectedFile());
		
		gsm.setData(sf);
		gsm.setState(GameStateManager.PLAYSTATE);
	}

	@Override
	public BufferedImage getScreen() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void passInput(Command command) {
		// TODO Auto-generated method stub

	}

}
