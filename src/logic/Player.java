public class Player {

	public static int counter = 0; // static variable
	private int identity;

	Player() {
		this.identity = Player.counter;
		Player.counter += 1;
	}

	public boolean activePlayer(Player otherPerson) {
		return this.identity == otherPerson.getIdentity();
	}

	public int getIdentity() {
		return this.identity;
	}

	/**
	* main method to test Player class 
	*/
	public static void main(String[] args) {
	 	System.out.println( "testing Player class.." );
		Player human = new Player();
		Player ai1 = new Player();
		Player ai2 = new Player();

		Player[] playerList = new Player[3];
		playerList[0] = ai1;
		playerList[1] = ai2;
		playerList[2] = human;

		for(int i = 0; i < playerList.length;i++) {
			Player otherPlayer = playerList[i];
			if( human.activePlayer(otherPlayer) ) {
				System.out.println( "Found myself at position " + i);
			}
		}

	}

}