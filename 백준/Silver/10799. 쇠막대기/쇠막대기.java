import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        // 배치를 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String layout = br.readLine();

        int numberOfStick = 0;
        Deque<Character> stick = new ArrayDeque<>();
        Deque<Character> tempBuffer = new ArrayDeque<>();
        for (char symbol : layout.toCharArray()) {
            if (symbol == '(') {
                if (!tempBuffer.isEmpty()) {
                    stick.addLast(tempBuffer.removeLast());
                    numberOfStick += 1;
                }
                tempBuffer.addLast(symbol);
            }
            if (symbol == ')') {
                if (tempBuffer.isEmpty()) {
                    stick.removeLast();
                } else {
                    tempBuffer.removeLast();
                    numberOfStick += stick.size();
                }
            }
        }

        System.out.println(numberOfStick);
    }
}