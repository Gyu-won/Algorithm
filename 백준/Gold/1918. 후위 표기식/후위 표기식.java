import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        // 식을 입력 받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String infix = br.readLine();

        StringBuilder result = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>();
        for (char character : infix.toCharArray()) {
            switch (character) {
                case '+':
                case '-':
                case '*':
                case '/':
                    while (!stack.isEmpty() && priority(stack.getLast()) >= priority(character)) {
                        result.append(stack.removeLast());
                    }
                    stack.addLast(character);
                    break;
                case '(':
                    stack.addLast(character);
                    break;
                case ')':
                    while (true) {
                        char characterFromStack = stack.removeLast();
                        if (characterFromStack == '(') {
                            break;
                        }
                        result.append(characterFromStack);
                    }
                    break;
                default:
                    result.append(character);
            }
        }
        while (!stack.isEmpty()) {
            result.append(stack.removeLast());
        }

        System.out.println(result);
    }

    private static int priority(char character) {
        switch (character) {
            case '(':
                return 0;
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }
        return -1;
    }
}