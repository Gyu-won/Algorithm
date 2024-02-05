import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final int n = 19;
    private static final int[] dx = new int[]{-1, 0, 1, 1, 1, 0, -1, -1};
    private static final int[] dy = new int[]{1, 1, 1, 0, -1, -1, -1, 0};

    private static int d;
    private static int r;
    private static int c;
    private static int[][] frame;

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public String solution() throws IOException {

        frame = new int[n][n];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                frame[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        d = 8;
        int winner = winner();

        StringBuilder result = new StringBuilder();
        result.append(winner);
        result.append("\n");

        if (winner != 0) {
            if (d > 3) {
                r += 4 * dx[d];
                c += 4 * dy[d];
            }
            result.append(r + 1);
            result.append(" ");
            result.append(c + 1);
        }

        return result.toString();
    }

    private int winner() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (frame[i][j] != 0) {
                    for (int k = 0; k < 8; k++) {
                        int count = 1;
                        int x = i + dx[k];
                        int y = j + dy[k];

                        if (checkValue(x, y, k, frame[i][j], count)) {
                            int rx = i - dx[k];
                            int ry = j - dy[k];
                            if (!(rx >= 0 && rx < n && ry >= 0 && ry < n) || frame[rx][ry] != frame[i][j]) {
                                d = k;
                                r = i;
                                c = j;
                                return frame[i][j];
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }

    private boolean checkValue(int x, int y, int d, int v, int count) {
        if (count == 5) {
            if (!(x >= 0 && x < n && y >= 0 && y < n) || frame[x][y] != v) {
                return true;
            }
            return false;
        }
        if (x >= 0 && x < n && y >= 0 && y < n && frame[x][y] == v) {
            return checkValue(x + dx[d], y + dy[d], d, v, count + 1);
        }
        return false;
    }
}

// 설계 시간: 12:09 - 12:29
// 풀이 시간: 12:30 - 13:04

// int frame[19][19] 를 입력받는다.

// 우승자를 판단한다.
// for i : 0 - 19
// for j : 0 - 19
// if a[i][j] != 0:
// for k : 0 - 8
// if 범위 체크, a[i+dx[k][j+dy[k]] == a[i][j]:
// if 반대방향 범위 및 값 체크:
// 해당방향으로 4개까지 범위 및 값 체크
// 5번째 값은 다르거나 벽이면 a[i][j] 기억, k도 기억, 종료

// 우승한 좌표를 출력한다.
// k를 바탕으로 좌표 계산

//1. 로직을 분할하고, 수도코드 작성(자료형)
//2. 시간복잡도: O(19 * 19 * 8 * 6)
//3. 이 로직으로 예제 문제 손으로 풀기
//4. 경계값 체크 후 코드 구현

//틀렸을 때
//1. 이분탐색으로 틀린부분 찾고, 로직 생각 하기
//2. 프린트해보기, 간단한 예외케이스를 만들기

