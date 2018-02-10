package logic;

import javax.swing.text.View;

public final class HumanPlayer extends Player {
	
	
	public HumanPlayer(Deck d)
	{
		super(d);
		this.name = "You";
	}
	
	public HumanPlayer()
	{
		super();
		this.name = "You";
	}

	/**
	 * @return Whether the player is human or not.
	 */
	public boolean isHuman() {
		return true;
	}	
	
	
	

	@Override
	public int chooseCategory() {
		return Session.view.getCategory();
	}

}
