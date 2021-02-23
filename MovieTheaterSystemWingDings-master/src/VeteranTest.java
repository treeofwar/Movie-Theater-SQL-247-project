package src;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import junit.framework.TestCase;
class VeteranTest extends TestCase{

	UserDecorator veterantest = new Veteran();
	@Test
	void test() {
		fail("Not yet implemented");
	}
	
	public void testGetDiscount() {
		double disc = 10;
		double produced = veterantest.getDiscount();
		assertEquals(disc, produced);
	}
	
	public void testSetDiscount() {
		veterantest.setDiscount(12);
		double test = veterantest.getDiscount();
		double expected = 12;
		assertEquals(test, expected);
	}
}
