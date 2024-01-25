import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    public void solution() throws IOException {
        // T를 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        // combination 배열을 초기화한다.
        long[][] combination = new long[30][30];
        for (int i = 0; i < 30; i++) {
            combination[i][i] = 1;
            combination[i][0] = 1;
        }

        // combination 배열을 만든다.
        for (int i = 2; i < 30; i++) {
            for (int j = 1; j < i; j++) {
                combination[i][j] = combination[i - 1][j] + combination[i - 1][j - 1];
            }
        }

        // T 번만큼 입력받으며 결과를 출력한다.
        StringTokenizer st;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            result.append(combination[m][n]);
            result.append("\n");
        }

        System.out.println(result);
    }
}

// 알고리즘: 강의 서쪽 사이트는 모두 연결될 것이고 각의 동쪽 사이트 중 N개를 뽑으면 되는 문제이다.
// 결국 MCN을 구하면 되는 조합 문제이다. (DP)
// MCN = (M-1)CN + (M-1)C(N-1)
// Combination[M][N] = Combination[M-1][N] + Combination[M-1][N-1]

// 시간복잡도: O(M**2)

// 정수 범위:
