import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // n 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // triangle 입력
        StringTokenizer st;
        int[][] triangle = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; j++) {
                triangle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dp 채우기 (둘중 최대값 + 자기자신)
        int[][] dp = fillDpTable(n, triangle);
        System.out.println(findMaxValue(n, dp));
    }

    private static int findMaxValue(int n, int[][] dp) {
        int maxValue = 0;
        for (int j = 0; j < n; j++) {
            maxValue = Math.max(maxValue, dp[n - 1][j]);
        }
        return maxValue;
    }

    private static int[][] fillDpTable(int n, int[][] triangle) {
        int[][] dp = new int[n][n];

        dp[0][0] = triangle[0][0];

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (isValid(i - 1, j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                if (isValid(i - 1, j)) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                }
                dp[i][j] += triangle[i][j];
            }
        }
        return dp;
    }

    private static boolean isValid(int r, int c) {
        return c >= 0 && c <= r;
    }
}

// 19:57 -
// 칸 채우기 하면 501 * 500 으로 해결 가능