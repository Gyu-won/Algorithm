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

        // int[n] sequence를 입력받는다.
        int[] sequence = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        // int[n] sequenceSum을 선언한다.
        int[] sequenceSum = new int[n];

        // for문
        for (int i = 0; i < n; i++) {
            // for문
            int maxSum = 0;
            for (int j = 0; j < i; j++) {
                // sequenceSum[i] = sequence[j] < sequence[i] 이고 sequenceSum[j]들 중 최대값 + sequence[i];
                if (sequence[j] < sequence[i]) {
                    maxSum = Math.max(maxSum, sequenceSum[j]);
                }
            }
            sequenceSum[i] = maxSum + sequence[i];
        }

        // sequenceSum 의 최대값을 리턴한다.
        int maxSum = 0;
        for (int i = 0; i < n; i++) {
            maxSum = Math.max(maxSum, sequenceSum[i]);
        }
        return maxSum;
    }
}

// 15:23 - 15:39
// 총 걸린 시간: 00:16

// 알고리즘: n이 1000 이기 때문에 O(n^2) 까지 가능하다
// 증가하는 부분수열을 모두 구하고, 그 중 합이 최대인 것을 고른다.
// 숫자 n 이 포함되는 증가 부분 수열은 이전에 나온 n보다 작은 수 중 최대 값 + 자기자신 이다.

// 시간복잡도:

// 정수 범위:
