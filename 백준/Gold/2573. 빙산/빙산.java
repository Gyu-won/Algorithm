import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final int[] dr = new int[]{-1, 0, 1, 0};
    private static final int[] dc = new int[]{0, 1, 0, -1};

    private static int n;
    private static int m;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        // n, m 입력 (3-300)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // icebergs 입력 ( 1이상 10000개 이하)
        int iceBergCount = 0;
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > 0) {
                    iceBergCount++;
                }
            }
        }

        // 빙산 다 녹을 때까지
        int time = 0;
        while (1 < iceBergCount) {
            time++;

            // 각 빙산을 돌면서 몇개 녹는지 확인 O(k)
            int[][] melting = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    melting[i][j] = calculateMelt(i, j);
                }
            }

            // 빙산 녹이기 O(k)
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] - melting[i][j] <= 0) {
                        if (map[i][j] > 0) {
                            iceBergCount--;
                        }
                        map[i][j] = 0;
                    } else {
                        map[i][j] -= melting[i][j];
                    }
                }
            }

            // 빙산 몇덩인지 확인 dfs O(n * m)
            boolean[][] visited = new boolean[n][m];
            int count = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] > 0 && !visited[i][j]) {
                        if (count == 1) {
                            System.out.println(time);
                            return;
                        }
                        dfs(i, j, visited);
                        count++;
                    }
                }
            }
        }

        System.out.println(0);
    }

    private static void dfs(int r, int c, boolean[][] visited) {
        visited[r][c] = true;
        for (int d = 0; d < 4; d++) {
            int x = r + dr[d];
            int y = c + dc[d];

            if (isValid(x, y) && map[x][y] > 0 && !visited[x][y]) {
                dfs(x, y, visited);
            }
        }
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }

    private static int calculateMelt(int r, int c) {
        int count = 0;
        for (int d = 0; d < 4; d++) {
            int x = r + dr[d];
            int y = c + dc[d];

            if (isValid(x, y) && map[x][y] == 0) {
                count++;
            }
        }
        return count;
    }
}

// 두덩이롭 분리될떄까지 최초 시간, 두덩이 분리 안되면 0 출력

// O(k^2) 가능

