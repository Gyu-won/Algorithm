import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final int[] dr = new int[]{-1, 0, 1, 0};
    private static final int[] dc = new int[]{0, 1, 0, -1};

    private static int robotR;
    private static int robotC;

    public static void main(String[] args) throws IOException {
        // n, m 입력 (3-50)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // robotX, robotY, d 입력 (상, 우, 하, 좌)
        st = new StringTokenizer(br.readLine(), " ");
        robotR = Integer.parseInt(st.nextToken());
        robotC = Integer.parseInt(st.nextToken());
        int direction = Integer.parseInt(st.nextToken());

        // map을 입력받는다. (0은 청소안됨, 1은 벽)
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 무한 반복
        int count = 0;
        while (true) {
            // 현재칸 청소 안됐으면 count up
            if (map[robotR][robotC] == 0) {
                count++;
                map[robotR][robotC] = 2;
            }

            // 청소되지 않은 칸 있는 경우
            if (isExistDirty(n, m, map)) {
                // 90도 회전
                direction = (direction + 3) % 4;

                // 빈칸인지 확인
                while (!isDirty(n, m, direction, map)) {
                    direction = (direction + 3) % 4;
                }
                move(direction);
            } else {
                // 청소되지 않은 칸 없는 경우
                // 후진 못하면 종료
                if (!movable(n, m, (direction + 2) % 4, map)) {
                    break;
                }
                // 할 수 있으면 후진
                move((direction + 2) % 4);
            }
        }

        System.out.println(count);
    }

    private static boolean movable(int n, int m, int d, int[][] map) {
        int x = robotR + dr[d];
        int y = robotC + dc[d];
        return x > 0 && x < n && y > 0 && y < m && map[x][y] != 1;
    }

    private static void move(int d) {
        robotR += dr[d];
        robotC += dc[d];
    }

    private static boolean isExistDirty(int n, int m, int[][] map) {
        for (int d = 0; d < 4; d++) {
            if (isDirty(n, m, d, map)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDirty(int n, int m, int d, int[][] map) {
        int x = robotR + dr[d];
        int y = robotC + dc[d];
        return x > 0 && x < n && y > 0 && y < m && map[x][y] == 0;
    }

}

// nxm 크기 직사각형 방 

// 4칸 모두 청소 -> 후진, 후진 못하면 정지
// 한칸이라도 빈칸이면 반시계 90도 회전해서 앞칸이 될때까지 회전 후 전진
// 작동을 시작한 후 멈출 때까지 청소한 칸의 개수

// 15:14

// O()
