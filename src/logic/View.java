package logic;

import logic.Game.Round;

/** 
 * Base view that the Web Service and Cli Service inherit from  
 */

public abstract class View {

	abstract public int getCategory();
	
	abstract public void initalRoundInfo(Round rnd);

	abstract public void displayEndRound(Round rnd);

	abstract public void displayDraw();
	
	abstract public void displayPlayerChange(Player player);

	abstract public void displayElim(Player player); 
	
	abstract public void gameOver(Player player);
	
}
