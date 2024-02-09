import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(new Solution().solution());
    }

    static class Solution {
        public int solution() throws IOException {
            // n을 입력받는다 (1-10000)
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(br.readLine());

            // int a[n] 를 입력받는다.
            int[] a = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                a[i] = Integer.parseInt(br.readLine());
            }

            // dp를 사용
            int[] dp = new int[n + 1];
            dp[1] = a[1];

            if (n == 1) {
                return dp[1];
            }

            dp[2] = dp[1] + a[2];
            for (int i = 3; i <= n; i++) {
                dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 3] + a[i - 1] + a[i], dp[i - 2] + a[i]));
            }

            return dp[n];
        }
    }
}

// 설계 시간: 14:13 -
// 풀이 시간:

//0. 문제요약
// 잔 선택하면 모두 마시고 다시 원래 위치로 놓아야한다
// 3잔 연속 마실 수 없다
// 1-n 까지 잔에서 최대한 많은 양의 술을 먹는 양
// dp[n] = max(dp[n-1]+ a[n], dp[n-2] + a[n])

//2. 시간복잡도: 

//3. 이 로직으로 예제 문제 손으로 풀기

//4. 경계값 체크 후 코드 구현
