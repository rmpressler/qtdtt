package Option;

import GameState.GameStateManager;

public class SaveOption extends Option {
	public SaveOption(GameStateManager gsm) {
		super(gsm);
		
		this.text = "Save Game";
	}

	@Override
	public void execute() {
		gsm.getWorld().saveGame();
	}

}
