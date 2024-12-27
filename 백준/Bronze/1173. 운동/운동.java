import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int goal = Integer.parseInt(st.nextToken());
		int minBeat = Integer.parseInt(st.nextToken());
		int maxBeat = Integer.parseInt(st.nextToken());
		int workout = Integer.parseInt(st.nextToken());
		int rest = Integer.parseInt(st.nextToken());

		int numberOfWorkout = countWorkout(goal, workout, maxBeat, rest, minBeat);
		System.out.println(numberOfWorkout);
	}

	private static int countWorkout(int goal, int workout, int maxBeat, int rest, int minBeat) {
		int time = 0, numberOfWorkout = 0, currentBeat = minBeat;
		if (maxBeat - minBeat < workout) {
			return -1;
		}
		while (numberOfWorkout < goal) {
			if (currentBeat + workout <= maxBeat) {
				currentBeat += workout;
				numberOfWorkout++;
			} else{
				currentBeat = Math.max(currentBeat - rest, minBeat);
			}
			time++;
		}
		return time;
	}
}

// 그리디
// 못하는 경우: 더하지도 못하고, 빼지도 못하는 경우
// 매 세트마다 확인 후 할 수 있으면 최대한 운동
// 최대: O(152 * 200)

// 입력
// while: 운동횟수 < t
	// m + t <= M : 운동, continue
	// m - t >= m : 휴식, continue
	// return -1;

// 09:06
// 결과: 최솟값 구하기
