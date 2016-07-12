package fr.treeptik.test;

//import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class HelloTest {

	@Test
	public static void testIfOk(){
		
		//error
//		throw new NullPointerException();
		
		//failure
//		Assert.fail("Test failure ! ");
		
		assertEquals(3, 5);
		assertFalse(false);
		
	}
	
}
