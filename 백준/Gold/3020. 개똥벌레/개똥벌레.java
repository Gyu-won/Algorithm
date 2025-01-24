import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws IOException {
		// n, h 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());

		// obstacles 입력
		int[] obstacles = new int[n];
		for (int i = 0; i < n; i++) {
			obstacles[i] = Integer.parseInt(br.readLine());
		}

		// 가로로 확인하며 : O(n)
		int[] numberOfObstacles = new int[h + 1];
		for (int i = 0; i < n; i++) {
			int obstacleLength = obstacles[i];
			if (i % 2 == 0) {
				// 석순이면 [0]++, [l]--
				numberOfObstacles[0]++;
				numberOfObstacles[obstacleLength]--;
			} else {
				//종유석이면 [h-l]++, [h]--
				numberOfObstacles[h - obstacleLength]++;
				numberOfObstacles[h]--;
			}
		}

		// 합배열 확인하며 최솟값과 그 개수 구하기: O(h)
		int minObstacle = numberOfObstacles[0];
		int numberOfSection = 1;
		for (int i = 1; i < h; i++) {
			numberOfObstacles[i] += numberOfObstacles[i - 1];
			if (numberOfObstacles[i] <= minObstacle) {
				if (numberOfObstacles[i] < minObstacle) {
					minObstacle = numberOfObstacles[i];
					numberOfSection = 0;
				}
				numberOfSection++;
			}
		}
		System.out.printf("%d %d", minObstacle, numberOfSection);
	}
}

