package logic;

public final class HumanPlayer extends Player {
	
	
	public HumanPlayer(Deck d)
	{
		super(d);
	}
	
	public HumanPlayer()
	{
		super();
	}

	/**
	 * @return Whether the player is human or not.
	 */
	public boolean isHuman() {
		return true;
	}	
	
	/**
	 * Returns the player's identity, always "You" for a human player.
	 * @return The player's name.
	 */
	public String getName()
	{
		return "You";
	}
}
