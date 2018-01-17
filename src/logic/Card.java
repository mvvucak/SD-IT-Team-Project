package logic;

public class Card implements Comparable<Card>{
	private String description;
	private int size;
	private int speed;
	private int range;
	private int firepower;
	private int cargo;

	//default constructor
	public Card() {

	}

	// constructor that extracts values from a String
	public Card(String card) {
		String[] details = card.split(" ");

		description = details[0];
		size = Integer.parseInt(details[1]);
		speed = Integer.parseInt(details[2]);
		range = Integer.parseInt(details[3]);
		firepower = Integer.parseInt(details[4]);
		cargo = Integer.parseInt(details[5]);

	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public int getFirepower() {
		return firepower;
	}

	public void setFirepower(int firepower) {
		this.firepower = firepower;
	}

	public int getCargo() {
		return cargo;
	}

	public void setCargo(int cargo) {
		this.cargo = cargo;
	}



	public int compareTo(Card other) {
		return 0;
	}

}
