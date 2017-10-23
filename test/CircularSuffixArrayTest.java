import static org.junit.Assert.*;

import org.junit.Test;

import edu.princeton.cs.algs4.SuffixArrayX;

public class CircularSuffixArrayTest
{

	@Test
	public void test()
	{
		String str = "ABRACADABRA!";
		CircularSuffixArray csa = new CircularSuffixArray(str);
		int[] index = { 11, 10, 7, 0, 3, 5, 8, 1, 4, 6, 9, 2 };
		for (int i = 0; i < index.length; i++)
			assertEquals(index[i], csa.index(i));
		SuffixArrayX csax = new SuffixArrayX(str);
		for (int i = 0; i < index.length; i++)
			assertEquals(index[i], csax.index(i));
	}
}
