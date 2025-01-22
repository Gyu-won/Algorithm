import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static final int RATIO_DIVISOR = 100;
	private static final int[] dr = new int[] {0, 1, 0, -1};
	private static final int[] dc = new int[] {-1, 0, 1, 0};
	private static final int[][][] SPREAD_RATIO = new int[][][] {
		{
			{0, 0, 2, 0, 0},
			{0, 10, 7, 1, 0},
			{5, 0, 0, 0, 0},
			{0, 10, 7, 1, 0},
			{0, 0, 2, 0, 0},
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
			{0, 0, 0, 0, 0},
		}
	};

	private static int n, r, c;
	private static int[][] sand;

	public static void main(String[] args) throws Exception {
		// n 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		// sand[r][c] 입력
		StringTokenizer st;
		sand = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				sand[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// move(): O(n^2)
		r = n / 2;
		c = n / 2;
		int totalOutedSand = 0, direction = 0, distance = 0;
		for (int i = 0; i < 2 * n - 1; i++) {
			if (i % 2 == 0 && i < 2 * (n - 1)) {
				distance++;
			}
			for (int j = 0; j < distance; j++) {
				move(direction);

				// wind(direction): O(25)
				totalOutedSand += wind(direction);
			}
			direction = (direction + 1) % 4;
		}
		System.out.println(totalOutedSand);
	}

	private static void move(int d) {
		r += dr[d];
		c += dc[d];
	}

	private static int wind(int d) {
		int outedSand = 0;
		int originalSand = sand[r][c];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				int spreadedSand = originalSand * SPREAD_RATIO[d][i][j] / RATIO_DIVISOR;

				int realR = r - 2 + i;
				int realC = c - 2 + j;
				if (isOut(realR, realC)) {
					outedSand += spreadedSand;
				} else {
					sand[realR][realC] += spreadedSand;
				}
				sand[r][c] -= spreadedSand;
			}
		}
		int alphaR = r + dr[d];
		int alphaC = c + dc[d];
		if (isOut(alphaR, alphaC)) {
			outedSand += sand[r][c];
		} else {
			sand[alphaR][alphaC] += sand[r][c];
		}
		sand[r][c] = 0;
		return outedSand;
	}

	private static boolean isOut(int nextR, int nextC) {
		return nextR < 0 || nextC < 0 || nextR >= n || nextC >= n;
	}
}
