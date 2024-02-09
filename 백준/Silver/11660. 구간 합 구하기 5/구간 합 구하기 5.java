import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(new Solution().solution());
    }

    static class Solution {
        public String solution() throws IOException {
            // n과 m을 입력받는다 (1-1024, 1-100000)
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            // int[n+1][n+1] a를 입력받는다.
            int[][] a = new int[n + 1][n + 1];
            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 1; j <= n; j++) {
                    a[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 합배열을 구하기 O(n^2)
            // dp[r][c] = dp[r-1][c] + dp[r][c-1] + a[r][c] - dp[r-1][c-1]
            int[][] dp = new int[n + 1][n + 1];
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1] + a[i][j] - dp[i - 1][j - 1];
                }
            }

            // x1, y1, x2, y2를 m 번 입력받는다.
            StringBuilder result = new StringBuilder();
            for (int k = 0; k < m; k++) {
                st = new StringTokenizer(br.readLine(), " ");

                // dp[x2][y2] - dp[x2][y1-1] - dp[x1-1][y2] + dp[x1-1][y1-1]
                int x1 = Integer.parseInt(st.nextToken());
                int y1 = Integer.parseInt(st.nextToken());
                int x2 = Integer.parseInt(st.nextToken());
                int y2 = Integer.parseInt(st.nextToken());

                result.append(dp[x2][y2] - dp[x2][y1 - 1] - dp[x1 - 1][y2] + dp[x1 - 1][y1 - 1]);
                result.append("\n");
            }
            return result.toString().trim();
        }
    }
}

// 설계 시간: 22:00 - 22:15
// 풀이 시간: 22:15 -

//0. 문제요약
// n*n 표에서 특정 부분의 합을 구하면 된다.
// 완탐으로 하면 시간 초과
// 같은 표니까 한번 구해놓으면 좋을 것 같다.

//2. 시간복잡도: 

//3. 이 로직으로 예제 문제 손으로 풀기

//4. 경계값 체크 후 코드 구현
