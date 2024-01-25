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

        // fibonacci 숫자를 출력한다.
        System.out.println(fibonacci(N));
    }

    private int fibonacci(int n) {
        if (n < 2) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}

// 알고리즘: 점화식이 주어졌고, 값이 항상 일정하기 때문에 이전값을 사용해서 문제를 푼다.
// n이 매우 작기 때문에 탑 다운 방식으로 문제를 풀자

// 시간복잡도: O(n)

// 정수 범위:
