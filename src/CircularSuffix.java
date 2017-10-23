
public class CircularSuffix
{
	// Length of the string
	private final int N;
	// Actual string
	private byte[] data;

	// Number of characters the string shifted to the left
	private final int j;

	// String representation of this suffix/

	private String s;

	public CircularSuffix(byte[] data, int offset)
	{
		this.N = data.length;
		this.data = data;
		this.j = offset;
	}

	public int digit(int index)
	{
		return byteAt(index) & 0xFF;
	}

	private byte byteAt(int index)
	{
		return data[(index + j) % N];
	}

	public int offset()
	{
		return j;
	}

	public int length()
	{
		return N;
	}

	@Override
	public String toString()
	{
		if (s == null)
		{
			byte[] arr = new byte[N];
			for (int i = 0; i < arr.length; i++)
				arr[i] = byteAt(i);

			s = new String(arr);
		}
		return s;
	}
}
