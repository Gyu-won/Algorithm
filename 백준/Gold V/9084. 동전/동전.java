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
            // t를 입력받는다 (1-10)
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int t = Integer.parseInt(br.readLine());

            // t 번 반복
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < t; i++) {
                // n을 입력받는다. (1-20)
                int n = Integer.parseInt(br.readLine());

                // int[n] coins를 입력받는다. (1-10000)
                int[] coins = new int[n];
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < n; j++) {
                    coins[j] = Integer.parseInt(st.nextToken());
                }

                // m을 입력받는다. (1-10000)
                int m = Integer.parseInt(br.readLine());

                int[] dp = new int[m + 1];
                dp[0] = 1;
                // coins를 돌면서 dp값을 세팅한다
                for (int j = 0; j < n; j++) {
                    int coin = coins[j];
                    for (int k = 1; k <= m; k++) {
                        if (k - coin >= 0) {
                            dp[k] += dp[k - coin];
                        }
                    }
                }

                result.append(dp[m]);
                result.append("\n");
            }
            return result.toString().trim();
        }
    }
}

// 요약
// 1, 5, 10, 50, 100, 500 원
// 동전의 종류가 주어질 때 주어진 금액을 만드는 모든 방법

// 14:15 -

// O(t * m * n);