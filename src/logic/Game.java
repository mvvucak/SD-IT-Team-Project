package logic;

import java.util.Arrays;
import java.util.Scanner;

import java.io.*;

public class Game {
	
	private int noOfPlayers = 1;
	private Player[] playerList;
	private Player operator; // ref to the person playing
	private int currentPlayerTurn;
	private int round;
	private Deck mainDeck;

	public Game(int noOfAi) {
		this.noOfPlayers = this.noOfPlayers + noOfAi;
		this.playerList = new Player[this.noOfPlayers];
		this.round = 1;
		boolean pleaseShuffle = true;
		Card[] pack = this.loadDeck();
		this.mainDeck = new Deck(pack, pleaseShuffle);
		this.init();
	}

	public void init() {
		// build a playerList 
		this.addPlayersToGame();
		// selects random player to start game
		this.currentPlayerTurn = this.selectRandomPlayer();
		this.loadDeck();
		this.allocateCardsToPlayer();
	}
	
	private void allocateCardsToPlayer() {
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
			scan.next(); // skip description 
			
			for(int i = 0; i < Card.catNames.length; i++) {
				Card.catNames[i] = scan.next();
			}
			int index = 0;
			 while (scan.hasNext()) {
					int[] values = new int[Card.catNames.length];
				    String desc = scan.next();
				    values[0] = scan.nextInt();
				    values[1] = scan.nextInt();
				    values[2] = scan.nextInt();
				    values[3] = scan.nextInt();
				    values[4] = scan.nextInt();
				    topTrumpsPack[index] = new Card(desc, values);
				    index++;
			  }
			 scan.close();
		} catch(IOException e) {
			
		}
	    System.out.println(topTrumpsPack[7].getDescription());

		return topTrumpsPack; 
	}


	private void addPlayersToGame() {
		// assign Player object to the game operator
		this.operator = new HumanPlayer(); 
		this.playerList[0] = this.operator;
		// adds list of Player objects to our Player arra
		int len = this.playerList.length;
		for(int i = 1; i < len; i++) {
			this.playerList[i] = new ComputerPlayer();
		}

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
	
	public static void main(String[] args) {
		Game testCase = new Game(4); 
		System.out.println("the current player" + testCase.currentPlayerTurn);
//		testCase.switchTurn();
//		testCase.switchTurn();
//		testCase.switchTurn();
//		testCase.switchTurn();
//		testCase.switchTurn();
//		System.out.println("Now I have turned human to off");
//		testCase.operator.setActiveStatus(false);
//		testCase.switchTurn();
//		testCase.switchTurn();
//		testCase.switchTurn();
//		testCase.switchTurn();
//		testCase.switchTurn();
//		testCase.switchTurn();
	}

}