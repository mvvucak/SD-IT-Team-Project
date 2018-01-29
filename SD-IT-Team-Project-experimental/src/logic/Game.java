package logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import java.io.*;

public class Game {
	
	private int noOfPlayers = 1;
	private Player[] playerList;
	private Player operator; // ref to the person playing
	private int currentPlayerTurn;
	private int round, draws;
	private Deck mainDeck;
	private ArrayList<Round> roundList;

	public Game(int noOfAi) {
		this.noOfPlayers = this.noOfPlayers + noOfAi;
		this.playerList = new Player[this.noOfPlayers];
		this.round = 0;
		this.draws = 0;
		this.roundList = new ArrayList<Round>();
		boolean pleaseShuffle = true;
		Card[] pack = this.loadDeck();
		this.mainDeck = new Deck(pack, pleaseShuffle);
		this.init();
	}

	public void init() {
		// build a playerList 
		this.addPlayersToGame();
		this.loadDeck();
		this.dealCards();
		this.currentPlayerTurn = this.selectRandomPlayer();
	}
	
	private void saveRound(Round rnd) {
		this.roundList.add(rnd);
	}
	
	public Round getCurrentRound() {
		return this.roundList.get(round);
	}
	
	private void dealCards() {
		Deck[] splitCards = this.mainDeck.split(this.noOfPlayers);
		for(int i = 0; i < this.noOfPlayers; i++ ) {
			playerList[i].setDeck(splitCards[i]); 
			//System.out.println("Player: " + playerList[i].getName() + " have cards " + playerList[i].getDeck().toString() );
		}
	}
	
	private Card[] loadDeck() {
	 	Card[] topTrumpsPack = new Card[Deck.MAXIMUM_DECK_SIZE];
		String filename = "StarCitizenDeck.txt";
		try {
			FileReader reader = new FileReader(filename); 
			Scanner scan = new Scanner(reader);
			scan.next(); // skip description 
			
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
				    
				   /* values[0] = scan.nextInt();
				    values[1] = scan.nextInt();
				    values[2] = scan.nextInt();
				    values[3] = scan.nextInt();
				    values[4] = scan.nextInt();*/
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
			this.playerList[i] = new ComputerPlayer();
		}

	}
	
	
	/**
	 * Adds cards from communal pile to the winner's deck and makes them the active player.
	 * Also checks if they have won the game.
	 * NOTE: Could be made private, likely only called within here. -Mat
	 * @param winner The winner of the latest round. can be changed to an index -Mat
	 * @return Whether the winner has also won the game.
	 */
	private boolean processWonRound(Player winner)
	{
		winner.addWonCards(this.mainDeck); //Assuming mainDeck will hold each round's cards -Mat.
		//Remove card references from the common pile.
		this.mainDeck.emptyDeck(); 
		//Assuming identity and index in player array are identical. -Mat.
		this.currentPlayerTurn = winner.getIdentity(); 
		
		return winner.hasWon();
	}
	
	/**
	 * compare integer if the first number is higher than second then it returns a 1
	 * zero if it equals each other and negative if first number is smaller than second
	 * @param rnd 
	 * @return 1 for a win, 0 for a draw and -1 for a loss
	 */
	
	private int findRoundResult(Round rnd) {
		  final int LOSS = -1;
		  final int DRAW = 0;
		  final int WIN = 1;
		  int categoryChoice = rnd.getCategory();
		int myCard = rnd.getStartingCard().getRelevantCat(categoryChoice);
		for(int i = 0; i < this.playerList.length; i++) {
			if(i == this.currentPlayerTurn) continue;
			Player opponent = playerList[i];
			int opponentsCard = opponent.getCurrentCard().getRelevantCat(categoryChoice);
			int comparedResult = Integer.compare(myCard, opponentsCard);
			if(comparedResult == 0) {
				rnd.setFinalResult(DRAW);
				rnd.setWinner(opponent);
				rnd.setWinningCard(opponent.getCurrentCard());
				return DRAW;
			} else if(comparedResult < 0) {
				rnd.setFinalResult(LOSS);
				rnd.setWinner(opponent);
				rnd.setWinningCard(opponent.getCurrentCard());
				return LOSS;
			}
			
		}
		rnd.setFinalResult(WIN);
		rnd.setWinner(this.getActivePlayer());
		rnd.setWinningCard(this.getActivePlayer().getCurrentCard());
		return WIN;
	}
	
	/**
	 * @return Whether it is currently the user's turn.
	 */
	public boolean isHumanActive() {
		return this.playerList[currentPlayerTurn].isHuman();
	}
	
	public int getRound() {
		return this.round;
	}
	
	public void setRound(int n) {
		this.round = n;
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
	
	public void switchTurn() {

		for(int i = 0; i < playerList.length; i++) {
			this.currentPlayerTurn++;
			int playerIndex = this.currentPlayerTurn % playerList.length;

			if(this.playerList[playerIndex].getActiveStatus()) {
				this.currentPlayerTurn = playerIndex; 
				break;
			} 
			continue;

		} 
		System.out.println("New turn " + this.currentPlayerTurn);
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
	
	public static void main(String[] args) {
		Game testCase = new Game(4); 
		System.out.println("the current player" + testCase.currentPlayerTurn);
	}
		
	public void play() {
		Player p = this.getActivePlayer();
		Round rnd = new Round(p);
		Session.view.initalRoundInfo(rnd);
		int categoryChoice = p.chooseCategory();
		rnd.setCategory(categoryChoice);
		this.findRoundResult(rnd);
		this.saveRound(rnd);
		Session.view.displayEndRound(rnd);
	}

	
	public class Round {
		
		private int roundNo, category, drawsOfTheCards, finalresult;
		private Player startingPlayer, winner;
		private Card startingCard, winningCard;
		private Player[] elimated;
		
		
		Round(Player startingPlayer) {
			this.roundNo = round + 1;
			this.startingPlayer = startingPlayer;
			this.startingCard = this.startingPlayer.getCurrentCard();
			this.winner = null;
			this.winningCard = null;
			this.elimated = new Player[playerList.length];
			this.run();
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

		public void setFinalResult(int state) {
			this.finalresult = state;
		}
		
		public int getFinalResult() {
			return this.finalresult;
		}

		private void run() {
			// selects random player to start game
			if(round == 1) {
				currentPlayerTurn = selectRandomPlayer();
			}
			for(Player p : playerList) {
				p.drawCard();
			}
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