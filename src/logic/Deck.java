public class Deck {

	private Card[] cards;

	Deck(Card[] c) {
		this.cards = c;
	}

	public Card[] shuffle() {
		int n = this.cards.length, i = 0, random;
		Card[] shuffled = new Card[n];
		final int end = n - 1;

		// while there remains elemetns to shuffle
		while (i <= end) {

			random = (int) Math.floor( Math.random() * (i + 1));

			if (random != i) {
      			shuffled[i] = shuffled[random];
    		}

    		shuffled[random] = this.cards[i];
    		i++;
		}

		return shuffled;
	}

	public static void main(String[] args) {
		Card[] testDeck = new Card[5];
		testDeck[0] = new Card("Blue");
		testDeck[1] = new Card("Green");
		testDeck[2] = new Card("Red");
		testDeck[3] = new Card("Purple");
		testDeck[4] = new Card("Cyan");

		Deck toBeShuffled = new Deck(testDeck);
		Card[] completedShuffle = toBeShuffled.shuffle();
		for (Card card : completedShuffle) {
			System.out.println(card.getDescription());
		}
	}

}