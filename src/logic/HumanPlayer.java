package logic;

import java.io.*;
import java.util.Scanner;


public final class HumanPlayer extends Player {
	
	
	public HumanPlayer(Deck d)
	{
		super(d);
	}

	/**
	 * @return Whether the player is human or not.
	 */
	public boolean isHuman() 
	{
		// TODO Auto-generated method stub
		return true;
	}	
	
	/**
	 * Returns the player's identity, always "You" for a human player.
	 * @return The player's name.
	 */
	public String getName()
	{
		return "You";
	}

	/**
	 * Asks the user to choose the category to compare cards against
	 * @return The name of the chosen category.
	 */
	public String chooseCategory() 
	{
		System.out.println("1. Size");
		System.out.println("2. Speed");
		System.out.println("3. Range");
		System.out.println("4. Firepower");
		System.out.println("5. Cargo");
		
		int category = 0;
		Scanner in = new Scanner(System.in);
		
		while(category < 1 || category > 5)
		{
			System.out.print("Choose a category: ");
			String line = in.nextLine();
			try
			{
				category = Integer.parseInt(line);
			}
			catch(NumberFormatException e)
			{
				System.out.println(line + " is not a valid input. Choose a number between 1 and 5.");
			}
			
		}
		in.close();
		
		return Card.catNames[category-1];

		/*switch(category)
		{
			case 1: return "Size";
			case 2: return "Speed";
			case 3: return "Range";
			case 4: return "Firepower";
			default: return "Cargo";
		}*/
	}
	
	public static void main (String[] args)
	{
		HumanPlayer tom = new HumanPlayer(new Deck());
		String test = tom.chooseCategory();
		System.out.println(test);
	}

}
