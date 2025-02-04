import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static boolean isComplete = false;
	private static int n;
	private static int[] averageScore;

	public static void main(String[] args) throws IOException {
		// n 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		// averageScore 입력
		averageScore = new int[n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), ".");
			int integerPart = Integer.parseInt(st.nextToken());
			int fractionalPart = Integer.parseInt(st.nextToken());
			averageScore[i] = integerPart * 1000 + fractionalPart;
		}

		int answer = 0;
		for (int numberOfPerson = 1; numberOfPerson <= 1000; numberOfPerson++) {
			dfs(numberOfPerson, 0);
			if (isComplete) {
				answer = numberOfPerson;
				break;
			}
		}
		System.out.println(answer);
	}

	private static void dfs(int numberOfPerson, int averageScoreIdx) {
		if (averageScoreIdx == n) {
			isComplete = true;
			return;
		}
		for (int sum = 0; sum <= numberOfPerson * 10; sum++) {
			if ((sum * 1000) / numberOfPerson == averageScore[averageScoreIdx]) {
				dfs(numberOfPerson, averageScoreIdx + 1);
			}
		}
	}
}

// 완전 탐색으로 구하기
// O(10012 * 500 * 50)
