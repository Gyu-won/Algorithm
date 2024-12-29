import java.awt.*;
import java.sql.SQLOutput;
import java.time.Month;
import java.util.*;
import java.io.*;
import java.util.List;

class Main {

	private static int r, c, n;
	private static int[] dr, dc;
	private static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		map = new int[r][c];
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < c; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		n = Integer.parseInt(br.readLine());
		dr = new int[n];
		dc = new int[n];
		for (int i = 0; i < n; i++){
			st = new StringTokenizer(br.readLine());

			dr[i] = Integer.parseInt(st.nextToken());
			dc[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(goCompany());
	}

	private static int goCompany() {
		Deque<Point> pointQueue = new ArrayDeque<>();
		boolean[][] visited = new boolean[r][c];
		for (int i = 0; i < c; i++) {
			if (map[0][i] == 1){
				visited[0][i] = true;
				pointQueue.add(new Point(0, i, 0));
			}
		}

		while (!pointQueue.isEmpty()) {
			Point point = pointQueue.removeFirst();
			if (point.r == r - 1) {
				return point.step;
			}
			for (int d = 0; d < n; d++) {
				int nextR = point.r + dr[d];
				int nextC = point.c + dc[d];

				if (canMove(nextR, nextC) && !visited[nextR][nextC]){
					visited[nextR][nextC] = true;
					pointQueue.add(new Point(nextR, nextC, point.step+1));
				}
			}
		}
		return -1;
	}

	private static boolean canMove(int nextR, int nextC) {
		return nextR >= 0 && nextR < r && nextC >= 0 && nextC < c && map[nextR][nextC] == 1;
	}

	static class Point {
		private final int r;
		private final int c;
		private final int step;

		Point(int r, int c, int step) {
			this.r = r;
			this.c = c;
			this.step = step;
		}
	}
}

// 첫째줄에서 갈 수 있는 경로 모두 탐색 O(1000)
	// goCompany: dfs, 마지막 row 도착 시 minStep 초기화 O(1000 * 1000 * 10)
// minStep 그대로면 -1 출력: Integer.maxvalue

// 11:46
