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
    private static int maxValue = 0;

    public static void main(String[] args) throws IOException {
        // n, m 을 입력 (3-8)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // map을 입력
        Deque<int[]> viruses = new ArrayDeque<>();
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 벽 세우기
        dfs(0, map);

        //최대값 출력
        System.out.println(maxValue);
    }

    private static void dfs(int count, int[][] map) {
        if (count == 3) {
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < m; j++) {
//                    System.out.print(map[i][j]);
//                    System.out.print(" ");
//                }
//                System.out.println();
//            }
//            System.out.println("=============");
            int[][] temp = spread(map);

            int securityCount = calculateCount(temp);
            maxValue = Math.max(maxValue, securityCount);
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    dfs(count + 1, map);
                    map[i][j] = 0;
                }
            }
        }
    }

    private static int[][] spread(int[][] map) {
        boolean[][] visited = new boolean[n][m];
        int[][] temp = new int[n][m];
        Deque<int[]> viruses = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                temp[i][j] = map[i][j];
                if (temp[i][j] == 2) {
                    viruses.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        while (!viruses.isEmpty()) {
            int[] virus = viruses.poll();
            int r = virus[0];
            int c = virus[1];

            for (int d = 0; d < 4; d++) {
                int x = r + dr[d];
                int y = c + dc[d];

                if (x >= 0 && x < n && y >= 0 && y < m && temp[x][y] == 0 && !visited[x][y]) {
                    visited[x][y] = true;
                    temp[x][y] = 2;
                    viruses.offer(new int[]{x, y});
                }
            }
        }
        return temp;
    }

    private static int calculateCount(int[][] map) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    count++;
                }
            }
        }
        return count;
    }


}

// nxm map, 0 빈칸, 1 벽, 2 바이러스, 벽 3개 세움, 안전영역 최대

// O(n!) 까지 가능

// 64 C 3으로 모든 벽을 다 세우는 경우 dfs
// 바이러스 퍼지는 거 bfs
// 개수 체크

// 16:11
