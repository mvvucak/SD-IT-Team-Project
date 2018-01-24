package logic;

import java.util.Arrays;

public class Deck {

	private Card[] cards;
	private int deckSize; // no of cards in current deck 
	public static final int MAXIMUM_DECK_SIZE = 40;
	
	
	Deck()
	{
		this.cards = new Card[MAXIMUM_DECK_SIZE];
		this.deckSize = 0;
	}
	
	Deck(Card[] c, boolean shuffle) {
		this.cards = c;
		this.deckSize = c.length;
		if(shuffle) this.shuffle();
	}
	
	/**
	 * Shuffles the deck. Replaces card array with a randomly reordered version.
	 */
	public void shuffle()
	{
		int i=0, random;
		//Card array to hold shuffled version of the deck.
		Card[] shuffled = new Card[MAXIMUM_DECK_SIZE];
		//Index containing last card in the deck.
		final int end = deckSize-1;
		
		//As long as there are elements in the original deck
		while (i <= end) {

			//Get random integer between 0 and i+1.
			random = (int) Math.floor( Math.random() * (i + 1));

			//Move a random card in the deck to the position i
			if (random != i) {
      			shuffled[i] = shuffled[random];
    		}

			//Replace the random card with a unique card from the deck.
			//No duplicates created this way.
    		shuffled[random] = this.cards[i];
    		i++;
		}
		
		//Replace non-shuffled deck with shuffled version.
		this.cards = shuffled;
		
	}
	
	/**
	 * Splits the deck into a given number of smaller decks (sub decks) evenly
	 * If even split is impossible, some decks will have one less card than others.
	 * @param n The number of sub decks to split the big deck into
	 * @return The array of smaller decks
	 */
	public Deck[] split(int n)
	{
		//Create array of decks and initialize each one.
		Deck[] subDecks = new Deck[n];
		for(int i = 0; i<n; i++)
			subDecks[i] = new Deck();
		
		//Pointer for the next deck in the cycle
		int whichDeck = 0;
		
		//For each card in the big deck
		for(int i = 0; i<deckSize; i++)
		{
			System.out.println("pos " + i);
			//Add the card to the current sub deck in the cycle
			Card cardToAdd = this.cards[i];
			subDecks[whichDeck].addCardToTop(cardToAdd);
			
			//Then go to the next sub deck in the cycle for the next loop.
			//Back to the very first sub deck if just added to the last.
			if(whichDeck == n-1)
			{
				whichDeck = 0;
			}
			else
			{
				whichDeck++;
			}
			
		}
		
		return subDecks;
	}
/*
	public static void main(String[] args) {
		
		Deck testDeck = new Deck();
		testDeck.addCardToTop(new Card("Blue"));
		testDeck.addCardToTop(new Card("Green"));
		testDeck.addCardToTop(new Card("Red"));
		testDeck.addCardToTop(new Card("Purple"));
		testDeck.addCardToTop(new Card("Cyan"));
		testDeck.addCardToTop(new Card("Yellow"));
		testDeck.addCardToTop(new Card("Pink"));
		testDeck.addCardToTop(new Card("Teal"));
		testDeck.addCardToTop(new Card("Orange"));
		testDeck.addCardToTop(new Card("Brown"));
		testDeck.addCardToTop(new Card("White"));
		testDeck.addCardToTop(new Card("Black"));
		testDeck.addCardToTop(new Card("Grey"));
		testDeck.addCardToTop(new Card("Beige"));
		testDeck.addCardToTop(new Card("Crimson"));
		testDeck.addCardToTop(new Card("Scarlet"));
		testDeck.addCardToTop(new Card("Violet"));
		
		
		System.out.println(testDeck.getDeckList());
		
		testDeck.shuffle();
		
		System.out.println(testDeck.getDeckList());
		
		Deck deckToAdd = new Deck();
		deckToAdd.addCardToTop(new Card("Maroon"));
		deckToAdd.addCardToTop(new Card("Magenta"));
		deckToAdd.addCardToTop(new Card("Ebony"));
		
		testDeck.addCardsToBottom(deckToAdd);
		
		System.out.println(testDeck.getDeckList());
		
		Deck[] newDecks = testDeck.split(3);
		for (Deck d: newDecks)
		{
			System.out.println(d.getDeckSize());
			System.out.println(d.getDeckList());
			
		}
		}*/
	
	public String getDeckList()
	{
		String cardList = "";
		for(int i=0; i<deckSize; i++)
		{
			cardList = String.format("%s %s %n", cardList, cards[i].getDescription());
		}
		return cardList;
	}
	
	public String getDeckDetails()
	{
		String cardDetails = "";
		
		for(int i=0; i<deckSize; i++)
		{
			cardDetails = String.format("%s %s %n", cardDetails, cards[i].printCard());
		}
		return cardDetails;
	}
	
	
	/**
	 * Adds a given card to the top of the deck..
	 * @param c The card to be added.
	 */
	public void addCardToTop(Card c)
	{
		System.out.println(this.cards.length + " no of cards " + this.deckSize);
		this.cards[deckSize] = c;

		//Increment deck size to register addition of a card.
		this.deckSize++;
	}
	
	/**
	 * Adds given cards to the bottom of the deck using an insertion algorithm.
	 * @param c The array of cards to be added.
	 * @param n The number of cards to be added.
	 */
	public void addCardsToBottom(Card[] c, int n)
	{
		//Update deck size to hold new cards
		deckSize = deckSize + n;
		
		//Shift all cards in the deck to make space for new cards at bottom.
		for(int i=deckSize-1; i >= n; i--)
		{		
			cards[i] = cards[i-n];
		}
		
		//Add new cards to the bottom of the deck
		for(int j=0; j<n;j++)
		{
			cards[j] = c[j];
		}
	}
	
	/**
	 * Adds given cards to the bottom of the deck using an insertion algorithm.
	 * Takes a Deck object instead of an array of cards.
	 * @param d The Deck containing the cards to be added
	 */
	public void addCardsToBottom(Deck d)
	{
		//Get cards to add and their number.
		Card[] c = d.getCards();
		int noCardsToAdd = d.getDeckSize();
		
		//Update deck size to hold new cards
		deckSize = deckSize + noCardsToAdd;
		
		//Shift all cards in deck to make space for new cards at bottom.
		for(int i=deckSize-1; i >= noCardsToAdd; i--)
		{		
			cards[i] = cards[i-noCardsToAdd];
		}
		
		//Add new cards to the bottom of the deck.
		for(int j=0; j<noCardsToAdd;j++)
		{
			cards[j] = c[j];
		}
	}
	
	public Card getTopCard()
	{
		return cards[deckSize-1];
	}
	
	public Card drawTopCard()
	{
		Card toDraw = cards[deckSize-1];
		cards[deckSize-1] = null;
		deckSize--;
		return toDraw;
	}

	public Card[] getCards() {
		return cards;
	}

	public void setCards(Card[] cards) {
		this.cards = cards;
	}

	public int getDeckSize() {
		return deckSize;
	}

	public void setDeckSize(int deckSize) {
		this.deckSize = deckSize;
	}
	
	public void setSpecificCard (Card c, int index)
	{
		if(cards[index]==null)
		{
			deckSize++;
		}
		this.cards[index] = c;
	}
	
	public Card getSpecificCard (int index)
	{
		return cards[index];
	}
	
	public String toString() {
		String output = "[";
		for(int i = 0; i < this.cards.length; i++) {
			if(this.cards[i] == null) continue;
			output += "\'";
			output += this.cards[i].getDescription();
			output += "\', ";
		}
		 	output += "]";
		return output;
	}

}