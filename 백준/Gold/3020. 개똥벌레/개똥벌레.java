import java.awt.*;
import java.sql.SQLOutput;
import java.time.Month;
import java.util.*;
import java.io.*;
import java.util.List;

import javax.print.attribute.standard.NumberOfDocuments;

import com.sun.source.tree.Tree;

class Main {
	public static void main(String[] args) throws Exception {
		// n, h 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());

		int[] up = new int[n/2];
		int[] down = new int[n/2];
		for (int i = 0; i < n; i++) {
			int height = Integer.parseInt(br.readLine());
			if (i % 2 == 0) {
				up[i / 2] = height;
			}else{
				down[i/2] = height;
			}
		}

		// up 정렬 o(nlogn)
		// down 정렬 o(nlogn)
		Arrays.sort(up);
		Arrays.sort(down);

		// h일때 장애물 개수 구하기 O(h)
		int[] numberOfObstacles = new int[h + 1];
		for (int i = 1; i <= h; i++) {
			int upFirstIdx = binarySearch(i, up);
			numberOfObstacles[i] += n / 2 - upFirstIdx;

			int downFirstIdx = binarySearch(i, down);
			numberOfObstacles[h - i + 1] += n / 2 - downFirstIdx;
		}

		// h배열 돌며 최솟값 구하기 O(h)
		int minObstacles = n;
		for (int i = 1; i <= h; i++) {
			minObstacles = Math.min(minObstacles, numberOfObstacles[i]);
		}

		// 최솟값과 같은것의 개수 구하기 O(h)
		int count = 0;
		for (int i = 1; i <= h; i++) {
			if (numberOfObstacles[i] == minObstacles) {
				count++;
			}
		}
		System.out.printf("%d %d", minObstacles, count);
	}

	// 들어갈 수 있는 가장 앞의 값
	private static int binarySearch(int n, int[] obstacles) {
		int start = 0, end = obstacles.length - 1;
		while(start <= end) {
			int mid = (start + end) / 2;
			if (obstacles[mid] < n) {
				start = mid + 1;
			}else {
				end = mid - 1;
			}
		}
		return start;
	}
}

// 완탐
// O(h * n) -> 시간초과

// 동굴 가로 n(짝수), 세로 h
// 석순 -> 종유석
// 파괴해야하는 장애물 최솟값
