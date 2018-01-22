package logic;

public class Game {
	
	private Player[] playerList;
	private int noOfPlayers = 1;
	private Player operator;
	private Player activeTurn;
	private int round;

	public Game(int noOfAi) {
		this.noOfPlayers = this.noOfPlayers += noOfAi;
		this.playerList = new Player[this.noOfPlayers];
		this.round = 0;
		this.init();
	}

	public void init() {
		// add players to game 
		this.addPlayersToGame();
		this.activeTurn = this.selectRandomPlayer();
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

	public Player getActiveTurn() {
		return this.activeTurn;
	}
	
	public int getNoOfPlayers() {
		return this.noOfPlayers; 
	}
	
	public Player[] getPlayerList() {
		return this.playerList;
	}
	
	public Player selectRandomPlayer() {
		int ran = (int) Math.floor(Math.random() * this.playerList.length);
		return this.playerList[ran];
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

}