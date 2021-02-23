package src;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import junit.framework.TestCase;

class ElderlyTest extends TestCase{
	
	User testUser = new User();
	UserDecorator elderlytest = new Elderly(testUser);
	
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
		double disc =  8;
		double produced = elderlytest.getDiscount();
		assertEquals(disc, produced);
	}
	
	@Test
	public void testSetDiscount() {
		double disc = 6;
		elderlytest.setDiscount(disc);
		double produced = elderlytest.getDiscount();
		assertEquals(disc, produced);
	}
}
