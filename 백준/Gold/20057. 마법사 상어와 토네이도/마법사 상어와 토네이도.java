import java.awt.*;
import java.sql.SQLOutput;
import java.time.Month;
import java.util.*;
import java.io.*;
import java.util.List;

class Main {

	private static final int[] dr = new int[]{0, 1, 0, -1};
	private static final int[] dc = new int[]{-1, 0, 1, 0};
	private static final int[][][] spreadRate = new int[][][]{
		{
			{0, 0, 2, 0, 0},
			{0, 10, 7, 1, 0},
			{5, 0, 0, 0, 0},
			{0, 10, 7, 1, 0},
			{0, 0, 2, 0, 0}
		},
		{
			{0, 0, 0, 0, 0},
			{0, 1, 0, 1, 0},
			{2, 7, 0, 7, 2},
			{0, 10, 0, 10, 0},
			{0, 0, 5, 0, 0}
		},
		{
			{0, 0, 2, 0, 0},
			{0, 1, 7, 10, 0},
			{0, 0, 0, 0, 5},
			{0, 1, 7, 10, 0},
			{0, 0, 2, 0, 0}
		},
		{
			{0, 0, 5, 0, 0},
			{0, 10, 0, 10, 0},
			{2, 7, 0, 7, 2},
			{0, 1, 0, 1, 0},
			{0, 0, 0, 0, 0}
		}
	};

	private static int n, outSand;
	private static int[][] board;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());

		board = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		boolean[][] visited = new boolean[n][n];
		int r = n/2, c = n/2, direction = 0;
		visited[r][c] = true;

		while (true) {
			int currentD = direction % 4;
			int nextR = r + dr[currentD];
			int nextC = c + dc[currentD];

			if (visited[nextR][nextC]){
				direction += 3;
				currentD = direction % 4;
				nextR = r + dr[currentD];
				nextC = c + dc[currentD];

				if (!isBoard(nextR, nextC)){
					break;
				}
			}
			r = nextR;
			c = nextC;
			visited[r][c] = true;
			spread(currentD, r, c);
			direction++;
		}

		System.out.println(outSand);
	}

	private static boolean isBoard(int r, int c) {
		return r >= 0 && r < n && c >= 0 && c < n;
	}

	private static void spread(int d, int r, int c) {
		int totalSand = board[r][c];
		for (int i = r - 2; i <= r + 2; i++) {
			for (int j = c - 2; j <= c + 2; j++) {
				int rate = spreadRate[d][i -r + 2][j-c +2];
				int addedSand = totalSand * rate / 100;
				if (isBoard(i, j)){
					board[i][j] += addedSand;
				}else{
					outSand += addedSand;
				}
				board[r][c] -= addedSand;
			}
		}

		int nextR = r + dr[d];
		int nextC = c + dc[d];
		if (isBoard(nextR, nextC)){
			board[nextR][nextC] += board[r][c];
		} else{
			outSand += board[r][c];
		}
	}
}

// n 입력
// int[n][n] board 입력
// visited[n/2][n/2] = true
// 처음 direction = 0
// while ()
	// direction % 4로 다음 위치 계산
	// visited[] false 면 -> 이동, spread, 방향 ++
	// visited[] true 면 (direction - 1) % 4 로 계산
		// canMove() true 면 이동, spread
		// canMove() false면 종료
// outSand 출력;

// spread(direction, r, c)
	// 방향에 따라 모래 퍼뜨리기
		// 범위 확인
			// 벗어나면 outSand에 더하기
			// 안이면 해당 값 더하기
		// y - restSand를 a로 이동


// 구현 O(n*n25)
