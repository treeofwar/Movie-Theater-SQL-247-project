package src;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Theatertest {

	Theater theatertest = new Theater(9, 8, 6, 6, "12:00PM", "AA:");

	@Test
	void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetTime() {
		String expected = "12:00PM";
		String produced = theatertest.getTime();
		assertEquals(expected, produced);
	}
	
	@Test
	public void testGetShowID() {
		Integer test = theatertest.getShowID();
		Integer expected = 8;
		assertEquals(test, expected);
	}
	
	@Test
	public void testSetTime() {
		theatertest.setTime("01:00PM");
		String test = theatertest.getTime();
		String expected = "01:00PM";
		assertEquals(test, expected);
	}
}
