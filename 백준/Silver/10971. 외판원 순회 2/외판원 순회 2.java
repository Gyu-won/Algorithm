import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int minValue = Integer.MAX_VALUE;
    private static int n;
    private static int[][] weight;

    public static void main(String[] args) throws IOException {
        // n을 입력 (2-10)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        // w[n][n] 입력
        weight = new int[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                weight[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 모두 visit 할때까지 돌고 마지막에 출발지까지 경로 더해줌
        for (int i = 0; i < n; i++) {
            boolean[] visited = new boolean[n];
            visited[i] = true;
            dfs(1, i, i, 0, visited);
        }

        System.out.println(minValue);
    }

    private static void dfs(int count, int start, int current, int totalWeight, boolean[] visited) {
        if (count == n) {
            if (weight[current][start] > 0) {
                minValue = Math.min(minValue, totalWeight + weight[current][start]);
            }
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i] && weight[current][i] > 0) {
                visited[i] = true;
                dfs(count + 1, start, i, totalWeight + weight[current][i], visited);
                visited[i] = false;
            }
        }
    }

}

// 08:44

// n 개의 도시, 도시 모두 순회해서 돌아옴, 비용 최소
// 다 계산하면 10!인데 가능