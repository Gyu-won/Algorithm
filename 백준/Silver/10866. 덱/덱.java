import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // N을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 각 명령에 대한 동작을 정의한다.
        Deque<Integer> deque = new ArrayDeque<>();
        StringBuilder result = new StringBuilder();
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            String command = st.nextToken();
            try {
                switch (command) {
                    case ("push_front"):
                        deque.addFirst(Integer.parseInt(st.nextToken()));
                        break;
                    case ("push_back"):
                        deque.addLast(Integer.parseInt(st.nextToken()));
                        break;
                    case ("pop_front"):
                        result.append(deque.removeFirst() + "\n");
                        break;
                    case ("pop_back"):
                        result.append(deque.removeLast() + "\n");
                        break;
                    case ("size"):
                        result.append(deque.size() + "\n");
                        break;
                    case ("empty"):
                        if (deque.isEmpty()) {
                            result.append("1\n");
                        } else {
                            result.append("0\n");
                        }
                        break;
                    case ("front"):
                        result.append(deque.getFirst() + "\n");
                        break;
                    case ("back"):
                        result.append(deque.getLast() + "\n");
                        break;
                }
            } catch (NoSuchElementException exception) {
                result.append("-1\n");
            }
        }

        System.out.println(result);
    }
}