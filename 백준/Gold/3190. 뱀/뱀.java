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
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        // n을 입력 (2-100)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        // k를 입력 (0-100)
        int k = Integer.parseInt(br.readLine());

        // k 개의 사과를 map에 입력
        map = new int[n + 1][n + 1];
        StringTokenizer st;
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map[r][c] = 2;
        }
        map[1][1] = 1;

        // l 입력
        int l = Integer.parseInt(br.readLine());

        // l개의 L이면 왼쪽 90도 D면 오른쪽 90도 정렬되서 주어짐 (1-10000) queue
        Deque<int[]> directions = new ArrayDeque<>();
        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int time = Integer.parseInt(st.nextToken());
            String direction = st.nextToken();

            if (direction.equals("D")) {
                directions.add(new int[]{time, 1});
            } else {
                directions.add(new int[]{time, 3});
            }
        }

        // 무한반복
        int time = 0;
        int direction = 1;
        int headR = 1, headC = 1;
        Deque<int[]> snake = new ArrayDeque<>();
        snake.add(new int[]{1, 1});
        while (true) {
            // 시간 증가
            time++;

            if (!canMove(direction, headR, headC)) {
                // 이동 불가면 종료
                break;
            }

            // 머리 이동가능이면
            //사과인지 확인
            int r = headR + dr[direction];
            int c = headC + dc[direction];
            if (map[r][c] != 2) {
                // 꼬리 땡기기
                int[] tail = snake.poll();
                map[tail[0]][tail[1]] = 0;
            }

            // 머리 이동
            map[r][c] = 1;
            headR = r;
            headC = c;
            snake.add(new int[]{headR, headC});

            // 회전 여부 확인
            if (!directions.isEmpty() && directions.getFirst()[0] == time) {
                direction = (direction + directions.poll()[1]) % 4;
            }
        }

        System.out.println(time);
    }

    private static boolean canMove(int direction, int r, int c) {
        int x = r + dr[direction];
        int y = c + dc[direction];

        return x > 0 && x <= n && y > 0 && y <= n && map[x][y] != 1;

    }
}

// nxn 보드
// 시작 길이는 1, 방향 오른쪽, 좌표 1, 1

// 머리이동
// 끝났는지 여부 체크
// 사과 있으면 가만히, 없으면 꼬리 줄이기
// 몇초안에 끝날지
