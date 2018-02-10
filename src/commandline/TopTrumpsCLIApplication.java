package commandline;

import java.util.Scanner;

import org.apache.commons.lang3.ArrayUtils;

import io.dropwizard.cli.Cli;
import logic.*;
import logic.Game.Round;
import database.Connection1;

/**
 * Top Trumps command line application
 */
public class TopTrumpsCLIApplication extends View {

	//private Game cliGame;
	private static Scanner scan = new Scanner(System.in);
	// This is the number of seconds loading takes (during testing set to 0)
	private int loadingTime = 0; 
	/**
	 * This main method is called by TopTrumps.java when the user specifies that they want to run in
	 * command line mode. The contents of args[0] is whether we should write game logs to a file.
 	 * @param args
	 */
	public static void main(String[] args) {
		boolean writeGameLogsToFile = false; // Should we write game logs to file?
		if (args[0].equalsIgnoreCase("true")) writeGameLogsToFile=true; // Command line selection
		
		// State
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application
		
		// Loop until the user wants to exit the game
		while (!userWantsToQuit) {
			boolean start = displayOptionMenu();
			if(start) Session.createNewGame(4, writeGameLogsToFile);
			int newGameId = Session.getLatestGame();
			Game game = Session.findGameById(newGameId);
			game.play();
		}

		scan.close();
	}
	
	private static boolean displayOptionMenu() {
		boolean startGame = false;
		while(!startGame) {
			int[] menuOptions = {1,2};
			int choice = giveOptions(menuOptions, "Would you like to View Statistics (1) of past games or Play a New Game (2)?");
			if(choice == 2) {
			startGame = true;
			} else { 
				if(choice == 1)  {
					print("Okay! Here are your statistics");
					pullStats();
				}
			}
		}
		return startGame;
	}
	
	
	private static void pullStats() {
		Connection1 db = new Connection1(); 
		db.connection();
		print(""+ "total number of games " + db.numberofgames());
		print(""+ "number of times computer won "+db.ai());
		print(""+"number of times human wins "+db.humanwin());
		print(""+"the average number of draws "+db.drawsum());
		print(""+"the largest number of rounds played in a single game "+db.maxroundspergame());
		db.closeconnection();
	}

	public static int giveOptions(int[] answers, String msg) {
		int countTries = 0;
		int response;
		do {
			if( (countTries & 1) == 0) print(msg);
			else print("Please Try again.");
			countTries++;
			response = scan.nextInt();
		} while(!ArrayUtils.contains(answers, response));
		return response;
	}
		
	/**
	 * Outputs a card's details to the console.
 	 * @param c The card to be printed, normally a player's current card.
 	 * NOTE: Method can be adjusted to take a Player or String as parameter later.
 	 * NOTE 2: Can be used to print out all players' cards at the end of a round too.
	 */
	public void printCard(Card c) 
	{
		System.out.println(c.printCard());
	}
	
	public static void print(String str) {
		System.out.println(str);
	}

//	public static void print(int num) {
//		System.out.println(num);
//	}
//	
	public void loading()   {
		try {
			System.out.print("\n Loading.");
			for(int i = 0; i < this.loadingTime; i++) {
				Thread.sleep(1000);
				System.out.print(".");
			}
			System.out.print("\n");
		} catch (Exception InterruptedException) {
			System.err.println("Error on loading screen");
		}
	}
	
	/**
	 * Asks the user to choose the category to compare cards against for this round.
	 * Only called when it is the human player's turn to choose.
 	 * @return The name of the chosen category. 
 	 * NOTE: can be changed to return the index of a category as an int.
	 */
	@Override
	public int getCategory() {
		loading();
		print("1. Size");
		print("2. Speed");
		print("3. Range");
		print("4. Firepower");
		print("5. Cargo");
		int[] answers = {1,2,3,4,5};
		int cat = giveOptions(answers, "Please select the category you'd like to play..");
		cat = cat - 1;
		return cat;
	}

	@Override
	public void displayEndRound(Round rnd) {
			loading();
			print("Selected Category " +  Card.catNames[rnd.getCategory()]);
			if(rnd.getResultStatus() == 1 ) {
				print(rnd.getWinner().getName() + " wins round " + rnd.getRoundNumber());
				print("The Winning Card : \n" + rnd.getWinningCard().printCard());
			} else if(rnd.getResultStatus() == 0) {
				print("This category resulted in a tie.");
			}
	}

	@Override
	public void initalRoundInfo(Round rnd) {
		loading();
		print("Round : "+rnd.getRoundNumber());
		print("Turn :" + rnd.getStartingPlayer().getName());
		print(rnd.getStartingCard().printCard());
	}

	@Override
	public void displayDraw() {
		print("This category resulted in a tie.");
	}

	@Override
	public void displayElim(Player[] eliminations) {
		for(Player player : eliminations) {
			if(player == null) continue;
			print(player.getName() + " has been eliminated. Better luck next time punk");
		}
	}

	@Override
	public void gameOver(Player player) {
		print(player.getName() + " has won the game!");
	}

	@Override
	public void displayPlayerChange(Player player) {
		loading();
		print("Switched turn to winner =  " + player.getName());
	}


}
