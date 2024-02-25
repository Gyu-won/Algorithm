import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int count = 0;

    public static void main(String[] args) throws IOException {
        // n 과 s 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        // numbers 입력
        int[] numbers = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        // combination
        combination(0, s, 0, n, numbers);
        if (s == 0) {
            count--;
        }
        System.out.println(count);
    }

    private static void combination(int current, int sum, int start, int n, int[] numbers) {
        if (current == sum) {
            count++;
        }

        for (int i = start; i < n; i++) {
            combination(current + numbers[i], sum, i + 1, n, numbers);
        }
    }
}

// n개의 정수 수열
// 결국 중복 허용하지 않는 조합

// O(2^n)도 가능

