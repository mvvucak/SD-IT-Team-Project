package database;

import java.sql.*;
import java.util.*;

import javax.swing.JOptionPane;

public class Connection1 {
	private int max;
	private int average;
	private int total;
	private int sum;
	private double d;
	int drawspergame;
	int gamenumber;
	boolean humanwinner;
	String dbname;
	String username;
	String password;
	private Connection connection = null;
	
	public Connection1() {
		
	}

	/**
	 * Method to connects to server checks for error and returns result to console
	 * 
	 */
	public void connection() {
		password = "2352006d";
		username = "m_17_2352006d";
		dbname = "m_17_2352006d";
		
		// 
		
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/" + dbname, username,
					password);
		}

		catch (SQLException e) {
			System.err.println("Connection Failed!");
			e.printStackTrace();
			return;
		}
		if (connection != null) {
			System.out.println("Connection successful");
		} else {
			System.err.println("Failed to make connection!");
		}
	}



	/**
	 * method connects to server
	 * 
	 */
	public void closeconnection() {
		try {
			connection.close();
			System.out.println("Connection closed");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connection could not be closed – SQL exception");
		}
	}

	// number of times computer won
	public int ai() {
		Statement stmt = null;

		String query = "SELECT COUNT(gamenumber) " + "FROM \"Game\".game " + "where humanwinner=false";

		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// the next method of ResultSet allows you to iterate through the results
			while (rs.next()) {
				// the getString method of the ResultSet object allows you to access the value
				// for the
				// given column name for the current row in the result set as a String. If the
				// value is an
				// integer you can use getInt(“col_name”)
				String avera = rs.getString("COUNT");
				int average = Integer.parseInt(avera);
				return average;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("error executing query " + query);
			return average;
		}
		return average;
	}

	// number of times human wins
	public int humanwin() {
		Statement stmt = null;

		String query = "SELECT COUNT(gamenumber) " + "FROM \"Game\".game " + "where humanwinner=true";

		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// the next method of ResultSet allows you to iterate through the results
			while (rs.next()) {
				// the getString method of the ResultSet object allows you to access the value
				// for the
				// given column name for the current row in the result set as a String. If the
				// value is an
				// integer you can use getInt(“col_name”)
				String avera = rs.getString("COUNT");
				int average = Integer.parseInt(avera);
				return average;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("error executing query " + query);
			return average;
		}
		return average;
	}

	// average number of draws
	public double total() {
		Statement stmt = null;

		String query = "SELECT AVG(drawspergame)\n" + "FROM \"Game\".game";
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// the next method of ResultSet allows you to iterate through the results
			while (rs.next()) {
				// the getString method of the ResultSet object allows you to access the value
				// for the
				// given column name for the current row in the result set as a String. If the
				// value is an
				// integer you can use getInt(“col_name”)
				String aver = rs.getString("AVG");
				d = Double.parseDouble(aver);
				return d;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("error executing query " + query);
			return d;
		}
		return d;
	}

	// total number of games
	public int numberofgames() {
		Statement stmt = null;

		String query = "SElECT COUNT (gamenumber)\n" + "FROM \"Game\".game";
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// the next method of ResultSet allows you to iterate through the results
			while (rs.next()) {
				// the getString method of the ResultSet object allows you to access the value
				// for the
				// given column name for the current row in the result set as a String. If the
				// value is an
				// integer you can use getInt(“col_name”)
				String tota = rs.getString("COUNT");
				total = Integer.parseInt(tota);
				return total;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("error executing query " + query);
			return total;
		}
		return total;
	}

	public int drawsum() {
		Statement stmt = null;

		String query = "SElECT SUM (drawspergame)\n" + "FROM \"Game\".game";
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// the next method of ResultSet allows you to iterate through the results
			while (rs.next()) {
				// the getString method of the ResultSet object allows you to access the value
				// for the
				// given column name for the current row in the result set as a String. If the
				// value is an
				// integer you can use getInt(“col_name”)
				String suma = rs.getString("SUM");
				sum = Integer.parseInt(suma);
				return sum;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("error executing query " + query);
			return sum;
		}
		return sum;
	}

		// insert
	public void insert(int drawspergame, boolean humanwinner, int roundspergame, 
			int human, int player2, int player3, int player4, int player5) 
	{
		gamenumber = numberofgames();
		gamenumber++;
		Statement stmt = null;
		String insert = "INSERT INTO \"Game\".game\r\n" + "Values ('" + gamenumber + "','"
		+ humanwinner +"','" + drawspergame + "','" + roundspergame + "','"+ human + "','"+ player2 + "','"+ player3 + "','"+ player4 + "','"+ player5 + "')";
		/// 
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate(insert);
			// the next method of ResultSet allows you to iterate through the results
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("wrong input");
		}
	}


	/// The largest number of rounds played in a single game
	public int maxroundspergame() {
		Statement stmt = null;
		String query = "SELECT MAX(roundspergame) FROM \"Game\".game";
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// the next method of ResultSet allows you to iterate through the results
			while (rs.next()) {
				// the getString method of the ResultSet object allows you to access the value
				// for the
				// given column name for the current row in the result set as a String. If the
				// value is an
				// integer you can use getInt(“col_name”)
				String maxs = rs.getString("Max");
				max = Integer.parseInt(maxs);
				return max;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("error executing query " + query);
			return max;
		}
		return max;
	}

}