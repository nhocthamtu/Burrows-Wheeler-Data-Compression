
public class CircularSuffixArraySorter
{
	// private static final int BITS_PER_BYTE = 8;
	// private static final int BITS_PER_INT  =  32;   // each Java int is 32 bits 
    private static final int R             = 256;   // extended ASCII alphabet size
    private static final int CUTOFF        =  15;   // cutoff to insertion sort
    private CircularSuffix[] a;
    
    public CircularSuffixArraySorter(CircularSuffix[] a)
    {
    	this.a = a;
    }
    
	public void sort()
	{
		int n = a.length;
		CircularSuffix[] aux = new CircularSuffix[n];
		sort(a, 0, n - 1, 0, aux);
	}
	
	public static void lsdSort(CircularSuffix[] a)
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

			// Swap aux and a for next round
			CircularSuffix[] tmp = a;
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
	private int charAt(CircularSuffix s, int d)
	{
		assert d >= 0 && d <= s.length();
		if (d == s.length())
			return -1;
		return s.digit(d);
	}

	// sort from a[lo] to a[hi], starting at the dth character
	private void sort(CircularSuffix[] a, int lo, int hi, int d, CircularSuffix[] aux)
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
	private static void insertion(CircularSuffix[] a, int lo, int hi, int d)
	{
		for (int i = lo; i <= hi; i++)
			for (int j = i; j > lo && less(a[j], a[j - 1], d); j--)
				exch(a, j, j - 1);
	}

	// exchange a[i] and a[j]
	private static<T> void exch(T[] a, int i, int j)
	{
		T temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	// is v less than w, starting at character d
	private static boolean less(CircularSuffix v, CircularSuffix w, int d)
	{
		// assert v.substring(0, d).equals(w.substring(0, d));
		for (int i = d; i < Math.min(v.length(), w.length()); i++)
		{
			if (v.digit(i) < w.digit(i))
				return true;
			if (v.digit(i) > w.digit(i))
				return false;
		}
		return v.length() < w.length();
	}
}
