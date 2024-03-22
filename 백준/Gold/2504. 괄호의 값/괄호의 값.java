import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        // string 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String string = br.readLine();

        //
        Deque<Character> brackets = new ArrayDeque<>();
        Deque<String> expressions = new ArrayDeque<>();

        for (int i = 0; i < string.length(); i++) {
            char bracket = string.charAt(i);
            if (isOpen(bracket)) {
                if (!brackets.isEmpty()) {
                    if (isOpen(string.charAt(i - 1))) {
                        expressions.addLast("*");
                    } else {
                        expressions.addLast("+");
                    }
                }

                brackets.addLast(bracket);
                expressions.addLast(String.valueOf(bracket));
                expressions.addLast(toNumber(bracket));
            } else {
                if (brackets.isEmpty() || isSmall(bracket) != isSmall(brackets.getLast())) {
                    System.out.println(0);
                    return;
                }
                char startBracket = brackets.removeLast();
                while (true) {
                    int operand1 = Integer.parseInt(expressions.removeLast());
                    String operator = expressions.removeLast();
                    if (operator.equals(String.valueOf(startBracket))) {
                        expressions.addLast(String.valueOf(operand1));
                        break;
                    }
                    int operand2 = Integer.parseInt(expressions.removeLast());
                    expressions.addLast(calculate(operand1, operand2, operator));
                }
            }
        }

        if (!brackets.isEmpty()) {
            System.out.println(0);
            return;
        }

        while (expressions.size() > 1) {
            int operand1 = Integer.parseInt(expressions.removeLast());
            int operand2 = Integer.parseInt(expressions.removeLast());
            expressions.addLast(calculate(operand1, operand2, "+"));
        }
        System.out.println(expressions.removeLast());
    }

    private static String calculate(int op1, int op2, String operator) {
        if (operator.equals("+")) {
            return String.valueOf(op1 + op2);
        }
        return String.valueOf(op1 * op2);
    }

    private static String toNumber(char bracket) {
        if (isSmall(bracket)) {
            return "2";
        }
        return "3";
    }

    private static boolean isOpen(char bracket) {
        return bracket == '(' || bracket == '[';
    }

    private static boolean isSmall(char bracket) {
        return bracket == '(' || bracket == ')';
    }
}

// (): 2 / []: 3
// 올바른 괄호열이면 계산, 아니면 0 출력

// 넣을 떄 제일 위에꺼가 (면 *넣고 넣기
// 부호, 괄호, 숫자 순으로 넣기
// 괄호가 짝이 안맞으면 0 출력
