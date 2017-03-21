public class CircularSuffixArray
{
	private String s;
	private CircularSuffix[] sorted;
	private int[] index;
	private static final int R = 256;

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

		// apply LSD sort
		sort(sorted);

		index = new int[sorted.length];
		// process for index look up.
		for (int i = 0; i < sorted.length; i++)
			index[sorted[i].offset()] = i;
	}

	private static void sort(CircularSuffix[] a)
	{
		final int w = a[0].length();
		final int n = a.length;
		CircularSuffix[] aux = new CircularSuffix[a.length];
		// int[] count = new int[R + 1];
		for (int d = w - 1; d >= 0; d--)
		{
			// compute frequency counts
			int[] count = new int[R + 1];
			for (int i = 0; i < n; i++)
				count[a[i].digit(d) + 1]++;

			// compute cumulates
			for (int r = 0; r < R; r++)
				count[r + 1] += count[r];

			// move data
			for (int i = 0; i < n; i++)
				aux[count[a[i].digit(d)]++] = a[i];

			//Swap aux and a for next round
			CircularSuffix[] tmp = a;
			a = aux;
			aux = tmp;
		}
		
		//After even number of iterations, a contains the sorted version
		//Therefore there is no need to copy back
		if((w & 1) == 0)
			return;
		
		System.arraycopy(a, 0, aux, 0, a.length);
	}

	public int length() // length of s
	{
		return s.length();
	}

	public int index(int i) // returns index of ith sorted suffix
	{
		return index[i];
	}
	
	public void print()
	{
		for(CircularSuffix cs : sorted)
		{
			System.out.println(cs.toString());
		}
	}
}