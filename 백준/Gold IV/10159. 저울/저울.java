import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(new Solution().solution());
    }

    static class Solution {
        public String solution() throws IOException {
            // n을 입력받는다. (5-100)
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(br.readLine());

            // m을 입력받는다. (0-2000)
            int m = Integer.parseInt(br.readLine());

            // f를 초기화 한다.
            int[][] f = new int[n + 1][n + 1];
            for (int i = 1; i <= n; i++) {
                f[i][i] = 1;
            }

            StringTokenizer st;
            // i > j 면 1, j > i 면 2로 표현
            for (int k = 0; k < m; k++) {
                st = new StringTokenizer(br.readLine(), " ");
                int i = Integer.parseInt(st.nextToken());
                int j = Integer.parseInt(st.nextToken());
                f[i][j] = 1;
                f[j][i] = 2;
            }

            // 플로이드
            for (int k = 1; k <= n; k++) {
                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= n; j++) {
                        if (f[i][j] == 0 && f[i][k] == f[k][j]) {
                            f[i][j] = f[i][k];
                        }
                    }
                }
            }
            // [i][j] == 0이고, [i][k] [k][j] 가 같으면 [i][k]로 변경

            StringBuilder result = new StringBuilder();
            for (int i = 1; i <= n; i++) {
                int count = 0;
                for (int j = 1; j <= n; j++) {
                    if (f[i][j] == 0) {
                        count++;
                    }
                }
                result.append(count);
                result.append("\n");
            }

            return result.toString().trim();
        }
    }
}

// 요약
// 무게가 서로 다른 1-n개의 물건
// 뭐가 더 무거운지 측정한 표로 측정안한거 알아낼 수도 못알아낼 수도
// 각물건에 대해서 비교 결과 알 수 없는 물건의 개수를 구하기
// 앞 > 뒤

// 16:30 -

// 1초: O(n^3)