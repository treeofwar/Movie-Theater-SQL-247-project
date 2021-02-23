import java.sql.SQLException;
import java.util.*;

public class User extends Guest {
	protected Integer id;
	protected int points;
	protected HashMap<String, Ticket> purchaseHistory = new HashMap<>();
	protected HashMap<String, Review> reviewHistory = new HashMap<>();
	protected String firstName;
	protected String lastName;
	protected String dateOfBirth;
	protected String email;
	protected String userName;
	protected String password;
	protected int age;
	protected boolean admin;
	
	public User() {
		super();
		this.firstName = "annon";
		this.lastName = "mouse";
		this.dateOfBirth = "09/09/1999";
		this.email = "no email yet";
		this.userName = "Guest";
		this.password = "null";
		this.age = 12;
		this.points = 0;
		this.purchaseHistory = new HashMap<>();
		this.reviewHistory = new HashMap<>();
	}
	
	public User(Integer userID, String firstName, String lastName, String dateOfBirth, String email, String userName, String password, int age, boolean admin) {
		super();
		this.id = userID;
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setDateOfBirth(dateOfBirth);
		this.setEmail(email);
		this.setUsername(userName);
		this.setPassword(password);
		this.setAge(age);
		this.purchaseHistory = new HashMap<>();
		this.reviewHistory = new HashMap<>();
		this.admin = false;
	}
	
	public Integer getID() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String name) {
		this.firstName = name;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String name) {
		this.lastName = name;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return userName;
	}

	public void setUsername(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		if(age <0) {
			System.out.println("invalid age");
		}
		else {
			this.age = age;
		}
	}
	
	/**@Overrides user purchase ticket
	 * Xavier
	 * Purchases the ticket currently in the user ticket space, applies points, uploads the ticket to user ticket history for refund use,
	 * sets current ticket to null
	 */
	public void purchaseTicket() {
		//super call to User purchase ticket function
		super.purchaseTicket();
	}
	
	/**
	 * spends the accumulated points on ticket price
	 * meant to be a helper function
	 */
	public void spendPoints() {
		//TODO enable points to be used to cheapen ticket
	}
	
	/**
	 * takes in a venue and allows a regular user to create a review for that venue
	 * @param venue
	 * @throws SQLException 
	 */
	public void createVenueReview(Venue venue) throws SQLException {
		SQLServerConnection.addVenuereview(venue);
		
	}
	
	/**
	 * takes in a show and allow the user to create a review for that show
	 * @param show
	 * @throws SQLException 
	 */
	public void createShowReview(Show show) throws SQLException {
		SQLServerConnection.addmoviereview(show);
	}
	
	/**
	 * deletes a listing
	 * @throws SQLException 
	 */
	public void deleteReview() throws SQLException {
		SQLServerConnection.findandremovemoviereview();
	}
	/**
	 * edits a listing
	 * @return
	 */
	public String editReview() {
		return null;
	}
	
	/**
	 * create a string representation of the object
	 */
	public String toString () {
		return "First Name: " +firstName + "\nLast Name: " +lastName + "\nDate of Birth: " + dateOfBirth + "\nEmail: " +email + "\nUsername: " + userName + "\nPassword: " + 
	password + "\nAge: " +age;
	}
}

