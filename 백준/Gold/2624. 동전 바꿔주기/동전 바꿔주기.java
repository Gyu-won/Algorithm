import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int t;
    private static int[] dp;
    private static int[] deriv;

    public static void main(String[] args) throws IOException {
        // T를 입력 (1-10000)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        // k 를 입력 (1-100)
        int k = Integer.parseInt(br.readLine());

        // coins 입력
        StringTokenizer st;
        dp = new int[t + 1];
        deriv = new int[t + 1];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int price = Integer.parseInt(st.nextToken());
            int amount = Integer.parseInt(st.nextToken());

            calculate(price, amount);

            for (int j = 1; j <= t; j++) {
                dp[j] += deriv[j];
                deriv[j] = 0;
            }
        }

        System.out.println(dp[t]);

    }

    private static void calculate(int price, int amount) {
        for (int i = 1; i <= t; i++) {
            if (dp[i] > 0) {
                for (int j = 1; j <= amount; j++) {
                    int k = i + j * price;
                    if (t < k) {
                        break;
                    }
                    deriv[k] += dp[i];
                }
            }
        }

        for (int i = 1; i <= amount; i++) {
            if (t < price * i){
                break;
            }
            deriv[price * i]++;
        }
    }
}

// t를 동전으로 변경
// 동전 교환하는 방법 수 dfs로
