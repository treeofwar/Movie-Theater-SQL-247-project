
public class Disabled extends UserDecorator{
	
	public Disabled(User user) {
		this.user = user;
	}
	
	public void addDiscount() {
		//for test purposes
		discount = 6;
	}
	
	public double getDiscount() {
		return discount;
	}
	
	public void setDiscount(double disc) {
		discount = disc;
	}
}
