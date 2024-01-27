import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public int solution() throws IOException {
        // N을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if (N == 1) {
            return 0;
        } else if (N == 2) {
            return 1;
        }

        // 1, 2, 3의 값을 세팅한다. int[] minCalculation
        int[] minCalculation = new int[N + 1];
        minCalculation[1] = 0;
        minCalculation[2] = 1;
        minCalculation[3] = 1;

        // 1부터 N까지 값을 구한다.
        for (int i = 4; i <= N; i++) {
            // i의 값은 i 보다 작은 같은 2의 배수 + 1 + 차이값 과 3의 배수 + 1 + 차이값 중 최소값 이다
            int number1 = i / 2 * 2;
            int number2 = i / 3 * 3;
            minCalculation[i] =
                    Math.min(minCalculation[number1 / 2] + i - number1, minCalculation[number2 / 3] + i - number2) + 1;
        }

        // minCaculation[N]을 리턴한다.
        return minCalculation[N];
    }
}

// 알고리즘: 10의 경우를 생각해보면 5일 때 + 1 또는 9일 떄 +1 이다.
// 1을 만들기 위해서는 직전 값이 무조건 2 나 3 둘 중 하나이다.
// 1을 0으로, 2를 1로, 3을 1로 세팅 후 바텀업 방식을 이용하여 10의 6승까지 최소를 다 계산한다.

// 시간복잡도: 0.15초 이기 때문에 천만번 이하 연산이어야 한다. 바텀업 방식 사용 O(N)

// 정수 범위
