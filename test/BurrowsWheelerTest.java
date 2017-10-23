import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.junit.Test;

public class BurrowsWheelerTest
{

	@Test
	public void testTransform() throws IOException
	{
		InputStream stdin = System.in;
		PrintStream stdout = System.out;

		String str = "ABRACADABRA!";

		InputStream is = new ByteArrayInputStream(str.getBytes(StandardCharsets.US_ASCII));
		ByteArrayOutputStream os = new ByteArrayOutputStream(str.length());
		System.setIn(is);
		System.setOut(new PrintStream(os));

		BurrowsWheeler.encode();
		System.setIn(stdin);
		System.setOut(stdout);

		byte[] solution = { 0x00, 0x00, 0x00, 0x03, 0x41, 0x52, 0x44, 0x21, 0x52, 0x43, 0x41, 0x41, 0x41, 0x41, 0x42,
				0x42 };

		assertTrue("Different from solution arr!", Arrays.equals(solution, os.toByteArray()));
	}
	
	@Test
	public void testInverseTransform() throws IOException
	{
		InputStream stdin = System.in;
		PrintStream stdout = System.out;

		byte[] transformed = { 0x00, 0x00, 0x00, 0x03, 0x41, 0x52, 0x44, 0x21, 0x52, 0x43, 0x41, 0x41, 0x41, 0x41, 0x42,
				0x42 };
		String str = "ABRACADABRA!";

		InputStream is = new ByteArrayInputStream(transformed);
		ByteArrayOutputStream os = new ByteArrayOutputStream(str.length());
		System.setIn(is);
		System.setOut(new PrintStream(os));

		BurrowsWheeler.decode();
		
		System.setIn(stdin);
		System.setOut(stdout);


		assertTrue("Different from solution arr!", Arrays.equals(str.getBytes(), os.toByteArray()));
	}

}
