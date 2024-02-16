import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(new Solution().solution());
    }

    static class Solution {
        public int solution() throws IOException {
            // n 과 k를 입력받는다 (int)
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            // int dp[k+1]을 선언한다.
            int[][] dp = new int[2][k + 1];

            // n 번 만큼 물건을 입력받으면서 dp 배열을 채운다
            int t = 0;
            for (; t < n; t++) {
                st = new StringTokenizer(br.readLine(), " ");

                int w = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                if (w <= k) {
                    for (int i = 1; i <= k; i++) {
                        // i - w > 0 -> d[i] = max(dp[i], dp[i-w]+v)
                        int before = 0;
                        if (i - w > 0 && dp[t % 2][i - w] != 0) {
                            before = dp[t % 2][i - w] + v;
                        }
                        dp[(t + 1) % 2][i] = Math.max(dp[t % 2][i], before);
                    }
                    dp[(t + 1) % 2][w] = Math.max(dp[(t + 1) % 2][w], v);
                } else {
                    for (int i = 1; i <= k; i++) {
                        dp[(t + 1) % 2][i] = dp[t % 2][i];
                    }
                }
            }

            // 최대값 계산
            int maxValue = 0;
            for (int i = 1; i <= k; i++) {
                maxValue = Math.max(maxValue, dp[t % 2][i]);
            }
            return maxValue;
        }
    }
}

// 요약
// n 개의 물건(1-100), 물건은 무게 w 와 가치 v (1-100,000), (0-1,000)
// k 만큼의 무게만 넣을 수 있는 가방(1-100,000)
// 물건 가치의 최대값

// 13:05

// 2초: O(n * k)