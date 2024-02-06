import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final int[] dr = new int[]{0, 1};
    private static final int[] dc = new int[]{1, 0};

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public long solution() throws IOException {
        // n을 입력받는다 (4- 100)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // int[n][n] a 를 입력받는다.
        int[][] a = new int[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if (a[0][0] == 0) {
            return 0;
        }

        // 경로를 선택하려면 오, 왼 중 선택하기 때문에 O(2^(n*n)으로 불가능)
        // long[n][n] dp를 선언한다.
        long[][] dp = new long[n][n];

        dp[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 2; k++) {
                    if (a[i][j] != 0) {
                        int mc = j + dc[k] * a[i][j];
                        int mr = i + dr[k] * a[i][j];

                        if (mc < n && mr < n) {
                            dp[mr][mc] += dp[i][j];
                        }
                    }
                }
            }
        }

        // dp[n-1][n-1]을 출력한다.
        return dp[n - 1][n - 1];
    }
}

// 설계 시간: 15:28 - 15:50
// 풀이 시간:

//0. 문제요약
// n,n 게임판 / 오른쪽에서 아래로 적힌 거리만큼 간다 / 0은 벽 / 점프는 한방향으로만
// 1,1 에서 n,n으로 가는 경로의 수

//2. 시간복잡도: O(n*n)

//3. 이 로직으로 예제 문제 손으로 풀기

//4. 경계값 체크 후 코드 구현

//틀렸을 때
//1. 이분탐색으로 틀린부분 찾고, 로직 생각 하기
//2. 프린트해보기, 간단한 예외케이스를 만들기

