package logic;

import java.util.HashMap;

public class Session {
	private static HashMap<Integer, Game> gameList = new HashMap<Integer, Game>();  
	private static Session instance = null;
	private static int counter = 1;
	
	private Session() {
		
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

	public static Game findGameById(int gameId) {
			return gameList.get(gameId);
	}
}