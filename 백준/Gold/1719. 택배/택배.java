import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final int INF = 2000000;
    private static final int DISCONNECT = -1;

    public static void main(String[] args) throws IOException {
        // n과 m을 입력받는다. (1-200, 1-10000)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][][] map = new int[n + 1][n + 1][2];
        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= n; c++) {
                if (r == c) {
                    map[r][c][0] = 0;
                    map[r][c][1] = r;
                } else {
                    map[r][c][0] = INF;
                    map[r][c][1] = DISCONNECT;
                }
            }
        }

        // routes 입력받는다 (1-10000)
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int point1 = Integer.parseInt(st.nextToken());
            int point2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            if (cost < map[point1][point2][0]) {
                map[point1][point2][0] = cost;
                map[point2][point1][0] = cost;
                map[point1][point2][1] = point2;
                map[point2][point1][1] = point1;
            }
        }

        // 플로이드를 쓰는데 k와 경로를 같이 저장, 자기자신은 0으로 저장
        floid(n, map);

        // 출력하기
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            StringBuilder oneRow = new StringBuilder();
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    oneRow.append("-");
                } else {
                    oneRow.append(map[i][j][1]);
                }
                oneRow.append(" ");
            }
            result.append(oneRow.toString().trim());
            result.append("\n");
        }
        System.out.println(result.toString().trim());
    }

    private static void floid(int n, int[][][] map) {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (map[i][k][0] + map[k][j][0] < map[i][j][0]) {
                        map[i][j][0] = map[i][k][0] + map[k][j][0];
                        map[i][j][1] = findParent(i, k, map);
                    }
                }
            }
        }
    }

    private static int findParent(int start, int k, int[][][] map) {
        if (k == map[start][k][1]) {
            return k;
        }
        return findParent(start, map[start][k][1], map);
    }
}

// 요약

// 2초 O(n^3)

// 10:41