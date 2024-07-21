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
		if (n < 3) {
			return 1;
		}
		long[] dp = new long[n + 1];
		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 1;
		for (int i = 3; i <= n; i++) {
			dp[i] = dp[i - 2] + dp[i - 3];
		}
		return dp[n];
	}
}
