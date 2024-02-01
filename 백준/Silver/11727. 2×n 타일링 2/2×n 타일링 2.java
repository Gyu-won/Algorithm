import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public int solution() throws IOException {
        // n을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // int[n+1] block 을 선언한다.
        int[] blocks = new int[n + 1];

        if (n == 1) {
            return 1;
        }

        // block[1] 을 1, block[2] 를 3으로 초기화 한다.
        blocks[1] = 1;
        blocks[2] = 3;

        // block[n] 까지를 구한다.
        for (int i = 3; i <= n; i++) {
            blocks[i] = (blocks[i - 1] + 2 * blocks[i - 2]) % 10007;
        }

        // block[n]을 리터한다.
        return blocks[n];
    }
}

// 알고리즘: block[n] = block[n-1] + block[n-2] * 2 이다.

// 시간복잡도:

// 정수 범위:
