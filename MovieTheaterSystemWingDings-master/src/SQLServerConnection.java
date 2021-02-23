
import java.sql.*;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

import com.mysql.cj.xdevapi.Statement;


import java.sql.*;
public class SQLServerConnection  {


	static Connection connection = null;
	static String databaseName = "movietest";
	static String url = "jdbc:mysql://localhost:3306/movietest";
	static java.sql.Statement stmt;
	static String username = "root";
	static String password = "password";
	static String thing = "select * from  user";
	static String thing1 = "select * from  movie";
	static String thing2 = "select * from  venue";
	static String thing3 = "select * from  venuereview";
	static String truncate = "truncate table user";
	static String truncate2 = "truncate table venue";
	static String truncate3 = "truncate table movie";
	static String deleterow = "delete from user where email=?";//?denotes user input or a preparedstatement 
	static String deletevenue = "delete from venue where name =?";
	static String deletemovie = "delete from movie where name=?";
	static String deletemoviereview = "delete from moviereview where Mreview_id=?";
	static String venuejoins = " select venuereview.review, venuereview.rating, venue.name, from venuereview inner join venue on venuereview.venue_id=venue.venueid";//todo finish this stupid ass join test if works
	static String sortasc = "select * from moviereview order by rating asc";
	static String sortdsc = "select * from moviereview order by rating desc";
	static String sortvenueasc = "select * from venuereview order by rating asc";
	static String sortvenuedsc = "select * from venuereview order by rating desc";
	static String newthing = "select * from venuereview where venue_id=?";
	static String newthing2 = "select * from moviereview where movie_id=?";
	static String thing4 = "select * from food";
	static String thing5 = "select * from theater";
	static String finduser="select * from user where User_Name=?";
	static String findpassword = "select * from user where Password =?";
	static Scanner keyboard;
	 public static HashMap<Integer,User> map = new HashMap<Integer, User>();
	 public static HashMap<Integer, Show> moviehash = new HashMap<Integer, Show>();
	 public static HashMap<Integer, Venue> venuehash = new HashMap<Integer, Venue>();
	 public static HashMap<Integer, Food> foodhash = new HashMap<Integer, Food>();
	 public static HashMap<Integer, Theater> theaterhash = new HashMap<Integer, Theater>();
	public static void start() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection(url, username, password);
		venueHash();
	
	}
	public static HashMap<Integer, Food> foodhash() throws SQLException {
        Food f;
        stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(thing4);
        while(rs.next()) {
                Integer foodid = rs.getInt("foodid");
                String name = rs.getString("name");
                double price = rs.getDouble("cost");
                Integer quantity = rs.getInt("quantity");
                Integer venueid = rs.getInt("venueid");
                f = new Food(foodid,name,price,quantity, venueid);
                foodhash.put(foodid,f);
                PreparedStatement ps = connection.prepareStatement(newthing);
                ps.setInt(1,venueid);
                ResultSet rs2 = ps.executeQuery();
                while(rs2.next()) {
                    String name2 = rs2.getString("name");
                    String type = rs2.getString("type");
                    String location = rs2.getString("location");


                }
            }
        return foodhash;
        }

	public static HashMap<Integer, Show>  showhash() throws SQLException {
		Show s;
		stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(thing1);
		while(rs.next()) {
			Integer movieid = rs.getInt("movieID");
			String name = rs.getString("name");
			String description = rs.getString("description");
			double price = rs.getDouble("price");
			Integer venueid = rs.getInt("venueid");
			s = new Show(movieid,name,description,price,venueid); 

			moviehash.put(movieid, s);
			PreparedStatement ps = connection.prepareStatement(newthing2);
			ps.setInt(1, movieid);
			ResultSet rs2 = ps.executeQuery();
			while(rs2.next()) {
				String review = rs2.getString("review");
				int rating = rs2.getInt("rating");
				int id = rs2.getInt("Mreview_id");
				s.addingReview(id,review,rating);
			}
			for(Entry<Integer, Venue> v: venuehash.entrySet()) {
				if(v.getValue().getID() == s.getVenueID()) {
					v.getValue().shows.put(movieid, s);
				}
			}
		}
		return moviehash;
	}
	
	public static HashMap<Integer, Theater> theaterHash() throws SQLException{
		Theater t;
		stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(thing5);
		while(rs.next()) {
			Integer theaterid = rs.getInt("theaterid");
			Integer movieid = rs.getInt("movieid");
			int r= rs.getInt("r");
			int c= rs.getInt("c");
			String time = rs.getString("t");
			String seats = rs.getString("seats");
			
			t = new Theater(theaterid,movieid,r,c,time,seats); 

			theaterhash.put(movieid, t);
		
		for(Entry<Integer, Show> s: moviehash.entrySet()) {
			if(s.getValue().getShowID() == t.getShowID()) {
				s.getValue().theaters.put(theaterid, t);
				}
			}
		}
		return theaterhash;
	}
	
