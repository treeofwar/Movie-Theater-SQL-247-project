import java.util.*;
import java.util.Map.Entry;

/**
 * 
 * @author WingDings
 */
public class Show {
	private static final double DEFAULT_PRICE = 12.00;
	private static final int UNICODE_OFFSET= 65;

	private int showId;
	private int venueid;
	private String name;
	private String description;
	public Map<Integer, Theater> theaters = new HashMap<Integer, Theater>();
	private int rows;
	private int cols;
	private Map<Integer, Review> reviews = new HashMap<Integer, Review>();
	private double price;
	

	public Show(Integer movieid,String name, String description, Double price, Integer venueid) {
		this.showId  = movieid;
		this.name = name;
		this.description = description;
		this.price = price;
		this.venueid = venueid;

	}
	
	/*
	 * getters and setters, no sanitization is done her
	 * 
	 * TODO sanitize setters.
	 */
	public Integer getShowID() {
		return showId;
	}
	
	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		if(rows>0)
			this.rows = rows;
		else
			System.out.print("you cant have less than a row in a theater");
	}

	public int getCol() {
		return cols;
	}

	public void setCol(int col) {
		if(col>0)
			this.cols = col;
		else
			System.out.println("you can have less than a seat in a row");
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		if(price > 0)
			this.price = price;
		else
			this.price = DEFAULT_PRICE;
	}

	
	public int getVenueID() {
		return venueid;
	}
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
  
  public void addingReview( int id,String review, int rating) {
		reviews.put(id,new Review(rating, review, null));
  }
	
	public Review getReview() {
		//TODO get specific review
		return null;
	}
	
	/**
	 * adds a batch of show times given an array of show times and dimensions of the theater
	 * @param array of show times in string format 00/00 00:00AM
	 * @param theaterRows
	 * @param theaterColumns
	 */
	/*
	public void addShowTimes(String[] time, int theaterRows, int theaterColumns) {
		for(int i = theaters.size(); i < theaters.size() + time.length; i++) {
			Theater t = new Theater(this.getShowID(), theaterRows, theaterColumns, time[i]);
			this.theaters.put(i,t);
		}
	}
	*/
	/**
	 * adds a batch of show times given an array of show times and dimensions of the theater
	 * @param time show time in string format 00/00 00:00AM
	 * @param theaterRows
	 * @param theaterColumns
	 */
	/*
	public void addShowTime(String time, int theaterRows, int theaterColumns) {
		Theater t = new Theater(this.getShowID(), theaterRows, theaterColumns, time);
		this.theaters.put(theaters.size(),t);
	}
*/
	/**
	 * adds a Review to the show
	 * @param stars, stars given the show
	 * @param review, review itself
	 */
	public void addReview(String userName, Review review) {
		this.reviews.put(reviews.size(), review);
	}
	
	/**
	 * creates a string of all the reviews of the show
	 */
	public String getAllReviews() {
		String ret = "";
		for(Entry<Integer, Review> r:reviews.entrySet()) {
			 ret = ret + r.getValue().toString() +"\n\n";
		}
		return ret;
	}
	
	/**
	 * prints the show times
	 * @return all the show times of this show
	 */
	public String showTimes() {
		String ret = "";
		for(Entry<Integer, Theater> t:theaters.entrySet()) {
			ret = ret + t.getValue().toString() + ". ";
		}
		return ret;
	}
	
	
	/**
	 * takes a string seat array and updates the shows seat array to reserve those seats
	 * @param reservation
	 */
	public boolean reserveSeats(String time, String[] reservation) {
		//retrieves the current seat reservation
		for(Entry<Integer, Theater> t:theaters.entrySet()) {
			if(time.contains(t.getValue().getTime())) {
				boolean seats[][] = t.getValue().getSeats();
				for(String string:reservation) {
				//splits String of AA into r and c
					char charR = string.charAt(0);
					char charC = string.charAt(1);
					int r = ((int) charR)-UNICODE_OFFSET;
					int c = ((int) charC)-UNICODE_OFFSET;
				
					if(seats[r][c]==false) {
						return false;
					}
				}
		
				for(String string:reservation) {
				//splits String of AA into r and c
				char charR = string.charAt(0);
				char charC = string.charAt(1);
				int r = ((int) charR)-UNICODE_OFFSET;
				int c = ((int) charC)-UNICODE_OFFSET;
				seats[r][c]=false;
				}
		
		//sets the seats in the theater at this show time to the updated array
			theaters.get(t.getKey()).setSeats(seats);	
			}
		}
		return true;
	}
	
	/**
	 * Takes in a seat reservation and frees up the shows seats at that area
	 * @param reservation
	 */
	public void cancelSeatReservation(String time, String [] reservation) {
		//retrieves the current seat reservations
		for(Entry<Integer, Theater> t:theaters.entrySet()) {
			if(time.contains(t.getValue().getTime())) {
				boolean seats[][] = t.getValue().getSeats();
				for(String string:reservation) {
					//splits String of AA into r and c
					char charR = string.charAt(0);
					char charC = string.charAt(1);
					int r = ((int) charR)-UNICODE_OFFSET;
					int c = ((int) charC)-UNICODE_OFFSET;
					seats[r][c]=false;
				}
				//sets the seats in the theater at this show time to the updated array
				theaters.get(time).setSeats(seats);
			}
		}
		
	}
	
	/**
	 * prints the available seats to the console using unicode character to denote column and row of the theater
	 */
	public void printSeats(String time) {
		for(Entry<Integer, Theater> t:theaters.entrySet()) {
			if(time.contains(t.getValue().getTime())) {
				boolean seats[][] = t.getValue().getSeats();
				//preps the column to be a name space
				System.out.print("  ");
				//prints the row names
				for(int i = 0; i < cols; i++ ) {
					char c =  (char) (i+UNICODE_OFFSET);
					System.out.print(c+" ");
				}
				//prints the seats as (O)pen or (x)occupied, prints the row label first.
				System.out.println();
				for(int i = 0; i < rows; i++) {
					char c =  (char) (i+UNICODE_OFFSET);
					System.out.print(c+ " ");
					for (int j = 0; j < cols; j++) {
						if(seats[i][j]==true)
							System.out.print("O ");
						else
							System.out.print("X ");
					}
					System.out.println();
				}
			}
		}
		
	}
	
	
	
	public String toStringShort() {
		return "ID:" + venueid + "Name: " + name;
	}
	
	public String toString() {
		return "Name: " + name + "\nPrice: "+ price + "\nDescription: " + description + "\nTimes: " + showTimes() + "\nReviews:\n" + this.getAllReviews();
	}
}
