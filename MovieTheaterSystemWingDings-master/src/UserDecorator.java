
public abstract class UserDecorator extends Guest{
	protected Guest user;
	protected double discount;
	public abstract void addDiscount();
}
