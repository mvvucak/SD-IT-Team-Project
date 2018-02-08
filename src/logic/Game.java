package logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

import java.io.*;

public class Game {
	
	private int gameId;
	private boolean isGameComplete;
	private int noOfPlayers = 1;
	private Player[] playerList;
	private Player operator; // ref to the person playing
	private int currentPlayerTurn;
	private int pRemainingCount; // number of players still active
	// holds count for total number of cards draws in a game
	// holds count for total number of rounds in a game
	private int roundCount, gameDrawCount; 
	private Deck mainDeck;
	private ArrayList<Round> roundList;

	public Game(int noOfAi, int newGameId) {
		this.gameId = newGameId;
		this.isGameComplete = false;
		this.noOfPlayers = this.noOfPlayers + noOfAi;
		this.playerList = new Player[this.noOfPlayers];
		this.pRemainingCount = this.noOfPlayers;
		this.roundCount = 0;
		this.gameDrawCount = 0;
		this.roundList = new ArrayList<Round>();
		boolean pleaseShuffle = true;
		Card[] pack = this.loadDeck();
		this.mainDeck = new Deck(pack, pleaseShuffle);
		this.init();
	}

	public void init() {
		// Add players to our player list 
		this.addPlayersToGame();
		// Save the cards from the deck text file
		this.loadDeck();
		// Divide all our starting cards up between players 
		this.dealCards();
		// Assigns a random player number to begin the game
		currentPlayerTurn = selectRandomPlayer();
	}
	
	public void saveRound(Round rnd) {
		this.roundList.add(rnd);
	}
	
	public Round getCurrentRound() {
		return this.roundList.get(this.roundCount);
	}
	
	private void dealCards() {
		Deck[] splitCards = this.mainDeck.split(this.noOfPlayers);
		for(int i = 0; i < this.noOfPlayers; i++ ) {
			playerList[i].setDeck(splitCards[i]); 
		}
	}
	
	private Card[] loadDeck() {
	 	Card[] topTrumpsPack = new Card[Deck.MAXIMUM_DECK_SIZE];
		String filename = "StarCitizenDeck.txt";
		try {
			FileReader reader = new FileReader(filename); 
			Scanner scan = new Scanner(reader);
			// skip description line in text file
			scan.next(); 
			
			for(int i = 0; i < Card.catNames.length; i++) {
				Card.catNames[i] = scan.next();
			}
			int index = 0;
			 while (scan.hasNext()) {
					int[] values = new int[Card.catNames.length];
				    String desc = scan.next();
				    //Changed value reading to use number of categories instead of making it hard-coded
				    //Otherwise just set values length to 5 instead of Card.catNames.length -Mat  
				    for(int i=0; i < values.length; i++) {
				    	values[i] = scan.nextInt();
				    }
				    topTrumpsPack[index] = new Card(desc, values);
				    index++;
			  }
			 scan.close();
		} catch(IOException e) {
			
		}
		return topTrumpsPack; 
	}


	private void addPlayersToGame() {
		// assign HumanPlayer object to the game operator and add them to Player array
		this.operator = new HumanPlayer(); 
		this.playerList[0] = this.operator;
		// Populate remainder of array with AIs
		int len = this.playerList.length;
		for(int i = 1; i < len; i++) {
			this.playerList[i] = new ComputerPlayer(i);
		}

	}
	
	
	/**
	 * Adds cards from communal pile to the winner's deck and makes them the active player.
	 * Also checks if they have won the game.
	 * @param winner The winner of the latest round. can be changed to an index -Mat
	 * @return Whether the winner has also won the game by checking their deck.
	 */
	private boolean processWonRound(Player winner)
	{
		//Add communal pile cards to the winner's deck and empty the communal pile.
		winner.addWonCards(this.mainDeck);
		this.mainDeck.emptyDeck(); 
		// Pass control of the next round to the winner 
		this.switchTurn(winner);
		
		return winner.hasWon();
	}
	 
	/**
	 * Transfer any cards in the communal pile to the overall winner and signal end of game.
	 */
	private void finishGame()
	{
		Player gameWinner = playerList[currentPlayerTurn];
		//Transfer remaining communal pile cards to winner's deck.
		gameWinner.addWonCards(this.mainDeck);
		this.mainDeck.emptyDeck();
		this.isGameComplete = true;
	}
	
