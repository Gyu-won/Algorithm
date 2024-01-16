import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        // N을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 최소 설탕 개수를 구한다.
        int minimumBucket = calculateBucketNumber(N);

        System.out.println(minimumBucket);
    }

    private static int calculateBucketNumber(int weight) {
        // 3kg를 0부터 늘려가면서 채운다.
        for (int i = 0; i <= weight / 3; i++) {
            // 5kg로 나눠 떨어질때까지
            if ((weight - 3 * i) % 5 == 0) {
                return i + (weight - 3 * i) / 5;
            }
        }
        return -1;
    }
}
