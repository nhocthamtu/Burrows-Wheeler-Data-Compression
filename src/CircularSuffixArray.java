public class CircularSuffixArray
{
	private int[] sorted;
	// private int[] index;

	public CircularSuffixArray(byte[] data) // circular suffix array of s
	{
		if (data == null)
			throw new NullPointerException();


		// Fill the Array with all possible circular suffixes.
		sorted = new int[data.length];
		for (int i = 0; i < sorted.length; i++)
			sorted[i] = i;

		// apply MSD sort
		new CircularSuffixArraySorter(sorted, data).sort();

		// index = new int[sorted.length];
		// // process for index look up.
		// for (int i = 0; i < sorted.length; i++)
		// index[i] = sorted[i].offset();
	}

	public int length() // length of s
	{
		return sorted.length;
	}

	public int index(int i) // returns index of ith sorted suffix
	{
		return sorted[i];
	}

	public void print()
	{

	}
}