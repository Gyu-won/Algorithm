import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final int[][] dr = new int[][]{
            {0, 0, 0},
            {0, 1, 1},
            {1, 2, 2},
            {1, 1, 2},
            {0, 0, 1},
            {0, 1, 2},
            {1, 1, 2},
            {0, -1, 0}
    };
    private static final int[][] dc = new int[][]{
            {1, 2, 3},
            {1, 0, 1},
            {0, 0, 1},
            {0, 1, 1},
            {1, 2, 1},
            {-1, -1, -1},
            {0, -1, -1},
            {1, 1, 2}
    };

    private static int n;
    private static int m;
    private static int maxValue = 0;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        // n, m 입력 (4-500)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // map 입력 (1-1000)
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int type = 0; type < 8; type++) {
            // 테트로미노 종류만큼 반복

            // 테트로미노 회전
            for (int d = 0; d < 4; d++) {
                rotate(type);

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (isValid(i, j, type)) {
                            calculateSum(i, j, type);
                        }
                    }
                }
            }
        }

        System.out.println(maxValue);
    }

    private static void calculateSum(int r, int c, int type) {
        int sum = map[r][c];
        for (int d = 0; d < 3; d++) {
            int x = r + dr[type][d];
            int y = c + dc[type][d];

            sum += map[x][y];
        }
        maxValue = Math.max(maxValue, sum);
    }

    private static boolean isValid(int r, int c, int type) {
        for (int d = 0; d < 3; d++) {
            int x = r + dr[type][d];
            int y = c + dc[type][d];

            if (x < 0 || x >= n || y < 0 || y >= m) {
                return false;
            }
        }
        return true;
    }

    private static void rotate(int type) {
        for (int i = 0; i < 3; i++) {
            int temp = dr[type][i];
            dr[type][i] = dc[type][i];
            dc[type][i] = temp * -1;
        }
    }
}

//  1, 0 / 2, 0 / 2, 1
//          0, -1/ 0, -2 / 1, -2
//          -1, 0 / -2, 0 / -2, -1
//          0, 1 / 0, 2 / -1, 2

// n xm 격자
// 회전, 대칭 가능, 판의 숫자 최대

// 테트로미노 5개, 회전 4번, 양끝점 O(n*m) = o(20 * 500 * 500) 가능