
public class CircularSuffix
{
	//Length of the string
	private final int N;
	//Actual string
	private char[] data;
	
	//Number of characters the string shifted to the left
	private final int j;

	public CircularSuffix(char[] data, int offset)
	{
		this.N = data.length;
		this.data = data;
		this.j = offset;
	}

	public char digit(int index)
	{
		return data[N - index - j - 1];
	}

	public int offset()
	{
		return j;
	}
}
