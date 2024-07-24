import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int numberOfTest = Integer.parseInt(br.readLine());

		StringBuilder testResult = new StringBuilder();
		for (int currentTest = 0; currentTest < numberOfTest; currentTest++) {
			Password password = guessPassword(br.readLine());
			testResult.append(password);
			testResult.append("\n");
		}
		System.out.println(testResult.toString().trim());
	}

	private static Password guessPassword(String keyboardInput) {
		Deque<Character> cursorLeft = new ArrayDeque<>();
		Deque<Character> cursorRight = new ArrayDeque<>();

		for (char inputKey : keyboardInput.toCharArray()) {
			if (inputKey == '<' && !cursorLeft.isEmpty()) {
				cursorRight.addLast(cursorLeft.removeLast());
			} else if (inputKey == '>' && !cursorRight.isEmpty()) {
				cursorLeft.addLast(cursorRight.removeLast());
			} else if (inputKey == '-' && !cursorLeft.isEmpty()) {
				cursorLeft.removeLast();
			} else if ((inputKey >= 65 && inputKey <= 90) || (inputKey >= 97 && inputKey <= 122) || (inputKey >= '0'
				&& inputKey <= '9')) {
				cursorLeft.addLast(inputKey);
			}
		}

		return new Password(cursorLeft, cursorRight);
	}

	private static class Password {
		Deque<Character> cursorLeft;
		Deque<Character> cursorRight;

		Password(Deque<Character> cursorLeft, Deque<Character> cursorRight) {
			this.cursorLeft = cursorLeft;
			this.cursorRight = cursorRight;
		}

		@Override
		public String toString() {
			StringBuilder password = new StringBuilder();
			while (!cursorLeft.isEmpty()) {
				password.append(cursorLeft.removeFirst());
			}
			while (!cursorRight.isEmpty()) {
				password.append(cursorRight.removeLast());
			}
			return password.toString();
		}
	}
}
