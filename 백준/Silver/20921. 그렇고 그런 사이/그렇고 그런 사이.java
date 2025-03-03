import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws IOException {
		// n, k 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		boolean[] visited = new boolean[n];

		// n-1부터 1까지 숫자가 k보다 작거나 같으면 해당 숫자를 넣기 O(n)
		StringBuilder result = new StringBuilder();
		for (int i = n - 1; i > 0; i--) {
			if (i <= k) {
				k -= i;
				visited[i] = true;
				result.append(i + 1).append(" ");
			}
			if (k <= 0) {
				break;
			}
		}

		// visited 안한 것들 추가 O(n)
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				result.append(i + 1).append(" ");
			}
		}

		System.out.println(result.toString().trim());
	}
}

// 모든사람에게 1-n 숫자를 나눠줌
// 왼쪽 사람이 오른쪽 사람보다 숫자가 크면 그렇고 그런 사이
// k개의 사이

// 조합 개수: n!
// 계산: n^2 (2 * 10^7)
