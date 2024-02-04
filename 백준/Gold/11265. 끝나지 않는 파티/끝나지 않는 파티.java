import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public String solution() throws IOException {
        // n과 m을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // int[n][n] bridge 를 입력받는다.
        int[][] bridge = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                int time = Integer.parseInt(st.nextToken());
                if (time == 0 && i != j) {
                    time = Integer.MAX_VALUE;
                }
                bridge[i][j] = time;
            }
        }

        // 플로이드 알고리즘을 사용하여 거쳐가는 경우가 더 작으면 경로를 update 한다.
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (bridge[i][k] + bridge[k][j] < bridge[i][j]) {
                        bridge[i][j] = bridge[i][k] + bridge[k][j];
                    }
                }
            }
        }

        // m번 사용자의 위치를 입력받아 결과를 출력한다.
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int depart = Integer.parseInt(st.nextToken()) - 1;
            int destination = Integer.parseInt(st.nextToken()) - 1;
            int time = Integer.parseInt(st.nextToken());
            if (bridge[depart][destination] <= time) {
                result.append("Enjoy other party");
            } else {
                result.append("Stay here");
            }
            result.append("\n");
        }
        return result.toString().trim();
    }
}

// 21:25 - 10:08
// 총 걸린 시간: 00:43

// 알고리즘: 모든 장소에서 모든 장소로 가는 경우를 구해야 하기 때문에 플로이드 알고리즘을 사용한다.

// 시간복잡도: O(n^3)

// 정수 범위:
