import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        // n 입력 (1-8)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringBuilder result = new StringBuilder();
        calculateMagicPrime(n, 2, result);
        calculateMagicPrime(n, 3, result);
        calculateMagicPrime(n, 5, result);
        calculateMagicPrime(n, 7, result);
        System.out.println(result.toString().trim());
    }

    private static void calculateMagicPrime(int n, int number, StringBuilder result) {
        if (n == 1) {
            result.append(number);
            result.append("\n");
            return;
        }
        for (int i = 0; i < 10; i++) {
            if (isPrime(number * 10 + i)) {
                calculateMagicPrime(n - 1, number * 10 + i, result);
            }
        }
    }

    private static boolean isPrime(int number) {
        if (number % 2 == 0) {
            return false;
        }
        for (int i = 3; i < number / 3; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}

// 08:42 -