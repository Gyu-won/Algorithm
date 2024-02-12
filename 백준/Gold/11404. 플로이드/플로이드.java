import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final int MAX_VALUE = 10000000;

    public static void main(String[] args) throws IOException {
        System.out.println(new Solution().solution());
    }

    static class Solution {
        public String solution() throws IOException {
            // n을 입력받는다. (2-100)
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(br.readLine());

            // m을 입력받는다. (1-100000)
            int m = Integer.parseInt(br.readLine());

            // int[][] route를 10000000으로 초기화, 자기자신은 0으로 초기화
            int[][] route = new int[n + 1][n + 1];
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i == j) {
                        route[i][j] = 0;
                        continue;
                    }
                    route[i][j] = MAX_VALUE;
                }
            }

            // a, b, c를 입력받는다. (c: 1-100000) int[n+1][n+1] route에 최소로 저장
            StringTokenizer st;
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                route[a][b] = Math.min(route[a][b], c);
            }

            // 플로이드
            for (int k = 1; k <= n; k++) {
                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= n; j++) {
                        if (route[i][j] > route[i][k] + route[k][j]) {
                            route[i][j] = route[i][k] + route[k][j];
                        }
                    }
                }
            }

            StringBuilder result = new StringBuilder();
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (route[i][j] == MAX_VALUE) {
                        result.append(0);
                    } else {
                        result.append(route[i][j]);
                    }
                    result.append(" ");
                }
                result.append("\n");
            }

            return result.toString().trim();
        }
    }
}

// 설계 시간: 13:52 - 14:02
// 풀이 시간:

//0. 문제요약
// n개의 도시와 m개의 버스, 버스는 비용이 든다
// 모든 도시의 쌍 a-b의 최소 비용
// 같은 노선 여러개 가능
// 갈 수 없으면 0을 출력

//

//2. 시간복잡도: O(

//3. 이 로직으로 예제 문제 손으로 풀기

//4. 경계값 체크 후 코드 구현
