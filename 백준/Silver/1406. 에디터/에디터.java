import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // 초기 문장을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text = br.readLine();
        Deque<String> cursorFront = new ArrayDeque<>();
        Deque<String> cursorBack = new ArrayDeque<>();
        for (char character : text.toCharArray()) {
            cursorFront.addLast(String.valueOf(character));
        }

        // M을 입력받는다.
        int M = Integer.parseInt(br.readLine());

        // M번만큼 문자를 입력받으며 결과를 계산한다.
        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            String inputCommand = br.readLine();
            try {
                if (inputCommand.startsWith("P")) {
                    st = new StringTokenizer(inputCommand, " ");
                    st.nextToken();
                    cursorFront.addLast(st.nextToken());
                } else if (inputCommand.equals("L")) {
                    cursorBack.addLast(cursorFront.removeLast());
                } else if (inputCommand.equals("D")) {
                    cursorFront.addLast(cursorBack.removeLast());
                } else if (inputCommand.equals("B")) {
                    cursorFront.removeLast();
                }
            } catch (NoSuchElementException ignored) {

            }
        }

        // 결과를 출력한다.
        StringBuilder result = new StringBuilder();
        while (true) {
            try {
                result.append(cursorFront.removeFirst());
            } catch (NoSuchElementException exception) {
                break;
            }
        }
        while (true) {
            try {
                result.append(cursorBack.removeLast());
            } catch (NoSuchElementException exception) {
                break;
            }
        }

        System.out.println(result);
    }
}