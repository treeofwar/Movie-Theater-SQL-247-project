package src;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import junit.framework.TestCase;

public class Foodtest extends TestCase{
	private Food testFood = new Food(1, "Test Food", 20.99, 1, 1);
	
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

	public void getCost() {
		double testCost = testFood.getCost();
		double expected = 20.99;
		assertEquals(testCost, expected);
	}
	
	public void getFoodID() {
		int test = testFood.getFoodId();
		int expected = 1;
		assertEquals(test, expected);
	}
	
	public void getVenueID() {
		int test = testFood.getVenueId();
		int expected = 1;
		assertEquals(test, expected);
	}
	
	public void testGetName() {
		String test = testFood.getName();
		String expected = "Test Food";
		assertEquals(test, expected);
	}
	
	public void testGetQuantity() {
		int test = testFood.getQUantity();
		int expected = 1;
		assertEquals(test, expected);
	}
	
	public void testSetName() {
		testFood.setName("Food");
		String test = testFood.getName();
		String expected = "Food";
		assertEquals(test, expected);
	}
	
	public void testSetCost() {
		testFood.setCost(15.99);
		double test = testFood.getCost();
		double expected = 15.99;
		assertEquals(test, expected);
	}
	
	public void testSetQuantity() {
		testFood.setQuantity(2);
		int test = testFood.getQuantity();
		int expected = 2;
		assertEquals(test, expected);
	}
}
