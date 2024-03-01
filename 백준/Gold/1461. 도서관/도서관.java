import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int totalDistance;
    private static int m;
    private static int zeroIdx;
    private static int[] books;

    public static void main(String[] args) throws IOException {
        // n과 m 입력 (1-50)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 책의 위치 입력 (-10000 - 10000) 0 제외
        books = new int[n + 1];
        books[0] = 0;
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= n; i++) {
            books[i] = Integer.parseInt(st.nextToken());
        }

        // 책의 위치 정렬
        Arrays.sort(books);

        // 0 위치 찾기
        zeroIdx = 0;
        for (; zeroIdx <= n; zeroIdx++) {
            if (books[zeroIdx] == 0) {
                break;
            }
        }

        totalDistance = 0;
        if (zeroIdx == 0) {
            // 0 위치가 0이면 [n]을 거리에 더하기
            totalDistance += books[n];

            // current에서 m을 빼면서 0위치 보다 작을 떄까지 거리를 더하기
            positiveMove(n - m);
        } else if (zeroIdx == n) {
            // 0위치가 n이면 [0] 을 거리에 더하기
            totalDistance += books[0] * -1;

            // current에서 m을 더하면서 0위치 보다 클때까지 거리를 더하기
            negativeMove(m);
        } else {
            // 아니면 [0] [n] 절대값 비교해서 더 큰거를 구하기
            if (books[0] * -1 < books[n]) {
                totalDistance += books[n];
                positiveMove(n - m);
                negativeMove(0);
            } else {
                totalDistance += books[0] * -1;
                positiveMove(n);
                negativeMove(m);
            }
        }

        System.out.println(totalDistance);
    }

    private static void negativeMove(int current) {
        while (current < zeroIdx) {
            totalDistance += books[current] * -2;
            current += m;
        }
    }

    private static void positiveMove(int current) {
        while (current > zeroIdx) {
            totalDistance += books[current] * 2;
            current -= m;
        }
    }
}

//


