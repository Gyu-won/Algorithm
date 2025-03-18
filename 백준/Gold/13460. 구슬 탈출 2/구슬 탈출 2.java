import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

class Main {

	private static final int[] dr = new int[] {0, 1, 0, -1};
	private static final int[] dc = new int[] {1, 0, -1, 0};

	private static int n, m, holeR, holeC;
	private static char[][] board;
	private static boolean[] success = new boolean[2];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// n, m 입력 3~10
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		// board 입력
		board = new char[n][m];
		int redStartR = 0, redStartC = 0, blueStartR = 0, blueStartC = 0;
		for (int r = 0; r < n; r++) {
			String line = br.readLine();
			for (int c = 0; c < m; c++) {
				board[r][c] = line.charAt(c);
				if (board[r][c] == 'R') {
					redStartR = r;
					redStartC = c;
					board[r][c] = '.';
				} else if (board[r][c] == 'B') {
					blueStartR = r;
					blueStartC = c;
					board[r][c] = '.';
				} else if (board[r][c] == 'O') {
					holeR = r;
					holeC = c;
					board[r][c] = '.';
				}
			}
		}

		// bfs로 모든 경우 다하기
		int result = bfs(redStartR, redStartC, blueStartR, blueStartC);
		System.out.println(result);
	}

	private static int bfs(int redStartR, int redStartC, int blueStartR, int blueStartC) {
		Deque<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {1, redStartR, redStartC, blueStartR, blueStartC});

		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			int time = current[0];
			int redR = current[1];
			int redC = current[2];
			int blueR = current[3];
			int blueC = current[4];

			for (int d = 0; d < 4; d++) {
				success[0] = false;
				success[1] = false;
				int[] nextRedPos = new int[2];
				int[] nextBluePos = new int[2];
				if (d % 2 == 0 && redR == blueR) {
					if (d == 0) {
						if (redC < blueC) {
							nextBluePos = move(blueR, blueC, redR, redC, d, 1);
							nextRedPos = move(redR, redC, nextBluePos[0], nextBluePos[1], d, 0);
						} else {
							nextRedPos = move(redR, redC, blueR, blueC, d, 0);
							nextBluePos = move(blueR, blueC, nextRedPos[0], nextRedPos[1], d, 1);
						}
					} else {
						if (redC < blueC) {
							nextRedPos = move(redR, redC, blueR, blueC, d, 0);
							nextBluePos = move(blueR, blueC, nextRedPos[0], nextRedPos[1], d, 1);
						} else {
							nextBluePos = move(blueR, blueC, redR, redC, d, 1);
							nextRedPos = move(redR, redC, nextBluePos[0], nextBluePos[1], d, 0);
						}
					}
				} else if (d % 2 == 1 && redC == blueC) {
					if (d == 1) {
						if (redR < blueR) {
							nextBluePos = move(blueR, blueC, redR, redC, d, 1);
							nextRedPos = move(redR, redC, nextBluePos[0], nextBluePos[1], d, 0);
						} else {
							nextRedPos = move(redR, redC, blueR, blueC, d, 0);
							nextBluePos = move(blueR, blueC, nextRedPos[0], nextRedPos[1], d, 1);
						}
					} else {
						if (redR < blueR) {
							nextRedPos = move(redR, redC, blueR, blueC, d, 0);
							nextBluePos = move(blueR, blueC, nextRedPos[0], nextRedPos[1], d, 1);
						} else {
							nextBluePos = move(blueR, blueC, redR, redC, d, 1);
							nextRedPos = move(redR, redC, nextBluePos[0], nextBluePos[1], d, 0);
						}
					}
				} else {
					nextRedPos = move(redR, redC, blueR, blueC, d, 0);
					nextBluePos = move(blueR, blueC, nextRedPos[0], nextRedPos[1], d, 1);
				}

				if (success[1]) {
					continue;
				}
				if (success[0]) {
					return time;
				}
				if (time != 10) {
					queue.add(new int[] {time + 1, nextRedPos[0], nextRedPos[1], nextBluePos[0],
						nextBluePos[1]});
				}
			}
		}
		return -1;
	}

	private static boolean canMove(int r, int c, int differentR, int differentC, int d) {
		r += dr[d];
		c += dc[d];
		return r >= 0 && r < n && c >= 0 && c < m && board[r][c] != '#' && !(r == differentR
			&& c == differentC);
	}

	private static int[] move(int r, int c, int differentR, int differentC, int d, int color) {
		while (canMove(r, c, differentR, differentC, d)) {
			r += dr[d];
			c += dc[d];

			if (r == holeR && c == holeC) {
				success[color] = true;
				return new int[] {0, 0};
			}
		}
		return new int[] {r, c};
	}
}
