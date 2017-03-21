
public class CircularSuffix
{
	//Length of the string
	private final int N;
	//Actual string
	private byte[] data;
	
	//Number of characters the string shifted to the left
	private final int j;

	public CircularSuffix(byte[] data, int offset)
	{
		this.N = data.length;
		this.data = data;
		this.j = offset;
	}

	public byte digit(int index)
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
}
