import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws IOException {
		// n 입력 <= 10^5
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		// cards[10^5] 입력 <= 10^5
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] myCards = new int[n / 2 + 1];
		int[] yourCards = new int[n / 2 + 1];
		for (int i = 0; i < n; i++) {
			if (i % 2 == 0) {
				myCards[(i + 2) / 2] = myCards[i / 2] + Integer.parseInt(st.nextToken());
			} else {
				yourCards[(i + 2) / 2] = yourCards[i / 2] + Integer.parseInt(st.nextToken());
			}
		}

		int maxSum = 0;
		// 밑장빼기 경우 O(n)
		for (int i = 0; i < n; i++) {
			// 현재 합 구하기
			int sum = myCards[(i + 1) / 2] - myCards[0];

			// 상대방의 부분합 더하기
			sum += (yourCards[yourCards.length - 1] - yourCards[i / 2]);

			// 자기 밑장빼기
			if (i % 2 == 1) {
				// 마지막꺼 빼기
				sum -= (yourCards[yourCards.length - 1] - yourCards[yourCards.length - 2]);
			}

			maxSum = Math.max(maxSum, sum);
		}

		System.out.println(maxSum);
	}
}

