import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.junit.Test;

public class MoveToFrontTest
{

	public void hexdump(byte[] arr)
	{
		for (byte c : arr)
			System.out.printf("%02X ", c);

		System.out.println();
	}

	@Test
	public void testEncode() throws IOException
	{
		InputStream stdin = System.in;
		PrintStream stdout = System.out;

		String str = "ABRACADABRA!";

		InputStream is = new ByteArrayInputStream(str.getBytes(StandardCharsets.US_ASCII));
		ByteArrayOutputStream os = new ByteArrayOutputStream(str.length());
		System.setIn(is);
		System.setOut(new PrintStream(os));

		MoveToFront.encode();
		System.setIn(stdin);
		System.setOut(stdout);

		byte[] solution = { 0x41, 0x42, 0x52, 0x02, 0x44, 0x01, 0x45, 0x01, 0x04, 0x04, 0x02, 0x26 };
		
		assertTrue("Different from solution arr!", Arrays.equals(solution, os.toByteArray()));
	}
	
	@Test
	public void testDecode() throws IOException
	{
		InputStream stdin = System.in;
		PrintStream stdout = System.out;

		String str = "ABRACADABRA!";
		byte[] solution = { 0x41, 0x42, 0x52, 0x02, 0x44, 0x01, 0x45, 0x01, 0x04, 0x04, 0x02, 0x26 };

		InputStream is = new ByteArrayInputStream(solution);
		ByteArrayOutputStream os = new ByteArrayOutputStream(str.length());
		System.setIn(is);
		System.setOut(new PrintStream(os));

		MoveToFront.decode();
		System.setIn(stdin);
		System.setOut(stdout);

		System.out.println(new String(os.toByteArray()));
	}
}
