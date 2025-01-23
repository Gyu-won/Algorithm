import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

class Main {

	private static final int[] dr = new int[] {-1, -1, -1, 0, 0, 1, 1, 1,};
	private static final int[] dc = new int[] {-1, 0, 1, -1, 1, -1, 0, 1,};

	public static void main(String[] args) throws IOException {
		// n, m, k 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		// nutrient[n][n] 입력
		int[][] nutrient = new int[n][n];
		for (int r = 0; r < n; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < n; c++) {
				nutrient[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// TreeBoard 입력
		Deque<Integer>[][] treeBoard = new ArrayDeque[n][n];
		List<Integer>[][] treeBoardForOrder = new ArrayList[n][n];
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				treeBoard[r][c] = new ArrayDeque<>();
				treeBoardForOrder[r][c] = new ArrayList<>();
			}
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int age = Integer.parseInt(st.nextToken());
			treeBoardForOrder[r][c].add(age);
		}

		// TreeBoard 정렬, ground 세팅
		int[][] ground = new int[n][n];
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				ground[r][c] = 5;
				Collections.sort(treeBoardForOrder[r][c]);
				for (int age : treeBoardForOrder[r][c]) {
					treeBoard[r][c].addLast(age);
				}
			}
		}

		// k 번 반복
		int year = 1;
		while (year <= k) {
			List<Tree> dieList = spring(n, ground, treeBoard);
			summer(ground, dieList);
			fall(n, treeBoard);
			winter(n, ground, nutrient);
			year++;
		}

		// 살아있는 나무 숫자 구하기
		int numberOfTree = 0;
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				numberOfTree += treeBoard[r][c].size();
			}
		}
		System.out.println(numberOfTree);
	}

	private static List<Tree> spring(int n, int[][] ground, Deque<Integer>[][] treeBoard) {
		// spring: 각 칸을 돌면서 어린 나무부터 나이만큼 나무 먹고, 나이 증가, 뒤에 나무는 그대로 제거 후 dieList insert
		List<Tree> dieList = new ArrayList<>();
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				Deque<Integer> treeQueue = treeBoard[r][c];
				int size = treeQueue.size();
				for (int t = 0; t < size; t++) {
					int age = treeQueue.removeFirst();
					if (age <= ground[r][c]) {
						ground[r][c] -= age;
						treeQueue.addLast(age + 1);
					} else {
						dieList.add(new Tree(r, c, age));
					}
				}
			}
		}
		return dieList;
	}

	private static void summer(int[][] ground, List<Tree> dieList) {
		// summer: dieList에 있는 나무들 ground[r][c]에 추가
		for (Tree tree : dieList) {
			ground[tree.r][tree.c] += tree.age / 2;
		}
	}

	private static void fall(int n, Deque<Integer>[][] treeBoard) {
		// fall: 나무 순회하면서(list) 5의 배수이면 옆에 deque의 앞에 추가
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				Deque<Integer> treeQueue = treeBoard[r][c];
				int size = treeQueue.size();
				for (int t = 0; t < size; t++) {
					int age = treeQueue.removeFirst();
					if (age % 5 == 0) {
						spread(n, r, c, treeBoard);
					}
					treeQueue.addLast(age);
				}
			}
		}
	}

	private static void spread(int n, int r, int c, Deque<Integer>[][] treeBoard) {
		for (int d = 0; d < 8; d++) {
			int nextR = r + dr[d];
			int nextC = c + dc[d];

			if (isValid(n, nextR, nextC)) {
				treeBoard[nextR][nextC].addFirst(1);
			}
		}
	}

	private static boolean isValid(int n, int r, int c) {
		return r >= 0 && r < n && c >= 0 && c < n;
	}

	private static void winter(int n, int[][] ground, int[][] nutrient) {
		// 겨울: 양분 추가 O(n * n)
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				ground[r][c] += nutrient[r][c];
			}
		}
	}

	// Tree: r, c, age
	private static class Tree {
		private final int r;
		private final int c;
		private final int age;

		Tree(int r, int c, int age) {
			this.r = r;
			this.c = c;
			this.age = age;
		}
	}
}

// O(전체 나무 순회 * k) < O(100000 * 1000)

// 봄(grow): 어린 나무부터 나이만큼 양분 먹기, 나이 1 증가, 못먹은 나무는 죽음
// 여름(die): 죽은 나무 나이 / 2가 양분으로 추가
// 가을(spread): 나무 나이 5의 배수이면 8개의 인접칸에 1인 나무 생김
// 겨울(feed): a[r][c] 만큼 양분 추가
