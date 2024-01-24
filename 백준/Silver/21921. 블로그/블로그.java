import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // N과 X를 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        // 0부터 X 까지 합을 구해서 최대값으로 설정한다.
        int[] visitNumbers = new int[N];
        long maxValue = 0;
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < X; i++) {
            int value = Integer.parseInt(st.nextToken());
            visitNumbers[i] = value;
            maxValue += value;
        }

        // i = X 부터 시작해서 뒤에값을 더하고 앞에 값을 빼면서 최대값과 비교한다.
        int count = 1;
        long currentValue = maxValue;
        for (int i = X; i < N; i++) {
            visitNumbers[i] = Integer.parseInt(st.nextToken());
            currentValue = currentValue + visitNumbers[i] - visitNumbers[i - X];

            if (currentValue > maxValue) {
                // 최대값보다 크면 최대값을 update 하고 count를 1로 세팅한다.
                maxValue = currentValue;
                count = 1;
            } else if (currentValue == maxValue) {
                // 최대값과 같다면 count를 1더한다.
                count++;
            }
        }

        StringBuilder answer = new StringBuilder();
        if (maxValue == 0) {
            answer.append("SAD");
        } else {
            answer.append(maxValue);
            answer.append("\n");
            answer.append(count);
        }
        System.out.println(answer);
    }
}

// 알고리즘: 2차원 for문을 돌면 합을 모두 구할 수 있지만 최대 O(n**2)으로 시간초과가 발생할 것이기 때문에
// 투포인터를 이용해서 O(n) 만에 최대값과 그 횟수를 구하면 된다.

// 시간복잡도: O(N)

// 정수 범위: 전체일때 250000 * 8000 으로 20억이가능하므로 long으로 설정
