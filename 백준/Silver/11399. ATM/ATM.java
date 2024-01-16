import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // N을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 돈 인출 시간을 오름차순 정렬한다.
        List<Integer> time = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            time.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(time);

        // 각 항목을 순회하면서 n-i번 더한다.
        int totalTime = 0;
        for (int i = 0; i < N; i++) {
            totalTime += time.get(i) * (N - i);
        }
        System.out.println(totalTime);
    }
}
