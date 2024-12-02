import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int numberOfDirection = 8;
	private static final int[] directionX = {0, 1, 1, 1, 0, -1, -1, -1};
	private static final int[] directionY = {-1, -1, 0, 1, 1, 1, 0, -1};

	private static int w, h;
	private static int[][] island;
	private static boolean[][] visited;

	public static void main(String[] argrs) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder result = new StringBuilder();

		while (true) {
			// w, h 입력받기
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());

			// 00 이면 종료
			if (w == 0 && h == 0) {
				break;
			}

			// island 입력받기
			island = new int[h][w];
			visited = new boolean[h][w];
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					island[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 섬 개수 구하기
			int numberOfIsland = countNumberOfIsland();
			result.append(numberOfIsland);
			result.append("\n");
		}

		// 결과 출력
		System.out.println(result.toString().trim());
	}

	private static int countNumberOfIsland() {
		int numberOfIsland = 0;

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (island[i][j] == 1 && !visited[i][j]) {
					numberOfIsland++;
					dfs(j, i);
				}
			}
		}
		return numberOfIsland;
	}

	private static void dfs(int x, int y) {
		visited[y][x] = true;
		for (int d = 0; d < numberOfDirection; d++) {
			int nextX = x + directionX[d];
			int nextY = y + directionY[d];

			if (isValid(nextX, nextY) && island[nextY][nextX] == 1 && !visited[nextY][nextX]) {
				dfs(nextX, nextY);
			}
		}
	}

	private static boolean isValid(int x, int y) {
		return x >= 0 && x < w && y >= 0 && y < h;
	}
}

// w, h <= 50
// O(w * h * testcase)

// 10:57
