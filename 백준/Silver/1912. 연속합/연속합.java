import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public int solution() throws IOException {
        // n을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // number를 입력받으며 부분합 int[n] totalSum 을 계산한다.
        int[] totalSum = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += Integer.parseInt(st.nextToken());
            totalSum[i] = sum;
        }

        // minSum을 0, maxSum 을 -1000으로 설정한다.
        int minSum = 0, maxSum = -1000;

        // for문을 돌면서 totalSum[i] - minSum 이 maxSum 보다 크면 maxSum을 update
        for (int i = 0; i < n; i++) {
            if (totalSum[i] - minSum > maxSum) {
                maxSum = totalSum[i] - minSum;
            }

            //totalSum[i]이 minSum보다 작으면 minSum update
            if (totalSum[i] < minSum) {
                minSum = totalSum[i];
            }
        }

        // maxSum을 리턴
        return maxSum;
    }
}

// 14:15 - 15:08
// 총 걸린 시간:

// 알고리즘: O(n^2) 이하의 알고리즘을 선택해야한다.
// 부분합을 구하고 이전까지의 최소부분합과 현재부분합의 차가 최대 부분합보다 크면 update
//
// 시간복잡도: O(n)

// 정수 범위:
