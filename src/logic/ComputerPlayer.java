package logic;

public final class ComputerPlayer extends Player {
	
	public ComputerPlayer(Deck d)
	{
		super(d);
	}
	
	public ComputerPlayer()
	{
		super();
	}

	@Override
	/**
	 * @return Whether the player is human or not.
	 */
	public boolean isHuman() {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Returns the AI player's name, represented by its ID.
	 * @return The player's name.
	 */
	public String getName()
	{
		String name = "Computer " + this.identity;
		return name;
	}

	@Override
	/**
	 * Automatically chooses the category to compare against.
	 * Choice is made based on the highest value on the AI's current card.
	 * @return The name of the chosen category.
	 */
	public String chooseCategory() {
		String bestCategory = this.currentCard.getHighest();
		return bestCategory;
	}

}
