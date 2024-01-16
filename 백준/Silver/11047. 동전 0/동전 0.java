import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // N과 K를 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 동전가치를 순서대로 입력받는다.
        Deque<Integer> coins = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            coins.addFirst(Integer.parseInt(br.readLine()));
        }

        // 큰 동전부터 최대한 많이 넣으며 반복한다.
        int totalNumberOfCoin = 0;
        for (int coin : coins) {
            int numberOfCoin = K / coin;
            if (numberOfCoin > 0) {
                totalNumberOfCoin += numberOfCoin;
                K %= coin;
            }
            if (K == 0) {
                break;
            }
        }
        System.out.println(totalNumberOfCoin);
    }
}
