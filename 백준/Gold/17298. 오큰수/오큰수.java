import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // N을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Integer> answers = new ArrayList<>(Collections.nCopies(N, -1));
        Deque<Integer> stack = new ArrayDeque<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Integer> sequence = new ArrayList<>();
        while (st.hasMoreTokens()) {
            sequence.add(Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && sequence.get(stack.getLast()) < sequence.get(i)) {
                answers.set(stack.removeLast(), sequence.get(i));
            }
            stack.addLast(i);
        }

        StringBuilder result = new StringBuilder();
        for (Integer answer : answers) {
            result.append(answer + " ");
        }
        System.out.println(result);
    }
}