public static HashMap<Integer, Venue> venueHash() throws SQLException {
		Venue v;
		stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(thing2);
		while(rs.next()) {
			Integer venueid = rs.getInt("venueid");
			String name = rs.getString("name");
			String type = rs.getString("type");
			String location = rs.getString("location");
			v = new Venue(venueid,name,type,location);
			venuehash.put(venueid, v);
			PreparedStatement ps = connection.prepareStatement(newthing);
			ps.setInt(1, venueid);
			ResultSet rs2 = ps.executeQuery();
			while(rs2.next()) {
				String review = rs2.getString("review");
				int rating = rs2.getInt("rating");
				int id = rs2.getInt("review_id");
				v.addingReview(id,review, rating);
			}
		}

		
		return venuehash;
	}
		public static HashMap<Integer,User> printuserhash() throws SQLException {
			User u;
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(thing);
			while(rs.next()) {
				Integer UserID = rs.getInt("UserID");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String email = rs.getString("email");
				int age = rs.getInt("age");
				String dateofbirth = rs.getString("dateofbirth");
				String username = rs.getString("User_name");
				String Password = rs.getString("Password");
				u = new User(UserID,first_name, last_name, email,dateofbirth,username,Password,age, false);
				map.put(UserID, u);
			}/*
			for(Integer i : map.keySet()) {
				User us = map.get(i);
				System.out.println(us.getID() + " "+ us.getFirstName() +" " + us.getLastName() + " " + us.getEmail() + " " + us.getAge() + " " + us.getDateOfBirth()  + " " + us.getUsername()  + " " + us.getPassword());
			}*/
			return map;
		}
		public static void truncatevenuetable() throws SQLException {
			stmt = connection.createStatement();
			int status = stmt.executeUpdate(truncate3);
			}
		
