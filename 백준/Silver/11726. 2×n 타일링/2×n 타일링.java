import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final int DIVISOR = 10007;

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public int solution() throws IOException {
        // n 을 입력받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        if (n == 1) {
            return 1;
        }

        // block[] 을 선언하고 block[1], block[2]를 입력받는다.
        int[] block = new int[n + 1];

        // block[n]을 구한다.
        block[1] = 1;
        block[2] = 2;
        for (int i = 3; i <= n; i++) {
            block[i] = (block[i - 1] % DIVISOR + block[i - 2] % DIVISOR) % DIVISOR;
        }

        return block[n];
    }
}

// 알고리즘: 이전의 것과 관계가 있으니 dp로 푼다.
// dp[n] = dp[n-2] + dp[n-1]
// 마지막이 2개 블럭인 경우와 1개 블럭인 경우로 나눌 수 있기 때문에 이렇게 하면 전체 경우이다.

// 시간복잡도: O(n)

// 정수 범위: 40을 넣었을 때 값이 넘어가서 10007로 나누어야 한다.
