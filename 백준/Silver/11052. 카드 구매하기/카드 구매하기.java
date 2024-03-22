import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        // n 입력 (1-1000)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // prices 입력 (1-10000)
        int[] prices = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= n; i++) {
            prices[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(calculateMaxPrice(n, prices));
    }

    private static int calculateMaxPrice(int n, int[] prices) {
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            dp[i] = Math.max(prices[i], dp[i]);

            for (int j = i + 1; j <= Math.min(2 * i, n); j++) {
                dp[j] = Math.max(dp[j], dp[i] + dp[j - i]);
            }
        }
        return dp[n];
    }
}

// 카드 개수가 적어도 가격이 비싸면 좋은 카드
// 카드팩에는 1-n개의 카드가 들어있음
// n개 구매하기 위해 지불 금액 최대

// 카드 하나씩 순회
// dp[cardNumber] update
// cardNumber < i -> dp[i] = dp[cardNumber] + dp[cardNumber
// O(n^2)