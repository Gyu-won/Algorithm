import java.awt.*;
import java.sql.SQLOutput;
import java.time.Month;
import java.util.*;
import java.io.*;
import java.util.List;

class Main {

	private static final int DIRECTION = 4;
	private static final int[] dr = new int[]{-1, 0, 1, 0};
	private static final int[] dc = new int[]{0, 1, 0, -1};
	private static final  List<List<Integer>> virusIndexList = new ArrayList<>();

	private static int n, m, virusSize;
	private static boolean[] combVisited;
	private static int[][] lab;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		lab = new int[n][n];
		List<int[]> inactiveVirusList = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				lab[i][j] = Integer.parseInt(st.nextToken());
				if (lab[i][j] == 2) {
					inactiveVirusList.add(new int[]{i, j});
				}
			}
		}

		virusSize = inactiveVirusList.size();
		combVisited = new boolean[virusSize];
		calculateCombination(0, 0);

		int minTime = Integer.MAX_VALUE;
		for (List<Integer> activeVirusIndexList: virusIndexList) {
			boolean[][] visited = new boolean[n][n];
			Deque<Virus> virusQueue = new ArrayDeque<>();
			for (int activeVirusIndex : activeVirusIndexList){
				int[] virus = inactiveVirusList.get(activeVirusIndex);

				virusQueue.addLast(new Virus(virus[0], virus[1], 0));
				visited[virus[0]][virus[1]] = true;
			}

			int time = calculateSpreadTime(virusQueue, visited);
			minTime = Math.min(time, minTime);

		}

		if (minTime == Integer.MAX_VALUE) {
			minTime = -1;
		}
		System.out.println(minTime);
	}

	private static int calculateSpreadTime(Deque<Virus> virusQueue, boolean[][] visited) {
		int time = 0;
		while(!virusQueue.isEmpty()) {
			if (isDone(visited)){
				return time;
			}
			while(!virusQueue.isEmpty()){
				Virus virus = virusQueue.getFirst();
				if (virus.time > time) {
					break;
				}
				virus = virusQueue.removeFirst();
				virus.spread(virusQueue, visited);
			}
			time++;
		}
		return Integer.MAX_VALUE;
	}

	private static boolean isDone(boolean[][] visited) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j] && lab[i][j] == 0){
					return false;
				}
			}
		}
		return true;
	}

	private static void calculateCombination(int start, int size) {
		if (size == m) {
			List<Integer> virusList = new ArrayList<>();
			for (int i = 0; i < start; i++) {
				if (combVisited[i]) {
					virusList.add(i);
				}
			}
			virusIndexList.add(virusList);
			return;
		}

		for (int i = start; i < virusSize; i++) {
			combVisited[i] = true;
			calculateCombination(i+1, size+1);
			combVisited[i] = false;
		}
	}

	static class Virus {
		private final int r;
		private final int c;
		private final int time;

		Virus(int r, int c, int time) {
			this.r = r;
			this.c = c;
			this.time = time;
		}

		private void spread(Deque<Virus> virusQueue, boolean[][] visited) {
			for (int i = 0; i < DIRECTION; i++) {
				int nextR = this.r + dr[i];
				int nextC = this.c + dc[i];

				if (canSpread(nextR, nextC) && !visited[nextR][nextC]) {
					virusQueue.addLast(new Virus(nextR, nextC, this.time+1));
					visited[nextR][nextC] = true;
				}
			}
		}

		private boolean canSpread(int r, int c) {
			return r >= 0 && r < n && c >= 0 && c < n && lab[r][c] != 1;
		}
	}
}

// 바이러스 선택 : 10 C m
// 시간 측정
	// while(true)
	// isDone: O(n*n) 돌면서 visited < 1 이면 false (1: 방문, 2: 비활성)
	// spread: queue에서 하나씩 뺴면서 spread, queue empty면 종료
		//	범위 벗어나거나 벽이면 못지나감, 비활성이면 똑같이
		// visited 0이면 1로 바꾸고 queue.add(r, c, time)
	// time++;
	// while문 종료 시 -1

// O(10 ^ 5 * 2500) 이내

// 16:02
// 상하좌우로 1초동안 활성 됨, m개를 활성으로
// nxn 크기 연구소 (0: 빈칸, 1: 벽, 2: 바이러스)


