import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	private static int n, count = 0;
	private static int[] queenPos;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		countNQueen();
		System.out.println(count);
	}

	private static void countNQueen() {
		queenPos = new int[n + 1];
		backtracking(1);
	}

	private static void backtracking(int c) {
		if (c > n) {
			count++;
			return;
		}
		for (int r = 1; r <= n; r++) {
			if (canPos(r, c)) {
				queenPos[c] = r;
				backtracking(c + 1);
				queenPos[c] = 0;
			}
		}
	}

	private static boolean canPos(int r, int c) {
		for (int i = 1; i < c; i++) {
			if (queenPos[i] == r || Math.abs(queenPos[i] - r) == c - i) {
				return false;
			}
		}
		return true;
	}
}

// 완탐하면 O(n!)
// backtracking으로 가지치기
