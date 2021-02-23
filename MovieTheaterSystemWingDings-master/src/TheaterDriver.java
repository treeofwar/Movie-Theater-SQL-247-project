import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.Map.Entry;

public class TheaterDriver {
	
	private static Guest user;
	private static Scanner key = new Scanner(System.in);
	private static String response;
	private static int numberResponse;
	private static Map<Integer, Show> shows = new HashMap<>();
	private static Map<Integer, Venue> venues = new HashMap<>();
	private static Map<Integer, User> users = new HashMap<>();
	public static HashMap<Integer, Food> foods = new HashMap<Integer, Food>();
	 public static HashMap<Integer, Theater> theaters = new HashMap<Integer, Theater>();
	private static boolean userQuit = false;
	
	//hardcode test variables
	private static User testUser = new User();
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		SQLServerConnection server = new SQLServerConnection();
		SQLServerConnection.start();
		venues = SQLServerConnection.venueHash(); 
		shows = SQLServerConnection.showhash();
		users = SQLServerConnection.printuserhash();
		foods = SQLServerConnection.foodhash();
		theaters = SQLServerConnection.theaterHash();
		for(Entry<Integer, Theater> t: theaters.entrySet()) {
          System.out.println(  t.getValue().getTime());
            t.getValue().printSeats();
        }
		run();
		
	}
	
	private static void run() throws SQLException {
		
		System.out.println("***WELCOME TO PORTIA'S PICS & FLICKS***\n\n" +
						   "Are you a registered user with us? Sign in! (1)\n\n" +
						   "Do you wish to continue as a guest? (2)\n\n" +
						   "Admin login (3)\n\n" +
						    "create an account (4)");
		numberResponse = key.nextInt();
		key.nextLine();
		while((numberResponse < 0 || numberResponse > 4)) {
			System.out.println("Invalid input, try again");
			numberResponse = key.nextInt();
		}
		
		switch(numberResponse) {
		case 1:
			userSignIn();
			System.out.println("Thank you for using PORTIA'S PICS & FLICKS!");
			break;
		case 2:
			guestPage();
			System.out.println("Thank you for using PORTIA'S PICS & FLICKS!");
			break;
		case 3:
			adminSignIn();
			System.out.println("Thank you for using PORTIA'S PICS & FLICKS!");
			break;
		case 4:
			SQLServerConnection.addUser();
			System.out.println("you have been added");
			run();
			break;
		default:
			System.out.println("Invalid choice, try again");
			break;
		}
	}
	
	/**
	 * contains the user splash page with choices for printing show types for purchasing purposes or leaving a review
	 * @throws SQLException 
	 */
	private static void userSignIn() throws SQLException {
		String username;
		String password;
		System.out.println("Registered User");
		System.out.println("Please sign in (enter anything for now)\n" +
						   "Username: ");
		username = key.next();
		key.nextLine();
		System.out.println("Password: ");
		password = key.next();

		key.nextLine();
		user = testUser;
		//user = loginCheck(username, password);
		if(user != null) {

			while(userQuit == false) {
				userLandingPage();
			}
		}
	}
	private static void userLandingPage() throws SQLException {
		User user = new User();
		System.out.println("What kind of shows would you like to see?\n" + 
				   "Movies (1)\n" +
				   "Plays (2)\n" +
				   "Concerts (3)\n" +
				   "Or leave a review for a show or venue (4)\n" +
				   "Quit (5)");
		numberResponse = key.nextInt();
		key.nextLine();
		while(numberResponse < 0 || numberResponse > 5) {
			System.out.println("Invalid input, try again");
			numberResponse = key.nextInt();
			key.nextLine();
		}
		if(numberResponse == 5) {
			userQuit = true;
		}
		if(numberResponse == 4) {
			reviewCheck(user);
		}
		else {
			showCheck(numberResponse, user);
		}
	}
	
	/**
	 * contains logic for just printing show types and purchasing ticket, base user purchase will prompt the guest to create a profile
	 */
	private static void guestPage() {
		user = new Guest();
		System.out.println("Welcome Guest!");
		System.out.println("What kind of shows would you like to see?\n" + 
						   "Movies (1)\n" +
						   "Plays (2)\n" +
						   "Concerts (3)\n");
		numberResponse = key.nextInt();
		key.nextLine();
		while(numberResponse < 0 || numberResponse > 3) {
			System.out.println("Invalid input, try again");
			numberResponse = key.nextInt();
		}
		while(userQuit == false) {
			showCheck(numberResponse, user);
		}
	}
	
	/**
	 * contains the logic for the admin page, will eventually need to extend to regular user unless we wish to do further splits
	 * @throws SQLException 
	 */

	private static void adminSignIn() throws SQLException {
		Admin user = new Admin(1, "hello", response, response, response, response, response, 1, true);

		String username;
		String password;
		System.out.println("Admin");
		System.out.println("Please sign in (enter anything for now)\n" +
						   "Username: ");
		username = key.next();
		key.nextLine();
		System.out.println("Password: ");
		password = key.next();
		key.nextLine();
		//user = (Admin) loginCheck(username, password);
		if(user != null) {

			while(userQuit == false) {
				adminLandingPage(user);
			}
		}
	}
	

	private static void adminLandingPage(Admin user) throws SQLException {
		//Admin use1r = new Admin(numberResponse, response, response, response, response, response, response, numberResponse, userQuit);

		System.out.println("Welcome " + user.getName() + "!\n");
		System.out.println("What would you like to do?\n" + 
						   "Add show (1)\n" + 
						   "Remove show (2)\n" +
						   "Add food (3) \n" + 
						   "Remove food (4)\n" +
						   "Quit (5)");
		numberResponse = key.nextInt();
		key.nextLine();
		if(numberResponse == 5) {
			userQuit = true;
		}
		while(numberResponse < 0 || numberResponse > 5) {
			
			System.out.println("Invalid input, try again");
			numberResponse = key.nextInt();
			key.nextLine();
		}
		while(userQuit == false) {
		adminFunctions(numberResponse, user);
		}
	}
	
	private static User loginCheck(String username, String password) throws SQLException {
		if(SQLServerConnection.findUsername(username)==SQLServerConnection.findPassword(password)) {
			return users.get(SQLServerConnection.findUsername(username));
		}
		return null;
	}
	
	
	/**
	 * contains logic for printing out the show hashmaps contents and allowing users to create and purchase a ticket from that information
	 * @param choice (the number relating to movie(1), play(2) or concert(3)
	 * @param user the user profile making this choice, passed through for ticket generation reasons
	 */
	private static void showCheck(int choice, Guest user) {
		int show = 0;
		Show tempShow = null;
		switch(choice) {
		
		case 1:
			System.out.println("Here are the available movies!\nSelect by ID a movie to see the venues and showtimes!");

			for(Entry<Integer, Venue> v: venues.entrySet()) {
				if(v.getValue().getType().contains("cineplex")) {
					System.out.println(v.getValue().toString());
					v.getValue().printShows();
				}
			}
			show = key.nextInt();
			key.nextLine();
			tempShow = shows.get(show);
			user.createTicket(tempShow);
			user.purchaseTicket();
			break;
		case 2:
			System.out.println("Here are the available plays!\nSelect by ID a play to see the venues and showtimes!");
			for(Entry<Integer, Venue> v: venues.entrySet()) {
				if(v.getValue().getType().contains("playhouse")) {
					System.out.println(v.getValue().toString());
					v.getValue().printShows();
				}
			}
			show = key.nextInt();
			key.nextLine();
			tempShow = shows.get(show);
			user.createTicket(tempShow);
			user.purchaseTicket();
			break;
		case 3:
			System.out.println("Here are the available concerts!\nSelect by ID a concert to see the venues and showtimes!");
			for(Entry<Integer, Venue> v: venues.entrySet()) {
				if(v.getValue().getType().contains("concerthall")) {
					System.out.println(v.getValue().toString());
					v.getValue().printShows();
				}
			}
			show = key.nextInt();
			key.nextLine();
			tempShow = shows.get(show);
			user.createTicket(tempShow);
			user.purchaseTicket();
			break;
		case 5:
			userQuit = true;
			break;
		default:
			System.out.println("How are you here bro wtf");
			break;
		}
	}
	
	/**
	 * contains logic for users to leave reviews for venue or show
	 * @param user the user object being used to create a review
	 * @throws SQLException 
	 */
	private static void reviewCheck(User user) throws SQLException {
		System.out.println("What would you like to leave a review for? \nVenue (1) \nShow (2)");
		int reviewchoice = key.nextInt();
		key.nextLine();
		switch(reviewchoice) {
		case 1:
			System.out.println("Which venue would you like to leave a review for?\n");
			System.out.println(Arrays.asList(venues)); 
			int venueid = key.nextInt();
					key.nextLine();
					Venue venue = SQLServerConnection.venuehash.get(venueid);
			//Review testReview = new Review(5, "Test", testUser);
			user.createVenueReview(venue);

			break;
		case 2:
			System.out.println("Which show would you like to leave a review for?\n");
			System.out.println(Arrays.asList(shows)); 
			int showid = key.nextInt(); 
					key.nextLine();
			Show show = SQLServerConnection.moviehash.get(showid);
			user.createShowReview(show);
			
			break;
		default:
			System.out.println("Please enter a proper choice");
			break;
		}
		
	}
	
	/**
	 * contains the logic for admin functions
	 * @param choice, int representing what the admin would like to do add a show(1) remove a show(2) add food (3) remove food (4)
	 * @param user, the admin making the changes
	 * @throws SQLException 
	 */
	private static void adminFunctions(int choice, Admin user) throws SQLException {
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("please choose a venue to work in");
		SQLServerConnection.printvenues();
		System.out.println("please enter the venue id that you want to work in");
		Integer vChoice = keyboard.nextInt();
		keyboard.nextLine();
		Venue venue = venues.get(vChoice);
		switch(choice) {
		case 1:
			System.out.print("Created show:\n" + user.addShowListing(venue).toStringShort());
			SQLServerConnection.showhash();
			SQLServerConnection.theaterHash();
			break;
		case 2:
			System.out.println("Removing show:\n" + user.removeShow(venue));
			SQLServerConnection.showhash();
			SQLServerConnection.theaterHash();
			break;
		case 3:
			System.out.println("Adding food\n" +user.addFood(venue).toString());
			SQLServerConnection.foodhash();
			break;
		case 4:
			System.out.println("Removing food\n" + user.removeFood(venue));
			SQLServerConnection.foodhash();
			break;
		case 5:
			userQuit = true;
			break;
		default:
			System.out.println("????");
			break;
		}
	}
}
