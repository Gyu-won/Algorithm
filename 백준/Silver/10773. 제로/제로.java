import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int numberOfWriting = Integer.parseInt(br.readLine());

		Deque<Integer> numberStack = new ArrayDeque<>();
		for (int currentWriting = 0; currentWriting < numberOfWriting; currentWriting++) {
			int number = Integer.parseInt(br.readLine());
			writeNumber(number, numberStack);
		}

		System.out.println(calculateSum(numberStack));
	}

	private static void writeNumber(int number, Deque<Integer> numberStack) {
		if (number == 0) {
			numberStack.removeLast();
		} else {
			numberStack.addLast(number);
		}
	}

	private static int calculateSum(Deque<Integer> numberStack) {
		return numberStack.stream().mapToInt(Integer::intValue).sum();
	}
}