public static void truncatemovietable() throws SQLException {
	stmt = connection.createStatement();
	int status = stmt.executeUpdate(truncate2);
	}
	public static void truncateUsertable() throws SQLException {
		stmt = connection.createStatement();
		int status = stmt.executeUpdate(truncate);
		System.out.println("truncated");
	}
	public static void addUser() throws SQLException {
		Scanner keyboard = new Scanner(System.in);
		String query = "insert into user(first_name,last_name,email,age,dateofbirth, User_Name,Password)" + " values (?,?,?,?,?,?,?)";
		System.out.println("please enter your first name");
		String name = keyboard.nextLine();
		System.out.println("please enter in your last name");
		String lastname = keyboard.nextLine();
		System.out.println("please enter your email");
		String email = keyboard.nextLine();
		System.out.println("please enter your date of birth with the format YYYY-MM-DD");
		String date = keyboard.nextLine();
		System.out.println("please enter your user name");
		String username = keyboard.nextLine();
		System.out.println("please enter your password");
		String password = keyboard.nextLine();
		System.out.println("please enter your age");
		int age = keyboard.nextInt();
		keyboard.nextLine();
		if(age <= 0) {
			System.out.println("youre not even alive yet");
		}
		else {
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1,name);
		ps.setString(2, lastname);
		ps.setString(3, email);
		ps.setInt(4, age);
		ps.setString(5,date);
		ps.setString(6,username);
		ps.setString(7,password);
		int status = ps.executeUpdate();
		
		if(status != 0) {
			System.out.println("database was connected correctly");
			}
		}
	}
	public static void addVenue() throws SQLException {
		Scanner keyboard = new Scanner(System.in);
		String query = "insert into venue(name,type,location)" + " values (?,?,?)";
		System.out.println("enter in your venues name");
		String name = keyboard.nextLine();
		System.out.println("enter in the venues type");
		String type = keyboard.nextLine();
		System.out.println("enter in your venues location");
		String location = keyboard.nextLine();
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1,name);
		ps.setString(2, type);
		ps.setString(3, location);
		int status = ps.executeUpdate();
		
		if(status != 0) {
			System.out.println("database was connected correctly");
			}
		}
	
	public static void addVenuereview(Venue venue) throws SQLException {
		Scanner keyboard = new Scanner(System.in);
		String query = "insert into venuereview(review,rating,venue_id)" + "values (?,?,?)";
		System.out.println("enter in your review");
		String review = keyboard.nextLine();
		
		int venueid = venue.getID();
		keyboard.nextLine();
		System.out.println("enter in your rating");
		int rating = keyboard.nextInt();
		keyboard.nextLine();
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, review);
		ps.setInt(2, rating);
		ps.setInt(3, venueid);
		int status = ps.executeUpdate();
		if(status != 0) {
			System.out.println("database was connected correctly");
			}
		
	}
	public static void printvenuereview() throws SQLException {
		stmt = connection.createStatement();//stmt is a statement and this is creating a connection that will let me create a statement
		ResultSet rs = stmt.executeQuery(thing3);//executes a result set that hold the statement 
		ResultSetMetaData rsmd = rs.getMetaData();//gets the data that the statement has
		System.out.println("");//spaces them out
		int numberofcol = rsmd.getColumnCount();
		for (int i = 1; i <= numberofcol; i++) {//grabs the colume names 
	        if (i > 1) System.out.print(",  ");
	        String columnName = rsmd.getColumnName(i);
	        System.out.print(columnName);
	      }
	      System.out.println("");
	  
	      while (rs.next()) {//goes through each colume and gets the string associated to it 
	        for (int i = 1; i <= numberofcol; i++) {
	          if (i > 1) System.out.print(",  ");
	          String columnValue = rs.getString(i);
	          System.out.print(columnValue);
	        }
	        System.out.println("");  //todo load into a hashmap
	      }
	}
	public static void addmoviereview(Show show) throws SQLException {
		Scanner keyboard = new Scanner(System.in);
		String query = "insert into moviereview(review,rating,movie_id)" + "values (?,?,?)";
		System.out.println("enter in your review");
		String review = keyboard.nextLine();
		int movieid = show.getShowID();
		keyboard.nextLine();
		System.out.println("enter in your rating");
		int rating = keyboard.nextInt();
		keyboard.nextLine();
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, review);
		ps.setInt(2, rating);
		ps.setInt(3, movieid);
		int status = ps.executeUpdate();
		if(status != 0) {
			System.out.println("database was connected correctly");
			}
	}
	public static Food addfood(Venue venue) throws SQLException {
		Scanner keyboard = new Scanner(System.in);
		String query = "insert into food(name,cost,quantity,venueid)" + " values (?,?,?,?)";
		System.out.println("enter the foods name");
		String name = keyboard.nextLine();
		System.out.println("enter in the cost of the food");
		double cost = keyboard.nextDouble();
		keyboard.nextLine();
		System.out.println("enter in the quantity of the food");
		int quantity = keyboard.nextInt();
		keyboard.nextLine();
		int venueid = venue.getID();
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, name);
		ps.setDouble(2, cost);
		ps.setInt(3, quantity);
		ps.setInt(4, venueid);
		int status = ps.executeUpdate();
		return new Food(status, name, cost, quantity, venueid);


	}
		
		
	
	public static Show addmovie(Venue venue) throws SQLException {
		Scanner keyboard = new Scanner(System.in);

		String query = "insert into movie(name,description,price,venueid)" + " values (?,?,?,?)";
		System.out.println("enter in your shows name");

		String name = keyboard.nextLine();
		System.out.println("enter in the movies description");
		String description = keyboard.nextLine();
		System.out.println("enter in your movies price");
		double price = keyboard.nextDouble();
		keyboard.nextLine();
		int venueid = venue.getID();
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1,name);
		ps.setString(2, description);
		ps.setDouble(3, price);
		ps.setInt(4, venueid);
		int status = ps.executeUpdate();
		return new Show(status, name, description, price, venueid);
	}

	public static Theater addtheater(Show show) throws SQLException {
		Scanner keyboard = new Scanner(System.in);
		String query = "insert into theater(movieid,r,c,t,seats)" + " values (?,?,?,?,?)";
		int movieid= show.getShowID();
		keyboard.nextLine();
		System.out.println("enter in the number of rows in the theater");
		int r= keyboard.nextInt();
		keyboard.nextLine();
		System.out.println("enter in the number of seats per row");
		int c= keyboard.nextInt();
		keyboard.nextLine();
		//seats to SQL logic
		String seats = "";
		for(int i = 0; i < r; i++) 
			for(int j = 0; j < c; j++) 
				seats = seats + "Y";
		
		System.out.println("What are the show times in format mm/dd 00:00am, enter them one at time");
		String t = keyboard.nextLine();
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1,movieid);
		ps.setInt(2, r);
		ps.setInt(3, c);
		ps.setString(4, t);
		ps.setString(5,seats);
		int status = ps.executeUpdate();
		return new Theater(status, movieid, r, c, t, seats);
		}

		
		
	
	public static void printvenues() throws SQLException {
		stmt = connection.createStatement();//stmt is a statement and this is creating a connection that will let me create a statement
		ResultSet rs = stmt.executeQuery(thing2);//executes a result set that hold the statement 
		ResultSetMetaData rsmd = rs.getMetaData();//gets the data that the statement has
		System.out.println("");//spaces them out
		int numberofcol = rsmd.getColumnCount();
		for (int i = 1; i <= numberofcol; i++) {//grabs the colume names 
	        if (i > 1) System.out.print(",  ");
	        String columnName = rsmd.getColumnName(i);
	        System.out.print(columnName);
	      }
	      System.out.println("");
	  
	      while (rs.next()) {//goes through each colume and gets the string associated to it 
	        for (int i = 1; i <= numberofcol; i++) {
	          if (i > 1) System.out.print(",  ");
	          String columnValue = rs.getString(i);
	          System.out.print(columnValue);
	        }
	        System.out.println("");  //todo load into a hashmap
	      }
	}
	public static void printUsers() throws SQLException {
		stmt = connection.createStatement();//stmt is a statement and this is creating a connection that will let me create a statement
		ResultSet rs = stmt.executeQuery(thing);//executes a result set that hold the statement 
		ResultSetMetaData rsmd = rs.getMetaData();//gets the data that the statement has
		System.out.println("");//spaces them out
		int numberofcol = rsmd.getColumnCount();
		for (int i = 1; i <= numberofcol; i++) {//grabs the colume names 
	        if (i > 1) System.out.print(",  ");
	        String columnName = rsmd.getColumnName(i);
	        System.out.print(columnName);
	      }
	      System.out.println("");
	  
	      while (rs.next()) {//goes through each colume and gets the string associated to it 
	        for (int i = 1; i <= numberofcol; i++) {
	          if (i > 1) System.out.print(",  ");
	          String columnValue = rs.getString(i);
	          System.out.print(columnValue);
	        }
	        System.out.println("");  //todo load into a hashmap
	      }
	     
	}

	//this logic is wrong
	public static ResultSet findUsername(String username) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement(finduser);
		stmt.setString(1, username);
		ResultSet id = stmt.executeQuery();
		return id;
	}
	//this logic is wrong
	public static ResultSet findPassword(String password) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement(findpassword);
		stmt.setString(1, password);
		ResultSet id = stmt.executeQuery();
		return id;

	}

