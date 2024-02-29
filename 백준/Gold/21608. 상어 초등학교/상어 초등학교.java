import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final int[] dr = new int[]{-1, 0, 1, 0};
    private static final int[] dc = new int[]{0, 1, 0, -1};

    private static int n;
    private static int studentN;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        // n 입력 (3-20)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        studentN = (int) Math.pow(n, 2);

        map = new int[n + 1][n + 1];

        // students 입력
        int[][] students = new int[studentN + 1][4];
        StringTokenizer st;
        for (int i = 0; i < studentN; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int number = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 4; j++) {
                students[number][j] = Integer.parseInt(st.nextToken());
            }

            // 한명씩 배정 : O(K)
            findSeat(number, students[number]);
        }

//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= n; j++) {
//                System.out.print(map[i][j]);
//                System.out.print(" ");
//            }
//            System.out.println();
//        }

        // 만족도 구하기: O(n^2)
        int satisfying = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                satisfying += calculateSatisfy(i, j, students[map[i][j]]);
            }
        }
        System.out.println(satisfying);
    }

    private static int calculateSatisfy(int r, int c, int[] students) {
        int student = map[r][c];

        int[] info = calculateLikeAndBlank(r, c, students);
        if (info[0] == 0) {
            return 0;
        }
        return (int) Math.pow(10, info[0] - 1);
    }

    private static void findSeat(int number, int[] students) {
        int maxLike = -1, maxBlank = -1;
        int r = 0, c = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (map[i][j] != 0) {
                    continue;
                }
                int[] info = calculateLikeAndBlank(i, j, students);

                if (maxLike < info[0]) {
                    maxLike = info[0];
                    maxBlank = info[1];
                    r = i;
                    c = j;
                } else if (maxLike == info[0] && maxBlank < info[1]) {
                    maxBlank = info[1];
                    r = i;
                    c = j;
                }
            }
        }
        map[r][c] = number;
    }

    private static int[] calculateLikeAndBlank(int r, int c, int[] students) {

        int[] info = new int[2];

        for (int d = 0; d < 4; d++) {
            int x = r + dr[d];
            int y = c + dc[d];

            if (x > 0 && x <= n && y > 0 && y <= n) {
                if (map[x][y] == 0) {
                    info[1]++;
                } else if (isContain(map[x][y], students)) {
                    info[0]++;
                }
            }
        }
        return info;
    }

    private static boolean isContain(int k, int[] students) {
        for (int i = 0; i < students.length; i++) {
            if (k == students[i]) {
                return true;
            }
        }
        return false;
    }
}

// k 400
// O(k^3)까지 가능

// nxn 교실, n^2 명 학생 (1, 1) (n,n)
// 4방향만 인접
// 좋아하는 학생 4명, 비어있는 칸중 좋아하는 학생이 가장 많은 칸으로 순서대로 배정
// 이거 만족하면 비어있는칸이 가장 많은 칸으로 정함, 그다음이 행번호, 열번호 작은칸

// 시뮬레이션
