import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		Deque<Integer> queue = new ArrayDeque<>();
		for (int i = 1; i <= n; i++) {
			queue.addLast(i);
		}

		int count = 0;
		List<Integer> yosepus = new ArrayList<>();
		while (!queue.isEmpty()) {
			int person = queue.removeFirst();
			count++;

			if (count % k == 0) {
				yosepus.add(person);
				continue;
			}
			queue.addLast(person);
		}

		StringBuilder result = new StringBuilder();
		result.append("<");
		for (int person : yosepus) {
			result.append(person);
			result.append(", ");
		}
		result.replace(result.length() - 2, result.length(), ">");
		System.out.println(result.toString());
	}
}

// 입력
// 제거
// 제거 하지 않고 돌리기
