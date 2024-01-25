import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int M;
    private static boolean[] visited;
    StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    public void solution() throws IOException {
        // N과 M을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];

        // permutation을 찾는다.
        permutation(0, "");

        System.out.println(answer.toString().trim());
    }

    private void permutation(int count, String result) {
        if (count == M) {
            answer.append(result);
            answer.append("\n");
            return;
        }
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                permutation(count + 1, result + i + " ");
                visited[i] = false;
            }
        }
    }
}

// 알고리즘: 순차적으로 하나씩 보면서 이미 나온 숫자는 뽑지 않아야 하며, 반복의 깊이가 정해져 있지 않기 떄문에 백트래킹 알고리즘을 사용한다.

// 시간복잡도: O(N*M)

// 정수 범위:
