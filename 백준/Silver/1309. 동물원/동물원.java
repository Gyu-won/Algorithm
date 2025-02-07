import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

	private static final int DIVISOR = 9901;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int[][] dp = new int[n][3];
		for (int i = 0; i < 3; i++) {
			dp[0][i] = 1;
		}

		for (int i = 1; i < n; i++) {
			dp[i][0] = (dp[i - 1][1] + dp[i - 1][2]) % DIVISOR;
			dp[i][1] = dp[i][0];
			dp[i][2] = (dp[i][0] + dp[i - 1][0]) % DIVISOR;
		}

		int answer = 0;
		for (int i = 0; i < 3; i++) {
			answer += dp[n - 1][i];
		}
		System.out.println(answer % DIVISOR);
	}
}
