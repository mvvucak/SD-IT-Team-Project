package logic;

public final class ComputerPlayer extends Player {
	
	public ComputerPlayer(Deck d, int n)
	{
		super(d);
		this.name = "Computer " + n;
	}
	
	public ComputerPlayer()
	{
		super();
	}
	
	public ComputerPlayer(int n)
	{
		super();
		this.name = "Computer " + n;
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
	 * Automatically chooses the category to compare against.
	 * Choice is made based on the highest value on the AI's current card.
	 * @return The name of the chosen category.
	 */
	public int chooseCategory() {
		int bestCategory = this.currentCard.getHighest();
		return bestCategory;
	}

}
