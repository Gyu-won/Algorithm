import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // t를 입력 (1-100)
        int test = Integer.parseInt(br.readLine());

        // t번 반복
        StringBuilder result = new StringBuilder();
        for (int t = 0; t < test; t++) {
            // p 입력 (1-100000)
            String p = br.readLine();

            // n 입력 (0-100000)
            int n = Integer.parseInt(br.readLine());

            // numbers 입력 (1-100)
            Pattern pattern = Pattern.compile("[0-9]+");
            Matcher matcher = pattern.matcher(br.readLine());
            List<Integer> numbers = new ArrayList<>();
            while (matcher.find()) {
                numbers.add(Integer.parseInt(matcher.group()));
            }

            // operation
            result.append(operate(p, numbers));
            result.append("\n");
        }
        System.out.println(result.toString().trim());
    }

    private static String operate(String p, List<Integer> numbers) {
        boolean isReverse = false;
        int start = 0, end = numbers.size() - 1;
        for (char command : p.toCharArray()) {
            if (command == 'R') {
                isReverse = !isReverse;
            } else {
                if (start > end) {
                    return "error";
                }
                if (isReverse) {
                    end--;
                } else {
                    start++;
                }
            }
        }
        return createResult(isReverse, start, end, numbers);
    }

    private static String createResult(boolean isReverse, int start, int end, List<Integer> numbers) {
        StringBuilder result = new StringBuilder();
        result.append("[");
        if (isReverse) {
            for (int i = end; i > start; i--) {
                result.append(numbers.get(i));
                result.append(",");
            }
            if (start < numbers.size() && start <= end) {
                result.append(numbers.get(start));
            }
        } else {
            for (int i = start; i < end; i++) {
                result.append(numbers.get(i));
                result.append(",");
            }
            if (end >= 0 && start <= end) {
                result.append(numbers.get(end));
            }
        }
        result.append("]");
        return result.toString();
    }
}

// R(뒤집기): 수 순서 뒤집기
// D(버리기): 첫번재 수 버리기, 비어있으면 에러
// error면 error 출력

// O(T * n
