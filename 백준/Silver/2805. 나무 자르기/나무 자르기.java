import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static int[] trees;

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public long solution() throws IOException {
        // n과 m을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // int[n] trees를 입력받는다.
        trees = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
        }

        // trees를 정렬한다.
        Arrays.sort(trees);

        // 0 ~ 1000000000 까지 이분 탐색을 통해 값을 지정하고, 잘리는 길이를 계산한다.
        long start = 0;
        long end = 1000000000;
        while (start <= end) {
            int mid = (int) (start + end) / 2;
            long cuttingAmount = calculateCuttingAmount(mid);

            if (cuttingAmount < m) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        // 최대 높이를 출력한다.
        return end;
    }

    private long calculateCuttingAmount(int height) {
        long amount = 0;
        for (int i = 0; i < n; i++) {
            int difference = trees[i] - height;
            if (difference > 0) {
                amount += difference;
            }
        }
        return amount;
    }
}

// 16:42 -
// 총 걸린 시간:

// 알고리즘: 최대값 부터 하나씩 내려보며 알아봐도 되지만 이 경우 O(10^9 * n)이라서 불가능하다
// 이진탐색으로 탐색하면 O(n * log10^9)으로 충분히 가능하다.

// 시간복잡도: O(n * log10^9)

// 정수 범위: 두개의 합을 구하는 것은 long, 나머지는 int
