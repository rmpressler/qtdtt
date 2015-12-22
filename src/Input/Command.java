package Input;

public class Command {
	private boolean toggle;
	private Action action;
	
	// For buttons/one-time checks
	public Command(Action a) {
		action = a;
	}
	
	// For press/release checks
	public Command(Action a, boolean t) {
		action = a;
		toggle = t;
	}
	
	/** Getter that returns action type as Enum <code>Action</code>
	 * 
	 * @return Enum <code>Action</code> indicating the input action
	 */
	public Action getAction() {
		return action;
	}
	
	/** Getter that returns whether the action was activated or deactivated
	 * ex. pressing space = true
	 * 		releasing space = false
	 * 
	 * @return whether the action was activated or deactivated
	 */
	public boolean getToggle() {
		return toggle;
	}
}
