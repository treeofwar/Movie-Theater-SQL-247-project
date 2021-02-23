package src;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import junit.framework.TestCase;

public class GuestUsertest extends TestCase{

	private GuestUser testGuestUser = new GuestUser("Bob", "11/16/1998", "nunya@gmail.com", "bob", "password", 21, 7.99);
	
	@BeforeEach
	public void setUp() throws Exception {
		
	}

	@AfterEach
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	public void testGetDiscount() {
		double testDiscount = testGuestUser.getDiscount();
		double expected = 7.99;
		assertEquals(testDiscount, expected);
	}
	
	public void testSetDiscount() {
		testGuestUser.setDiscount(5.99);
		double test = testGuestUser.getDiscount();
		double expected = 5.99;
		assertEquals(test, expected);
	}
}
