import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // n, m 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // map 입력
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        // dp 선언
        int[][] dp = new int[n][m];

        // dp 양 사이드 값은 똑같이 초기화
        dp[0][0] = map[0][0];
        boolean isOne = false;
        for (int i = 1; i < n; i++) {
            dp[i][0] = map[i][0];
            if (dp[i][0] == 1) {
                isOne = true;
            }
        }
        for (int j = 1; j < m; j++) {
            dp[0][j] = map[0][j];
            if (dp[0][j] == 1) {
                isOne = true;
            }
        }

        int maxLength = dp[0][0];
        if (isOne) {
            maxLength = 1;
        }

        // 1, 1, 부터 순회
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                // map 값이 1이면
                if (map[i][j] == 1) {
                    // dp를 3개중 최소값 + 1 로 채워줌
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;

                    // max 값 보다 크면 update
                    maxLength = Math.max(maxLength, dp[i][j]);
                }
            }
        }

        // max의 제곱 리턴
        System.out.println((int) Math.pow(maxLength, 2));
    }
}

// n x m 배열 (1-1000)
// 1로된 가장 큰 정사각형 배열

// 다 보면 꼭짓점 순회가 O(n^2)
// 그거마다 해당 영역 다 봐야되서 O(n^2) 안됨

// 넓이는 규칙이 없으므로 변의 길이로 판단
// 변의 길이를 확인하려면 1일때 3개중 최소값 + 1을 하면 됨

// O(n^2 logn) 까지 가능

