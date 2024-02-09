import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static int m;
    private static int[] s;
    private static boolean[] visited;
    private static StringBuilder result = new StringBuilder();
    private static List<String> results = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        System.out.println(new Solution().solution());
    }

    static class Solution {
        public String solution() throws IOException {
            // n과 m을 입력받는다.
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            visited = new boolean[n];

            // int[n] s를 입력받는다.
            s = new int[n];
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < n; i++) {
                s[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(s);

            // dfs로 구현한다.
            dfs(0, "");

            // set을 정렬한다.
            return result.toString().trim();
        }

        private void dfs(int count, String r) {
            if (count == m) {
                if (!results.contains(r)) {
                    results.add(r);
                    result.append(r.trim());
                    result.append("\n");
                }
                return;
            }
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    dfs(count + 1, r + s[i] + " ");
                    visited[i] = false;
                }
            }
        }

    }
}

// 설계 시간: 22:37
// 풀이 시간:

//0. 문제요약
// 순열 n, m 인데 set에 넣어서 중복 허용 하지 않음

//2. 시간복잡도: 

//3. 이 로직으로 예제 문제 손으로 풀기

//4. 경계값 체크 후 코드 구현
