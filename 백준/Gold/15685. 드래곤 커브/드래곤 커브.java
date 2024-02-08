import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static final int[] dx = new int[]{1, 0, -1, 0};
    private static final int[] dy = new int[]{0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public int solution() throws IOException {
        // n을 입력받는다 (1-20)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        boolean[][] map = new boolean[101][101];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            // n개의 드래곤 커브를 입력받는다
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            List<int[]> curves = new ArrayList<>();

            // 0 세대그리기, map에 true로 바꾸기
            // 시작점이랑, 끝점 list<int[]> curve에 포함시키기
            map[y][x] = true;
            curves.add(new int[]{y, x});

            map[y + dy[d]][x + dx[d]] = true;
            curves.add(new int[]{y + dy[d], x + dx[d]});

            // g 세대까지 반복
            for (int j = 1; j <= g; j++) {
                // 마지막 점 기준으로 마지막 점 제외까지 차이 구하고 회전 점 구하기
                int lx = curves.get(curves.size() - 1)[1];
                int ly = curves.get(curves.size() - 1)[0];

                int size = curves.size();
                for (int k = size - 2; k >= 0; k--) {
                    int xDiff = curves.get(k)[1] - lx;
                    int yDiff = curves.get(k)[0] - ly;

                    int newY = ly + xDiff;
                    int newX = lx - yDiff;

                    // 회전 점 list 포함시키고, map true로 바꾸기
                    curves.add(new int[]{newY, newX});
                    map[newY][newX] = true;
                }
            }
        }

        // 꼭직점 4개가 모두 포함되는거 찾기 O(100 * 100) boolean[101][101] map
        int count = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] && map[i + 1][j] && map[i][j + 1] && map[i + 1][j + 1]) {
                    count++;
                }
            }
        }
        return count;
    }
}

// 설계 시간: 13:25-13:52
// 풀이 시간: 13:52 - 14:12

//0. 문제요약
// k세대 드래곤 커브는 k-1세대 드래곤 커브를 끝점 기준 90도 시계방향 회전
// 네 꼭짓점이 드래곤 커브에 해당하는 사각형의 개수 구하기

//2. 시간복잡도:

//3. 이 로직으로 예제 문제 손으로 풀기

//4. 경계값 체크 후 코드 구현
