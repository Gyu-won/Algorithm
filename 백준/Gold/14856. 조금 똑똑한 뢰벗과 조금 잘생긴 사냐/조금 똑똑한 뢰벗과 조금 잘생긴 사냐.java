import java.awt.*;
import java.sql.SQLOutput;
import java.time.Month;
import java.util.*;
import java.io.*;
import java.util.List;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long n = Long.parseLong(br.readLine());

		List<Long> fibonacciList = calculateFibonacci(n);
		Deque<Long> fibonacciQueue = new ArrayDeque<>();
		while (n > 0) {
			int idx = Collections.binarySearch(fibonacciList, n);
			if (idx >= 0) {
				fibonacciQueue.addLast(fibonacciList.get(idx));
				break;
			}
			long maxFibonacci = fibonacciList.get((idx * -1 - 2));
			fibonacciQueue.addLast(maxFibonacci);
			n -= maxFibonacci;
		}

		StringBuilder result = new StringBuilder();
		result.append(fibonacciQueue.size());
		result.append("\n");
		while(!fibonacciQueue.isEmpty()){
			result.append(fibonacciQueue.removeLast());
			result.append(" ");
		}
		System.out.println(result.toString().trim());
	}

	private static List<Long> calculateFibonacci(long n){
		List<Long> fibonnaciList = new ArrayList<>();
		fibonnaciList.add(1L);
		fibonnaciList.add(2L);

		for (int i = 2; i < 100; i++) {
			long f1 = fibonnaciList.get(i - 2);
			long f2 = fibonnaciList.get(i - 1);
			if (f1 + f2 > n) {
				break;
			}
			fibonnaciList.add(f1 + f2);
		}
		return fibonnaciList;
	}
}

// n 입력
// n 보다 작거나 같은 피보나치 수 구하기 (이분 탐색) logn
// stack에 더하기
// 위 과정을 n == 0 일떄까지 반복
// n == 0 이면 stack size 출력, stack pop 하면서 결과 출력
// n: long 타입

// O(nlogn)

// 14:43
