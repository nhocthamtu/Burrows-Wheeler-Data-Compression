import static org.junit.Assert.*;

import org.junit.Test;

public class CircularSuffixTest
{
	private final char[] data = "ABRACADABRA!".toCharArray();
	@Test
	public void test()
	{
		CircularSuffix cs = new CircularSuffix(data, 0);
		for(int i = 0; i < data.length; i ++)
		{
			assertEquals(data[i], cs.digit(i));
		}
	}
}
