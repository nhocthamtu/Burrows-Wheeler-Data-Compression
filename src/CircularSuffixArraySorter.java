
public class CircularSuffixArraySorter
{
	// private static final int BITS_PER_BYTE = 8;
	// private static final int BITS_PER_INT = 32; // each Java int is 32 bits
	private static final int R = 256; // extended ASCII alphabet size
	private static final int CUTOFF = 15; // cutoff to insertion sort
	
	//Index of the suffix, hence shift offset to the left
	private int[] a;
	
	//The actual data to encode
	private byte[] data;
	private final int N;

	public CircularSuffixArraySorter(int[] a, byte[] data)
	{
		this.a = a;
		this.data = data;
		this.N = data.length;
	}

	public void sort()
	{
		int n = a.length;
		int[] aux = new int[n];
		sort(a, 0, n - 1, 0, aux);
	}

	public void lsdSort(int[] a)
	{
		final int w = a.length;
		final int n = a.length;
		int[] aux = new int[n];
		// int[] count = new int[R + 1];
		for (int d = w - 1; d >= 0; d--)
		{
			// compute frequency counts
			int[] count = new int[R + 1];
			for (int i = 0; i < n; i++)
				count[charAt(i, d) + 1]++;

			// compute cumulates
			for (int r = 0; r < R; r++)
				count[r + 1] += count[r];

			// move data
			for (int i = 0; i < n; i++)
				aux[count[charAt(i, d)]++] = a[i];

			// Swap aux and a for next round
			int[] tmp = a;
			a = aux;
			aux = tmp;
		}

		// After even number of iterations, a contains the sorted version
		// Therefore there is no need to copy back
		if ((w & 1) == 0)
			return;

		System.arraycopy(a, 0, aux, 0, a.length);
	}

	// return dth character of s, -1 if d = length of string
	private int charAt(int s, int d)
	{
		assert d >= 0 && d <= N;
		if (d == N)
			return -1;
		return data[(d + s) % N] & 0xFF;
	}

	// sort from a[lo] to a[hi], starting at the dth character
	private void sort(int[] a, int lo, int hi, int d, int[] aux)
	{

		// cutoff to insertion sort for small subarrays
		if (hi <= lo + CUTOFF)
		{
			insertion(a, lo, hi, d);
			return;
		}

		// compute frequency counts
		int[] count = new int[R + 2];
		for (int i = lo; i <= hi; i++)
		{
			int c = charAt(a[i], d);
			count[c + 2]++;
		}

		// transform counts to indicies
		for (int r = 0; r < R + 1; r++)
			count[r + 1] += count[r];

		// distribute
		for (int i = lo; i <= hi; i++)
		{
			int c = charAt(a[i], d);
			aux[count[c + 1]++] = a[i];
		}

		// copy back
		for (int i = lo; i <= hi; i++)
			a[i] = aux[i - lo];

		// recursively sort for each character (excludes sentinel -1)
		for (int r = 0; r < R; r++)
			sort(a, lo + count[r], lo + count[r + 1] - 1, d + 1, aux);
	}

	// insertion sort a[lo..hi], starting at dth character
	private void insertion(int[] a, int lo, int hi, int d)
	{
		for (int i = lo; i <= hi; i++)
			for (int j = i; j > lo && less(a[j], a[j - 1], d); j--)
				exch(a, j, j - 1);
	}

	// exchange a[i] and a[j]
	private static <T> void exch(int[] a, int i, int j)
	{
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	// is v less than w, starting at int d
	private boolean less(int v, int w, int d)
	{
		// assert v.substring(0, d).equals(w.substring(0, d));
		for (int i = d; i < N; i++)
		{
			if (charAt(v, i) < charAt(w, i))
				return true;
			if (charAt(v, i) > charAt(w, i))
				return false;
		}
		return false;
	}
}
