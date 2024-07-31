import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	private static final int MAX_SIZE = 100001;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int source = Integer.parseInt(st.nextToken());
		int destination = Integer.parseInt(st.nextToken());

		System.out.println(calculateFindingTime(source, destination));
	}

	private static int calculateFindingTime(int source, int destination) {
		boolean[] visited = new boolean[MAX_SIZE];

		Deque<Tagger> taggerQueue = new ArrayDeque<>();
		taggerQueue.addLast(new Tagger(source, 0));
		visited[source] = true;

		while (!taggerQueue.isEmpty()) {
			Tagger tagger = taggerQueue.removeFirst();
			if (tagger.position == destination) {
				return tagger.time;
			}

			for (int i = 0; i < 3; i++) {
				int nextPosition;
				if (i == 0) {
					nextPosition = tagger.position - 1;
				} else if (i == 1) {
					nextPosition = tagger.position + 1;
				} else {
					nextPosition = tagger.position * 2;
				}

				if (isValid(nextPosition) && !visited[nextPosition]) {
					visited[nextPosition] = true;
					taggerQueue.addLast(new Tagger(nextPosition, tagger.time + 1));
				}
			}
		}
		return 0;
	}

	private static boolean isValid(int position) {
		return position >= 0 && position < MAX_SIZE;
	}

	private static class Tagger {
		private final int position;
		private final int time;

		Tagger(int position, int time) {
			this.position = position;
			this.time = time;
		}
	}
}

// 가장 최소의 시간을 구하자.
// k로 갈 수 있는 숫자들을 가장 가까운 순으로 나열해서 visited 처리하면 O(100000)이내에 처리할 수 있다.
// queue 저장 시 level도 같이 저장해서 k가 출력되면 바로 level을 리턴