public static void findandremove() throws SQLException {
	System.out.println("please enter your email in");
	String email = keyboard.nextLine();
	PreparedStatement stmt = connection.prepareStatement(deleterow);//stmt is a statement and this is creating a connection that will let me create a statement
	stmt.setString(1,email );
	stmt.executeUpdate();
	System.out.println(" has been deleted");
	}

public static void findandremovevenue() throws SQLException {
	System.out.println("enter in a venue name you want deleted");
	String vdelete = keyboard.nextLine();
	PreparedStatement stmt = connection.prepareStatement(deletevenue);
	stmt.setString(1, vdelete);
	stmt.executeUpdate();
}

public static String findandremovemovie(Venue venue) throws SQLException {
	Scanner keyboard = new Scanner(System.in);
	System.out.println("enter in the name of a movie to get rid of");
	String movieName = keyboard.nextLine();
	int venueid = venue.getID();
	PreparedStatement stmt = connection.prepareStatement(deletemovie);
	stmt.setString(1, movieName);
	//stmt.setInt(2, venueid);
	stmt.executeUpdate();
	return movieName;
}
public static void findandremovemoviereview() throws SQLException {
	Scanner keyboard = new Scanner(System.in);
	System.out.println("enter a moviereview id to delete");
	int reviewid = keyboard.nextInt();
	keyboard.nextLine();
	PreparedStatement stmt = connection.prepareStatement(deletemoviereview);
	stmt.setInt(1, reviewid);
	stmt.executeUpdate();
}
public static void sortmoviereviewasc() throws SQLException {
	stmt = connection.createStatement();
	ResultSet rs = stmt.executeQuery(sortasc);
		while(rs.next()) {
			Integer reviewid = rs.getInt("Mreview_id");
			String review = rs.getString("review");
			int rating = rs.getInt("rating");
		System.out.print("ID: " +rating);
		System.out.print(", review: " + review);
		System.out.println(", rating: " + rating);
	}
}
public static void sortmoviereviewdsc() throws SQLException {
	stmt = connection.createStatement();
	ResultSet rs = stmt.executeQuery(sortdsc);
		while(rs.next()) {
			Integer reviewid = rs.getInt("Mreview_id");
			String review = rs.getString("review");
			int rating = rs.getInt("rating");
		System.out.print("ID: " +rating);
		System.out.print(", review: " + review);
		System.out.println(", rating: " + rating);
	}
}
public static void sortvenuereviewasc() throws SQLException {
	stmt = connection.createStatement();
	ResultSet rs = stmt.executeQuery(sortvenueasc);
		while(rs.next()) {
			Integer reviewid = rs.getInt("review_id");
			String review = rs.getString("review");
			int rating = rs.getInt("rating");
		System.out.print("ID: " +rating);
		System.out.print(", review: " + review);
		System.out.println(", rating: " + rating);
	}
	
}
public static void sortvenuereviewdsc() throws SQLException {
	stmt = connection.createStatement();
	ResultSet rs = stmt.executeQuery(sortvenuedsc);

		while(rs.next()) {
			Integer reviewid = rs.getInt("review_id");
			String review = rs.getString("review");
			int rating = rs.getInt("rating");
		System.out.print("ID: " +rating);
		System.out.print(", review: " + review);
		System.out.println(", rating: " + rating);
		}
	}
}


