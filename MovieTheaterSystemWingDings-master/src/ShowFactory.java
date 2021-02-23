/**
 * 
 * @author WingDings
 */

public class ShowFactory {
	/**
	 * 
	 * @param venue, the calling venue
	 * @param name, name of the show
	 * @param description, IMDB-esque description of the show
	 * @param time, date and time of the show
	 * @param review, user reviews of the show
	 * @param theaterRows, the rows in the theater
	 * @param theaterColumns, the seats in each row
	 * @return the newly created show
	 */
	public Show createShow(Venue venue, String name, String description, String[] time,
				Review review, int theaterRows, int theaterColumns, double price) {
		Show newShow = new Show(venue, name, description, time, review, theaterRows, theaterColumns, price);
		return newShow;
	}
}
