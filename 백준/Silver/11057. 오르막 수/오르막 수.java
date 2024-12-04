import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	private static final int DIVISOR = 10007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int inclineNumber = countInclineNumber(n);
		System.out.println(inclineNumber);
	}

	private static int countInclineNumber(int n) {
		int[][] dp = initialDP(n);
		fillDP(dp);
		return sumDPColumn(dp);
	}

	private static int[][] initialDP(int n) {
		int[][] dp = new int[10][n];

		dp[0][0] = 1;
		for (int i = 1; i < 10; i++) {
			dp[i][0] = 1;
		}
		for (int i = 1; i < n; i++) {
			dp[0][i] = 1;
		}

		return dp;
	}

	private static void fillDP(int[][] dp) {
		int n = dp[0].length;
		for (int r = 1; r < 10; r++) {
			for (int c = 1; c < n; c++) {
				dp[r][c] = (dp[r - 1][c] + dp[r][c - 1]) % DIVISOR;
			}
		}
	}

	private static int sumDPColumn(int[][] dp) {
		int inclineNumber = 0;
		int dpColumn = dp[0].length - 1;
		for (int i = 0; i < 10; i++) {
			inclineNumber += dp[i][dpColumn];
		}
		return inclineNumber % DIVISOR;
	}
}
