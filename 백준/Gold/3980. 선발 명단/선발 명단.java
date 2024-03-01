import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static boolean[] visited;
    private static int[][] players;
    private static int maxSum;

    public static void main(String[] args) throws IOException {
        // n 입력받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringBuilder result = new StringBuilder();

        // players를 입력받는다
        visited = new boolean[11];
        players = new int[11][11];
        for (int time = 0; time < n; time++) {
            maxSum = 0;

            for (int i = 0; i < 11; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < 11; j++) {
                    players[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            dfs(0, 0);
            result.append(maxSum);
            result.append("\n");
        }

        System.out.println(result.toString().trim());
    }

    private static void dfs(int number, int sum) {
        if (number == 11) {
            maxSum = Math.max(maxSum, sum);
            return;
        }

        for (int i = 0; i < 11; i++) {
            if (players[number][i] > 0 && !visited[i]) {
                visited[i] = true;
                dfs(number + 1, sum + players[number][i]);
                visited[i] = false;
            }
        }
    }
}

// 능력치 합의 최대값 구하기

// 각 사람이 할 수 있는 포지션을 다 구하면: 5^11 = 가능
// 백트래킹으로 다 보고 최대값 구하기
