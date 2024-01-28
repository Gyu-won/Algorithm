import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public long solution() throws IOException {
        // n을 입력받는다 (long)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());

        // 제곱근을 계산한다.
        long value = (long) Math.sqrt(n);

        // 값을 올림한다. (long)
        if (value * value < n) {
            return value + 1;
        }
        return value;
    }
}

// 알고리즘:

// 시간복잡도: O()

// 정수 범위