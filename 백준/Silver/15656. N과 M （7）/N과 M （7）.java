import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static int m;
    private static int[] a;
    private static StringBuilder result = new StringBuilder();

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

            // int[n] a 를 입력받는다.
            a = new int[n];
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }

            // a를 정렬한다.
            Arrays.sort(a);

            // dfs로 구현한다.
            dfs(0, "");

            return result.toString().trim();
        }

        private void dfs(int count, String s) {
            if (count == m) {
                result.append(s.trim());
                result.append("\n");
                return;
            }

            for (int i = 0; i < n; i++) {
                dfs(count + 1, s + a[i] + " ");
            }
        }
    }
}

// 설계 시간: 17:25
// 풀이 시간:

//0. 문제요약
// n m 순열을 오름차순으로 출력한다.

//2. 시간복잡도: 

//3. 이 로직으로 예제 문제 손으로 풀기

//4. 경계값 체크 후 코드 구현
