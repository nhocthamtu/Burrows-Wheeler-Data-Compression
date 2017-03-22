import static org.junit.Assert.*;

import org.junit.Test;

public class CircularSuffixArrayTest
{

	@Test
	public void test()
	{
		CircularSuffixArray csa = new CircularSuffixArray("ABRACADABRA!");
//		csa.print();
		int[] index = { 11, 10, 7, 0, 3, 5, 8, 1, 4, 6, 9, 2 };
		for (int i = 0; i < index.length; i++)
			assertEquals(index[i], csa.index(i));
		
	}
}
