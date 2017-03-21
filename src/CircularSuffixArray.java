public class CircularSuffixArray
{
	private String s;
	private CircularSuffix[] sorted;
	private int[] index;

	public CircularSuffixArray(String s) // circular suffix array of s
	{
		if (s == null)
			throw new NullPointerException();

		this.s = s;
		
		char[] data = s.toCharArray();

		sorted = new CircularSuffix[s.length()];
		for (int i = 0; i < sorted.length; i++)
		{
			sorted[i] = new CircularSuffix(data, i);
		}
	}

	public int length() // length of s
	{
		return s.length();
	}

	public int index(int i) // returns index of ith sorted suffix
	{
		return index[i];
	}
}