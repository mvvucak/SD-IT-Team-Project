package logic;

public class Game {
	
	private int noOfPlayers = 1;
	private Player[] playerList;
	private Player operator; // ref to the person playing
	private int currentPlayerTurn;
	private int round;

	public Game(int noOfAi) {
		this.noOfPlayers = this.noOfPlayers + noOfAi;
		this.playerList = new Player[this.noOfPlayers];
		this.round = 1;
		this.init();
	}

	public void init() {
		// build a playerList 
		this.addPlayersToGame();
		// selects random player to start game
		this.currentPlayerTurn = this.selectRandomPlayer();
	}

	private void addPlayersToGame() {
		// adds list of Player objects to our Player array
		int len = this.playerList.length;
		for(int i = 0; i < len; i++) {
			this.playerList[i] = new Player();
		}
		// assign Player object to the game operator
		this.operator = this.playerList[0];
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
		testCase.switchTurn();
		testCase.switchTurn();
		testCase.switchTurn();
		testCase.switchTurn();
		testCase.switchTurn();
		System.out.println("Now I have turned human to off");
		testCase.operator.setActiveStatus(false);
		testCase.switchTurn();
		testCase.switchTurn();
		testCase.switchTurn();
		testCase.switchTurn();
		testCase.switchTurn();
		testCase.switchTurn();
	}

}