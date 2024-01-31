import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final int NUMBER_SIZE = 100001;

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public int solution() throws IOException {
        // n 과 k를 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // sequenceCount 배열을 선언한다. int[]
        int[] sequenceCount = new int[NUMBER_SIZE];

        // n번 돌면서 각 숫자의 count를 1 더해주고, 길이도 1 더해준다.
        st = new StringTokenizer(br.readLine(), " ");
        int[] sequence = new int[200000];
        int start = 0, end = 0, maxLen = 0;
        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(st.nextToken());
            sequence[i] = number;
            if (sequenceCount[number] == k) {
                //  k 보다 크거나 같으면, max 값을 update 하고, start를 옮긴다.
                maxLen = Math.max(maxLen, end - start);
                while (sequenceCount[number] >= k) {
                    sequenceCount[sequence[start]]--;
                    start++;
                }
            }
            sequenceCount[number]++;
            end++;
        }
        maxLen = Math.max(maxLen, end - start);

        // max값을 출력한다.
        return maxLen;
    }
}

// 알고리즘: n이 20만 정도라 nlogn까지 가능
// 앞에서 하나씩 돌면서 숫자와 개수를 구하고, 숫자가 넘어가면 길이를 max값과 비교하여 출력
// 메모리 제한 커서 계속 선언해줘도 됨

// 시간복잡도: O(n)

// 정수 범위
