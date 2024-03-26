import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        // n, k 입력 (1-10^7) (1-1000)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        System.out.println(calculateAddedBottle(n, k));
    }

    private static int calculateAddedBottle(int n, int k) {
        // n <= k 이면 0 리턴
        if (n <= k) {
            return 0;
        }

        // k-1 번 가장 큰 2의 제곱을 뺌
        for (int i = 0; i < k - 1; i++) {
            n -= calculateBiggestPowerOfTwo(n);
        }

        // 최대 2승 - 남은 수
        return calculateBiggerPowerOfTwo(n) - n;
    }

    private static int calculateBiggestPowerOfTwo(int n) {
        if (n <= 1) {
            return n;
        }
        for (int i = 1; ; i++) {
            if (n < Math.pow(2, i)) {
                return (int) Math.pow(2, i - 1);
            }
        }
    }

    private static int calculateBiggerPowerOfTwo(int n) {
        if (n <= 1) {
            return n;
        }
        for (int i = 1; ; i++) {
            if (n <= Math.pow(2, i)) {
                return (int) Math.pow(2, i);
            }
        }
    }
}

// n 개의 물병, 1리터씩, 한번에 k개의 물병 옮길 수 있음
// 차있는 물병이 k 개 이하
// 불가능하면 물 새로 삼
// 사야하는 물병의 최솟값
// 0 - 2^m 까지 합으로 하면 됨