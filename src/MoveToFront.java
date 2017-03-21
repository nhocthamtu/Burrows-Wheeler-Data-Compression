import java.io.IOException;

public class MoveToFront
{
	// apply move-to-front encoding, reading from standard input and writing to
	// standard output

	public static void encode() throws IOException
	{
		// Simulate a link list
		int[] next = new int[256];
		int head = 0;
		for (int i = 0; i < next.length; i++)
			next[i] = i + 1;

		int c = 0;
		while ((c = System.in.read()) != -1)
		{
			if (c == head)
			{
				System.out.write(0);
			} else
			{
				int index = 1;
				int cur = head;
				while (next[cur] < next.length)
				{
					if (next[cur] == c)
					{
						System.out.write(index);
						int tmp = next[cur];
						// unlink table[cur] from link list
						next[cur] = next[next[cur]];

						// move that node to the front of the list
						next[tmp] = head;
						head = tmp;
						break;
					}
					cur = next[cur];
					index++;
				}
			}
		}
	}

	// apply move-to-front decoding, reading from standard input and writing to
	// standard output
	public static void decode() throws IOException
	{
		// Simulate a link list, this is the table that stores the next pointers
		int[] next = new int[256];
		// head of the link list
		int head = 0;

		// initialize the next pointers
		for (int i = 0; i < next.length; i++)
			next[i] = i + 1;

		int c = 0;
		while ((c = System.in.read()) != -1)
		{
			int index = 0;
			int cur = head;
			int prev = -1;
			while (index != c)
			{
				prev = cur;
				cur = next[cur];
				index++;
			}
			System.out.write(cur);

			// Don't do anything if the char is already the head
			if (prev == -1)
				continue;

			int tmp = next[prev];
			// unlink table[prev] from link list
			next[prev] = next[tmp];

			// move that node to the front of the list
			next[tmp] = head;
			head = tmp;
		}
	}

	// if args[0] is '-', apply move-to-front encoding
	// if args[0] is '+', apply move-to-front decoding
	public static void main(String[] args) throws IOException
	{
		if (args[0].equals("-"))
			encode();
		else if (args[0].equals("+"))
			decode();
	}
}