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

        // int[n] numbers 를 입력받는다.
        int[] numbers = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        // int[n] sequenceLength을 선언한다.
        int[] sequenceLength = new int[n];

        // 반복문을 돌며 sequenceLength를 1부터 n-1 까지 채운다.
        int maxLength = 0;
        for (int i = 0; i < n; i++) {
            sequenceLength[i] = 1;

            // 반복문을 돌며 i보다 작은 j에 대하여 numbers[i] > numbers[j] 이고 sequenceLength[j] + 1 이 sequenceLength[i] 보다 크면 값을 update ㅎ나다.
            for (int j = 0; j < i; j++) {
                if (numbers[j] < numbers[i] && sequenceLength[i] < sequenceLength[j] + 1) {
                    sequenceLength[i] = sequenceLength[j] + 1;
                }
            }
            // maxLength를 update 한다.
            maxLength = Math.max(maxLength, sequenceLength[i]);
        }

        return maxLength;
    }
}

// 11:25 - 12:43
// 총 걸린 시간: 01:18

// 알고리즘: 완전 탐색으로 모든 가능한 순열을 계산하고 최대 길이를 구하는 것은 어렵다.
// 가장 긴 부분 수열은 해당 값보다 원소가 작으면서 가장 긴 것에 + 1 이다.

// 시간복잡도: O(n^2)

// 정수 범위:
