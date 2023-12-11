import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    private static final Deque<Integer> stack = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        // T를 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer T = Integer.parseInt(br.readLine());

        // 문장을 입력받고 문장을 뒤집는다.
        StringTokenizer st;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                result.append(new StringBuilder(st.nextToken()).reverse() + " ");
            }
            result.append("\n");
        }

        //결과를 출력한다.
        System.out.println(result);
    }
}