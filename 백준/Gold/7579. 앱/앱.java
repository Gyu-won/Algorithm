import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int n;

	public static void main(String[] args) throws IOException {
		// N<=100, M<=10^7 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		// memories[n]<=10^7 입력
		int[] memories = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			memories[i] = Integer.parseInt(st.nextToken());
		}

		// costs[n]<=100 입력
		int[] costs = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			costs[i] = Integer.parseInt(st.nextToken());
		}

		int[][] dp = fillDp(memories, costs);
		for (int i = 0; i < dp[0].length; i++) {
			if (dp[n][i] >= m) {
				System.out.println(i);
				return;
			}
		}
	}

	private static int[][] fillDp(int[] memories, int[] costs) {
		int totalCost = 0;
		for (int cost : costs) {
			totalCost += cost;
		}

		int[][] dp = new int[n + 1][totalCost + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j <= totalCost; j++) {
				if (j - costs[i - 1] < 0) {
					dp[i][j] = dp[i - 1][j];
				} else {
					dp[i][j] = Math.max(dp[i - 1][j],
						dp[i - 1][j - costs[i - 1]] + memories[i - 1]);
				}
			}
		}
		return dp;
	}
}

// dfs knapsack 높이 최대 100
// 무게<=0 이면 0 리턴
// n < 0 이면 0리턴

// N: 앱 개수, M: 필요한 메모리
