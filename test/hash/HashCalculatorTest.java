package hash;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class HashCalculatorTest {
	
	@Test
	public void shouldReturnEqualValuesForEqualEntries() throws Exception {
		String hash = HashCalculator.calculateHash("abc");
		assertEquals(hash, HashCalculator.calculateHash("abc"));
	}
	
	@Test
	public void shouldReturnDifferentValuesForDifferentEntries() throws Exception {
		String hash = HashCalculator.calculateHash("123");
		assertTrue(!hash.equals(HashCalculator.calculateHash("122")));
	}

}
