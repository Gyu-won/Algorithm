import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());

		int[][] map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int numberOfRoad = 0;
		for (int i = 0; i < n; i++) {
			if (isHorizonRoad(n, l, i, map)) {
				numberOfRoad++;
			}
			if (isVerticalRoad(n, l, i, map)) {
				numberOfRoad++;
			}
		}
		System.out.println(numberOfRoad);
	}

	private static boolean isHorizonRoad(int n, int l, int r, int[][] map) {
		int sameHeight = 1;
		for (int c = 1; c < n; c++) {
			int difference = Math.abs(map[r][c] - map[r][c-1]);
			if (difference > 1) {
				return false;
			}

			if (difference == 0) {
				sameHeight++;
				continue;
			}

			if (map[r][c] < map[r][c-1]) {
				if (c+l > n) {
					return false;
				}
				for (int roadC = c + 1; roadC < c + l; roadC++) {
					if (map[r][roadC] != map[r][c]) {
						return false;
					}
				}
				sameHeight = 0;
				c += l - 1;
			} else {
				if (sameHeight < l) {
					return false;
				}
				sameHeight = 1;
			}
		}
		return true;
	}

	private static boolean isVerticalRoad(int n, int l, int c, int[][] map) {
		int sameHeight = 1;
		for (int r = 1; r < n; r++) {
			int difference = Math.abs(map[r][c] - map[r-1][c]);
			if (difference > 1) {
				return false;
			}

			if (difference == 0) {
				sameHeight++;
				continue;
			}

			if (map[r][c] < map[r-1][c]) {
				if (r+l > n) {
					return false;
				}
				for (int roadR = r + 1; roadR < r + l; roadR++) {
					if (map[roadR][c] != map[r][c]) {
						return false;
					}
				}
				sameHeight = 0;
				r += l - 1;
			} else {
				if (sameHeight < l) {
					return false;
				}
				sameHeight = 1;
			}
		}
		return true;
	}
}

// n, l 입력
// map 입력
// row 형태 길 여부
// col 형태 길 여부
	// 길 여부 확인
		// 같으면 통과 가능
		// 1 작으면 current+2가 <= n 인지, for문으로 l까지 같은지 확인
		// 1 크면 current-22가 >= 0 인지, sameHeight >= l 인지 확인

// 결과: 지나갈 수 있는 길의 개수 (int)
