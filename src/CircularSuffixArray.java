public class CircularSuffixArray
{
	private String s;
	private CircularSuffix[] sorted;
	// private int[] index;

	public CircularSuffixArray(String s) // circular suffix array of s
	{
		if (s == null)
			throw new NullPointerException();

		this.s = s;

		byte[] data = s.getBytes();

		// Fill the Array with all possible circular suffixes.
		sorted = new CircularSuffix[s.length()];
		for (int i = 0; i < sorted.length; i++)
			sorted[i] = new CircularSuffix(data, i);

		// apply MSD sort
		new CircularSuffixArraySorter(sorted).sort();

		// index = new int[sorted.length];
		// // process for index look up.
		// for (int i = 0; i < sorted.length; i++)
		// index[i] = sorted[i].offset();
	}

	public int length() // length of s
	{
		return s.length();
	}

	public int index(int i) // returns index of ith sorted suffix
	{
		return sorted[i].offset();
	}

	// public void print()
	// {
	// for(CircularSuffix cs : sorted)
	// {
	// System.out.println(cs.toString());
	// }
	// }
}