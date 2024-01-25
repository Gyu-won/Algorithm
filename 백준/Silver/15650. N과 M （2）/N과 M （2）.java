import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int M;
    StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    public void solution() throws IOException {
        // N과 M을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // permutation을 찾는다.
        permutation(0, "", 1);

        System.out.println(answer.toString().trim());

    }

    private void permutation(int count, String result, int start) {
        if (count == M) {
            answer.append(result);
            answer.append("\n");
            return;
        }
        for (int i = start; i <= N; i++) {
            permutation(count + 1, result + i + " ", i + 1);
        }
    }
}

// 알고리즘: dfs로 노드를 찾되, 이전 수보다 큰 수는 탐색하지 않도록 한다.

// 시간복잡도: O(N*M)

// 정수 범위:
