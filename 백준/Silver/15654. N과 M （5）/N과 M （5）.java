import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static int k;
    private static int[] sequence;
    private static boolean[] visited;
    private static StringBuilder result = new StringBuilder();


    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public String solution() throws IOException {
        // n 과 k를 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        // int[n] sequence를 입력받는다.
        sequence = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        // sequence를 정렬한다.
        Arrays.sort(sequence);

        // visited를 초기화한다.
        visited = new boolean[n];

        // 조합 알고리즘을 수행한다
        permutation(0, "");

        // String result를 출력한다.
        return result.toString().trim();
    }

    private void permutation(int count, String answer) {
        if (count == k) {
            result.append(answer.trim());
            result.append("\n");
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                permutation(count + 1, answer + sequence[i] + " ");
                visited[i] = false;
            }
        }
    }
}

// 16:44 - 16:59
// 총 걸린 시간: 00:15

// 알고리즘: 정렬 + 같은값 허용하지 않는 순열

// 시간복잡도: O(k * n * n)

// 정수 범위:
