package logic;

public class Card implements Comparable<Card>{
	private String description;
	private int[] cat = new int[5]; 
	public static String[] catNames = {"Size", "Speed", "Range", "Firepower", "Cargo"};
	public static int indexToCompare;

	//default constructor
	public Card(String desc) {
		this.description = desc;
	}

	public Card(String desc, int[] someValues) {
		this.description = desc;
		this.cat = someValues;
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
	
		/**		
		 * Finds the category with the highest value for this card.		
		 * @return The name of the category with the highest value.		
		 */		
		public String getHighest()		
		{		
		int max = 0;		
			int maxIndex = 0;		
			for (int i=0; i < 5; i++)		
			{		
				if(this.cat[i] > max)		
				{		
					max = this.cat[i];		
					maxIndex = i;		
			}			
		}		
			return Card.catNames[maxIndex];		
		}		
			
	

	// a method to return the right value by the category
	public int getRelevantCat (String category) {
		int b = -1;
		if (category.equals("size")) 
			return cat[0];
		else if (category.equals("speed")) 
			return cat[1];
		else if (category.equals("range")) 
			return cat[2];
		else if (category.equals("firepower")) 
			return cat[3];
		else if (category.equals("cargo")) 
			return cat[4];
		else
			return b;
		
	}
	
	//a method to return the right value by the index
	public int getRelevantCat(int index) {
		return cat[index];
		
	}
	
	// a method to print out the card information
	public String printCard() {
		String card = "- - - - - - -"+"\r\n";
		card += "name: " + getDescription() +"\r\n\n";
	
		card += "size: " + getRelevantCat("size") +"\r\n";
		card += "speed: " + getRelevantCat("speed") +"\r\n";
		card += "range: " + getRelevantCat("range") +"\r\n";
		card += "firepower: " + getRelevantCat("firepower") +"\r\n";
		card += "cargo: " + getRelevantCat("cargo") +"\r\n";
		card += "- - - - - - -"+"\r\n";
		
		return card;
	}



	public void setCat(int[] cat) {
		this.cat = cat;
	}


	public int compareTo(Card other) {
		int forThis =this.getRelevantCat(indexToCompare);
		int forThat =other.getRelevantCat(indexToCompare);
		
		if (forThis == forThat) 
			return 0;
		else if (forThis > forThat)
			return 1;
		else 
			return -1;

	}

	//
	public static void main(String[] args)
	{
		indexToCompare = 1;
		Card b = new Card("tchooo");
		int[] arr1 = {0, 10, 16, 32, 64}; 
		b.setCat(arr1);
		
		Card c = new Card("roorooroo");
		int[] arr = {4, 8, 16, 32, 64}; 
		c.setCat(arr);
		
		System.out.println(c.printCard());
		System.out.println(c.compareTo(b));
	}
	
}
