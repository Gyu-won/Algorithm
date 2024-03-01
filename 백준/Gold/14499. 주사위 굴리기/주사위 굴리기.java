import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final int[] dr = new int[]{0, 0, 0, -1, 1};
    private static final int[] dc = new int[]{0, 1, -1, 0, 0};

    private static int n;
    private static int m;
    private static int[] dice;

    public static void main(String[] args) throws IOException {
        // n, m, x, y, k 입력 (1-20)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // map 입력 (0-10)
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 명령 입력 (1-1000) 동: 1 / 서: 2 / 북: 3 / 남: 4
        int[] commands = new int[k];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < k; i++) {
            commands[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder answer = new StringBuilder();
        dice = new int[6];
        for (int command = 0; command < k; command++) {
            int r = x + dr[commands[command]];
            int c = y + dc[commands[command]];

            if (canMove(r, c)) {
                x = r;
                y = c;
                roll(commands[command]);

                if (map[x][y] == 0) {
                    map[x][y] = dice[5];
                } else {
                    dice[5] = map[x][y];
                    map[x][y] = 0;
                }

                answer.append(dice[0]);
                answer.append("\n");
            }
        }
        System.out.println(answer.toString().trim());
    }

    private static boolean canMove(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }

    private static void roll(int direction) {
        if (direction == 1) {
            int temp = dice[3];
            dice[3] = dice[5];
            dice[5] = dice[2];
            dice[2] = dice[0];
            dice[0] = temp;
        } else if (direction == 2) {
            int temp = dice[2];
            dice[2] = dice[5];
            dice[5] = dice[3];
            dice[3] = dice[0];
            dice[0] = temp;
        } else if (direction == 3) {
            int temp = dice[4];
            dice[4] = dice[5];
            dice[5] = dice[1];
            dice[1] = dice[0];
            dice[0] = temp;
        } else {
            int temp = dice[1];
            dice[1] = dice[5];
            dice[5] = dice[4];
            dice[4] = dice[0];
            dice[0] = temp;
        }
    }
}

// nxm 지도
// 주사위 놓여진곳 x, y
// 바닥 수가 0이면 주사위 수 바닥에 복사, 0이 아니면 칸에 있는수가 주사위 바닥으로 복사되고 칸은0
// 이동할때마다 주사위 상단에 있는 값
// 바깥으로 이동하면 무시, 출력도 안함