	/**
	 * compare integer if the first number is higher than second then it returns a 1
	 * zero if it equals each other and negative if first number is smaller than second
	 * @param rnd 
	 * @return boolean 
	 */
	
	private int findRoundResult(Round rnd) {
			int cat = rnd.getCategory();
			Card.indexToCompare = cat;
			// we need to get all current players in game to being comparing
			Player[] playersInGame = rnd.getPlayersInRound();
			Arrays.sort(playersInGame, new Round());
			// When 1 Player remaining then they automatically win the round
			if(pRemainingCount <= 1) return 1; 

			int firstPlace = playersInGame[0].getCurrentCard().getRelevantCat(cat);
			int secondPlace = playersInGame[1].getCurrentCard().getRelevantCat(cat);
			// If we subtract 1st place score from 2nd place 
			// then we can determine the round result
			return firstPlace - secondPlace;
	}
	
	/**
	 * Checks if any active players in the game have lost all their cards
	 * then change their active status to false and change the remaining player count 
	 * @return 
	 */
	public Player[] processEliminations() {
		Player[] elims = new Player[this.pRemainingCount];
		int count = 0;
		for(Player p : playerList) {
			if(p.getActiveStatus()) { 
				if(p.hasLost()) { 
					elims[count] = p;
					this.pRemainingCount--;
					count++;
				}
			}
		}
		// If there is only 1 player left playing then end the game
		if(this.pRemainingCount <= 1) this.finishGame();
		return elims;
	}
	
	/**
	 * @return Whether it is currently the user's turn.
	 */
	public boolean isHumanActive() {
		return this.playerList[currentPlayerTurn].isHuman();
	}
	
	public int getRound() {
		return this.roundCount;
	}
	
	public void setRound(int n) {
		this.roundCount = n;
	}

	public Player getOperator() {
		return this.operator;
	}

	public int getCurrentPlayerTurn() {
		return this.currentPlayerTurn;
	}
	
	public int getNoOfPlayers() {
		return this.noOfPlayers; 
	}
	
	public Player[] getPlayerList() {
		return this.playerList;
	}
	
	public Card getPlayerCard() {
		return this.playerList[this.currentPlayerTurn].getCurrentCard();
	}
	
	public Player getActivePlayer() {
		return this.playerList[this.currentPlayerTurn];
	}
	
	public int selectRandomPlayer() {
		int ran = (int) Math.floor(Math.random() * this.playerList.length);
		return ran;
	}
	
	public void switchTurn(Player winner) {
		// Find Winner in PlayerList by finding the position 
		// of the matching object 
		for(int i = 0; i < playerList.length; i++) {
			if(playerList[i] == winner) {
				this.currentPlayerTurn = i;
			}
		} 
	}
	
	public Player getPlayer(int query) {
		  Player matched = null;
	        for(Player p : this.playerList ) {
	            if(p == null) continue;
	            if(p.getIdentity() == query) {
	                matched = p;
	                break;
	            }
	        }
	        return matched;
	}
	
	//Next 2 methods are mostly for interaction with CLIApplication. 
	//Used in case CL or Online app needs to get user's card or other details. May not be used.
	
	/**
	 * @return The user's player object.
	 */
	public Player getHumanPlayer() {
		return this.operator;
	}
	
	/**
	 * @return The user's currently drawn card.
	 */
	public Card getHumansCurrentCard(){
		return this.operator.getCurrentCard();
	}
	
	public int getGameId() {
		return this.gameId;
	}
	
	public Round startNewRound() {
		// Check if previous round was completed
		if(this.getRound() > 1 ) { 
			Round prevRnd = this.roundList.get(this.getRound() - 1);
			if(prevRnd.getResultStatus() == -1) {
				return prevRnd;
			}
		}
		return new Round(this.getActivePlayer());
	}
	
	public Deck getCommunalPile() {
		return this.mainDeck;
	}
	
	public boolean getIsGameComplete() {
		return this.isGameComplete;
	}
		
	/**
	 * The core game loop 
	 * Call this method with an instance of Game object - it will run 
	 */
		
