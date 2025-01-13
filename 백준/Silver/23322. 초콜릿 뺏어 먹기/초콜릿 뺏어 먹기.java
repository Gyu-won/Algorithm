import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws Exception {
		// n, k 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		// int[n] chocolates 입력 (정렬 됨)
		int[] chocolates = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			chocolates[i] = Integer.parseInt(st.nextToken());
		}

		// O(n)
		int day = 0, quantity = 0;
		for (int number = k + 1; number <= n; ) {
			if (chocolates[number - k] < chocolates[number]) {
				day++;
				int difference = chocolates[number] - chocolates[number - k];
				quantity += difference;
				chocolates[number] -= difference;
				Arrays.sort(chocolates);
				continue;
			}
			number++;
		}

		System.out.printf("%d %d", quantity, day);
	}
}

// 초콜릿 오름차순 정렬되어 있음
// K < i 인 i를 골라서 i - K 통에 있는 개수랑 똑같을 때까지 i 번째 통에서 초콜릿 먹고 재정렬
// 최대한 많이 빨리 초콜릿 먹을 때 몇개 먹을지

// 가장 앞에 것 부터 고르는 게 가장 많이 빠르게 먹는 방법
