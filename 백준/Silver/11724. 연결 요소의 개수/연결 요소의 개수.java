import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	private static boolean[] visited;
	private static List<List<Integer>> graph;

	public static void main(String[] args) throws IOException {
		// n, m 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int numberOfNode = Integer.parseInt(st.nextToken());
		int numberOfEdge = Integer.parseInt(st.nextToken());

		initializeGraph(numberOfNode);
		
		int x, y;
		for (int i = 0; i < numberOfEdge; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			graph.get(x).add(y);
			graph.get(y).add(x);
		}

		int connectedComponents = countConnectComponents(numberOfNode);
		System.out.println(connectedComponents);
	}

	private static void initializeGraph(int numberOfNode) {
		visited = new boolean[numberOfNode + 1];
		graph = new ArrayList<>();
		for (int i = 0; i <= numberOfNode; i++) {
			graph.add(new ArrayList<>());
		}
	}

	private static int countConnectComponents(int numberOfNode) {
		int connectedComponents = 0;
		for (int i = 1; i <= numberOfNode; i++) {
			if (!visited[i]) {
				connectedComponents++;
				dfs(i);
			}
		}
		return connectedComponents;
	}

	private static void dfs(int node) {
		visited[node] = true;
		for (int nextNode : graph.get(node)) {
			if (!visited[nextNode]) {
				dfs(nextNode);
			}
		}
	}
}
