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
            // n을 입력받는다. (1-1500000)
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(br.readLine());

            // int[n] t, int[n] p 를 입력받는다.
            int[] t = new int[n + 1];
            int[] p = new int[n + 1];
            StringTokenizer st;
            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                t[i] = Integer.parseInt(st.nextToken());
                p[i] = Integer.parseInt(st.nextToken());
            }

            // dp 선언한다.
            int[] dp = new int[n + 2];

            // 최대값 구한다.
            for (int i = 0; i < n; i++) {
                int d = n - i;
                if (d + t[d] <= n + 1) {
                    dp[d] += dp[d + t[d]] + p[d];
                }
                dp[d] = Math.max(dp[d + 1], dp[d]);
            }
            
            return dp[1];
        }

    }
}

// 설계 시간:
// 풀이 시간:

//0. 문제요약
// n일동안 최대한 많은 상담
// 상담 하는데는 1일 보다 많이 걸린다
// 최대한 많은 수익을 얻도록 상담하는 법
// 완탐 불가
// 뒤에서부터 해서 dp[n] = dp[n+t] + p[n], 범위 초과하면 dp[n+t] 가 0
// 이후 최대값 구하기

//2. 시간복잡도:

//3. 이 로직으로 예제 문제 손으로 풀기

//4. 경계값 체크 후 코드 구현
