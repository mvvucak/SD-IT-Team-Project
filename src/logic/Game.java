public class Game() {
	private Player[] playerArr;
	private int noOfPlayers = 0; 

	Game(int noOfPlayers) {
		this.noOfPlayers = noOfPlayers;
		this.playerArr = new Player[noOfPlayers];
		this.addPlayersSessions();
		this.init();
	}

	public void init() {
		// add players to game 
		this.addAllPlayers()

			while (!gameover) {
				this.getRound(); // print round number to cmdl
				this.getRandomPlayer();

			// ----------------------------------------------------
			// Add your game logic here based on the requirements
			// ----------------------------------------------------
			

			gameover=true; // use this when the user wants to exit the game
			
		}
	}

	private Player[] addPlayersSession() {
		// adds list of Player objects to our Player array
		for() {
			this.playerArr[i] = new Player();
		}
	}

	private Player selectRandomPlayer() {
		// gets a random number between 0 and max no of players
		// int randomNo = ( (int) (Math.random() ); 
		Player selected = Player[randomNo];
		return selected; 
	}

}