import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {

	private static int n, blankSize, blankScore, answer = 0;
	private static boolean[] visited;
	private static int[] sequence;
	private static List<Integer> blankNumbers = new ArrayList<>();
	private static List<Integer> blankIdxs = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		int score = Integer.parseInt(st.nextToken());

		sequence = new int[n];
		boolean[] isExist = new boolean[n + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			sequence[i] = Integer.parseInt(st.nextToken());
			isExist[sequence[i]] = true;
			if (sequence[i] == 0) {
				blankIdxs.add(i);
			}
		}

		// 빠진 숫자 구하기
		blankSize = blankIdxs.size();
		for (int i = 1; i <= n; i++) {
			if (!isExist[i]) {
				blankNumbers.add(i);
			}
		}

		//basic Score 계산
		int basicScore = 0;
		for (int i = 0; i < n - 1; i++) {
			if (sequence[i] > 0) {
				for (int j = i + 1; j < n; j++) {
					if (sequence[i] < sequence[j]) {
						basicScore++;
					}
				}
			}
		}
		blankScore = score - basicScore;

		visited = new boolean[blankSize];
		combination(new ArrayList<>());
		System.out.println(answer);
	}

	private static void combination(List<Integer> combList) {
		if (combList.size() == blankSize) {
			int score = calculateBlankScore(combList);
			if (score == blankScore) {
				answer++;
			}
			return;
		}
		for (int i = 0; i < blankSize; i++) {
			if (!visited[i]) {
				visited[i] = true;
				combList.add(blankNumbers.get(i));
				combination(combList);
				combList.remove(combList.size() - 1);
				visited[i] = false;
			}
		}
	}

	private static int calculateBlankScore(List<Integer> combList) {
		int score = 0;
		for (int idx = 0; idx < n; idx++) {
			if (!isBlank(idx)) {
				for (int i = 0; i < blankSize; i++) {
					if (blankIdxs.get(i) > idx && combList.get(i) > sequence[idx]) {
						score++;
					} else if (blankIdxs.get(i) < idx && combList.get(i) < sequence[idx]) {
						score++;
					}
				}
			}
		}

		for (int i = 0; i < blankSize - 1; i++) {
			for (int j = i + 1; j < blankSize; j++) {
				if (combList.get(i) < combList.get(j)) {
					score++;
				}
			}
		}

		return score;
	}

	private static boolean isBlank(int idx) {
		for (int i = 0; i < blankSize; i++) {
			if (idx == blankIdxs.get(i)) {
				return true;
			}
		}
		return false;
	}
}
