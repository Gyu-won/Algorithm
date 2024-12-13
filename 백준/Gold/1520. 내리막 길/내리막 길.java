import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int[] dr = {-1, 0, 1, 0};
	private static final int[] dc = {0, 1, 0, -1};

	private static int m, n;
	private static boolean[][] visited;
	private static int[][] map, numberOfWay;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());

		map = new int[m][n];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		countNumberOfWay();
		System.out.println(numberOfWay[0][0]);
	}

	private static void countNumberOfWay() {
		visited = new boolean[m][n];
		numberOfWay = new int[m][n];

		visited[m - 1][n - 1] = true;
		numberOfWay[m - 1][n - 1] = 1;

		dfs(0, 0);
	}

	private static int dfs(int r, int c) {
		for (int d = 0; d < 4; d++) {
			int nextR = r + dr[d];
			int nextC = c + dc[d];

			if (isInMap(nextR, nextC) && map[r][c] > map[nextR][nextC]) {
				if (visited[nextR][nextC]) {
					numberOfWay[r][c] += numberOfWay[nextR][nextC];
				} else {
					visited[nextR][nextC] = true;
					numberOfWay[r][c] += dfs(nextR, nextC);
				}
			}
		}
		return numberOfWay[r][c];
	}

	private static boolean isInMap(int r, int c) {
		return r >= 0 && r < m && c >= 0 && c < n;
	}
}

// map 입력

// 현재 위치에서 갈 수 있는 곳 이동
// 방문 한 곳이면 visited의 숫자를 더하고 가지치기
// 방문 안했으면 계속 이어서 방문하기 -1
