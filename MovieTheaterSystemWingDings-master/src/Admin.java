
import java.sql.SQLException;
import java.util.Scanner;

public class Admin extends User {
	private Venue venue;
	
	public Admin(Integer id, String firstName, String lastName, String dateOfBirth, String email, String userName, String password, int age, boolean admin) {
		super(id, firstName, lastName, dateOfBirth, email, userName, password, age, admin);
		this.admin = true;
	}
	
	/**
	 * adds a show to the show iterator of a venue after querying the user for the information, takes in
	 * multiple showtimes to create a list of the same show at different times.
	 * @throws SQLException 
	 */
	public Show addShowListing(Venue venue) throws SQLException {
		Scanner keyboard = new Scanner (System.in);
		Show show = SQLServerConnection.addmovie(venue);
		System.out.println("how many show times are there for this show?");
		int numOfTimes = keyboard.nextInt();
		keyboard.nextLine();
		String times[] = new String[numOfTimes];

		int count = 0;
		while (count < numOfTimes) {
			SQLServerConnection.addtheater(show);
			count++;
			}

		//TODO review logic to go here
		//search sql for same show and copy review over else review is set to empty
		Review review = null;
		
		venue.shows.put(venue.shows.size(),show);
		return show;
	}
	
	/**
	 * 
	 * @param show
	 * @throws SQLException 
	 */
	public String removeShow(Venue venue) throws SQLException {
		return SQLServerConnection.findandremovemovie(venue);
	}
	
	/**
	 * @throws SQLException 
	 * 
	 *
	 */
	public Food addFood(Venue venue) throws SQLException {
		return SQLServerConnection.addfood(venue);
	}
	/**
	 * 
	 * @param food
	 */
	public String removeFood(Venue venue) {
		return null;
	}
	
	public String getName() {
		return firstName + " " + lastName;
	}
}

