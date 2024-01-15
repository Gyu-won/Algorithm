import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        // N을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 좌석배치를 입력받는다.
        String seatArrangement = br.readLine();

        // LL을 C로 치환한다.
        String seatArrangementWithCouple = seatArrangement.replace("LL", "C");

        int numberOfCupHolder = seatArrangementWithCouple.length() + 1;

        // 컵을 놓을 수 있는 최대 사람 수를 구한다.
        System.out.println(Math.min(N, numberOfCupHolder));
    }
}
