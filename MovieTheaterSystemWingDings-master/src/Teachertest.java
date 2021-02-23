package src;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import junit.framework.TestCase;
class Teachertest extends TestCase{

	UserDecorator teachertest = new Teacher();
	@Test
	void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetDiscount() {
		double disc = 10;
		double produced = teachertest.getDiscount();
		assertEquals(disc, produced);
	}

	@Test
	public void testSetDiscount() {
		teachertest.setDiscount(4);
		double test = teachertest.getDiscount();
		double expected = 4;
		assertEquals(test, expected);
	}
}
