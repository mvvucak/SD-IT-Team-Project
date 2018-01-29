package commandline;

import java.util.Scanner;

import org.apache.commons.lang3.ArrayUtils;

import io.dropwizard.cli.Cli;
import logic.*;
import logic.Game.Round;

/**
 * Top Trumps command line application
 */
public class TopTrumpsCLIApplication extends View {

	private static Game cliGame;
	private static Scanner scan = new Scanner(System.in); 
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
		
		displayOptionMenu();
		// Loop until the user wants to exit the game
		while (!userWantsToQuit) {
			cliGame.play();
			userWantsToQuit=true; // use this when the user wants to exit the game
		}

		scan.close();
	}
	
	private static void displayOptionMenu() {
		boolean hideMenu = false;
		while(!hideMenu) {
			int[] menuOptions = {1,2};
			int choice = giveOptions(menuOptions, "Would you like to View Statistics (1) of past games or Play a New Game (2)?");
			if(choice == 2) {
				cliGame = Session.createNewGame(4);
				hideMenu = true;
			} else { if(choice == 1) 
				printToConsole("Okay! Here are your statistics");
			}
		}
	}
	
	
	private static int giveOptions(int[] answers, String msg) {
		int countTries = 0;
		int response;
		do {
			if( (countTries & 1) == 0) printToConsole(msg);
			else printToConsole("Please Try again.");
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
	private void printCard(Card c) 
	{
		System.out.println(c.printCard());
	}
	
	
	private static void printToConsole(String str) {
		System.out.println(str);
	}
	
	/**
	 * Asks the user to choose the category to compare cards against for this round.
	 * Only called when it is the human player's turn to choose.
 	 * @return The name of the chosen category. 
 	 * NOTE: can be changed to return the index of a category as an int.
	 */
	@Override
	public int getCategory() {
		printToConsole("1. Size");
		printToConsole("2. Speed");
		printToConsole("3. Range");
		printToConsole("4. Firepower");
		printToConsole("5. Cargo");
		int[] answers = {1,2,3,4,5};
		int cat = giveOptions(answers, "Please select the category you'd like to play..");
		cat = cat - 1;
		return cat;
	}

	@Override
	public void displayEndRound(Round rnd) {
		printToConsole("Selected Category " +  Card.catNames[rnd.getCategory()]);
		if(rnd.getFinalResult() == 0 ) {
			printToConsole("Result : It was a draw!");
		} else {
			printToConsole("Winning Player " + rnd.getWinner().getName());
			printToConsole("Winning Card : \n" + rnd.getWinningCard().printCard());
		}
	}

	@Override
	public void initalRoundInfo(Round rnd) {
		printToConsole("Round : "+rnd.getRoundNumber());
		printToConsole("Turn :" + rnd.getStartingPlayer().getName());
		printToConsole(rnd.getStartingCard().printCard());
	}

}
