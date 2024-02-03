import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static int m;
    private static final int[] rowDirection = new int[]{-1, -1, 0, 1, 1, 1, 0, -1};
    private static final int[] colDirection = new int[]{0, 1, 1, 1, 0, -1, -1, -1};
    private static Deque<FireBall>[][] map;

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public int solution() throws IOException {
        // n, m, k를 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // List<FireBall>[n][n] map을 생성한다.
        map = new ArrayDeque[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = new ArrayDeque<>();
            }
        }

        // 초기 fireball을 생성한다.
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            new FireBall(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
                    Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
        }

        for (int number = 0; number < k; number++) {
            // fireball을 이동시킨다.
            Deque<FireBall> fireBalls = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (FireBall fireBall : map[i][j]) {
                        fireBalls.offer(fireBall.move());
                    }
                    map[i][j].clear();
                }
            }

            while (!fireBalls.isEmpty()) {
                FireBall fireBall = fireBalls.poll();
                fireBall.insert();
            }

            // 겹치는 fireball을 합친다.
            // fireball을 분해한다. (질량이 0이면 소멸)
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j].size() > 1) {
                        FireBall.combineAndSplit(i, j);
                    }
                }
            }
        }

        // k번 반복 후 전체 파이어볼의 질량 합을 계산한다.
        int totalWeight = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (FireBall fireBall : map[i][j]) {
                    totalWeight += fireBall.weight;
                }
            }
        }
        return totalWeight;
    }

    private static class FireBall {
        private int row;
        private int col;
        private final int weight;
        private final int speed;
        private final int direction;

        FireBall(int row, int col, int weight, int speed, int direction) {
            this.row = row;
            this.col = col;
            this.weight = weight;
            this.speed = speed;
            this.direction = direction;
            map[row][col].offer(this);
        }

        public static void combineAndSplit(int r, int c) {
            int totalWeight = 0;
            int totalSpeed = 0;
            int count = 0;
            int directionFlag = 0;
            while (!map[r][c].isEmpty()) {
                FireBall fireBall = map[r][c].poll();
                totalWeight += fireBall.weight;
                totalSpeed += fireBall.speed;
                count++;
                directionFlag += fireBall.direction % 2;
            }

            int weight = totalWeight / 5;
            if (weight > 0) {
                int speed = totalSpeed / count;
                if (directionFlag == 0 || directionFlag == count) {
                    directionFlag = 0;
                } else {
                    directionFlag = 1;
                }

                for (int i = directionFlag; i < 8; i += 2) {
                    new FireBall(r, c, weight, speed, i);
                }
            }
        }

        public FireBall move() {
            row += rowDirection[direction] * speed;
            col += colDirection[direction] * speed;

            if (row < 0) {
                row = row % n == 0 ? 0 : n + row % n;
            } else {
                row = row % n;
            }

            if (col < 0) {
                col = col % n == 0 ? 0 : n + col % n;
            } else {
                col = col % n;
            }
            return this;
        }

        public void insert() {
            map[this.row][this.col].add(this);
        }
    }
}

// 10:34 - 11:59
// 13:48

// 알고리즘: 파이어볼을 시뮬레이션으로 순서대로 실행하면 되는데, 파이어볼 객체를 만들고, 큐를 사용하면 될 것 같다.

// 시간복잡도: O(k * n * n)

// 정수 범위: int
