import java.io.IOException;

public class BurrowsWheeler
{

	public static int getFirst(CircularSuffixArray csa)
	{
		for (int i = 0; i < csa.length(); i++)
		{
			if (csa.index(i) == 0)
				return i;
		}

		return -1;
	}
	
	// apply Burrows-Wheeler transform, reading from standard input and writing
	// to standard output
	public static void transform() throws IOException
	{
		int len = System.in.available();
		byte[] buf = new byte[len];
		System.in.read(buf);
		CircularSuffixArray csa = new CircularSuffixArray(new String(buf));
		int first = getFirst(csa);

		// Write first to stdout, using big endian
		for (int i = 24; i >= 0; i -= 8)
			System.out.write((first >> i) & 0xFF);
		
		//Write the end of each element in sorted suffix
		for (int i = 0; i < len; i++)
			System.out.write(buf[csa.index(i)]);
	}

	// apply Burrows-Wheeler inverse transform, reading from standard input and
	// writing to standard output
	public static void inverseTransform()
	{
		
	}

	// if args[0] is '-', apply Burrows-Wheeler transform
	// if args[0] is '+', apply Burrows-Wheeler inverse transform
	public static void main(String[] args) throws IOException
	{
		if (args[0].equals("-"))
			transform();
		else if (args[0].equals("+"))
			inverseTransform();
	}
}