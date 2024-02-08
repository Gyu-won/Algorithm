import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public long solution() throws IOException {
        // n을 입력받는다. ( 2 - 5000)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // int[n] stones 를 입력받는다. ( 1 - 1000,000)
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] stones = new int[n];
        for (int i = 0; i < n; i++) {
            stones[i] = Integer.parseInt(st.nextToken());
        }

        // 완탐으로 구한다.
        long[][] power = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                power[i][j] = (long) (j - i) * (1 + Math.abs(stones[i] - stones[j]));
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < i; j++) {
                power[0][i] = Math.min(power[0][i], Math.max(power[0][j], power[j][i]));
            }
        }

        return power[0][n - 1];
    }
}

// 설계 시간: 22:35-
// 풀이 시간:

//0. 문제요약
// 가장 왼쪽 돌에서 제일 오른쪽으로 건너간다
// 오른쪽으로 이동한다, 이동할때 힘을 쓴다, 돌 한번 최대 힘이 k라 할떄 k 최소인거
// dp[3] = min(power[1][3], power[1][2] + power[2][3])

//2. 시간복잡도: 

//3. 이 로직으로 예제 문제 손으로 풀기

//4. 경계값 체크 후 코드 구현
