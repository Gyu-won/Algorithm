import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final int[] dr = new int[]{-1, 0, 1, 0};
    private static final int[] dc = new int[]{0, 1, 0, -1};

    private static int count = 0;

    public static void main(String[] args) throws IOException {
        // n, m 을 입력받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // int[m][n] map을 입력받는다.
        char[][] map = new char[m][n];
        for (int i = 0; i < m; i++) {
            map[i] = br.readLine().toCharArray();
        }

        boolean[][] visited = new boolean[m][n];

        int teamPower = 0;
        int enemyPower = 0;
        // n, m 부터 visited 가 아니면 dfs를 수행한다.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    count = 0;
                    dfs(i, j, visited, map);

                    if (map[i][j] == 'W') {
                        teamPower += count * count;
                    } else {
                        enemyPower += count * count;
                    }
                }
            }
        }

        System.out.println(teamPower + " " + enemyPower);
    }

    private static void dfs(int i, int j, boolean[][] visited, char[][] map) {
        visited[i][j] = true;
        count++;
        for (int d = 0; d < 4; d++) {
            int x = i + dr[d];
            int y = j + dc[d];

            if (x >= 0 && x < map.length && y >= 0 && y < map[0].length && map[x][y] == map[i][j] && !visited[x][y]) {
                dfs(x, y, visited, map);
            }
        }
    }
}

// n 명 뭉쳐있으면 n제곱 위력
// mxn 배열 (1-100)
// 내 병사 W, 적군 병사 B

// 23:13