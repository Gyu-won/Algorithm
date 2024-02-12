import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    private static final int[] dz = new int[]{-1, 1, 0, 0, 0, 0};
    private static final int[] dy = new int[]{0, 0, 0, 0, 1, -1};
    private static final int[] dx = new int[]{0, 0, -1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        System.out.println(new Solution().solution());
    }

    static class Solution {
        public int solution() throws IOException {
            // m, n, h를 입력받는다. (2-100)
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            // int[h][n][m] tomatoes 를 입력받는다.
            int complete = 0;
            int numberOfTomato = 0;
            Deque<int[]> queue = new ArrayDeque<>();
            int[][][] tomatoes = new int[h][n][m];
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < n; j++) {
                    st = new StringTokenizer(br.readLine(), " ");
                    for (int k = 0; k < m; k++) {
                        tomatoes[i][j][k] = Integer.parseInt(st.nextToken());
                        if (tomatoes[i][j][k] == 1) {
                            complete++;
                            queue.offer(new int[]{i, j, k});
                        }
                        if (tomatoes[i][j][k] != -1) {
                            numberOfTomato++;
                        }
                    }
                }
            }
            // queue 에 익은 토마토를 추가한다.
            // complete 를 익은 토마토 개수만큼 초기화 한다.

            // count를 0으로 초기화 한다.
            int count = 0;

            // queue가 empty가 아닐때까지 반복한다.
            while (!queue.isEmpty()) {
                // queue 크기 만큼 뺴면서 토마토를 익히고 익히거를 다시 queue에 넣는다.
                int size = queue.size();
                for (int c = 0; c < size; c++) {
                    int[] tomato = queue.poll();
                    for (int d = 0; d < 6; d++) {
                        int z = tomato[0] + dz[d];
                        int y = tomato[1] + dy[d];
                        int x = tomato[2] + dx[d];

                        if (z >= 0 && z < h && y >= 0 && y < n && x >= 0 && x < m && tomatoes[z][y][x] == 0) {
                            tomatoes[z][y][x] = 1;
                            complete++;
                            queue.offer(new int[]{z, y, x});
                        }
                    }
                }
                // queue 끝나면 count를 증가한다.
                count++;
            }

            // complete 가 m*n*h랑 같으면 count - 1을 출력한다.
            if (complete == numberOfTomato) {
                return count - 1;
            }
            return -1;
        }
    }
}

// 설계 시간: 14:33 - 14:45
// 풀이 시간: O(m*n*h)

//0. 문제요약
// 3차원 배열에서 6방향 토마ㅗ가 하루 지나면 익는다
// 모든 토마토가 다 익는 최소 일 수
// 토마토가 없는 칸이 있을 수도 있다.
// 1은 익은, 0은 안익, -1을 없음
// 모두 못 익으면 -1, 익어있으면 0

//2. 시간복잡도: O(

//3. 이 로직으로 예제 문제 손으로 풀기

//4. 경계값 체크 후 코드 구현
