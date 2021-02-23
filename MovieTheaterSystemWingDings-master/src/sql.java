import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;
import com.mysql.cj.xdevapi.Statement;



import java.sql.*;
public class sql {

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
	static Scanner keyboard;
	 public static HashMap<Integer,User> map = new HashMap<Integer, User>();
	 public static HashMap<Integer, Show> moviehash = new HashMap<Integer, Show>();
	 public static HashMap<Integer, Venue> venuehash = new HashMap<Integer, Venue>();
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection(url, username, password);
		System.out.println("hello");
	venueHash();
		
	}
	public static void showhash() throws SQLException {
		Show s;
		stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(thing1);
		while(rs.next()) {
			Integer movieid = rs.getInt("movieID");
			String name = rs.getString("name");
			String description = rs.getString("description");
			double price = rs.getDouble("price");
			String moviedate = rs.getString("moviedate");
			String movietime = rs.getString("movietime");
			Integer venueid = rs.getInt("venueid");
			s = new Show(movieid,name,description,price,moviedate,movietime,venueid);
			moviehash.put(movieid, s);
			PreparedStatement ps = connection.prepareStatement(newthing2);
			ps.setInt(1, movieid);
			ResultSet rs2 = ps.executeQuery();
			while(rs2.next()) {
				String review = rs2.getString("review");
				int rating = rs2.getInt("rating");
				int id = rs2.getInt("Mreview_id");
				s.addingreview(id,review,rating);
			}
		}
	}
public static void venueHash() throws SQLException {
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
				v.addingreview(id,review, rating);
			}
			for(Integer i : Venue.Review.keySet()) {
			Venue vs= Review.getVenueReview(i);//make get venue review method in venue
			
			}
		}
	}
		public static void printuserhash() throws SQLException {
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
				u = new User(UserID,first_name, last_name, email,age,dateofbirth,username,Password);
				map.put(UserID, u);
			}
			for(Integer i : map.keySet()) {
				User us = map.get(i);
				System.out.println(us.getID() + " "+ us.getFirst_name() +" " + us.getLast_name() + " " + us.getEmail() + " " + us.getAge() + " " + us.getDateofbirth()  + " " + us.getUsername()  + " " + us.getPassword());
			}
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
	
	public static void addVenuereview() throws SQLException {
		Scanner keyboard = new Scanner(System.in);
		String query = "insert into venuereview(review,rating,venue_id)" + "values (?,?,?)";
		System.out.println("enter in your review");
		String review = keyboard.nextLine();
		System.out.println("enter in your venues id");
		int venueid = keyboard.nextInt();
		keyboard.nextLine();
		System.out.println("enter in your rating");
		int rating = keyboard.nextInt();
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
	public static void addmoviereview() throws SQLException {
		Scanner keyboard = new Scanner(System.in);
		String query = "insert into moviereview(review,rating,movie_id)" + "values (?,?,?)";
		System.out.println("enter in your review");
		String review = keyboard.nextLine();
		System.out.println("enter in your venues id");
		int movieid = keyboard.nextInt();
		keyboard.nextLine();
		System.out.println("enter in your rating");
		int rating = keyboard.nextInt();
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, review);
		ps.setInt(2, rating);
		ps.setInt(3, movieid);
		int status = ps.executeUpdate();
		if(status != 0) {
			System.out.println("database was connected correctly");
			}
	}
	public static void addmovie() throws SQLException {
		Scanner keyboard = new Scanner(System.in);
		String query = "insert into movie(name,description,price,moviedate,movietime,venueid)" + " values (?,?,?,?,?,?)";
		System.out.println("enter in your name name");
		String name = keyboard.nextLine();
		System.out.println("enter in the movies description");
		String description = keyboard.nextLine();
		System.out.println("enter in your movies date");
		String date = keyboard.nextLine();
		System.out.println("enter in your movies time");
		String time = keyboard.nextLine();
		System.out.println("enter in your movies price");
		double price = keyboard.nextDouble();
		keyboard.nextLine();
		System.out.println("enter in your venues id");
		int venueid = keyboard.nextInt();
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1,name);
		ps.setString(2, description);
		ps.setString(3, date);
		ps.setString(3, time);
		ps.setDouble(5, price);
		ps.setInt(6, venueid);
		int status = ps.executeUpdate();
		
		if(status != 0) {
			System.out.println("database was connected correctly");
			}
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
}
public static void findandremovemovie() throws SQLException {
	System.out.println("enter in a movie to get rid of");
	String name = keyboard.nextLine();
	PreparedStatement stmt = connection.prepareStatement(deletemovie);
	stmt.setString(1, name);
}
public static void findandremovemoviereview() throws SQLException {
	System.out.println("enter a moviereview id to delete");
	int reviewid = keyboard.nextInt();
	PreparedStatement stmt = connection.prepareStatement(deletemoviereview);
	stmt.setInt(1, reviewid);
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

