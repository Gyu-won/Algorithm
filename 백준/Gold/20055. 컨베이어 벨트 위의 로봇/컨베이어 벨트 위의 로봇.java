import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static int k;
    private static Belt[][] belts;

    public static void main(String[] args) throws IOException {
        // n, k를 입력 (2-100), (1-200)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        // 내구도 입력 (1-1000)
        st = new StringTokenizer(br.readLine(), " ");
        belts = new Belt[2][n];
        for (int i = 0; i < 2 * n; i++) {
            int s = Integer.parseInt(st.nextToken());
            if (i < n) {
                belts[0][i] = new Belt(s);
            } else {
                belts[1][2 * n - i - 1] = new Belt(s);
            }
        }

        int count = 0;
        while (true) {
            // 벨트 회전 O(2N)
            rotate();

            // n위치 벨트 내리기
            removeRobot();

            // 로봇 이동 O(n)
            move();

            // n위치 벨트 로봇 내리기
            removeRobot();

            // 새로운 로봇 올리기
            load();

            count++;
            // 내구도 확인
            if (isExit()) {
                break;
            }
        }

        System.out.println(count);
    }

    private static boolean isExit() {
        int count = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                if (belts[i][j].s == 0) {
                    count++;
                }
            }
        }
        return count >= k;
    }

    private static void load() {
        if (belts[0][0].s > 0) {
            belts[0][0].robot = true;
            belts[0][0].s--;
        }
    }

    private static void move() {
        for (int i = n - 2; i > 0; i--) {
            if (belts[0][i].robot && !belts[0][i + 1].robot && belts[0][i + 1].s > 0) {
                belts[0][i + 1].robot = true;
                belts[0][i + 1].s--;
                belts[0][i].robot = false;
            }
        }
    }

    private static void removeRobot() {
        belts[0][n - 1].robot = false;
    }

    private static void rotate() {
        Belt temp = belts[0][0];
        belts[0][0] = belts[1][0];
        for (int i = 0; i < n - 1; i++) {
            belts[1][i] = belts[1][i + 1];
        }
        belts[1][n - 1] = belts[0][n - 1];
        for (int i = n - 1; i > 1; i--) {
            belts[0][i] = belts[0][i - 1];
        }
        belts[0][1] = temp;
    }

    private static class Belt {
        private int s;
        private boolean robot;

        Belt(int s) {
            this.s = s;
            this.robot = false;
        }
    }
}

// 1번이 올리는 위치, n번이 내리는 위치
// 로봇을 위치에 올리거나 로봇이 이동하면 내구도는 즉시 1감소

// 벨트 회전
// 벨트가 회전하는 방향으로 이동한다(내구도가 1보다 작거나, 앞에 로봇이 있으면 정지)
// 로봇을 올린다(0이 아니면 올림)
// 내구도 0 개수가 k 이상이면 종료