
/**
 * Venue abstract class to be implemented by children
 * forcing characteristics and methods to be used
 * by the children
 * @author WingDings
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Venue{
	
	protected int venueid;
	protected String name;
	protected String location;
	protected String type;
	public Map<Integer, Show> shows = new HashMap<Integer, Show>();
	protected Map<Integer, Review> reviews = new HashMap<Integer, Review>();
	private Map<Integer, Food> Food = new HashMap<Integer, Food>();
	protected ShowFactory factory;
	
	public Venue(Integer venueid,String name, String type, String location) {
		this.venueid = venueid;
		this.name = name;
		this.location = location;
		this.type = type;
	}
	
	public Integer getID() {
		return venueid;
	}
	
	public String getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}
	public String getLocation() {
		return location;
	}
	public Review getReviews() {
		//TODO get specific review
		return null;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public void printShows() {
		for(Map.Entry<Integer, Show> entry : shows.entrySet()) {
			System.out.println(entry.getValue().toStringShort());
	
		}
	}
	
	public void printShow(Show show) {
		System.out.println(show.toStringShort());
	}
	
	public void printFood() {
		
	}
	
	public void addingReview( int id,String review, int rating) {
		User user = null;
		reviews.put(id,new Review(rating, review, user));
	}
	
	
	public Review getVenueReview(int id) {
		return reviews.get(id);
	}
	/**
	 * adds a Review to the venue
	 * @param stars, stars given the venue
	 * @param review, review itself
	 */
	

	public String toString() {
		return "\n" + "ID:" + venueid + "\nName: " + name + "\nType: " + type + "\nLocation: " + location + "\nReviews:\n" + this.getReviews(); 
  }
}