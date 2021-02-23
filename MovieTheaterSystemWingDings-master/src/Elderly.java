
public class Elderly extends UserDecorator{
	
	public Elderly(User user) {
		this.user = user;
	}
	
	public void addDiscount() {
		//for test purposes
		discount = 8;
	}
	
	public double getDiscount() {
		return discount;
	}
	
	public void setDiscount(double disc) {
		discount = disc;
	}
}
