
public class Veteran extends UserDecorator{
	
	public Veteran(User user) {
		this.user = user;
		discount = 10;
	}
	
	public void addDiscount() {
		//for test purposes
		discount = 10;
	}

	public double getDiscount() {
		return discount;
	}
	
	public void setDiscount(double disc) {
		discount = disc;
	}
}
