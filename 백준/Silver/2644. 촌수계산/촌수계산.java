import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	private static int n, m;
	private static List<List<Integer>> relation;

	public static void main(String[] argrs) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		int number1 = Integer.parseInt(st.nextToken());
		int number2 = Integer.parseInt(st.nextToken());

		m = Integer.parseInt(br.readLine());
		relation = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			relation.add(new ArrayList<>());
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			relation.get(x).add(y);
			relation.get(y).add(x);
		}

		int result = countRelation(number1, number2);
		System.out.println(result);
	}

	private static int countRelation(int number1, int number2) {
		if (number1 == number2) {
			return 0;
		}
		return bfs(number1, number2);
	}

	private static int bfs(int number1, int number2) {
		boolean[] visited = new boolean[n + 1];
		Deque<Relation> queue = new ArrayDeque<>();
		queue.addLast(new Relation(number1, 0));

		while (!queue.isEmpty()) {
			Relation node = queue.removeFirst();

			for (int number : relation.get(node.number)) {
				if (number == number2) {
					return node.relation + 1;
				}
				if (!visited[number]) {
					visited[number] = true;
					Relation newNode = new Relation(number, node.relation + 1);
					queue.addLast(newNode);
				}
			}
		}
		return -1;
	}

	static class Relation {
		private final int number;
		private final int relation;

		Relation(int number, int relation) {
			this.number = number;
			this.relation = relation;
		}
	}
}

// 10:41

// n 입력
// 두 사람 같으면 0
// bfs로 한층 씩 계산
// O(n * n)
