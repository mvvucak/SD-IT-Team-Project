package commandline;

import java.util.Scanner;

import org.apache.commons.lang3.ArrayUtils;

import logic.*;

/**
 * Top Trumps command line application
 */
public class TopTrumpsCLIApplication {

	private static Game cliGame;
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
			printToConsole("Round "+cliGame.getRound()+" has begun1");
			// ----------------------------------------------------
			// Add your game logic here based on the requirements
			// ----------------------------------------------------
			userWantsToQuit=true; // use this when the user wants to exit the game
			
		}


	}
	
	private static void displayOptionMenu() {
		Scanner scan = new Scanner(System.in); 
		int choice;
		int[] answers = {1,2}; 
		do {
			printToConsole("Would you like to View Statistics (1) of past games or Play a New Game (2)?");
			choice = scan.nextInt();
		} while(!validChoice(choice, answers));
		if(choice == 2) cliGame = Session.createNewGame(4);
		else if(choice == 1) printToConsole("Okay! Here are your statistics");
		scan.close();
	}
	
	private static boolean validChoice(int choice, int[] answers) {
	    return ArrayUtils.contains(answers, choice);
	}
	
	private static void printToConsole(String str) {
		System.out.println(str);
	}

}
