import java.io.IOException;

import edu.princeton.cs.algs4.StdIn;

public class MoveToFront
{
	// apply move-to-front encoding, reading from standard input and writing to
	// standard output

	public static void encode() throws IllegalStateException
	{
		// Simulate a link list
		int[] next = new int[256];
		int head = 0;
		for (int i = 0; i < next.length; i++)
			next[i] = i + 1;

		int c = 0;

		int written = 0;

		int read = 0;
		c = StdIn.readByte();
		while (c != -1)
		{
			if (read != written)
				throw new IllegalStateException("Somehow encoding failed!");

			read++;
			if (c == head)
			{
				written++;
				System.out.write(0);
			} else
			{
				int index = 1;
				int cur = head;
				while (next[cur] < next.length)
				{
					if (next[cur] == c)
					{
						written++;
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
				if (next[cur] >= next.length)
					throw new IllegalStateException("Unable to find the character in Link Lis!");
			}
			c = StdIn.readByte();
		}
		if (read != written)
			throw new IllegalStateException("Sizes of files are unequal! IN: " + read + " OUT: " + written);
		System.err.println("READ: " + read);
	}

	// apply move-to-front decoding, reading from standard input and writing to
	// standard output
	public static void decode() throws IllegalStateException
	{
		// Simulate a link list, this is the table that stores the next pointers
		int[] next = new int[256];
		// head of the link list
		int head = 0;

		// initialize the next pointers
		for (int i = 0; i < next.length; i++)
			next[i] = i + 1;

		int written = 0;

		int c = 0;

		int read = 0;
		c = StdIn.readByte();
		while (c != -1)
		{
			read++;
			int cur = head;
			int prev = -1;
			for (int i = 0; i < c; i++)
			{
				prev = cur;
				cur = next[cur];
			}
			written++;
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
			c = StdIn.readByte();
		}
		if (read != written)
			throw new IllegalStateException("Sizes of files are unequal! IN: " + read + " OUT: " + written);
		System.err.println("READ: " + read);
	}

	// if args[0] is '-', apply move-to-front encoding
	// if args[0] is '+', apply move-to-front decoding
	public static void main(String[] args) throws IOException
	{
		if (args.length != 1)
		{
			System.out.println("usage: MoveToFront -/+ [InFile] [OutFile]d");
			return;
		}

		if (args[0].equals("-"))
			encode();
		else if (args[0].equals("+"))
			decode();
		System.out.close();
	}
}