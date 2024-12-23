import java.util.*;
import java.io.*;

class Main {

	private static int n, m, minDangerZone = 64;
	private static int[][] map;
	private static final List<CCTV> cctvList = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		for (int i = 0; i < n; i++){
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > 0 && map[i][j] < 6) {
					cctvList.add(new CCTV(map[i][j], i, j));
				}
			}
		}


		calculateMinDangerZone(0);
		System.out.println(minDangerZone);
	}

	private static void calculateMinDangerZone(int idx) {
		if (idx == cctvList.size()) {
			int numberOfDangerZone = calculateNumberOfDangerZone();
			minDangerZone = Math.min(minDangerZone, numberOfDangerZone);
			return;
		}

		CCTV cctv = cctvList.get(idx);
		if (cctv.number == 1){
			for (int d = 0; d < 4; d++) {
				cctv.monitor(d);
				calculateMinDangerZone(idx+1);
				cctv.release(d);
			}
		} else if (cctv.number == 2) {
			for (int d = 0; d < 2; d++) {
				cctv.monitor(d);
				cctv.monitor(d+2);
				calculateMinDangerZone(idx+1);
				cctv.release(d+2);
				cctv.release(d);
			}
		} else if (cctv.number == 3) {
			for (int d = 0; d < 4; d++) {
				cctv.monitor(d);
				cctv.monitor((d+1) % 4);
				calculateMinDangerZone(idx+1);
				cctv.release(d);
				cctv.release((d+1) % 4);
			}
		} else if (cctv.number == 4) {
			for (int d = 0; d < 4; d++){
				cctv.monitor(d);
				cctv.monitor((d+3)%4);
				cctv.monitor((d+1)%4);
				calculateMinDangerZone(idx+1);
				cctv.release(d);
				cctv.release((d+3)%4);
				cctv.release((d+1)%4);
			}
		} else{
			cctv.monitor(0);
			cctv.monitor(1);
			cctv.monitor(2);
			cctv.monitor(3);
			calculateMinDangerZone(idx+1);
			cctv.release(0);
			cctv.release(1);
			cctv.release(2);
			cctv.release(3);
		}
	}

	private static int calculateNumberOfDangerZone(){
		int numberOfDangerZone = 0;
		for (int i = 0; i < n; i++){
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 0) {
					numberOfDangerZone++;
				}
			}
		}
		return numberOfDangerZone;
	}

	static class CCTV {
		private final int number;
		private final int r;
		private final int c;

		CCTV(int number, int r, int c) {
			this.number = number;
			this.r = r;
			this.c = c;
		}

		private void monitor(int direction){
			if (direction == 0) {
				for (int currentR = this.r-1; currentR >= 0; currentR--){
					if (map[currentR][this.c] == 6) {
						return;
					}
					if (map[currentR][this.c] < 1) {
						map[currentR][this.c]--;
					}
				}
			} else if (direction == 1) {
				for (int currentC = this.c+1; currentC < m; currentC++) {
					if (map[this.r][currentC] == 6) {
						return;
					}
					if (map[this.r][currentC] < 1) {
						map[this.r][currentC]--;
					}
				}
			} else if (direction == 2) {
				for (int currentR = this.r+1; currentR < n; currentR++){
					if (map[currentR][this.c] == 6) {
						return;
					}
					if (map[currentR][this.c] < 1) {
						map[currentR][this.c]--;
					}
				}
			} else if (direction == 3) {
				for (int currentC = this.c-1; currentC >= 0; currentC--) {
					if (map[this.r][currentC] == 6) {
						return;
					}
					if (map[this.r][currentC] < 1) {
						map[this.r][currentC]--;
					}
				}
			}
		}

		private void release(int direction){
			if (direction == 0) {
				for (int currentR = this.r-1; currentR >= 0; currentR--){
					if (map[currentR][this.c] == 6) {
						return;
					}
					if (map[currentR][this.c] < 0) {
						map[currentR][this.c]++;
					}
				}
			} else if (direction == 1) {
				for (int currentC = this.c+1; currentC < m; currentC++) {
					if (map[this.r][currentC] == 6) {
						return;
					}
					if (map[this.r][currentC] < 0) {
						map[this.r][currentC]++;
					}
				}
			} else if (direction == 2) {
				for (int currentR = this.r+1; currentR < n; currentR++){
					if (map[currentR][this.c] == 6) {
						return;
					}
					if (map[currentR][this.c] < 0) {
						map[currentR][this.c]++;
					}
				}
			} else if (direction == 3) {
				for (int currentC = this.c-1; currentC >= 0; currentC--) {
					if (map[this.r][currentC] == 6) {
						return;
					}
					if (map[this.r][currentC] < 0) {
						map[this.r][currentC]++;
					}
				}
			}
		}
	}
}

// 10:52 -
// cctv 방향 정하기 4^8 = 2&16 dfs
	// 1번이면 4가지 monitorCCTV1
	// 2번이면 2가지
	// 3번이면 4가지
	// 4번이면 4가지
	// 5번이면 1가지
	// 볼 수 있는 곳 -1로 변경
// 빈칸 계산하기 O(n*m)
