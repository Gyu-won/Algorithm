import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int k = Integer.parseInt(br.readLine());

		StringTokenizer st;
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());

			int v = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			int[] visited = new int[v + 1];
			List<List<Integer>> edgeList = new ArrayList<>();
			for (int j = 0; j <= v; j++) {
				visited[j] = -1;
				edgeList.add(new ArrayList<>());
			}

			for (int j = 0; j < e; j++) {
				st = new StringTokenizer(br.readLine());

				int node1 = Integer.parseInt(st.nextToken());
				int node2 = Integer.parseInt(st.nextToken());

				edgeList.get(node1).add(node2);
				edgeList.get(node2).add(node1);
			}

			boolean isBipartiteGraph = isBipatiteGraph(edgeList, visited);
			if (isBipartiteGraph) {
				result.append("YES");
			} else {
				result.append("NO");
			}
			result.append("\n");
		}

		System.out.println(result.toString().trim());
	}

	private static boolean isBipatiteGraph(List<List<Integer>> edgeList, int[] visited) {
		int numberOfNode = edgeList.size() - 1;

		Deque<Integer> queue = new ArrayDeque<>();
		for (int nodeNumber = 1; nodeNumber <= numberOfNode; nodeNumber++) {
			if (visited[nodeNumber] == -1) {
				visited[nodeNumber] = 0;
				queue.addLast(nodeNumber);

				while (!queue.isEmpty()) {
					int node = queue.removeFirst();
					int nextSetNumber = (visited[node] + 1) % 2;

					for (int nextNode : edgeList.get(node)) {
						if (visited[nextNode] == -1) {
							visited[nextNode] = nextSetNumber;
							queue.addLast(nextNode);
						} else {
							if (visited[nextNode] != nextSetNumber) {
								return false;
							}
						}
					}
				}
			}
		}
		return true;
	}
}

// 입력
// bfs로 탐색하면서 visited에 1, 2를 넣기
// 0이면 그냥 넣고, 1이거나 2면 확인 후 다르면 false 리턴, 같으면 pass
// O(K * (V+E))
