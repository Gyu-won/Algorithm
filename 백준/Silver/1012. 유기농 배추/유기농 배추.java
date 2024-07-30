import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static final int numberOfDirection = 4;
	private static int[] directionX = {-1, 0, 1, 0};
	private static int[] directionY = {0, 1, 0, -1};

	private static int width, height;
	private static boolean[][] visited;
	private static int[][] field;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int numberOfTest = Integer.parseInt(br.readLine());

		StringBuilder result = new StringBuilder();
		for (int testNumber = 0; testNumber < numberOfTest; testNumber++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			width = Integer.parseInt(st.nextToken());
			height = Integer.parseInt(st.nextToken());

			field = new int[width][height];
			visited = new boolean[width][height];

			int numberOfCabbages = Integer.parseInt(st.nextToken());
			for (int cabbageNumber = 0; cabbageNumber < numberOfCabbages; cabbageNumber++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				field[x][y] = 1;
			}
			result.append(calculateNumberOfWorm());
			result.append("\n");
		}

		System.out.println(result.toString().trim());
	}

	private static int calculateNumberOfWorm() {
		int numberOfWorm = 0;

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (field[x][y] == 1 && !visited[x][y]) {
					numberOfWorm++;
					dfs(x, y);
				}
			}
		}
		return numberOfWorm;
	}

	private static void dfs(int x, int y) {
		if (x < 0 || x >= width || y < 0 || y >= height || visited[x][y] || field[x][y] == 0) {
			return;
		}

		visited[x][y] = true;
		for (int direction = 0; direction < numberOfDirection; direction++) {
			int nextX = x + directionX[direction];
			int nextY = y + directionY[direction];
			dfs(nextX, nextY);
		}
	}
}

// 모여있는 배추밭의 개수를 구하면 됨
// dfs나 bfs
// O(n * m * 4 * T)
