package src;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Tickettest {
	Tickettest tickettest = new Ticket(null ,"12:00PM", null , null, 6);

	@Test
	void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetTime() {
		String timetest = "12:00PM";
		String produced = tickettest.getTime();
		assertEquals(timetest, produced);
	}
	
	@Test
	public void testSetTime() {
		tickettest.setTime("01:00PM");
		String test = tickettest.getTime();
		String expected = "01:00PM";
		assertEquals(test, expected);
	}
	
	@Test
	public void testGetPrice() {
		double test = tickettest.getPrice();
		double expected = 6;
		assertEquals(test, expected);
	}
	
	@Test
	public void testSetPrice() {
		tickettest.setPrice(8);
		double test = tickettest.testGetPrice();
		double expected = 8;
		assertEquals(test, expected);
	}
}
