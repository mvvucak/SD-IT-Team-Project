package logic;

public abstract class Player {
 
	public static int counter = 0; // static variable
	protected boolean active; // check if still playing 
	protected int identity;
	protected Deck deck;
	protected Card currentCard;

	Player(Deck d) {
		deck = d;
		this.active = true;
		this.identity = Player.counter;
		Player.counter += 1;
	}
	
	Player()
	{
		this.active = true;
		this.identity = Player.counter;
		Player.counter += 1;
	}
	
	public abstract boolean isHuman();
	
	public abstract String getName();
	
	public abstract int chooseCategory();
	
	/**
	 * Removes the top card from the player's deck and adds it to their hand.
	 */
	public void drawCard()
	{
		this.currentCard = deck.drawTopCard();
	}
	
	/**
	 * Adds cards to the bottom of the player's deck when they win a round.
	 * @param d A deck containing all claimed cards (min. 2).
	 */
	public void addWonCards(Deck d)
	{
		this.deck.addCardsToBottom(d);
	}
	
	public void setDeck(Deck val) {
		 this.deck = val;
	}
	
	public Deck getDeck() {
		return this.deck;
	}
	
	
	
	/**
	 * Returns the details of the card currently in the player's hand.
	 * @return A formatted string containing card details.
	 */
	public String showCurrentCard()
	{
		return this.currentCard.printCard();
	}
	/**
	 * Prints out the details of the card currently in the player's hand.
	 * Alternative to showCurrentCard, one will be deleted when done.
	 */
	public void printCurrentCard()
	{
		String cardDetails = this.currentCard.printCard();
		System.out.println("You drew: ");
		System.out.println(cardDetails);
	}

	public boolean comparePlayer(Player otherPerson) {
		return this.identity == otherPerson.getIdentity();
	}
	
	/**
	 * @return Whether the player has won (i.e. has all the cards in the game).
	 */
	public boolean hasWon()
	{
		return deck.isFull();
	}
	
	public boolean hasLost() {
		final boolean ELIMINATED = true;
		final boolean NOT_ELIMINATED = false;
// 
		if(this.deck.isEmpty()) {
			this.active = false; 
			return ELIMINATED;
		}
		return NOT_ELIMINATED;
		
	}

	public int getIdentity() {
		return this.identity;
	}
	
	public boolean getActiveStatus() {
		return this.active;
	}
	
	public void setActiveStatus(boolean val) {
		this.active = val;
	}

	/**
	* main method to test Player class 
	
	public static void main(String[] args) {
	 	System.out.println( "testing Player class.." );
		Player human = new Player();
		Player ai1 = new Player();
		Player ai2 = new Player();

		Player[] playerList = new Player[3];
		playerList[0] = ai1;
		playerList[1] = human;
		playerList[2] = ai2;

		for(int i = 0; i < playerList.length;i++) {
			Player otherPlayer = playerList[i];
			if( human.activePlayer(otherPlayer) ) {
				System.out.println( "Found myself at position " + i);
			}
		}

	}*/

	public Card getCurrentCard() {
		// return top card in deck
		return this.currentCard;
	}
	

}