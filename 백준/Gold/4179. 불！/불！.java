import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    private static int[] dr = new int[]{-1, 0, 1, 0};
    private static int[] dc = new int[]{0, 1, 0, -1};

    private static int n;
    private static int m;
    private static char[][] map;

    public static void main(String[] args) throws IOException {
        // r, c 입력 (1-1000)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // map 입력
        Deque<Instance> fires = new ArrayDeque<>();
        Deque<Instance> routes = new ArrayDeque<>();
        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j);

                if (map[i][j] == 'J') {
                    routes.offer(new Instance(i, j, 0));
                } else if (map[i][j] == 'F') {
                    fires.offer(new Instance(i, j, 0));
                }
            }
        }

        int time = 0;
        while (!routes.isEmpty()) {
            time++;

            // 불 먼저 bfs 이동
            while (!fires.isEmpty() && fires.peek().time < time) {
                Instance fire = fires.poll();
                spread(fire, fires);
            }

            // 지훈이 이동 bfs
            while (!routes.isEmpty() && routes.peek().time < time) {
                Instance jihoon = routes.poll();
                if (move(jihoon, routes)) {
                    System.out.println(time);
                    return;
                }
            }

        }
        System.out.println("IMPOSSIBLE");
    }

    private static boolean move(Instance jihoon, Deque<Instance> routes) {
        int r = jihoon.r;
        int c = jihoon.c;
        int t = jihoon.time;

        for (int d = 0; d < 4; d++) {
            int x = r + dr[d];
            int y = c + dc[d];

            if (isOut(x, y)) {
                return true;
            }

            if (map[x][y] == '.') {
                map[x][y] = 'J';
                routes.offer(new Instance(x, y, t + 1));
            }
        }
        return false;
    }

    private static void spread(Instance fire, Deque<Instance> fires) {
        int r = fire.r;
        int c = fire.c;
        int t = fire.time;

        for (int d = 0; d < 4; d++) {
            int x = r + dr[d];
            int y = c + dc[d];

            if (!isOut(x, y) && (map[x][y] == '.' || map[x][y] == 'J')) {
                map[x][y] = 'F';
                fires.offer(new Instance(x, y, t + 1));
            }
        }
    }

    private static boolean isOut(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
    }

    private static class Instance {
        private final int r;
        private final int c;
        private final int time;

        Instance(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }
}

// 09:10

// 불을 먼저 이동 시키고, 지훈이 이동