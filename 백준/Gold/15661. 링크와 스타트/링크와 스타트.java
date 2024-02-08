import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static int minDiff = Integer.MAX_VALUE;
    private static boolean[] visited;
    private static int[][] s;

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public long solution() throws IOException {
        // n을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        // int s[n][n]을 입력받는다
        s = new int[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                s[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 모든 조합을 구하면 2^n승인데 n이 20이기 떄문에 가능
        // 부분집합으로 모든 팀 경우를 다 구한다.
        // true 팀과 false 팀의 차이로 min 값을 업데이트한다.
        visited = new boolean[n];
        subset(0);

        return minDiff;
    }

    private void subset(int cnt) {
        if (cnt == n) {
            int start = 0;
            int link = 0;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (visited[i] == visited[j]) {
                        if (visited[i]) {
                            start += s[i][j] + s[j][i];
                        } else {
                            link += s[i][j] + s[j][i];
                        }
                    }
                }
            }

            if (start != 0 && link != 0) {
                minDiff = Math.min(minDiff, Math.abs(start - link));
            }
            return;
        }

        visited[cnt] = true;
        subset(cnt + 1);
        visited[cnt] = false;
        subset(cnt + 1);
    }

}

// 설계 시간: 10:30 - 10:49
// 풀이 시간: 10:49 -

//0. 문제요약
// n명의 사람을 두 팀으로 한명 이상씩 포함되도록 나눠야 한다.
// 팀 능력치의 차이가 최소가 되도록

//2. 시간복잡도: O(2^n)

//3. 이 로직으로 예제 문제 손으로 풀기

//4. 경계값 체크 후 코드 구현
