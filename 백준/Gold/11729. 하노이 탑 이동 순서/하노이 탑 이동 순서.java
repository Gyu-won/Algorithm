import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws IOException {
        // n 입력 (1-20)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // hanoi(n) 구하기
        System.out.println(hanoi(n, 1, 2, 3));
        System.out.println(result.toString().trim());
    }

    private static int hanoi(int n, int start, int mid, int end) {
        if (n == 1) {
            result.append(start);
            result.append(" ");
            result.append(end);
            result.append("\n");
            return 1;
        }

        int count = hanoi(n - 1, start, end, mid);
        result.append(start);
        result.append(" ");
        result.append(end);
        result.append("\n");
        count++;
        count += hanoi(n - 1, mid, start, end);
        return count;
    }
}

// 19:37 -


