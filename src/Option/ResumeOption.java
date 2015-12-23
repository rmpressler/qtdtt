package Option;

import GameState.GameStateManager;

public class ResumeOption extends Option{
	
	public ResumeOption(GameStateManager gsm) {
		super(gsm);
		this.text = "Resume";
	}

	@Override
	public void execute() {
		gsm.setState(GameStateManager.PLAYSTATE);
	}
}
