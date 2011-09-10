package hash;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class HashCalculatorTest {
	
	@Test
	public void shouldReturnEqualValuesForEqualEntries() throws Exception {
		HashCalculator calculator = new HashCalculator("abc");
		String hash = calculator.getValue();
		
		HashCalculator otherCalculator = new HashCalculator("abc");
		String otherHash = otherCalculator.getValue();
		assertEquals(hash, otherHash);
	}
	
	@Test
	public void shouldReturnDifferentValuesForDifferentEntries() throws Exception {
		HashCalculator calculator = new HashCalculator("123");
		String hash = calculator.getValue();
		
		HashCalculator otherCalculator = new HashCalculator("122");
		String otherHash = otherCalculator.getValue();
		
		assertTrue(!hash.equals(otherHash));
	}
	
	@Test
	public void shouldNotGenerate255LongerString() throws Exception {
		HashCalculator calculator = new HashCalculator("lalalallalala");
		String hash = calculator.getValue();
		
		assertTrue(hash.length() < 255);
	}

}
