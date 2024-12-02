import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	private static int n, count = 0;
	private static boolean[] visited;
	private static List<Integer>[] relation;

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		relation = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			relation[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			relation[b].add(a);
		}

		// 개수 세기
		String efficientHacking = calculateEfficientHacking();
		System.out.println(efficientHacking);
	}

	private static String calculateEfficientHacking() {
		int maxCount = 0;
		StringBuilder result = new StringBuilder();
		visited = new boolean[n + 1];

		for (int i = 1; i <= n; i++) {
			dfs(i);
			for (int j = 1; j <= n; j++) {
				visited[j] = false;
			}
			if (count >= maxCount) {
				if (count > maxCount) {
					maxCount = count;
					result.delete(0, result.length());
				}
				result.append(i);
				result.append(" ");
			}
			count = 0;
		}
		return result.toString().trim();
	}

	private static void dfs(int computer) {
		visited[computer] = true;
		count++;
		for (int nextComputer : relation[computer]) {
			if (!visited[nextComputer]) {
				dfs(nextComputer);
			}
		}
	}
	// visited 초기화
	// dfs
	// 최댓값 갱신
}
