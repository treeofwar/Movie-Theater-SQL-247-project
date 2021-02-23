import java.util.Random;

/**
 * Ticket class to be had by User that
 * holds all the show information as well as
 * food information
 * @author WingDings
 */
public class Ticket {
	protected Show show;
	protected String time;
	protected String[] seats;
	protected double price;
	protected Food[] food;

	/**
	 * Ticket with food constructor
	 * @param show
	 * @param time
	 * @param seats
	 * @param food
	 * @param price
	 */
	public Ticket(Show show,String time, String[] seats, Food[] food, double price) {

		this.setShow(show);
		this.setTime(time);
		this.setSeats(seats);
		this.setFood(null);
		this.setPrice(price);
	}
	
	/**
	 * Ticket without food constructor
	 * @param show
	 * @param time
	 * @param seats
	 * @param price
	 */
	public Ticket(Show show,String time, String[] seats, double price) {

		this.setShow(show);
		this.setTime(time);
		this.setSeats(seats);
		this.setFood(null);
		this.setPrice(price);
	}
	

	public String[] getSeats() {
		return seats;
	}

	public void setSeats(String[] seats) {
		this.seats = seats;
	}

	public Show getShow() {
		return show;
	}

	public String getTime() {
		return time;
	}

	public double getPrice() {
		return price;
	}

	public Food[] getFood() {
		return food;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setFood(Food[] food) {
		this.food = food;
	}
	
	/**
	 * returns the seats array as a string
	 * @return the seats array as a string
	 */
	public String seatsToString() {
		String ret = "";
		for(int i = 0; i < seats.length; i++) {
			ret = ret + seats[i] + " ";
		}
		return ret;
	}

	/**
	 * returns the string of a foodless ticket
	 * @return the ticket to string without the food item
	 */
	public String toString() {
		Random rand = new Random();
		int rando = rand.nextInt(1000000000);
		return "*            " + SQLServerConnection.venuehash.get(this.getShow().getVenueID()).getName() + "            \n" +
		"             " + getShow().getName() + "               \n" +
		"         Time: " + getTime() + "        \n" +
		"             Seats: " + seatsToString() + "            \n" +
		"                                   \n" +
		"                                   \n" +
		"            Total: " + getPrice() + "            *\n" +
		"                                   \n" +
		"                                   \n" +
		"                                   \n" +
		"      Ticket ID: " + rando + "        \n" +
		"                                   \n" +
		"   Thank you for your business!!   \n" +
		"  C: C: C: C: C: C: C: C: C: C: C: \n";
				 
	}
	
	/**
	 * returns the string of a ticket with food
	 * @returns the values of a ticket with a food object
	 */
	public String toStringWithFood() {
		Random rand = new Random();
		int rando = rand.nextInt(1000000000);
		return "            " + SQLServerConnection.venuehash.get(this.getShow().getVenueID()).getName() + "            \n" +
		"              " + getShow().getName() + "               \n" +
		"        Time: " + getTime() + "        \n" +
		"             Seats: " + seatsToString() + "            \n" +
		"        Food: " + getFood() + "                 \n" +
		"                                   \n" +
		"                                   \n" +
		"            Total: " + getPrice() + "            \n" +
		"                                   \n" +
		"                                   \n" +
		"                                   \n" +
		"      Ticket ID: " + rando + "        \n" +
		"                                   \n" +
		"   Thank you for your business!!   \n" +
		"  C: C: C: C: C: C: C: C: C: C: C: \n";
	}
}
