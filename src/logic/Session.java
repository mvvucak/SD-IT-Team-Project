package logic;

import java.util.ArrayList;

public class Session {
	private static ArrayList<Game> gameList = new ArrayList<Game>();  
	
	public static Game createNewGame(int aiSelect) {
		Game newGame = new Game(aiSelect);
		gameList.add(newGame);
		return newGame;
	}

	public static Game findGameById(int gameId) {
		// loop through game list
		return null;
	}
}
