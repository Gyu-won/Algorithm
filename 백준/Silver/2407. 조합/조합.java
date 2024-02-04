import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public BigInteger solution() throws IOException {
        // n과 m을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // long[n][m] combination 을 선언한다.
        BigInteger[][] combination = new BigInteger[n + 1][m + 1];

        // combination[i][i]를 1로 초기화한다.
        for (int i = 0; i <= m; i++) {
            combination[i][i] = new BigInteger("1");
        }

        // cobination[i][0]을 1로 초기화 한다
        for (int i = 0; i <= n; i++) {
            combination[i][0] = new BigInteger("1");
        }

        // dp를 활용하여 그래프를 combination[n][m] 까지 채운다.
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= Math.min(i - 1, m); j++) {
                combination[i][j] = combination[i - 1][j - 1].add(combination[i - 1][j]);
            }
        }

        // dp[n][m]을 출력한다.
        return combination[n][m];
    }
}

// 10:41 - 11:04
// 총 걸린 시간: 23분

// 알고리즘: 조합의 개수는 dp를 사용하여 문제를 푼다. dp[n][m] = dp[n-1][m-1] + dp[n-1][m]

// 시간복잡도: O(n*m)

// 정수 범위:
