package logic;

public class Card implements Comparable<Card>{
	private String description;
	private int[] cat = new int[5]; 

	//default constructor
	public Card(String desc) {
		this.description = desc;
	}


	// constructor that extracts values from a String
	// public Card(String card) {
	// 	String[] details = card.split(" ");

	// 	description = details[0];
	// 	size = Integer.parseInt(details[1]);
	// 	speed = Integer.parseInt(details[2]);
	// 	range = Integer.parseInt(details[3]);
	// 	firepower = Integer.parseInt(details[4]);
	// 	cargo = Integer.parseInt(details[5]);

	// }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}





	public int compareTo(Card other) {
		return 0;
	}

}
