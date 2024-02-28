
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // n 입력 (3-100)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // numbers 입력
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        // dp 배열 선언
        long[] dp = new long[21];

        // 시작 값을 1로 세팅
        dp[numbers[0]] = 1;

        // 1부터 n-2까지 반복
        for (int i = 1; i < n - 1; i++) {
            long[] temp = new long[21];
            int number = numbers[i];

            // 값이 0이 아닌 애들에 대해서
            for (int j = 0; j <= 20; j++) {
                if (dp[j] > 0) {
                    // 값 더하거나 뺐을 때 0-20 이면 deriv값에 dp의 값을 더해줌
                    if (j - number >= 0) {
                        temp[j - number] += dp[j];
                    }
                    if (j + number <= 20) {
                        temp[j + number] += dp[j];
                    }

                }
            }
            dp = temp;
        }

        //dp 마지막 number를 출력
        System.out.println(dp[numbers[n - 1]]);
    }
}

// 숫자는 0-20 이어야만함
// 상근이가 만들 수 있는 등식의 수

// O(n^3) 가능

// 모든 경우를 dfs로 다 보는건 불가능 O(2^n)
//