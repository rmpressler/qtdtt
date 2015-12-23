package Option;

import GameState.GameStateManager;

public class LoadOption extends Option {
	public LoadOption(GameStateManager gsm) {
		super(gsm);
		
		this.text = "Load Game";
	}

	@Override
	public void execute() {
		gsm.setState(GameStateManager.LOADSTATE);
	}

}
