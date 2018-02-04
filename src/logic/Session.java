package logic;

import java.util.HashMap;

public class Session {
	private static HashMap<Integer, Game> gameList = new HashMap<Integer, Game>();  
	public static View view;
	private static Session instance = null;
	private static int counter = 0;
	
	private Session() {
		// Exists only to defeat instantiation
	}
	
	public static Session getInstance() {
		if(instance == null) {
			instance = new Session();
		 }
		return instance;
	}
	
	public static Game createNewGame(int aiSelect) {
		counter++;
		Game newGame = new Game(aiSelect, counter);		
		gameList.put(counter, newGame);
		return newGame;
	}
	
	public static void setView(View viewType) {
		// set the type of view you want to display to
		view = viewType;
	}
	
	public static int getLatestGame() {
		return counter;
	}

	public static Game findGameById(int gameId) {
		System.err.println("doing a wee search " + gameId);
		return gameList.get(gameId);	
	}
}
