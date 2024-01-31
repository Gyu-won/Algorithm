import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static final int NUMBER_SIZE = 100001;

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public String solution() throws IOException {
        // n을 입력받는다 int
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // long[] liquids 를 입력받는다.
        long[] liquids = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            liquids[i] = Integer.parseInt(st.nextToken());
        }

        // liquids를 정렬한다.
        Arrays.sort(liquids);

        // start와 end(int) , minDifference(long), result(String)을 선언한다.
        int start = 0, end = n - 1;
        long minDifference = Long.MAX_VALUE;
        String result = "";

        while (start < end) {
            // 두 값을 더한값의 절대값이 minDifference보다 작으면 result를 update 한다.
            long sum = liquids[start] + liquids[end];
            long difference = Math.abs(sum);
            if (difference < minDifference) {
                minDifference = difference;
                result = liquids[start] + " " + liquids[end];
            }

            if (sum < 0) {
                // 두 값을 더한 값이 음수면 start를 증가시킨다
                start++;
            } else if (sum > 0) {
                // 양수면 end를 감소시키면 된다.
                end--;
            } else {
                // 0이면 start와 end를 리턴한다.
                return liquids[start] + " " + liquids[end];
            }
        }

        // result를 리턴한다.
        return result;
    }
}

// 알고리즘: 완전탐색으로 모든 용액의 합을 구하는것은 할 수 없다)
// 정렬후, 투포인터 알고리즘을 사용한다.
// 두 값을 더한 값이 음수면 start를 증가시키면 되고, 양수면 end를 감소시키면 된다.
// 0이면 그냥 그거 바로 출력한다.

// 시간복잡도: O(n)

// 정수 범위
