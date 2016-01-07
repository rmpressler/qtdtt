package Option;

import GameState.GameStateManager;

/**Encapsulates the 'New Game' menu option.
 * 
 * Contains methods to get a BufferedImage representation of the option
 * and to begin a new game. Requires a reference to the GameStateManager.
 * 
 * @author r.pressler
 *
 */
public class NewOption extends Option {
	public NewOption(GameStateManager gsm) {
		super(gsm);
		
		this.text = "New Game";
	}

	@Override
	public void execute() {
		gsm.startNewGame();
	}
}
