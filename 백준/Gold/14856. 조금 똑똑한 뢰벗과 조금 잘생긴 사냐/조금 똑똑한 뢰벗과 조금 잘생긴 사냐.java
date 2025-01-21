import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

class Main {

	private static long[] fibonacci = new long[86];

	public static void main(String[] args) throws Exception {
		// long n 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long n = Long.parseLong(br.readLine());

		// 피보나치 수열 구하기
		fibonacci[0] = 1;
		fibonacci[1] = 2;
		for (int i = 2; i < 86; i++) {
			fibonacci[i] = fibonacci[i - 2] + fibonacci[i - 1];
		}

		// n 보다 작은 제일 큰 피보나치 수 빼기
		Deque<Long> fibonacciStack = new ArrayDeque<>();
		while (n > 0) {
			// 최대 피보나치 수 찾기: O(log 86)
			long maxFibonacci = findMaxFibonacci(n);

			// 값 빼기
			n -= maxFibonacci;
			fibonacciStack.addLast(maxFibonacci);
		}

		StringBuilder result = new StringBuilder();
		result.append(fibonacciStack.size());
		result.append("\n");
		while (!fibonacciStack.isEmpty()) {
			result.append(fibonacciStack.removeLast());
			result.append(" ");
		}
		System.out.println(result.toString().trim());
	}

	private static long findMaxFibonacci(long n) {
		int left = 0;
		int right = fibonacci.length - 1;

		while (left <= right) {
			int mid = (left + right) / 2;
			if (fibonacci[mid] <= n) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return fibonacci[right];
	}
}

// dp[86] > 10^18
// sum[85] > 10^18
