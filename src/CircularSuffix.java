
public class CircularSuffix
{
	private final int N;
	private char[] data;
	int j;
	
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
}
