
/**
 * This class sets up the base for all show reviews
 * @author Wingdings
 */
public class Review {

	protected int stars;
	protected String review;

	protected User reviewer;
	
	public Review(int stars, String review, User user) {
		this.setStars(stars);
		this.setReview(review);
		this.reviewer = user;

	}
	
	/**
	 * gets the star ratings for each show
	 * @return
	 */
	public int getStars() {
  return stars;
	}
	
	/**
	 * gets the reviews for each show
	 * @return
	 */

	public String getReview() {

		return null;
	}
	
	/**
	 * 
	 */
	public void setReview(String review) {
		this.review = review;
	}
	
	/**
	 * sets the star ratings for each show
	 * @return
	 */
	public void setStars(int stars) {
		//TODO
		//sanitize to 1-5 stars
		this.stars = stars;
	}
	
	/**
	 * sorts reviews
	 */
	public void sortReview() {

	}

  /**
	 * writes the reviews for a listing
	 * @return
	 */

	// these three functions should be moved to user class

	public String writeReview() {
		return null;
	}
	/**
	 * deletes a listing
	 */
	public void deleteReview() {

	}
	/**
	 * edits a listing
	 * @return
	 */
	public String editReview() {
		return null;
	}

	public static Venue getVenueReview(Integer i) {
		// TODO Auto-generated method stub
		return null;
	}
	public String toString() {
		return "Stars: " + stars + "\nReviewer: " + reviewer.getUsername() +"\nReview: " + review; 

	}
}
