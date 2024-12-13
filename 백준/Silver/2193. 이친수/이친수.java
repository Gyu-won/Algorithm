import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		long result = calculate(n);
		System.out.println(result);
	}

	private static long calculate(int n) {
		long[] dp = new long[n + 1];
		
		if (n < 3){
			return 1;
		}
		dp[1] = 1;
		dp[2] = 1;

		for (int i = 3; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		return dp[n];
	}
}

// 그냥하면 O(2n승)
// O(n)
// an = an-1 + an-2
