import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public int solution() throws IOException {
        // n, m, r을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        // int[n+1] item을 입력받는다.
        int[] items = new int[n + 1];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        // route를 입력받는다.
        int[][] route = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    route[i][j] = 0;
                } else {
                    route[i][j] = 16;
                }
            }
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            route[s][d] = w;
            route[d][s] = w;
        }

        // 플로이드 써서 가중치 다 구하기
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (route[i][k] + route[k][j] < route[i][j]) {
                        route[i][j] = route[i][k] + route[k][j];
                    }
                }
            }
        }

        // 가중치가 m 이하인 것 다 더해서 maxItem 구하기
        int maxItem = 0;
        for (int i = 1; i <= n; i++) {
            int itemNumber = 0;
            for (int j = 1; j <= n; j++) {
                if (route[i][j] <= m) {
                    itemNumber += items[j];
                }
            }
            maxItem = Math.max(maxItem, itemNumber);
        }
        return maxItem;
    }
}

// 설계 시간:22:13 - 22:42
// 풀이 시간:

//0. 문제요약
// 특정 지역에서 갈 수 곳중 m 이내의 가중치 합 최대값

//2. 시간복잡도: 플로이드 O(n^3), O(n^2)

//3. 이 로직으로 예제 문제 손으로 풀기

//4. 경계값 체크 후 코드 구현

//틀렸을 때
//1. 이분탐색으로 틀린부분 찾고, 로직 생각 하기
//2. 프린트해보기, 간단한 예외케이스를 만들기

