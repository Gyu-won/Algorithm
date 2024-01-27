import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public String solution() throws IOException {
        // N을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 결과를 리턴한다.
        if (N % 2 == 0) {
            return "CY";
        } else {
            return "SK";
        }
    }
}

// 알고리즘: 게임을 이기는 사람은 개수 - 4를 만드는 사람이고 이게 반복된다.
// winner[N] = winner[N-4]
// 0(창영), 1(상근), 2(창영), 3(상근)
// 정리하면 짝수이면 창영, 홀수이면 상근이가 이긴다.

// 시간복잡도:

// 정수 범위
