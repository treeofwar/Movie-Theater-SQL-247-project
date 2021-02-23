package src;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import junit.framework.TestCase;

public class DisabledTest extends TestCase{
	
	User testUser = new User();
	UserDecorator disabletest = new Disabled(testUser);
	
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
	
	@Test
	public void testGetDiscount() {
		double disc = 6;
		double produced = disabletest.getDiscount();
		assertEquals(disc, produced);
	}
	
	@Test
	public void testSetDiscount() {
		double disc = 6;
		disabletest.setDiscount(disc);
		double produced = disabletest.getDiscount();
		assertEquals(disc, produced);
	}
}
