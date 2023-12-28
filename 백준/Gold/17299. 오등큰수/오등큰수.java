import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // N을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 수열 A와 숫자의 등장 횟수를 저장한다.
        List<Integer> sequence = new ArrayList<>();
        Map<Integer, Integer> count = new HashMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        while (st.hasMoreTokens()) {
            int value = Integer.parseInt(st.nextToken());
            sequence.add(value);
            if (count.containsKey(value)) {
                count.put(value, count.get(value) + 1);
            } else {
                count.put(value, 1);
            }
        }

        // 각 자리 값들을 구한다.
        Deque<Integer> stack = new ArrayDeque<>();
        List<String> answers = new ArrayList<>(Collections.nCopies(N, "-1"));
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && count.get(sequence.get(stack.getLast())) < count.get(sequence.get(i))) {
                answers.set(stack.removeLast(), Integer.toString(sequence.get(i)));
            }
            stack.add(i);
        }

        String result = String.join(" ", answers);
        System.out.println(result);
    }
}