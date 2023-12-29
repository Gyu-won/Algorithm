import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        // N을 입력받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 후위 표기식을 입력받는다.
        String suffix = br.readLine();

        // 대응 값을 입력받는다.
        Map<Character, Double> values = new HashMap<>();
        for (int i = 0; i < N; i++) {
            values.put((char) ('A' + i), Double.valueOf(br.readLine()));
        }

        // 결과를 계산한다.
        Deque<Double> stack = new ArrayDeque<>();
        for (char element : suffix.toCharArray()) {
            if (values.containsKey(element)) {
                stack.addLast(values.get(element));
                continue;
            }
            double secondOperand = stack.removeLast();
            double firstOperand = stack.removeLast();
            if (element == '+') {
                stack.addLast(firstOperand + secondOperand);
                continue;
            }
            if (element == '-') {
                stack.addLast(firstOperand - secondOperand);
                continue;
            }
            if (element == '*') {
                stack.addLast(firstOperand * secondOperand);
                continue;
            }
            if (element == '/') {
                stack.addLast(firstOperand / secondOperand);
            }
        }

        System.out.printf("%.2f", stack.getLast());
    }
}