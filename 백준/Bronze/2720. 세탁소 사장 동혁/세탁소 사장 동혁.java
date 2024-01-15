import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final int[] moneys = new int[]{25, 10, 5, 1};

    public static void main(String[] args) throws IOException {
        // T를 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        // 동전의 개수를 구한다.
        StringBuilder exchange = new StringBuilder();
        for (int i = 0; i < T; i++) {
            // 결과를 result에 저장한다.
            exchange.append(calculateExchange(Integer.parseInt(br.readLine())));
            exchange.append("\n");
        }

        System.out.println(exchange.toString().trim());
    }

    private static String calculateExchange(int exchange) {
        // 금액이 큰 것부터 최대한 많이 채운다.
        StringBuilder result = new StringBuilder();
        for (int money : moneys) {
            result.append(exchange / money);
            result.append(" ");
            exchange %= money;
        }
        return result.toString().trim();
    }
}