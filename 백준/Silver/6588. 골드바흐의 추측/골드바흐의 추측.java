import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    final static int MAX_NUMBER = 1000000;
    static boolean[] notPrime = new boolean[MAX_NUMBER];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        findPrimes();

        StringBuilder result = new StringBuilder();
        while (true) {
            String n = br.readLine();
            if (n.equals("0")) {
                break;
            }

            result.append(validGoldbach(Integer.parseInt(n)));
            result.append("\n");
        }
        System.out.println(result);
    }

    private static String validGoldbach(int n) {
        for (int odd = 3; odd <= n / 2; odd += 2) {
            if (!notPrime[odd] && !notPrime[n - odd]) {
                StringBuilder str = new StringBuilder();
                str.append(n);
                str.append(" = ");
                str.append(odd);
                str.append(" + ");
                str.append(n - odd);
                return str.toString();
            }
        }
        return "Goldbach's conjecture is wrong.";
    }

    private static void findPrimes() {
        for (int i = 3; i <= Math.sqrt(MAX_NUMBER); i += 2) {
            for (int j = 3; i * j < MAX_NUMBER; j += 2) {
                notPrime[i * j] = true;
            }
        }
    }
}