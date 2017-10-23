import edu.princeton.cs.algs4.SuffixArrayX;

public class CircularSuffixArray
{
	private final SuffixArrayX sax;
	// private int[] index;

	public CircularSuffixArray(String data) // circular suffix array of s
	{
		sax = new SuffixArrayX(data);
	}

	public int length() // length of s
	{
		return sax.length();
	}

	public int index(int i) // returns index of ith sorted suffix
	{
		return sax.index(i);
	}
}