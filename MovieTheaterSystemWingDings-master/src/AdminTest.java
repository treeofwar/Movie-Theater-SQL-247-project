package src;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import junit.framework.TestCase;

public class AdminTest extends TestCase{
	
	Admin admin = new Admin(12, "devon", "harant", "11/16/1998", "devharant@gmail.com", "dharant", "password", 21, true);
	
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
	public void testGetName() {
		String name = "devon harant";
		String producedName = admin.getName();
		assertEquals(name, producedName);
	}
}
