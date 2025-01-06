import java.awt.*;
import java.sql.SQLOutput;
import java.time.Month;
import java.util.*;
import java.io.*;
import java.util.List;

class Main {

	private static int n;
	private static long[] a;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		a = new long[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			a[i] = Long.parseLong(st.nextToken());
		}

		Arrays.sort(a);

		StringBuilder result = new StringBuilder();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int queryType = Integer.parseInt(st.nextToken());

			if (queryType == 1) {
				long minNumber = Long.parseLong(st.nextToken());
				int minBoundary = binarySearch(minNumber);
				result.append(n - minBoundary);
			} else if (queryType == 2) {
				long minNumber = Long.parseLong(st.nextToken());
				int minBoundary = binarySearch(minNumber + 1);
				result.append(n - minBoundary);
			}else{
				long minNumber = Long.parseLong(st.nextToken());
				int minBoundary = binarySearch(minNumber);
				long maxNumber = Long.parseLong(st.nextToken());
				int maxBoundary = binarySearch(maxNumber + 1);
				result.append(maxBoundary - minBoundary);
			}
			result.append("\n");
		}
		System.out.println(result.toString().trim());
	}

	private static int binarySearch(long k) {
		int mid, left = 0, right = n - 1;

		while(left <= right) {
			mid = (left + right) / 2;
			if (a[mid] >= k) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}
}

// a 배열 정렬 후 탐색 -> O(m * logn)

// n, m 입력 < 10^5
// long a[n] 입력
// a 배열 정렬
// m개의 질의 입력
	// 크거나 같은 값 index 반환
	// if 문으로 경우에 따라 값 출력

// 1: k<=
// 2: k <
// 3: i <= a <= j
