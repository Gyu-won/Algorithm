import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static int m;
    private static int[] s;
    private static Set<String> sSet = new LinkedHashSet<>();

    public static void main(String[] args) throws IOException {
        System.out.println(new Solution().solution());
    }

    static class Solution {
        public String solution() throws IOException {
            // n, m 을 입력받는다.
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            // int[n] s를 입력받는다.
            s = new int[n];
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < n; i++) {
                s[i] = Integer.parseInt(st.nextToken());
            }

            // s를 정렬한다.
            Arrays.sort(s);

            // dfs
            dfs(0, 0, "");

            StringBuilder result = new StringBuilder();
            for (String s : sSet) {
                result.append(s);
                result.append("\n");
            }
            return result.toString().trim();
        }

        private void dfs(int count, int start, String string) {
            if (count == m) {
                sSet.add(string.trim());
                return;
            }
            for (int i = start; i < n; i++) {
                dfs(count + 1, i + 1, string + s[i] + " ");
            }
        }

    }
}

// 설계 시간: 23:19 -
// 풀이 시간:

//0. 문제요약
// 고른거를 중복 없이
// start 넘겨줘서 큰 순으로 하되, LinkedHashSet을 사용해서 중복 없도록

//2. 시간복잡도: O(n^n)

//3. 이 로직으로 예제 문제 손으로 풀기

//4. 경계값 체크 후 코드 구현
