import java.awt.*;
import java.sql.SQLOutput;
import java.time.Month;
import java.util.*;
import java.io.*;
import java.util.List;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder result = new StringBuilder();

		while (true) {
			int n = Integer.parseInt(br.readLine());
			if (n == 0) {
				break;
			}

			double maxHeight = 0;
			List<Double> heights = new ArrayList<>();
			List<String> students = new ArrayList<>();

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				students.add(st.nextToken());

				double height = Double.parseDouble(st.nextToken());
				heights.add(height);
				maxHeight = Math.max(maxHeight, height);
			}

			for (int i = 0; i < n; i++) {
				if (heights.get(i) == maxHeight) {
					result.append(students.get(i));
					result.append(" ");
				}
			}
			int length = result.length();
			result.delete(length - 1, length);
			result.append("\n");
		}

		System.out.println(result.toString().trim());
	}
}

// 무한 루프
	// n 입력
	// n이 0이면 break
	// String[] students 이름 입력, maxHieht 계산
	// maxHeight 와 같으면 출력
