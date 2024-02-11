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
            // c와 n을 입력받는다. (1-1000, 1-20)
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int c = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            int[] dp = new int[c + 101];
            for (int i = 0; i < c + 101; i++) {
                dp[i] = Integer.MAX_VALUE;
            }

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int p = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());

                dp[e] = Math.min(p, dp[e]);
            }

            for (int i = 1; i < c + 101; i++) {
                for (int j = 1; j <= i / 2; j++) {
                    if (dp[j] != Integer.MAX_VALUE && dp[i - j] != Integer.MAX_VALUE) {
                        dp[i] = Math.min(dp[i], dp[j] + dp[i - j]);
                    }
                }
            }

            int result = Integer.MAX_VALUE;
            for (int i = c; i < c + 101; i++) {
                result = Math.min(result, dp[i]);
            }

            return result;
        }
    }
}

// 설계 시간: 13:04 -
// 풀이 시간:

//0. 문제요약
// 홍보 도시랑 홍보 비용 + 홍보 효과가 주어진다.
// 비용의 정수 배만큼의 돈을 투자할 수 있다 ( 1-)
// 호텔 고객 c명 이상 늘리기 위해서 투자해야하는 최소 금액

// dp[n] = dp[i] + dp[n - i] 의 최소
// n-1 + 최대 = 1019

//2. 시간복잡도:

//3. 이 로직으로 예제 문제 손으로 풀기

//4. 경계값 체크 후 코드 구현
