import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static final int[] rowDirection = new int[] {-1, 0, 1, 0};
	private static final int[] colDirection = new int[] {-0, 1, 0, -1};

	private static int n;
	private static int[][] region;
	private static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		// n 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		// nxn region 입력
		region = new int[n][n];
		visited = new boolean[n][n];
		for (int row = 0; row < n; row++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int col = 0; col < n; col++) {
				region[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(calculateMaxNumberOfSafeZone());
	}

	private static int calculateMaxNumberOfSafeZone() {
		int maxNumberOfSafetyZone = 1;
		for (int amountOfRain = 1; amountOfRain < 100; amountOfRain++) {
			int numberOfSafetyZone = calculateSafetyZone(amountOfRain);
			if (numberOfSafetyZone == 0) {
				break;
			}
			maxNumberOfSafetyZone = Math.max(maxNumberOfSafetyZone, numberOfSafetyZone);
			resetVisited();

		}
		return maxNumberOfSafetyZone;
	}

	private static int calculateSafetyZone(int amountOfRain) {
		int numberOfSafetyZone = 0;
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < n; col++) {
				if (!visited[row][col] && region[row][col] > amountOfRain) {
					numberOfSafetyZone++;
					dfs(row, col, amountOfRain);
				}
			}
		}
		return numberOfSafetyZone;
	}

	private static void dfs(int row, int col, int amountOfRain) {
		visited[row][col] = true;

		for (int direction = 0; direction < 4; direction++) {
			int nextRow = row + rowDirection[direction];
			int nextCol = col + colDirection[direction];

			if (nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < n && !visited[nextRow][nextCol]
				&& region[row][col] > amountOfRain) {
				dfs(nextRow, nextCol, amountOfRain);
			}
		}
	}

	private static void resetVisited() {
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < n; col++) {
				visited[row][col] = false;
			}
		}
	}
}

// 23:00
// O(100 가지 비 * 100 * 100) = O(10^6)
// 높이가 1 이상 이기 떄문에 1부터 199까지 하고 최소는 1이다.
// 각 비의 양에 맞게 bfs로 계산

// nxn 격자
// 비의 양이 변경되면서 안전한 영역의 최대 개수
