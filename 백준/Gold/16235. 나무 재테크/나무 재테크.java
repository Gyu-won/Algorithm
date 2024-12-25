import java.util.*;
import java.io.*;

class Main {

	private static final int DIRECTION = 8;
	private static final int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};
	private static final int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1};

	private static int n;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int[][] a = new int[n+1][n+1];
		int[][] nutrient = new int[n+1][n+1];
		List<Integer>[][] treeMap = new ArrayList[n+1][n+1];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				a[i][j] = Integer.parseInt(st.nextToken());
				nutrient[i][j] = 5;
				treeMap[i][j] = new ArrayList<>();
			}
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());

			treeMap[r][c].add(age);
		}

		for (int i = 0; i < k; i++) {
			springAndSummer(nutrient, treeMap);
			fall(treeMap);
			winter(a, nutrient);
		}

		int totalTree = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				totalTree += treeMap[i][j].size();
			}
		}
		System.out.println(totalTree);
	}

	private static void springAndSummer(int[][] nutrient, List<Integer>[][] treeMap){
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++){
				int idx = 0;
				List<Integer> newTreeMap = new ArrayList<>();
				for (; idx < treeMap[i][j].size(); idx++) {
					int treeAge = treeMap[i][j].get(idx);
					if (treeAge > nutrient[i][j]) {
						break;
					}
					nutrient[i][j] -= treeAge;
					newTreeMap.add(treeAge + 1);
				}
				for (; idx < treeMap[i][j].size(); idx++) {
					int treeAge = treeMap[i][j].get(idx);
					nutrient[i][j] += treeAge/2;
				}
				treeMap[i][j] = newTreeMap;
			}
		}
	}

	private static void fall(List<Integer>[][] treeMap) {
		int[][] newTree = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				for (int treeAge : treeMap[i][j]) {
					if (treeAge % 5 == 0) {
						spread(i, j, newTree);
					}
				}
			}
		}
		plant(newTree, treeMap);
	}

	private static void winter(int[][] a, int[][] nutrient) {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				nutrient[i][j] += a[i][j];
			}
		}
	}

	private static void spread(int r, int c, int[][] newTree){
		for (int d = 0; d < DIRECTION; d++) {
			int nextR = r + dr[d];
			int nextC = c + dc[d];

			if (nextR > 0 && nextR <= n && nextC > 0 && nextC <= n) {
				newTree[nextR][nextC]++;
			}
		}
	}

	private static void plant(int[][] newTree, List<Integer>[][] treeMap) {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (newTree[i][j] > 0) {
					List<Integer> trees = Collections.nCopies(newTree[i][j], 1);
					treeMap[i][j].addAll(0, trees);
				}
			}
		}
	}
}

// n, m, k 입력
// Deque<Integer>[n][n] map 입력
// int[n][n] gradient 입력
// m번 나무 생성
// k년 반복
	// grow: 양분이랑 비교해서 + 1,
	// die: grow 종료되면 removeLast 하면서 양분에 더하기
	// spread: 이중 for문 돌며 번식하기
	// addGradient: 양분 추가

// 15:00
// 봄: 나이 어린 나무부터 나이만큼 양분 먹고 나이 1 증가, 못먹으면 죽음
// 여름: 죽은 나무 나이 / 2 값이 양분으로 추가
// 가을: 나이가 5의 배수이면 인접 8개 칸에 번식
// 겨울: A[r][c] 만큼 양분 추가
// 결과: k년이 지난 후 살아있는 나무 개수
// n, m, k
