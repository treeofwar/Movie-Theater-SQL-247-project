
public class Food {

	private int foodId;
	private int venueId;
	private String name;
	private double cost;
	private int quantity;

	public Food(Integer foodId, String name, double cost, int quantity, int venueId) {
		this.venueId = venueId;
		this.setName(name);
		this.setCost(cost);
		this.setQuantity(quantity);
		this.foodId = foodId;
	}
	
	public int getVenueId() {
		return venueId;
	}
	
	public int getFoodId() {
		return foodId;
	}
	
	public String getName() {
		return name;
	}
	public int getQuantity() {
		return quantity;
	}
	public double getCost() {
		return cost;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String toString() {
		return "Name: " + name + "\nQuantity: " + quantity + "\nCost: " + cost;
	}
}
