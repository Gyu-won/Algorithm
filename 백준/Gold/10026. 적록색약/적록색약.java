import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final int[] dr = new int[]{-1, 0, 1, 0};
    private static final int[] dc = new int[]{0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        // n 입력 (1-100)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // drawing 입력 (nxn)
        char[][] drawing = new char[n][n];
        for (int i = 0; i < n; i++) {
            drawing[i] = br.readLine().toCharArray();
        }

        // specialDrawing 입력
        char[][] specialDrawing = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (drawing[i][j] == 'G') {
                    specialDrawing[i][j] = 'R';
                    continue;
                }
                specialDrawing[i][j] = drawing[i][j];
            }
        }

        // dfs
        StringBuilder result = new StringBuilder();

        int count = 0;
        boolean[][] visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    dfs(i, j, n, visited, drawing);
                    count++;
                }
            }
        }
        result.append(count);
        result.append(" ");

        count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = false;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    dfs(i, j, n, visited, specialDrawing);
                    count++;
                }
            }
        }
        result.append(count);

        System.out.println(result);

    }

    private static void dfs(int r, int c, int n, boolean[][] visited, char[][] drawing) {
        for (int i = 0; i < 4; i++) {
            int x = r + dr[i];
            int y = c + dc[i];

            if (isValid(x, y, n) && drawing[x][y] == drawing[r][c] && !visited[x][y]) {
                visited[x][y] = true;
                dfs(x, y, n, visited, drawing);
            }
        }
    }

    private static boolean isValid(int x, int y, int n) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}

// 색상은 rgb, 구역은 같은색
// 적록색약은 초록색 = 빨간색

// O(n^2)

