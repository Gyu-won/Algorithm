import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int answer = 4;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] minFourSquares = new int[N + 1];
        minFourSquares[1] = 1;

        for (int i = 2; i <= N; i++) {
            int min = 4;
            for (int j = 1; j * j <= i; j++) {
                min = Math.min(min, minFourSquares[i - j * j]);
            }
            minFourSquares[i] = min + 1;
        }
        System.out.println(minFourSquares[N]);
    }
}

// 알고리즘: 제곱의 합으로 이루어져있기 떄문에 해당 숫자보다 작은 제곱수를 뺀 값에 1을 더해주면 해당 값의 최소 값이므로 dp를 사용한다.

// 시간복잡도

// 정수 범위
