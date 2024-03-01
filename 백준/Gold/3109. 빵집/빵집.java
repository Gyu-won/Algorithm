import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final int[] dr = new int[]{-1, 0, 1};

    private static int n;
    private static int m;
    private static int count = 0;
    private static boolean exitFlag;
    private static boolean[][] visited;
    private static char[][] map;

    public static void main(String[] args) throws IOException {
        // r, c 입력 (1-10000, 5-500)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // map 입력
        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        // 최대한 위로 가기 O(r * c)
        // 시작점마다 반복
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            // 갈 수 있는 방향 탐색, 끝에 도달하면 count + 1
            int r = i;
            int c = 0;

            exitFlag = false;
            moveDirection(r, c);
        }

        System.out.println(count);
    }

    private static void moveDirection(int r, int c) {
        if (c == m - 1) {
            count++;
            exitFlag = true;
            return;
        }

        for (int d = 0; d < 3; d++) {
            int x = r + dr[d];
            int y = c + 1;

            if (x >= 0 && x < n && !visited[x][y] && map[x][y] == '.') {
                visited[x][y] = true;
                moveDirection(x, y);
            }
            if (exitFlag) {
                return;
            }
        }
    }
}

// 오위, 오, 오아래 연결 가능, 경로는 겹칠 수 없음

