import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static int k;
    private static int[] sequence;
    private static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public String solution() throws IOException {
        // n과 k를 입력받는다.
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

        // 중복허용하지 않는 조합을 사용한다.
        permutation(0, 0, "");

        // 결과를 출력한다.
        return result.toString().trim();
    }

    private void permutation(int count, int start, String answer) {
        if (count == k) {
            result.append(answer.trim());
            result.append("\n");
            return;
        }
        for (int i = start; i < n; i++) {
            permutation(count + 1, i + 1, answer + sequence[i] + " ");
        }
    }
}

// 17:01 - 17:12
// 총 걸린 시간: 00:11

// 알고리즘: 정렬 + 중복 허용하지 않는 조합

// 시간복잡도: O(k * n * n)

// 정수 범위:
