package logic;

import java.util.HashMap;

public class Session {
	private static HashMap<Integer, Game> gameList = new HashMap<Integer, Game>();  
	public static View view;
	private static Session instance = null;
	private static int counter = 1;
	
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
		Game newGame = new Game(aiSelect);
		gameList.put(counter, newGame);
		counter++;
		return newGame;
	}
	
	public static void setView(View viewType) {
		// set the type of view you want to display to
		view = viewType;
	}

	public static Game findGameById(int gameId) {
			return gameList.get(gameId);
	}
}
