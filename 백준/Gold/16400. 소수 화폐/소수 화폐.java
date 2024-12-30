import java.awt.*;
import java.sql.SQLOutput;
import java.time.Month;
import java.util.*;
import java.io.*;
import java.util.List;

class Main {

	private static final int MOD = 123456789;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		List<Integer> primeList = calculatePrime(n);
		int[] dp = fillDp(n, primeList);
		System.out.println(dp[n]);
	}

	private static List<Integer> calculatePrime(int n){
		boolean[] isNotPrime = new boolean[n + 1];
		List<Integer> primeList = new ArrayList<>();

		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (!isNotPrime[i]) {
				for (int j = i; j * i <= n; j++) {
					isNotPrime[i * j] = true;
				}
			}
		}
		for (int i = 2; i <= n; i++) {
			if (!isNotPrime[i]) {
				primeList.add(i);
			}
		}
		return primeList;
	}

	private static int[] fillDp(int n, List<Integer> primeList) {
		int[] dp = new int[n+1];
		dp[0] = 1;

		for (int prime : primeList) {
			for (int i = prime; i <= n; i++) {
				dp[i] = (dp[i] + dp[i - prime]) % MOD;
			}
		}
		return dp;
	}
}

// n 보다 작은 소수 판별하여 소수 list에 넣기
	// 2 ~ n/2 까지 반복
	// isNotPrime false -> primeList 추가, i * i true로 바꾸기,
// r: 2~n, c: 2 ~ r-1
	// isNotprime[c] true: dp[r][c] = dp[r][c-1]
	// false: dp[r][c] = dp[r-c][c] + dp[r][c-1]
// isNotPrime[r] false 면 dp[r][r] = dp[r][c-1] + 1;
// dp[n][n] 출력

// 14:00
