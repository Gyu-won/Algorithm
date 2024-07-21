import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int numberOfTest = Integer.parseInt(br.readLine());

		for (int currentTest = 0; currentTest < numberOfTest; currentTest++) {
			int n = Integer.parseInt(br.readLine());
			System.out.println(calculatePadoban(n));
		}
	}

	private static long calculatePadoban(int n) {
		if (n == 1) {
			return 1;
		}
		if (n < 5) {
			return n / 2;
		}
		long[] dp = new long[n + 1];
		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 1;
		dp[3] = 1;
		dp[4] = 2;
		for (int i = 5; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 5];
		}
		return dp[n];
	}
}
