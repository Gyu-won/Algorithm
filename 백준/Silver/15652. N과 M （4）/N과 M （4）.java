import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static int m;
    private static StringBuilder result = new StringBuilder();
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public String solution() throws IOException {
        // n과 m을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 방문 여부 배열을 선언한다.
        visited = new boolean[n + 1];

        // backTracking을 적용한다.
        backTracking(1, "");

        return result.toString().trim();
    }

    private void backTracking(int start, String s) {
        if (s.length() == m * 2) {
            result.append(s.trim());
            result.append("\n");
            return;
        }
        for (int i = start; i <= n; i++) {
            backTracking(i, s + i + " ");
        }
    }
}

// 알고리즘: 백트래킹 알고리즘을 사용하여 조합을 구한다.

// 시간복잡도:

// 정수 범위
