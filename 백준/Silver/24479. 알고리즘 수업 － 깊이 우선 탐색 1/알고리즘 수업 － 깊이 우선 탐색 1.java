import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	private static int n, sequence = 0;
	private static int[] visited;
	private static List<Integer>[] edgeList;

	public static void main(String[] args) throws IOException {
		// n, m, r 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int startPoint = Integer.parseInt(st.nextToken());

		// 간선 입력받기
		visited = new int[n + 1];
		edgeList = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			edgeList[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			edgeList[u].add(v);
			edgeList[v].add(u);
		}

		// 간선 정렬
		for (int i = 1; i <= n; i++) {
			Collections.sort(edgeList[i]);
		}

		// 결과 출력
		String result = findDfsSequence(startPoint);
		System.out.println(result);
	}

	private static String findDfsSequence(int startPoint) {
		dfs(startPoint);

		StringBuilder result = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			result.append(visited[i]);
			result.append("\n");
		}
		return result.toString().trim();
	}

	private static void dfs(int node) {
		sequence++;
		visited[node] = sequence;
		for (int nextNode : edgeList[node]) {
			if (visited[nextNode] == 0) {
				dfs(nextNode);
			}
		}
	}
}

// 23:09
