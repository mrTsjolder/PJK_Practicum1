import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class isValidNameTest {
	
	@Test
	public void testValidNames() {

		assertTrue(File.isValidName("AaBb123456789.-_"));
		
	}

	@Test
	public void testInvalidNames() {
		
		// null
		assertFalse(File.isValidName(null));
		
		// Empty String
		assertFalse(File.isValidName(" "));
		
		// Other symbols than allowed
		assertFalse(File.isValidName("#word"));
		assertFalse(File.isValidName("AaBb123$"));
		
	}
}

