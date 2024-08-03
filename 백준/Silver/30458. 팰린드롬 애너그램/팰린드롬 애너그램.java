import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int textLength = Integer.parseInt(br.readLine());
		String text = br.readLine();

		System.out.println(canPalidrom(textLength, text));
	}

	private static String canPalidrom(int textLength, String text) {
		boolean[] isEven = calculateIsEven(textLength, text);
		if (hasTrue(isEven)) {
			return "No";
		}
		return "Yes";
	}

	private static boolean[] calculateIsEven(int textLength, String text) {
		boolean[] isEven = new boolean[26];
		for (int index = 0; index < textLength / 2; index++) {
			int charIndex = text.charAt(index) - 'a';
			isEven[charIndex] = !isEven[charIndex];
		}
		for (int index = (textLength + 1) / 2; index < textLength; index++) {
			int charIndex = text.charAt(index) - 'a';
			isEven[charIndex] = !isEven[charIndex];
		}
		return isEven;
	}

	private static boolean hasTrue(boolean[] isEven) {
		for (int index = 0; index < isEven.length; index++) {
			if (isEven[index]) {
				return true;
			}
		}
		return false;
	}
}

// 문자열에 등장하는 문자 개수가 중간 문자 제외 짝수개면 yes 아니면 no
// 문자열 돌면서 중간것 제외 개수 채우기: O(N)
// 홀수가 있으면 no, 없으면 yes: O(26)
