import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    private static final int[] dr = new int[]{-1, 0, 1, 0};
    private static final int[] dc = new int[]{0, 1, 0, -1};

    private static int n;
    private static int m;
    private static int[][] map;
    private static int h;
    private static int w;
    private static int destR;
    private static int destC;

    public static void main(String[] args) throws IOException {
        // n, m 입력 (2-1000)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // map 입력
        map = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // h, w, sr, sc, destR, destC 입력
        st = new StringTokenizer(br.readLine(), " ");
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        int sr = Integer.parseInt(st.nextToken());
        int sc = Integer.parseInt(st.nextToken());
        destR = Integer.parseInt(st.nextToken());
        destC = Integer.parseInt(st.nextToken());

        System.out.println(bfs(sr, sc));

    }

    private static int bfs(int sr, int sc) {

        boolean[][] visited = new boolean[n + 1][m + 1];
        // bfs 추가
        Deque<Point> queue = new ArrayDeque<>();
        queue.offer(new Point(sr, sc, 0));

        while (!queue.isEmpty()) {
            // 이동
            Point point = queue.poll();
            if (point.r == destR && point.c == destC) {
                return point.depth;
            }

            for (int d = 0; d < 4; d++) {
                int x = point.r + dr[d];
                int y = point.c + dc[d];

                if (isValid(x, y, visited)) {
                    // visited 안하고, 가능한애들
                    if (canMove(x, y)) {
                        // queue 추가
                        queue.offer(new Point(x, y, point.depth + 1));
                    }

                    // visited 처리
                    visited[x][y] = true;
                }
            }
        }
        return -1;
    }

    private static boolean canMove(int r, int c) {
        for (int i = r; i < r + h; i++) {
            for (int j = c; j < c + w; j++) {
                if (map[i][j] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isValid(int r, int c, boolean[][] visited) {
        return r > 0 && r + h - 1 <= n && c > 0 && c + w - 1 <= m && !visited[r][c];
    }

    private static class Point {
        private final int r;
        private final int c;
        private final int depth;

        Point(int r, int c, int depth) {
            this.r = r;
            this.c = c;
            this.depth = depth;
        }
    }
}

// nxm 격자 팥, hxw 직사각형
// 시작 1, 1 / 직사각형 끝점이 0, 0으로 이동, 벽이나 외부가 포함되면 안됨
// 0 빈칸, 1 벽
// 최소 이동횟수, 못하면 -1 출력

// bfs O(n*m)