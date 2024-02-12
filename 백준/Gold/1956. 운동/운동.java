import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final int MAX_VALUE = 8000000;

    public static void main(String[] args) throws IOException {
        System.out.println(new Solution().solution());
    }

    static class Solution {
        public int solution() throws IOException {
            // v와 e를 입력받는다 (2-400, 0-v(v-1))
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            // route를 maxvalue로 초기화 한다.
            int[][] route = new int[v + 1][v + 1];
            for (int i = 1; i <= v; i++) {
                for (int j = 1; j <= v; j++) {
                    route[i][j] = MAX_VALUE;
                }
            }

            // int[v+1][v+1] route를 입력받는다. (1-10000)
            for (int k = 0; k < e; k++) {
                st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                route[a][b] = c;
            }

            // 플로이드 알고리즘을 적용한다.
            for (int k = 1; k <= v; k++) {
                for (int i = 1; i <= v; i++) {
                    for (int j = 1; j <= v; j++) {
                        if (route[i][j] > route[i][k] + route[k][j]) {
                            route[i][j] = route[i][k] + route[k][j];
                        }
                    }
                }
            }

            int result = MAX_VALUE;
            // i = j 인 경우의 최소값을 구한다.
            for (int i = 1; i <= v; i++) {
                result = Math.min(result, route[i][i]);
            }

            return result == MAX_VALUE ? -1 : result;
        }
    }
}

// 설계 시간: 14:11 - 14:20
// 풀이 시간:

//0. 문제요약
// v개의 마을과 e개의 일방통행 도로 (중복 x)
// 도로의 길이 합이 가장 작은 사이클
// 사이클이 없으면 -1

//2. 시간복잡도: O(

//3. 이 로직으로 예제 문제 손으로 풀기

//4. 경계값 체크 후 코드 구현
