import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws Exception {
		// n 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		// int[n][2] points 입력
		StringTokenizer st;
		int[][] points = new int[n][2];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			points[i][0] = Integer.parseInt(st.nextToken());
			points[i][1] = Integer.parseInt(st.nextToken());
		}

		// numberOfQuery 입력
		int numberOfQuery = Integer.parseInt(br.readLine());

		// query를 하나씩 입력받기
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < numberOfQuery; i++) {
			double query = Double.parseDouble(br.readLine());
			int insertIdx = binarySearch(query, points);
			result.append(calculateResult(insertIdx, points));
			result.append("\n");
		}
		System.out.println(result.toString().trim());
	}

	private static int binarySearch(double query, int[][] points) {
		// query 구간 binarySearch: 중복도 x, 들어갈 위치 리턴 (int insertIndex
		int left = 0, right = points.length - 1;

		while (left <= right) {
			int mid = (left + right) / 2;

			if (points[mid][0] < query) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return left;
	}

	private static int calculateResult(int insertIdx, int[][] points) {
		// points[insertIndex -1][1] points[insertIndex][1] 비교 하여 결과 계산
		int prevPointY = points[insertIdx - 1][1];
		int nextPointY = points[insertIdx][1];

		if (prevPointY < nextPointY) {
			return 1;
		} else if (prevPointY > nextPointY) {
			return -1;
		}
		return 0;
	}
}

//
