import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

	private static final int DIVISOR = 15746;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		if (n < 3) {
			System.out.println(n);
			return;
		}

		int[] dp = new int[n + 1];
		dp[1] = 1;
		dp[2] = 2;

		for (int i = 3; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
			dp[i] %= DIVISOR;
		}
		System.out.println(dp[n]);
	}
}
