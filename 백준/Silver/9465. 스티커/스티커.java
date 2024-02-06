import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public String solution() throws IOException {
        //1. 로직을 분할하고, 수도코드 작성(자료형)

        // t를 입력받는다. int
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        // for c in 0 - t:
        StringBuilder result = new StringBuilder();
        for (int c = 0; c < t; c++) {
            // n을 입력받는다
            int n = Integer.parseInt(br.readLine());

            // int[2][n] s를 입력받는다.
            int[][] s = new int[2][n];
            StringTokenizer st;
            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < n; j++) {
                    s[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // n == 1: s[0][0], s[1][0]의 max 값 리턴
            if (n == 1) {
                result.append(Math.max(s[0][0], s[1][0]));
                result.append("\n");
                continue;
            }

            // dp[][0] 값 세팅
            int[][] dp = new int[2][n];
            dp[0][0] = s[0][0];
            dp[1][0] = s[1][0];

            // dp[][1] 값 세팅
            dp[0][1] = dp[1][0] + s[0][1];
            dp[1][1] = dp[0][0] + s[1][1];

            // dp를 사용하여 문제를 해결한다.
            for (int i = 2; i < n; i++) {
                dp[0][i] = Math.max(dp[1][i - 2], dp[1][i - 1]) + s[0][i];
                dp[1][i] = Math.max(dp[0][i - 2], dp[0][i - 1]) + s[1][i];
            }

            // dp[][n-1] 의 최대값 출력
            result.append(Math.max(dp[0][n - 1], dp[1][n - 1]));
            result.append("\n");
        }

        return result.toString().trim();
    }
}

// 설계 시간: 11:42 - 12:03
// 풀이 시간: 12:03 - 12:15

//0. 문제요약
// 2행 n열의 스티커, 4방향 스티커는 사용불가, 점수 최대인 스티커,

//2. 시간복잡도: O(n*t)

//3. 이 로직으로 예제 문제 손으로 풀기

//4. 경계값 체크 후 코드 구현

//틀렸을 때
//1. 이분탐색으로 틀린부분 찾고, 로직 생각 하기
//2. 프린트해보기, 간단한 예외케이스를 만들기

