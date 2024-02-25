import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static final int INF = 1000000;

    public static void main(String[] args) throws IOException {
        // n, m, x를 입력 (1-1000), (1-10000)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        // m개의 도로를 입력 (시작, 끝, t)
        List<Road>[] roads = new ArrayList[n + 1];
        List<Road>[] reverseRoads = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            roads[i] = new ArrayList<>();
            reverseRoads[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            roads[start].add(new Road(dest, time));
            reverseRoads[dest].add(new Road(start, time));
        }

        // 다익스트라로 (x에서 n으로 가는 경로 구하기) O(m)
        int[] comeback = dijkstra(x, n, roads);

        // bfs로 n -> x로 가는 경로 구하기, O(n * nlogn)
        int[] go = dijkstra(x, n, reverseRoads);

        //둘을 더해서 최대인 값을 리턴
        int maxValue = 0;
        for (int i = 1; i <= n; i++) {
            maxValue = Math.max(maxValue, go[i] + comeback[i]);
        }

        System.out.println(maxValue);
    }

    private static int[] dijkstra(int x, int n, List<Road>[] roads) {
        int[] comeback = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            comeback[i] = INF;
        }
        comeback[x] = 0;

        Deque<Road> queue = new ArrayDeque<>();
        queue.offer(new Road(x, 0));

        while (!queue.isEmpty()) {
            Road current = queue.poll();

            for (Road road : roads[current.dest]) {
                if (current.time + road.time < comeback[road.dest]) {
                    comeback[road.dest] = current.time + road.time;
                    queue.offer(new Road(road.dest, comeback[road.dest]));
                }
            }
        }
        return comeback;
    }

    private static class Road {
        private final int dest;
        private final int time;

        Road(int dest, int time) {
            this.dest = dest;
            this.time = time;
        }
    }
}

// n개의 마을, m개의 단방향 도로
// 최단시간에 왔다가 가야함, 이때 최대로 걸리는 학생을 구하기

//

// 16:01 -