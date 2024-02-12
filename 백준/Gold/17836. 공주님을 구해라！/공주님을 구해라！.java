import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    private static final int MAX_VALUE = 10001;
    private static final int[] dx = new int[]{-1, 0, 1, 0};
    private static final int[] dy = new int[]{0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        System.out.println(new Solution().solution());
    }

    static class Solution {
        public String solution() throws IOException {
            // n, m, t를 입력받는다.
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            // boolean[n][m] visited를 입력받는다. 0은 빈공간, 1은 true, 2는 swordX, swordY
            boolean[][] visited = new boolean[n][m];
            int swordX = -1, swordY = -1;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < m; j++) {
                    int input = Integer.parseInt(st.nextToken());
                    if (input == 1) {
                        visited[i][j] = true;
                    }
                    if (input == 2) {
                        swordX = i;
                        swordY = j;
                    }
                }
            }

            // int[n][m] map을 10001으로 초기화한다.
            int[][] map = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    map[i][j] = MAX_VALUE;
                }
            }

            // 0, 0을 queue에 추가하고, visited true로 바꾸고 map을 0으로 초기화 한다.
            Deque<int[]> queue = new ArrayDeque<>();
            map[0][0] = 0;
            queue.offer(new int[]{0, 0, map[0][0]});
            visited[0][0] = true;

            // queue empty 까지 반복
            while (!queue.isEmpty()) {
                // 4방향을 보면서 visited 안했으면 map + 1 로 바꾼다.
                int[] current = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int x = current[0] + dx[d];
                    int y = current[1] + dy[d];

                    if (x >= 0 && x < n && y >= 0 && y < m && !visited[x][y]) {
                        map[x][y] = current[2] + 1;
                        visited[x][y] = true;
                        queue.offer(new int[]{x, y, map[x][y]});
                    }
                }
            }

            // sword 사용해서 길을 구한다. sword 까지 길 + 좌표 차이 와 max 최소값
            int distanceWithSword = map[swordX][swordY] + (n - 1 - swordX) + (m - 1 - swordY);
            map[n - 1][m - 1] = Math.min(map[n - 1][m - 1], distanceWithSword);

            // map[n][m] <= t이면 값을 출력, 아니면 Fail을 출력한다.
            if (map[n - 1][m - 1] <= t) {
                return String.valueOf(map[n - 1][m - 1]);
            }
            return "Fail";

        }
    }
}

// 설계 시간: 15:25 - 15:43
// 풀이 시간:

//0. 문제요약
// n, m 크기 성 시작은 (1, 1)
// t시간 이내에 용사 못만나면 돌이 됨
// 상하좌우 이동 가능
// 그냥은 안되는데 칼 있으면 바로 직진 가능
// 구출 못하면 fail 구출하면 최단시간 출력

//2. 시간복잡도: O(n*m)

//3. 이 로직으로 예제 문제 손으로 풀기

//4. 경계값 체크 후 코드 구현
