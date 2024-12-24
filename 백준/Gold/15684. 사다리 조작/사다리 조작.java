import java.util.*;
import java.io.*;

class Main {
	private static int n, h, numberOfLadder = 0, answer = -1;
	private static boolean[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());

		map = new boolean[h+1][n+1];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			map[r][c] = true;
		}

		for (; numberOfLadder <=3 ; numberOfLadder++) {
			if (answer > -1) {
				break;
			}
			dfs(1, 1, 0);
		}
		System.out.println(answer);
	}

	private static void dfs(int r, int c, int count) {
		if (answer > -1) {
			return;
		}
		if (count == numberOfLadder) {
			if (isComplete()) {
				answer = numberOfLadder;
			}
			return;
		}

		for (int nextC = c; nextC < n; nextC++) {
			if(canInstall(r, nextC)) {
				map[r][nextC] = true;
				dfs(r, nextC+1, count+1);
				map[r][nextC] = false;
			}
		}

		for (int nextR = r+1; nextR <= h; nextR++) {
			for (int nextC = 1; nextC < n; nextC++){
				if (canInstall(nextR, nextC)) {
					map[nextR][nextC] = true;
					dfs(nextR, nextC+1, count+1);
					map[nextR][nextC] = false;
				}
			}
		}
	}

	private static boolean isComplete() {
		for (int startPoint = 1; startPoint <= n; startPoint++) {
			int currentCol = startPoint;
			for (int currentRow = 1; currentRow <= h; currentRow++) {
				if (map[currentRow][currentCol-1]) {
					currentCol--;
				} else if (map[currentRow][currentCol]) {
					currentCol++;
				}
			}
			if (currentCol != startPoint) {
				return false;
			}
		}
		return true;
	}

	private static boolean canInstall(int r, int c){
		return !map[r][c - 1] && !map[r][c + 1] && !map[r][c];
	}

}

// 완탐 O(h^3 * n * n * m)
// map 입력
// 0부터 해서 3까지 반복
	// 사다리 개수만큼 추가 dfs O(m * n C 3)
	// 올바른지 확인 O(n * m)

// 15:08
// n개 세로선, m개 가로선 / 가로선 놓을 수 있는 위치 h개
// 가로선이 연속하거나 접하면 안됨 i번 세로선 -> i번이 나오도록 가로선 갯수 최솟값
