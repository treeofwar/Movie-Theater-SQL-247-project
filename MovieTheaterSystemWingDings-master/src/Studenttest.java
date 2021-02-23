package src;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import junit.framework.TestCase;
class Studenttest extends TestCase{

	UserDecorator studenttest = new Student();
	@Test
	
	void test() {
		fail("Not yet implemented");
	}
	
	public void testGetDiscount() {
		double disc = 10;
		double produced = studenttest.getDiscount();
		assertEquals(disc, produced);
	}
	
	@Test
	public void testSetDiscount() {
		studenttest.setDiscount(4);
		double test = studenttest.getDiscount();
		double expected = 4;
		assertEquals(test, expected);
	}
}
