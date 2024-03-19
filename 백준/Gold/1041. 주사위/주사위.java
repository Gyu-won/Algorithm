import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // n 입력 (1-1000000)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // dice 입력 (1-50)
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] dice = new int[6];
        for (int i = 0; i < 6; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }
        int temp = dice[3];
        dice[3] = dice[5];
        dice[5] = temp;

        if (n == 1) {
            int sum = 0, maxValue = 0;
            for (int i = 0; i < 6; i++) {
                sum += dice[i];
                maxValue = Math.max(maxValue, dice[i]);
            }
            System.out.println(sum - maxValue);
            return;
        }

        // maxNumber
        int minNumber = findMin(dice);

        // maxSum
        int minSum = findMinSum(dice);

        // maxTriple
        int minTriple = findTriple(dice);

        // 결과 출력
        System.out.println(calculate(n, minNumber, minSum, minTriple));

    }

    private static long calculate(int n, int one, int two, int three) {
        long sum = (n - 2) * (long) (5 * n - 6) * one;
        sum += (8 * n - 12) * two;
        sum += 4 * three;
        return sum;
    }

    private static int findMin(int[] dice) {
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < 6; i++) {
            minValue = Math.min(minValue, dice[i]);
        }
        return minValue;
    }

    private static int findMinSum(int[] dice) {
        int minSum = Integer.MAX_VALUE;
        for (int i = 0; i < 6; i++) {
            for (int j = i + 1; j < 6; j++) {
                if (j != i + 3) {
                    minSum = Math.min(minSum, dice[i] + dice[j]);
                }
            }
        }
        return minSum;
    }

    private static int findTriple(int[] dice) {
        int tripleSum = Integer.MAX_VALUE;
        tripleSum = Math.min(tripleSum, dice[0] + dice[1] + dice[2]);
        tripleSum = Math.min(tripleSum, dice[0] + dice[1] + dice[5]);
        tripleSum = Math.min(tripleSum, dice[0] + dice[4] + dice[5]);
        tripleSum = Math.min(tripleSum, dice[0] + dice[2] + dice[4]);
        tripleSum = Math.min(tripleSum, dice[3] + dice[1] + dice[2]);
        tripleSum = Math.min(tripleSum, dice[3] + dice[1] + dice[5]);
        tripleSum = Math.min(tripleSum, dice[3] + dice[4] + dice[5]);
        tripleSum = Math.min(tripleSum, dice[3] + dice[2] + dice[4]);
        return tripleSum;
    }
}

// 1면: (n-2)(5n-6) -> 최대값
// 2면: 8n - 12 -> 2개 최대값
// 3면: 4개 -> 3개 최대값
