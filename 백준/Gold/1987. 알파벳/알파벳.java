import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int numberOfDirection = 4;
	private static final int[] directionX = {0, 1, 0, -1};
	private static final int[] directionY = {-1, 0, 1, 0};

	private static int r, c, maxCount = 0;
	private static boolean[] visited;
	private static int[][] board;

	public static void main(String[] args) throws IOException {
		// r, c 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		// board 입력
		board = new int[r][c];
		for (int i = 0; i < r; i++) {
			String row = br.readLine();
			for (int j = 0; j < row.length(); j++) {
				int space = row.charAt(j) - 'A';
				board[i][j] = space;
			}
		}

		countMaxNumberOfSpace();
		System.out.println(maxCount);
	}

	private static void countMaxNumberOfSpace() {
		visited = new boolean[26];
		dfs(0, 0, 1);
	}

	private static void dfs(int x, int y, int count) {
		int spaceValue = board[y][x];
		visited[spaceValue] = true;
		if (maxCount < count) {
			maxCount = count;
		}

		for (int d = 0; d < numberOfDirection; d++) {
			int nextX = x + directionX[d];
			int nextY = y + directionY[d];

			if (isValid(nextX, nextY) && !visited[board[nextY][nextX]]) {
				dfs(nextX, nextY, count + 1);
				visited[board[nextY][nextX]] = false;
			}
		}
	}

	private static boolean isValid(int x, int y) {
		return x >= 0 && x < c && y >= 0 && y < r;
	}
}
