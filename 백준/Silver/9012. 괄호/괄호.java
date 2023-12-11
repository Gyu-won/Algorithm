import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) throws IOException {
        // T를 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        // 괄호 문자열을 입력받으면서 결과 얻는다.
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < T; i++) {
            Deque<Character> stack = new ArrayDeque<>();
            char[] parentheses = br.readLine().toCharArray();

            if (isValid(parentheses, stack)) {
                result.append("YES\n");
            } else {
                result.append("NO\n");
            }
        }

        // 결과를 출력한다.
        System.out.println(result);
    }

    private static boolean isValid(char[] parentheses, Deque<Character> stack) {
        for (Character parenthesis : parentheses) {
            try {
                if (parenthesis.equals('(')) {
                    stack.addLast(parenthesis);
                }
                if (parenthesis.equals(')')) {
                    stack.removeLast();
                }
            } catch (NoSuchElementException exception) {
                return false;
            }
        }
        return stack.isEmpty();
    }
}