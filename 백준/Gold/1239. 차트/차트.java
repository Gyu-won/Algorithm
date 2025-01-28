import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {
	private static int n, maxNumberOfLine = 0;
	private static int[] numberOfDogs;
	private static boolean[] visited;

	public static void main(String[] args) throws IOException {
		// n 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		// int[n] numberOfDogs 입력
		numberOfDogs = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			numberOfDogs[i] = Integer.parseInt(st.nextToken());
		}

		visited = new boolean[n];
		combination(new ArrayList<>());
		System.out.println(maxNumberOfLine);
	}

	private static void combination(List<Integer> chart) {
		if (chart.size() == n) {
			calculateLine(chart);
			return;
		}

		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				visited[i] = true;
				chart.add(numberOfDogs[i]);
				combination(chart);
				chart.remove(chart.size() - 1);
				visited[i] = false;
			}
		}
	}

	private static void calculateLine(List<Integer> chart) {
		int numberOfLine = 0;
		int[] chartSum = new int[n];
		chartSum[0] = chart.get(0);
		for (int i = 1; i < n; i++) {
			chartSum[i] += chartSum[i - 1] + chart.get(i);
		}

		for (int i = 0; i < n; i++) {
			if (chartSum[i] <= 50) {
				int target = chartSum[i] + 50;
				for (int j = i + 1; j < n && chartSum[j] <= target; j++) {
					if (chartSum[j] == target) {
						numberOfLine++;
					}
				}
			}
		}
		maxNumberOfLine = Math.max(maxNumberOfLine, numberOfLine);
	}
}

// 11:46

// 모든 조합: 8!
// 부분합 구하기 O(8)
// for 문 돌며 O(8)
// 부분합[i] + 50 < 100 인 경우 contains 인지 확인: O(8)

// < O(10!)
