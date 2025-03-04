import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static final int DIVISOR = 1000000007;

	public static void main(String[] args) throws IOException {
		// n, m 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		// 초기화
		int[][] dp = new int[26][m];
		for (int i = 0; i < 26 - n; i++) {
			dp[i][0] = 1;
		}
		for (int i = n; i < 26; i++) {
			dp[i][0] = 1;
		}

		// dp[26][m]을 채우면 됨 O(26 * 26 * m)
		for (int i = 1; i < m; i++) {
			for (int j = 0; j < 26; j++) {
				for (int s = 0; s <= j - n; s++) {
					dp[j][i] += dp[s][i - 1];
					dp[j][i] %= DIVISOR;
				}
				for (int e = j + n; e < 26; e++) {
					dp[j][i] += dp[e][i - 1];
					dp[j][i] %= DIVISOR;
				}
				if (n == 0) {
					dp[j][i] -= dp[j][i - 1];
					if (dp[j][i] < 0) {
						dp[j][i] += DIVISOR;
					}
				}
			}
		}

		// 마지막에 dp 합 출력
		int total = 0;
		for (int i = 0; i < 26; i++) {
			total += dp[i][m - 1];
			total %= DIVISOR;
		}
		System.out.println(total);
	}
}

// 이전꺼랑 거리가 n 이상인 길이 m 의 문자열

