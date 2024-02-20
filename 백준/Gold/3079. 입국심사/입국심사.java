import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final Long INF = 1000000000000000000L;

    public static void main(String[] args) throws IOException {
        //n 과 m 을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // int time[n] 을 입력받는다.
        int[] table = new int[n];
        for (int i = 0; i < n; i++) {
            table[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(binarySearch(m, table));
    }

    private static long binarySearch(int m, int[] table) {
        long start = 1, end = INF;

        while (start <= end) {
            long mid = (start + end) / 2;

            if (isPossible(mid, m, table)) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    private static boolean isPossible(long time, int m, int[] table) {
        long count = 0;
        for (int i = 0; i < table.length; i++) {
            count += time / table[i];
            if (count >= m) {
                break;
            }
        }

        return count >= m;
    }
}

// 요약
// m 명의 사람, n명의 심사대, 시간은 tk (1-1000000000), (1-100000)
// 심사받는 시간의 최소시간

// 최대 가능한 답은 10억 * 10억이다

// 1초: O(log * n)

// 12:13