import java.awt.*;
import java.sql.SQLOutput;
import java.time.Month;
import java.util.*;
import java.io.*;
import java.util.List;

class Main {

	private static int n, a, b, count;
	private static boolean isComplete;
	private static boolean[] visited;
	private static List<Integer>[] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());

		graph = new ArrayList[n+1];
		for (int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());

			graph[node1].add(node2);
			graph[node2].add(node1);
		}

		visited = new boolean[n+1];
		visited[a] = true;
		visited[b] = true;
		for (int start: graph[a]) {
			count = 0;
			if (start == b){
				break;
			}
			bfs(start);
			if (isComplete) {
				break;
			}
		}
		System.out.println(count);
	}

	private static void bfs(int start) {
		Deque<Integer> stack = new ArrayDeque<>();
		stack.add(start);
		visited[start] = true;
		count++;

		while (!stack.isEmpty()) {
			int node = stack.removeLast();
			for (int nextNode : graph[node]) {
				if (nextNode == b){
					isComplete = true;
					continue;
				}
				if (!visited[nextNode]){
					count++;
					visited[nextNode] = true;
					stack.addLast(nextNode);
				}
			}
		}
	}
}

// n, a, b 입력
// list[] graph 입력
// a와 연결 된 노드 for 문으로 bfs 탐색, count 값 설정
	// bfs
	// node == a 면 제외
	// node == b이면 isComplete true로 변경

// a 와 b 사이에 있는 숫자의 개수를 구하면 됨
