import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final int MONEY = 1000;
    private static final int[] moneys = new int[]{500, 100, 50, 10, 5, 1};

    public static void main(String[] args) throws IOException {
        // price를 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int price = Integer.parseInt(br.readLine());

        // exchange를 구한다.
        System.out.println(calculateNumberOfExchange(MONEY - price));
    }

    private static int calculateNumberOfExchange(int exchange) {
        // 거스름돈 개수의 합을 구한다.
        int count = 0;
        for (int money : moneys) {
            count += exchange / money;
            exchange %= money;
        }
        return count;
    }
}