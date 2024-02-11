import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(new Solution().solution());
    }

    static class Solution {
        public int solution() throws IOException {
            // n 과 k를 입력받는다 (1-100, 1-10000)
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            // coins를 입력받는다. (n개 입력받기, 1-100000)
            int[] coins = new int[n];
            for (int i = 0; i < n; i++) {
                coins[i] = Integer.parseInt(br.readLine());
            }

            Arrays.sort(coins);

            int[] dp = new int[k + 1];
            dp[0] = 1;
            for (int i = 0; i < n; i++) {
                for (int j = 1; j <= k; j++) {
                    if (j >= coins[i]) {
                        dp[j] += dp[j - coins[i]];
                    }
                }
            }

            return dp[k];
        }
    }
}

// 설계 시간: 00:23 -
// 풀이 시간:

//0. 문제요약
// n가지 동전으로 가치가 k가 되도록 하는 경우의 수
// 조합으로 순서가 다른건 같은 경우이다.

// dfs를 이용해서 들어갈 수 있는 가장 큰 숫자부터 넣고, 해당 값 과, 나눌 수를 넘겨줘서 끝까지 채우기

//2. 시간복잡도: O(

//3. 이 로직으로 예제 문제 손으로 풀기

//4. 경계값 체크 후 코드 구현
