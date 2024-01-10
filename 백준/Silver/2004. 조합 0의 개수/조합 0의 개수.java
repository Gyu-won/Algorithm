import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 2와 5의 개수를 구하자

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int numberOfTwo =
                calculateNumberOfPower(n, 2) - calculateNumberOfPower(m, 2) - calculateNumberOfPower(n - m, 2);
        int numberOfFive =
                calculateNumberOfPower(n, 5) - calculateNumberOfPower(m, 5) - calculateNumberOfPower(n - m, 5);

        System.out.println(Math.min(numberOfTwo, numberOfFive));

    }

    private static int calculateNumberOfPower(int n, int divisor) {
        int count = 0;
        while (n >= divisor) {
            count += n / divisor;
            n /= divisor;
        }
        return count;
    }
}