	public void play() {
		while(!this.isGameComplete) {
			// Player p = this.getActivePlayer();
			Round rnd = this.startNewRound();
			this.saveRound(rnd);
			Session.view.initalRoundInfo(rnd);
			int categoryChoice = this.getActivePlayer().chooseCategory();
			this.processTurn(rnd, categoryChoice);
			Session.view.displayPlayerChange(playerList[this.currentPlayerTurn]);
			Session.view.displayElim(this.processEliminations());
			Session.view.displayEndRound(rnd); 
		}
		Player gameWinner = playerList[currentPlayerTurn];
		System.out.println(gameWinner.getName()+" won with "+gameWinner.getDeck().getDeckSize());
		Session.view.gameOver(gameWinner);
	}
	
	public void processTurn(Round rnd, int categoryChoice) {
		rnd.setCategory(categoryChoice);
		rnd.setResultStatus(this.findRoundResult(rnd));
		
		if(rnd.getResultStatus() == 1) {
			Player winner = rnd.playersInRound[0];
			rnd.setWinner(winner);
			rnd.setWinningCard(winner.getCurrentCard());
			this.processWonRound(winner);
			this.isGameComplete = winner.hasWon();
			// we need a game over method for adding the main deck in the final
		}
	}
	
	
	public class Round implements Comparator<Player> {
		
		private int roundNo, category, drawsOfTheCards, resultStatus; 
		private Player startingPlayer, winner;
		private Card startingCard, winningCard;
		private Player[] playersInRound;
		private Card[] cardsDrawn;
		
		Round(Player startingPlayer) {
			this.roundNo = roundCount += 1;
			this.startingPlayer = startingPlayer;
			this.startingCard = this.startingPlayer.getCurrentCard();
			this.drawsOfTheCards = 0;
			this.winner = null;
			// resultStatus stores a number that represents 
			// win (1), tie(0), or incomplete (-1)
			this.resultStatus = -1;
			this.cardsDrawn = new Card[pRemainingCount];
			this.winningCard = null;
			this.playersInRound = new Player[pRemainingCount];
			this.run();
		}
		
		public int getResultStatus() {
			return this.resultStatus;
		}

		public void setResultStatus(int newRoundResult) {
			if(newRoundResult > 0) {
				this.resultStatus = 1;
			} else if(newRoundResult == 0) {
				this.resultStatus = 0;
			} else {
				this.resultStatus = -1;
			}
		}

		public Round() {
			// Empty Constructor needed for the comparator 
		}
		
		public Player[] getPlayersInRound() {
			return this.playersInRound;
		}

		public void setCardsDrawn(Card[] allCardsDrawn) {
			this.cardsDrawn = allCardsDrawn;
		}

		public void setWinningCard(Card card) {
			this.winningCard = card;
		}
		
		public Card getWinningCard() {
			return this.winningCard;
		}

		public void setWinner(Player winner) {
			this.winner = winner;
		}
		
		public Player getWinner() {
			return this.winner;
		}
		
		public int compare(Player you, Player other) {
			int forThis = you.getCurrentCard().getRelevantCat(Card.indexToCompare);
			int forThat = other.getCurrentCard().getRelevantCat(Card.indexToCompare);
			
			if (forThis == forThat) 
				return 0;
			else if (forThis > forThat)
				return -1;
			else 
				return 1;
		}

		private void run() {
			
			for(Player p : playerList) {
				if(p.getActiveStatus() == false) continue;
				p.drawCard();
				this.playersInRound[this.drawsOfTheCards] = p;
				this.cardsDrawn[this.drawsOfTheCards] = p.getCurrentCard();
				this.drawsOfTheCards++;
			}
			gameDrawCount += this.drawsOfTheCards;
			mainDeck.addCardsToBottom(this.cardsDrawn, this.drawsOfTheCards);
			this.startingPlayer = playerList[currentPlayerTurn];
			this.startingCard = this.startingPlayer.getCurrentCard();
		}
		
		public int getCategory() {
			return this.category;
		}
		
		public void setCategory(int val) {
			this.category = val;
		}
		
		public Card getStartingCard() {
			return this.startingCard;
		}
		
		public Player getStartingPlayer() {
			return this.startingPlayer;
		}
		
		public int getRoundNumber() {
			return this.roundNo;
		}
		
		
	}

}