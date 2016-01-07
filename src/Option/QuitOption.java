package Option;

import GameState.GameStateManager;
import static GameState.GameStateManager.*;

public class QuitOption extends Option {	
	public QuitOption(GameStateManager gsm) {
		super(gsm);
		
		this.text = "Quit";
	}

	@Override
	public void execute() {
		gsm.setState(MENUSTATE);
	}
}
