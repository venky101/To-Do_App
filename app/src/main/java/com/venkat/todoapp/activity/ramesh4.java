// Java program to demonstrate
// Stream.iterate method

import java.util.stream.Stream;
public class GFG {

	public static void main(String[] args)
	{

		// create a stream using iterate
		Stream<Integer> stream
			= Stream.iterate(1,
							i -> i <= 20, i -> i * 2);

		// print Values
		stream.forEach(System.out::println);
	}
}
