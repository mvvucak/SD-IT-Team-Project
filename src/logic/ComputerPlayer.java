package logic;

public final class ComputerPlayer extends Player {
	
	public ComputerPlayer(Deck d)
	{
		super(d);
	}

	@Override
	public boolean isHuman() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String chooseCategory() {
		String bestCategory = this.currentCard.getHighest();
		return bestCategory;
	}

}
