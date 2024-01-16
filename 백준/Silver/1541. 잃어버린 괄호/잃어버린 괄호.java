import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // 수식을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String expression = br.readLine();

        // - 가 처음 나오는 위치를 찾아서 2부분으로 나눈다.
        int firstMinus = expression.indexOf("-");
        int minimumSum = 0;
        if (firstMinus == -1) {
            StringTokenizer st = new StringTokenizer(expression, "+");
            while (st.hasMoreTokens()) {
                minimumSum += Integer.parseInt(st.nextToken());
            }
            System.out.println(minimumSum);
            return;
        }

        // 앞 부분은 +, -로 나누어서 다 더해준다.
        int firstValue = calculateSum(expression.substring(0, firstMinus));

        // 뒷 부분도 +나 - 로 나누어서 다 더해준다.
        int secondValue = calculateSum(expression.substring(firstMinus + 1));

        // 앞부분에서 뒷부분을 뺀다.
        System.out.println(firstValue - secondValue);
    }

    private static int calculateSum(String expression) {
        String[] numbers = expression.split("\\+|-");

        int sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            sum += Integer.parseInt(numbers[i]);
        }
        return sum;
    }
}
