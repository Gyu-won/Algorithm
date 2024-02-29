import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static int cnt = 1;
    private static int n;
    private static String value = "-1";

    public static void main(String[] args) throws IOException {
        // n 을 입력받는다 (1-1,000,000)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        if (n == 1) {
            System.out.println(0);
            return;
        }

        for (int digit = 1; digit <= 10; digit++) {
            for (int i = 1; i < 10; i++) {
                List<Integer> numbers = new ArrayList<>();
                numbers.add(i);
                dfs(digit, i, numbers);
                if (!value.equals("-1")) {
                    System.out.println(value);
                    return;
                }
            }
        }
        System.out.println(value);
    }

    private static void dfs(int digit, int firstNumber, List<Integer> numbers) {
        if (digit == numbers.size()) {
            cnt++;
            if (cnt == n) {
                StringBuilder result = new StringBuilder();
                for (int i = 0; i < numbers.size(); i++) {
                    result.append(numbers.get(i));
                }
                value = result.toString();
            }
            return;
        }
        for (int i = 0; i < firstNumber; i++) {
            numbers.add(i);
            dfs(digit, i, numbers);
            numbers.remove(numbers.size() - 1);
        }

    }

}

// O(nlogn) 가능