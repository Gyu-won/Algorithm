import java.util.ArrayList;
import java.util.List;

class Solution {
    private static List<List<Integer>> graph;
    private static boolean[] visited;

    public int solution(int n, int[][] computers) {
        // 방문 배열 생성
        visited = new boolean[n];

        // 그래프 표현
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add((new ArrayList<>()));
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && computers[i][j] == 1) {
                    graph.get(i).add(j);
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                answer++;
                visited[i] = true;
                findNetwork(i);
            }
        }
        return answer;
    }

    private void findNetwork(int currentComputer) {
        for (int computer : graph.get(currentComputer)) {
            if (!visited[computer]) {
                visited[computer] = true;
                findNetwork(computer);
            }
        }
    }
}