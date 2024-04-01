import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // lineNumber 입력 (1-100)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int lineNumber = Integer.parseInt(br.readLine());

        // lines 입력받기
        int maxNumber = 0;
        StringTokenizer st;
        int[][] lines = new int[lineNumber][2];
        for (int i = 0; i < lineNumber; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            lines[i][0] = Integer.parseInt(st.nextToken());
            lines[i][1] = Integer.parseInt(st.nextToken());

            maxNumber = Math.max(maxNumber, lines[i][0]);
            maxNumber = Math.max(maxNumber, lines[i][1]);
        }

        // dp 배열 생성
        int[][] dp = new int[maxNumber + 1][maxNumber + 1];

        // dp 초기값 입력
        for (int i = 0; i < lineNumber; i++) {
            dp[lines[i][0]][lines[i][1]] = 1;
        }

        // dp 채우기
        for (int i = 1; i <= maxNumber; i++) {
            for (int j = 1; j <= maxNumber; j++) {
                dp[i][j] = Math.max(dp[i - 1][j - 1] + dp[i][j], Math.max(dp[i - 1][j], dp[i][j - 1]));
            }
        }

        // 값 리턴
        System.out.println(lineNumber - dp[maxNumber][maxNumber]);

    }
}

// 22:35
// dp[i][j] = dp[i-1][j-1] + dp[i][j] or dp[i-1][j] or dp[i][j-1]
