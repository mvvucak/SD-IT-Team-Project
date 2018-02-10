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
	int tieswon;
	int drawswon;
	String dbname;
	String username;
	String password;
	private Connection connection = null;

	/**
	 * Method to connects to server checks for error and returns result to console
	 * 
	 */
	public void connection() {
		password = "takeonme123";
		username = "postgres";
		dbname = "postgres";
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost/" + dbname, username, password);
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
				System.out.println(average);
				return 10;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("error executing query " + query);
			return average;
		}
		return average;
	}

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
				System.out.println(d);
				return d;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("error executing query " + query);
			return d;
		}
		return d;
	}

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
				System.out.println(total);
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
				System.out.println(sum);
				return sum;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("error executing query " + query);
			return sum;
		}
		return sum;
	}

	public void insert() {
		gamenumber = numberofgames();
		gamenumber++;
		Statement stmt = null;
		String insert = "INSERT INTO \"Game\".game\r\n" + "Values ('" + drawspergame + "','" + gamenumber + "','"
				+ humanwinner +"','"+ drawswon + "','"+ tieswon + "','\"+ roundspergame + \"')";
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate(insert);
			// the next method of ResultSet allows you to iterate through the results
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("wrong input");
		}
	}

	public int ties() {
		Statement stmt = null;
		int gamenumb = numberofgames();
		String gn = Integer.toString(gamenumb);
		String query = "Select tieswon From \"Game\".game WHERE gamenumber =  '\"+gn +\"' ;";
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
				String suma = rs.getString("tieswon");
				sum = Integer.parseInt(suma);
				System.out.println(sum);
				return sum;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("error executing query " + query);
			return sum;
		}
		return sum;
	}
	
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
				System.out.println(sum);
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
