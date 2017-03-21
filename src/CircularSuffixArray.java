public class CircularSuffixArray
{
	private String s;
	public CircularSuffixArray(String s) // circular suffix array of s
	{
		if(s == null)
			throw new NullPointerException();
		
		this.s = s;
	}

	public int length() // length of s
	{
		return s.length();
	}

	public int index(int i) // returns index of ith sorted suffix
	{
		
	}
}