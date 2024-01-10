import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] count = new int[2];
        for (int i = 1; i <= N; i++) {
            count[0] += calculateTwoCount(i);
            count[1] += calculateFiveCount(i);
        }

        System.out.println(Math.min(count[0], count[1]));
    }

    private static int calculateFiveCount(int i) {
        int count = 0;
        while (i % 5 == 0) {
            count++;
            i /= 5;
        }
        return count;
    }

    private static int calculateTwoCount(int i) {
        int count = 0;
        while (i % 2 == 0) {
            count++;
            i /= 2;
        }
        return count;
    }
}