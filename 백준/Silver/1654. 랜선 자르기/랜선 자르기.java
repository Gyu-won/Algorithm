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
        // k와 n을 입력받는다. (1-1000, 1-1000000)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        // int[k] lines를 입력받는다.
        int[] lines = new int[k];
        for (int i = 0; i < k; i++) {
            lines[i] = Integer.parseInt(br.readLine());
        }

        // lines를 정렬한다.
        Arrays.sort(lines);

        // 이분탐색: 중간값이 n 이상으로 되면 start를 떙기고, 안되면 end를 땡김
        long start = 1, end = lines[k - 1];
        while (start <= end) {
            long mid = (start + end) / 2;
            int count = 0;
            for (int i = 0; i < k; i++) {
                count += lines[i] / mid;
            }

            if (count >= n) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return end;
    }
}

// 설계 시간: 22:00-
// 풀이 시간:

//0. 문제요약
// k개의 랜선으로 모두 같은 길이의 랜선 n개이상으로 만들어야 한다
// 만들 수 없는 경우는 없다,
// 만들 수 있는 랜선의 최대 길이

//2. 시간복잡도: O(klogk + logk * k)

//3. 이 로직으로 예제 문제 손으로 풀기

//4. 경계값 체크 후 코드 구현
