import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int MAX_NUMBER = 1000000;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    public void solution() throws IOException {
        // 소수 여부를 체크하는 boolean 배열을 구한다.
        boolean[] isNotPrime = new boolean[MAX_NUMBER + 1];
        isNotPrime[0] = isNotPrime[1] = true;
        for (int i = 2; i <= Math.sqrt(MAX_NUMBER); i++) {
            if (!isNotPrime[i]) {
                for (int j = i * i; j <= MAX_NUMBER; j += i) {
                    isNotPrime[j] = true;
                }
            }
        }
        
        // 방문여부 배열을 구한다.
        boolean[] visited = new boolean[MAX_NUMBER+1];

        // sequenceLength 를 입력받는다. int
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int sequenceLength = Integer.parseInt(br.readLine());

        // element를 입력받아 소수이면 결과에 곱한다. (결과: long)
        long result = 1;
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < sequenceLength; i++) {
            int number = Integer.parseInt(st.nextToken());
            if (!isNotPrime[number] && !visited[number]) {
                result *= number;
                visited[number] = true;
            }
        }

        // 결과값이 1이면 -1을 아니면 결과를 출력한다.
        System.out.println(result == 1 ? -1 : result);
    }
}

// 알고리즘: 소수인지 체크하는 알고리즘과 이들의 곱을 구한다.

// 시간복잡도: O(Ai 최대값)

// 정수 범위:
