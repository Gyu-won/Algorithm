import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public long solution() throws IOException {
        // n을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // budgets을 입력받으며 expectBudget을 구한다. int[]
        int[] budgets = new int[n];
        int expectBudget = 0;
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            budgets[i] = Integer.parseInt(st.nextToken());
            expectBudget += budgets[i];
        }

        // budgets을 정렬한다.
        Arrays.sort(budgets);

        // budget을 입력받는다.
        int budget = Integer.parseInt(br.readLine());

        // budget이 expectBudget보다 크거나 같으면 최대값을 리턴한다.
        if (budget >= expectBudget) {
            return budgets[n - 1];
        }

        // budget이 expectBudget보다 작으면 n부터 최대값까지 이분탐색을 수행한다.
        int difference = expectBudget - budget;
        int start = 1;
        int end = budgets[n - 1];
        while (start <= end) {
            int mid = (start + end) / 2;
            int index = Arrays.binarySearch(budgets, mid);
            if (index < 0) {
                index = (index + 1) * -1;
            }

            int differenceSum = 0;
            for (int i = index; i < n; i++) {
                differenceSum += budgets[i] - mid;
            }

            if (differenceSum < difference) {
                end = mid - 1;
            } else if (differenceSum > difference) {
                start = mid + 1;
            } else {
                return mid;
            }
        }

        return end;
    }
}

// 알고리즘: 다 더했을 때 합을 안넘으면 그냥 최대값 리턴
// 합을 넘으면 상한선이 최대갔이 된다.
// n 부터 최대값까지 이분탐색

// 시간복잡도: O(n + nlogn + logb * n)

// 정수 범위
