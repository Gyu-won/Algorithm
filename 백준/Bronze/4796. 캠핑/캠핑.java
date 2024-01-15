import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // L, P, V를 반복해서 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();
        for (int i = 1; ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int L = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            if (L == 0) {
                break;
            }
            result.append("Case ");
            result.append(i);
            result.append(": ");
            result.append(calculateMaximumUsage(L, P, V));
            result.append("\n");
        }

        // 결과를 출력한다.
        System.out.println(result.toString().trim());
    }

    private static int calculateMaximumUsage(int L, int P, int V) {
        // 최대 사용 날짜를 구한다.
        return (V / P) * L + Math.min(V % P, L);
    }
}
