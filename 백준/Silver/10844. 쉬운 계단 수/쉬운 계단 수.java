import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(new Solution().solution());
    }

    static class Solution {
        private static final int MAX_NUMBER = 1000000000;

        public long solution() throws IOException {
            // n을 입력받는다.
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(br.readLine());

            // int dp[n][11]을 선언한다
            int[][] dp = new int[n][12];

            //dp[0]을 입력한다
            for (int i = 2; i < 11; i++) {
                dp[0][i] = 1;
            }

            // dp[n-1] 까지 구한다
            for (int i = 1; i < n; i++) {
                for (int j = 1; j < 11; j++) {
                    dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % MAX_NUMBER;
                }
            }

            // 정답을 구한다.
            long sum = 0;
            for (int i = 1; i < 11; i++) {
                sum += dp[n - 1][i];
            }
            return sum % MAX_NUMBER;
        }
    }
}

// 설계 시간: 16:06 -
// 풀이 시간:

//0. 문제요약
// 길이가 n인 계단수의 개수를 구한다.
// 0으로 시작하면 안되고 인접한 자리는 1이다.
// 정답을 1000000000으로 나눈 나머지를 출력

// 제일 앞에 나올 수 있는게 1, 2, 3, 4, 5, 6, 7, 8: 16
// 9는 무조건 1개

// dp [n][k] = dp[n-1][k-1] + dp[n-1][k+1]

//2. 시간복잡도: 

//3. 이 로직으로 예제 문제 손으로 풀기

//4. 경계값 체크 후 코드 구현
