import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

class Main {

	private static int n, minimumDifference = Integer.MAX_VALUE;
	private static boolean[] visited;
	private static int[][] scoreBoard;
	private static final Deque<Integer> team = new ArrayDeque<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		StringTokenizer st;
		visited = new boolean[n + 1];
		scoreBoard = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				scoreBoard[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		combination(0);
		System.out.println(minimumDifference);
	}

	private static void combination(int k) {
		if (team.size() == n / 2) {
			int[] teamScore = calculateScore();
			int scoreDifference = Math.abs(teamScore[0] - teamScore[1]);
			minimumDifference = Math.min(minimumDifference, scoreDifference);
			return;
		}

		for (int i = k + 1; i <= n; i++) {
			if (!visited[i]) {
				team.addLast(i);
				visited[i] = true;
				combination(i);
				visited[i] = false;
				team.removeLast();
			}
		}
	}

	private static int[] calculateScore() {
		int[] teamScore = new int[2];

		List<Integer> secondTeam = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			if (visited[i]) {
				for (int teamMember : team) {
					teamScore[0] += scoreBoard[i][teamMember];
				}
				continue;
			}
			secondTeam.add(i);
		}

		for (int member1 : secondTeam) {
			for (int member2 : secondTeam) {
				teamScore[1] += scoreBoard[member1][member2];
			}
		}
		return teamScore;
	}
}

// 입력받기
// 팀 짜기 O(20C10) -> 조합 담기 -> List를 리턴
// 점수 계산 -> List 순회하면서 점수 계산

// 모든 조합을 고려
// 결과: 능력치 차이의 최솟값 출력
