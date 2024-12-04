import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	private static final int numberOfDirection = 4;
	private static final int[] dr = {-1, 0, 1, 0};
	private static final int[] dc = {0, 1, 0, -1};

	private static int n;
	private static double sharkSize = 2.0;
	private static int[][] board;

	public static void main(String[] args) throws IOException {
		// n 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		// board 입력
		StringTokenizer st;
		int sharkRow = -1, sharkCol = -1;
		board = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == 9) {
					sharkRow = i;
					sharkCol = j;
				}
			}
		}

		int totalTime = 0;
		while (true) {
			Fish fish = findTargetFish(sharkRow, sharkCol);
			if (fish.distance == Integer.MAX_VALUE) {
				break;
			}
			board[sharkRow][sharkCol] = 0;
			sharkRow = fish.r;
			sharkCol = fish.c;
			board[sharkRow][sharkCol] = 9;
			sharkSize += 1 / Math.floor(sharkSize);
			totalTime += fish.distance;
		}
		System.out.println(totalTime);
	}

	private static Fish findTargetFish(int sharkRow, int sharkCol) {
		int fishRow = Integer.MAX_VALUE, fishCol = Integer.MAX_VALUE, minDistance = Integer.MAX_VALUE;
		boolean[][] visited = new boolean[n][n];
		Deque<Fish> queue = new ArrayDeque<>();

		visited[sharkRow][sharkCol] = true;
		queue.addLast(new Fish(sharkRow, sharkCol, 0));

		while (!queue.isEmpty()) {
			Fish fish = queue.removeFirst();
			if (minDistance <= fish.distance) {
				break;
			}

			for (int d = 0; d < numberOfDirection; d++) {
				int nextRow = fish.r + dr[d];
				int nextCol = fish.c + dc[d];

				if (isValid(nextRow, nextCol) && canMove(nextRow, nextCol) && !visited[nextRow][nextCol]) {
					if (canEat(nextRow, nextCol)){
						minDistance = fish.distance + 1;
						if (nextRow < fishRow){
							fishRow = nextRow;
							fishCol = nextCol;
						}
						else if (nextRow == fishRow && nextCol < fishCol){
							fishCol = nextCol;
						}
					}
					visited[nextRow][nextCol] = true;
					queue.addLast(new Fish(nextRow, nextCol, fish.distance + 1));
				}
			}
		}
		return new Fish(fishRow, fishCol, minDistance);
	}

	private static boolean canEat(int r, int c) {
		int fishSize = board[r][c];
		return fishSize > 0 && fishSize < Math.floor(sharkSize);
	}

	private static boolean isValid(int r, int c) {
		return r >= 0 && r < n && c >= 0 && c < n;
	}

	private static boolean canMove(int r, int c) {
		return board[r][c] <= Math.floor(sharkSize);
	}

	private static class Fish {
		private final int r;
		private final int c;
		private final int distance;

		Fish(int r, int c, int distance) {
			this.r = r;
			this.c = c;
			this.distance = distance;
		}
	}
}
