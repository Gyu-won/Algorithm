import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
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

        // 경로 배열 int[][]를 선언한다.
        int[][] graph = new int[N][N];

        // N번만큼 입력받으며 경로를 세팅한다.
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    graph[i][j] = 1;
                }
            }
        }

        // bfs
        for (int i = 0; i < N; i++) {
            Deque<Integer> queue = new ArrayDeque<>();
            for (int j = 0; j < N; j++) {
                if (graph[i][j] == 1 && i != j) {
                    queue.offer(j);
                }
            }

            while (!queue.isEmpty()) {
                int to = queue.poll();
                for (int j = 0; j < N; j++) {
                    if (graph[to][j] == 1 && graph[i][j] == 0) {
                        graph[i][j] = 1;
                        queue.offer(j);
                    }
                }
            }
        }

        // 결과를 출력한다.
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < N; i++) {
            StringBuilder oneRow = new StringBuilder();
            for (int j = 0; j < N; j++) {
                oneRow.append(graph[i][j]);
                oneRow.append(" ");
            }
            result.append(oneRow.toString().trim());
            result.append("\n");
        }

        return result.toString().trim();
    }
}

// 알고리즘: 모든 노드에서 다른 모든 노드로 가는 경로를 계속 따라가며 구해야 하기 때문에 bfs를 사용한다.

// 시간복잡도: O(N * N * N)

// 정수 범위
