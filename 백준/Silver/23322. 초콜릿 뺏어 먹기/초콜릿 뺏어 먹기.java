import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws IOException {
		// n 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());

		// k 입력
		int k = Integer.parseInt(st.nextToken());

		// int chocolates[n] 입력
		int[] chocolates = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			chocolates[i] = Integer.parseInt(st.nextToken());
		}

		// n-1 순회하며 chocolate[0]과 다른 값을 더하고, chocolates[0] 빼기: O(n_
		int chocolateToEat = 0;
		int eatingDay = 0;
		for (int i = 1; i < n; i++) {
			int difference = chocolates[i] - chocolates[0];
			if (difference > 0) {
				chocolateToEat += difference;
				eatingDay++;
			}
		}

		StringBuilder result = new StringBuilder();
		result.append(chocolateToEat).append(" ").append(eatingDay);
		System.out.println(result.toString());
	}
}

// 앞에것 부터 먹어서 빠르게 줄이

