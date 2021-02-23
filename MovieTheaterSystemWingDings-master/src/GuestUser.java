
public class GuestUser extends Guest {
private double discount;

	public GuestUser() {
		//TODO create discount
		this.discount = 0.0;
	}

	public GuestUser(String name, String dateOfBirth, String email, String userName, String password, int age, double discount) {
		super();
		this.discount = discount;
	}
	public double enterDiscount() {
		//no discount on guest users
		return 0.0;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public void createAccount() {
		//TODO
	}

}
