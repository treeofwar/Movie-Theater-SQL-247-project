package src;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Showtest {

	Show showtest = new Show(9, "Frozen 2", "its empty", 15, 9);

	@Test
	void test() {
		fail("Not yet implemented");
	}
	
	public void testGetShowName() {
		String nametest = "Frozen 2";
		String produced = showtest.getName();
		assertEquals(nametest, produced);
	}

	public void testGetShowID() {
		int test = showtest.getShowID();
		int expected = 9;
		assertEquals(test, expected);
	}
	
	public void testGetRows() {
		int test = showtest.getVenueID();
		int expected = 9;
		assertEquals(test, expected);
	}
	
	public void testGetPrice() {
		double test = showtest.getPrice();
		double expected = 15;
		assertEquals(test, expected);
	}
	
	public void testGetDescription() {
		String test = showtest.getDescription();
		String expected = "its empty";
		assertEquals(test, expected);
	}
	
	public void testSetName() {
		showtest.setName("bob");
		String test = showtest.getName();
		String expected = "bob";
		assertEquals(test, expected);
	}
	
	public void testSetPrice() {
		showtest.setPrice(12);
		double test = showtest.getPrice();
		double expected = 12;
		assertEquals(test, expected);
	}
	
	public void testSetDescription() {
		showtest.setDescription("bro what");
		String test = showtest.getDescription();
		String expected = "bro what";
		assertEquals(test, expected);
	}
}
