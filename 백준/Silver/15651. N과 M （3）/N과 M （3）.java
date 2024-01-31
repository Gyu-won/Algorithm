import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static int m;
    private static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public String solution() throws IOException {
        // n과 m을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // dfs로 출력한다.
        dfs("");
        return result.toString();
    }

    private void dfs(String sequence) {
        if (sequence.length() == m * 2) {
            result.append(sequence.trim());
            result.append("\n");
            return;
        }

        for (int i = 1; i <= n; i++) {
            dfs(sequence + i + " ");
        }
    }
}

// 알고리즘: dfs로 구현한다.

// 시간복잡도:

// 정수 범위
