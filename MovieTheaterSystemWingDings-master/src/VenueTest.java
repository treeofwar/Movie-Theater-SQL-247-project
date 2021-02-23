package src;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VenueTest {
	Venue venue = new Venue(9 ,"Place", "Concert Hall", "Columbia");

	@Test
	void test() {
		fail("Not yet implemented");
	}

	public void testGetName() {
		String nametest = "Place";
		String produced = venue.getName();
		assertEquals(nametest, produced);
	}
	
	public void testGetID() {
		Integer test = venue.getID();
		Integer expected = 9;
		assertEquals(test, expected);
	}
	
	public void testGetType() {
		String test = venue.getType();
		String expected = "Concert Hall";
		assertEquals(test, expected);
	}
	
	public void testGetLocation() {
		String test = venue.getLocation();
		String expected = "Columbia";
		assertEquals(test, expected);
	}
}
