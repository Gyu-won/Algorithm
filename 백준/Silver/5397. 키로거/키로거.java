import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int numberOfTest = Integer.parseInt(br.readLine());

		StringBuilder testResult = new StringBuilder();
		for (int currentTest = 0; currentTest < numberOfTest; currentTest++) {
			List<Character> passwordKeys = guessPasswordKeys(br.readLine());
			passwordKeys.forEach(testResult::append);
			testResult.append("\n");
		}
		System.out.println(testResult.toString().trim());
	}

	private static List<Character> guessPasswordKeys(String keyboardInput) {
		List<Character> password = new LinkedList<>();
		int cursor = 0;
		for (char inputKey : keyboardInput.toCharArray()) {
			if (inputKey == '<' && cursor > 0) {
				cursor--;
			} else if (inputKey == '>' && cursor < password.size()) {
				cursor++;
			} else if (inputKey == '-' && cursor > 0) {
				password.remove(cursor - 1);
				cursor--;
			} else if ((inputKey >= 65 && inputKey <= 90) || (inputKey >= 97 && inputKey <= 122) || (inputKey >= '0'
				&& inputKey <= '9')) {
				password.add(cursor, inputKey);
				cursor++;
			}
		}
		return password;
	}
}

// O(L)
// <가 입력되면 cursor를 null이 아니면 앞으로
// >가 입력되면 cursor를 null이 아니면 뒤로
// 문자 입력시 새로 만들고 cursor의 next 연결 후 cursor 이동
// -가 입력되면 cursor.prev의 next를 cursor.next로 넣고 cursor는 cursor.prev로 이동
// 뒤에 배열을 다떙겨야하기 때문에 연결리스트로 구현
