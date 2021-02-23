package src;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserTest {

	User testuser = new User(12, "chris", "nix", "11/16/1998", "noemail@gmail.com", "ca",
			"password", 20, true);
	
	@Test
	void test() {
		fail("Not yet implemented");
	}

	public void testGetFirstName() {
		String name = "chris";
		String producedName = testuser.getFirstName();
		assertEquals(name, producedName);
	}
	
	public void testGetLastName() {
		String name = "nix";
		String producedName = testuser.getLastName();
		assertEquals(name, producedName);
	}
	
	public void testGetID() {
		Integer test = testuser.getID();
		Integer expected = 12;
		assertEquals(test, expected);
	}
	
	public void testGetDateOfBirth() {
		String test = testuser.getDateOfBirth();
		String expected = "11/16/1998";
		assertEquals(test, expected);
	}
	
	public void testGetEmail() {
		String test = testuser.getEmail();
		String expected = "noemail@gmail.com";
		assertEquals(test, expected);
	}
	
	public void testGetUsername() {
		String test = testuser.getUsername();
		String expected = "ca";
		assertEquals(test, expected);
	}
	
	public void testGetPassword() {
		String test = testuser.getPassword();
		String expected = "password";
		assertEquals(test, expected);
	}
	
	public void testGetAge() {
		int test = testuser.getAge();
		int expected = 20;
		assertEquals(test, expected);
	}
}
