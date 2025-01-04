import java.awt.*;
import java.sql.SQLOutput;
import java.time.Month;
import java.util.*;
import java.io.*;
import java.util.List;

class Main {

	private static int n, totalPopulation = 0;
	private static int[][] population;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		StringTokenizer st;
		population = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				population[i][j] = Integer.parseInt(st.nextToken());
				totalPopulation += population[i][j];
			}
		}

		int minDifference = Integer.MAX_VALUE;
		for (int x = 1; x <= n; x++){
			for (int y = 1; y <= n; y++) {
				for (int d1 = 1; d1 <= n; d1++) {
					for (int d2 = 1; d2 < n; d2++){
						if(isValid(x, y, d1, d2)){
							int[] votePopulation = calculateVotePopulation(x, y, d1, d2);
							int difference = calculateMaxDifference(votePopulation);
							minDifference = Math.min(minDifference,difference);
						}
					}
				}
			}
		}
		System.out.println(minDifference);
	}

	private static boolean isValid(int x, int y, int d1, int d2) {
		return x + d1 + d2 <= n && 1 <= y - d1 && y+d2 <= n;
	}

	private static int[] calculateVotePopulation(int x, int y, int d1, int d2) {
		int[] votePopulation = new int[6];
		votePopulation[5] = totalPopulation;

		// int[][] check = new int[n + 1][n + 1];
		for (int r = 1; r < x + d1; r++) {
			for (int c = 1; c <= y; c++) {
				if (r + c < x + y){
					votePopulation[1] += population[r][c];
					votePopulation[5] -= population[r][c];
					// check[r][c] = 1;
					continue;
				}
			}
		}

		for (int r = 1; r <= x + d2; r++) {
			for (int c = y + 1; c <= n; c++) {
				if (r - x < c - y){
					votePopulation[2] += population[r][c];
					votePopulation[5] -= population[r][c];
					// check[r][c] = 2;
				}
			}
		}

		for (int r = x + d1; r <= n; r++) {
			for (int c = 1; c < y - d1 + d2; c++) {
				if (c - y + d1 < r - x - d1){
					votePopulation[3] += population[r][c];
					votePopulation[5] -= population[r][c];
					// check[r][c] = 3;
				}
			}
		}

		for (int r = x + d2 + 1; r <= n; r++) {
			for (int c = y-d1+d2; c <= n; c++) {
				if (x + y + 2 * d2 < r + c) {
					votePopulation[4] += population[r][c];
					votePopulation[5] -= population[r][c];
					// check[r][c] = 4;
				}
			}
		}

		// for (int i = 1; i <= n; i++) {
		// 	for (int j = 1; j <= n; j++) {
		// 		System.out.printf("%d ", check[i][j]);
		// 	}
		// 	System.out.println();
		// }
		// System.out.println("-----------------");

		return votePopulation;
	}

	private static int calculateMaxDifference(int[] votePopulation){
		int maxPopulation = votePopulation[1];
		int minPopulation = votePopulation[1];
		for (int i = 2; i <= 5; i++) {
			minPopulation = Math.min(minPopulation, votePopulation[i]);
			maxPopulation = Math.max(maxPopulation, votePopulation[i]);
		}
		return maxPopulation - minPopulation;
	}
}

// x, y 구하기
	// d1, d2 구하기
		// calculateVotePopulation(x, y, d1, d2)
		// min, max 값 차이 구하기
		// 값 없데이트

// 구현: O(n^6)
