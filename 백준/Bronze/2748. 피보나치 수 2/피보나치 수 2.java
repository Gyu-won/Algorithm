import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    public void solution() throws IOException {
        // n을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // fibonacciArray를 저장한다.
        long[] fibonacciArray = new long[N + 1];
        fibonacciArray[0] = 0;
        fibonacciArray[1] = 1;

        // fibonacci 숫자를 구한다.
        for (int i = 2; i <= N; i++) {
            fibonacciArray[i] = fibonacciArray[i - 1] + fibonacciArray[i - 2];
        }

        System.out.println(fibonacciArray[N]);
    }
}

// 알고리즘: 점화식이 주어졌고, 값이 항상 일정하기 때문에 이전값을 사용해서 문제를 푼다.
// n이 커졌기 때문에 바텀 업 방식으로 문제를 풀자

// 시간복잡도: O(n)

// 정수 범위:
