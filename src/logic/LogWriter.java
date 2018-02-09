package logic;


import java.io.IOException;
import java.io.PrintWriter;
import logic.Card;



public class LogWriter {
	
	private String report;
	private int gameId;
	private Player[] players;
	private final String fileName = "toptrumps.log";
	
	public LogWriter(Player[] players) {
		this.players = players;
		this.report = "";
	}
	
	//1.main loaded deck
	//2.deck after shuffling
	//4.the contents of the communal pile
	public void sendDeck(Card[] deck) {
		
		for(int i = 0; i < deck.length; i++) {			
			report += deck[i].getDescription() + "\r\n";
		}
		report += spaceDivider();
		
	}
	
	public void sendDeck(Deck d)
	{
		report += d.getDeckList();
		report += spaceDivider();
	}
	
	//3.the contents of the user and computer decks
	public void writePlayerDecks() {
		for(int i = 0; i < players.length; i++) {
			if (players[i].getActiveStatus() == true) {
				if (i == 0 ) {
					report += "You got the following deck: \r\n" + players[i].getDeck().getDeckList();
				}
				else {
				report += players[i].getName() + " got the following deck: \r\n" + players[i].getDeck().getDeckList();}
			}
		report += spaceDivider();
	}
	}
	
	//5.current cards in play 
	public void writeTopCards() {
		for(Player p: players) {
			if (p.getActiveStatus() == true) {
				report += p.getName() + " drew: " + p.getCurrentCard().getDescription() + "\r\n";			
			}
		}
		report += spaceDivider();
		
	}
	
	//6.get category selected and THEN CORRESPONDING VALUES
	public void writeCategoryInfo(int chosenCategory) {
		report += "The selected category: " + Card.catNames[chosenCategory] + "\r\n";
		for(Player p: players)
			if (p.getActiveStatus() == true) {
				report += p.getName() + " got the value " + p.getCurrentCard().getRelevantCat(chosenCategory) + "\r\n";
			}
		report += spaceDivider();
	}
	
	//7. the contents of each deck after a round
	public void writePlayerDecks(int round) {
		for(int i = 0; i < players.length; i++) {
			if (players[i].getActiveStatus() == true) {
				if (i == 0 ) {
					report += "Your deck after round " + round + ":" + "\r\n" + players[i].getDeck().getDeckList();
				}
				else {
				report += players[i].getName() + "'s deck after round " + round + ":" + "\r\n" + players[i].getDeck().getDeckList();}
			}
		report += spaceDivider();
	}
	}
	
	//8.printing the winner
	public void writeWinner(Player player) {
		report += "The winner is " + player.getName() + "\r\n" + spaceDivider();
	}
	
	public String spaceDivider() {

		String beautDashes = "------------------------------------------ \r\n";
		return beautDashes;
		
	} 
	

	
	public void saveLog() {
		try {

			PrintWriter save = new PrintWriter(fileName);

			save.write(report);

			save.close();

			} catch (IOException e) {

			

			}
		// write the full contents of report to a file 
	}
}