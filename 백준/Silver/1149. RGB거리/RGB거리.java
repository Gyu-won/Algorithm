import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // n 입력 (2-1000)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // costs 입력
        int[][] costs = new int[3][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            costs[0][i] = Integer.parseInt(st.nextToken());
            costs[1][i] = Integer.parseInt(st.nextToken());
            costs[2][i] = Integer.parseInt(st.nextToken());
        }

        // dp 세팅
        int[][] dp = new int[3][n];
        dp[0][0] = costs[0][0];
        dp[1][0] = costs[1][0];
        dp[2][0] = costs[2][0];

        for (int i = 1; i < n; i++) {
            dp[0][i] = Math.min(dp[1][i - 1], dp[2][i - 1]) + costs[0][i];
            dp[1][i] = Math.min(dp[0][i - 1], dp[2][i - 1]) + costs[1][i];
            dp[2][i] = Math.min(dp[0][i - 1], dp[1][i - 1]) + costs[2][i];
        }

        // dp[n-1] 중 최소값 출력
        System.out.println(Math.min(Math.min(dp[0][n - 1], dp[1][n - 1]), dp[2][n - 1]));
    }
}

// n 개의 집, RGB로 칠할때 최소 비용
// 1 != 2, n !- n-1, i != i-1, i+1

// 최소 비용이 되려면 모든 경우 다해보면 3^n승으로 불가

// O(n^2 logn) 까지 가능
