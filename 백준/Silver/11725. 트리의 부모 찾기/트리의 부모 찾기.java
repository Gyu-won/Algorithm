import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public String solution() throws IOException {
        // N을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // N 번 만큼 간선을 입력받아서 연결리스트를 구성한다.
        StringTokenizer st;
        ArrayList<Integer>[] edges = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            edges[node1].add(node2);
            edges[node2].add(node1);
        }

        // 방문 배열을 초기화 한다.
        boolean[] visited = new boolean[N + 1];

        // 부모 배열을 만든다.
        int[] parents = new int[N + 1];

        // 1번을 큐에 넣고 bfs를 시작한다.
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        visited[1] = true;

        // 큐에서 값을 빼면서 연결 된것에 해당 값을 넣어준다.
        while (!queue.isEmpty()) {
            int parent = queue.poll();
            for (int child : edges[parent]) {
                if (!visited[child]) {
                    visited[child] = true;
                    parents[child] = parent;
                    queue.offer(child);
                }
            }
        }

        // 결과를 출력한다.
        StringBuilder result = new StringBuilder();
        for (int i = 2; i <= N; i++) {
            result.append(parents[i]);
            result.append("\n");
        }
        return result.toString().trim();
    }
}

// 알고리즘: 같은 줄에 있는 노드는 부모노드가 같기 때문에 같은 줄단위로 보는 bfs를 사용한다.

// 시간복잡도: O(N)

// 정수 범위
