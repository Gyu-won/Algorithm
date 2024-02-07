import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    private static final int[] dr = new int[]{-1, 0, 1, 0};
    private static final int[] dc = new int[]{0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public int solution() throws IOException {
        // m, n을 입력받는다. (2-1000)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        // 익은 토마토를 queue에 넣는다
        Deque<int[]> queue = new ArrayDeque<>();

        // int[n][m] tomatos 를 입력받는다.
        int[][] tomatoes = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                tomatoes[i][j] = Integer.parseInt(st.nextToken());
                if (tomatoes[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        // queue가 empty 일 때까지 토마토를 익힌다.
        int count = 0;
        while (!queue.isEmpty()) {
            // queue 크기만큼 반복 하며 count 1 증가
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                // pop -> 범위 내 + 0이면 1로 변경, queue에 추가
                int[] tomato = queue.poll();

                for (int d = 0; d < 4; d++) {
                    int r = tomato[0] + dr[d];
                    int c = tomato[1] + dc[d];

                    if (r >= 0 && r < n && c >= 0 && c < m && tomatoes[r][c] == 0) {
                        tomatoes[r][c] = 1;
                        queue.offer(new int[]{r, c});
                    }
                }
            }
            count++;
        }

        // tomatoes에 0이 없으면 count를 리턴 / 있으면 -1을 리턴
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (tomatoes[i][j] == 0) {
                    return -1;
                }
            }
        }
        return count - 1;
    }
}

// 설계 시간: 12:37 - 12:54
// 풀이 시간: 12:54 - 13:14

//0. 문제요약
// 하루가 지나면 익은 토마토 4방향 토마토 익는다 / 며칠이 지나면 다 익는지 최소 일 수
// 토마토가 없는 칸이 있을 수도 있다. // 1은 익은 토마토, 0은 안익은 토마토, -1을 없는 칸
// 토마토는 무조건 하나 이상 / 시작부터 모두 익어있으면 0 토마토 못익으면 -1

//2. 시간복잡도: O(m*n)

//3. 이 로직으로 예제 문제 손으로 풀기

//4. 경계값 체크 후 코드 구현

//틀렸을 때
//1. 이분탐색으로 틀린부분 찾고, 로직 생각 하기
//2. 프린트해보기, 간단한 예외케이스를 만들기

