import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    private static final int INF = Integer.MAX_VALUE;

    private static boolean invalidFlag = false;
    private static int[] roads;
    private static boolean[] visited;
    private static ArrayList<int[]>[] edges;

    public static void main(String[] args) throws IOException {
        System.out.println(new Solution().solution());
    }

    static class Solution {
        public String solution() throws IOException {
            // n(1-500), m(1-6000) 을 입력받는다.
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            // ArrayList<>[] edges 를 입력받는다.
            edges = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                edges[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());

                edges[s].add(new int[]{e, p});
            }

            // int[n] roads를 모두 무한대로 초기화 한다.
            roads = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                roads[i] = INF;
            }
            // roads[1] = 0
            roads[1] = 0;

            visited = new boolean[n + 1];
            visited[1] = true;

            // dfs
            dfs(1, 0);

            if (invalidFlag) {
                return "-1";
            }

            // 다 끝나고 INF 인 애들 -1로 변경한 후 리턴
            StringBuilder result = new StringBuilder();
            for (int i = 2; i <= n; i++) {
                if (roads[i] == INF) {
                    result.append(-1);
                } else {
                    result.append(roads[i]);
                }
                result.append("\n");
            }
            return result.toString().trim();
        }

        private void dfs(int current, int currentP) {
            for (int[] edge : edges[current]) {
                int dest = edge[0];
                int price = edge[1];

                // visited 했고, 그 경로 p + [1] - route[경로 도착지] < 0이면 return -1
                if (currentP + price < roads[dest]) {
                    if (visited[dest]) {
                        invalidFlag = true;
                        return;
                    }
                    // 경로보고, routes[도착지] 보다 작으면  routes update, queue 추가
                    roads[dest] = currentP + price;
                    visited[dest] = true;
                    dfs(dest, roads[dest]);
                    visited[dest] = false;
                }
            }
        }
    }
}

// 요약
// n 개의 도시, m 개의 버스
// 단방향 이동, 시간은 양수, 음수, 0 모두 가능
// 1번 도시에서 나머지 도시로 가는 가장 빠른 시간 구하기
// 갈 수 없으면 -1, 사이클이 있고 그 사이클의 비용 합이 음수라면 -1

// 16:13 - 16:38 -

// 1초: O()