import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Main {
    private static final Deque<Integer> stack = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        // N을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // N번 명령을 입력받으면서 명령을 수행한다.
        StringTokenizer st;
        String command;
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            command = st.nextToken();

            if (command.equals("push")) {
                stack.addLast(Integer.parseInt(st.nextToken()));
            }
            if (command.equals("pop")) {
                result.append(pop() + "\n");
            }
            if (command.equals("size")) {
                result.append(stack.size() + "\n");
            }
            if (command.equals("empty")) {
                result.append(checkEmpty() + "\n");
            }
            if (command.equals("top")) {
                result.append(getTop() + "\n");
            }
        }

        // 결과를 출력한다.
        System.out.println(result);
    }

    private static Integer getTop() {
        try {
            return stack.getLast();
        } catch (NoSuchElementException exception) {
            return -1;
        }
    }

    private static Integer checkEmpty() {
        if (stack.isEmpty()) {
            return 1;
        }
        return 0;
    }

    private static Integer pop() {
        try {
            return stack.removeLast();
        } catch (NoSuchElementException exception) {
            return -1;
        }
    }

}