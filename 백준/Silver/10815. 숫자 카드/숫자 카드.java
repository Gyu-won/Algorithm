import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    public void solution() throws IOException {
        // N을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // cards를 입력받는다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] cards = new int[N];
        for (int i = 0; i < N; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        // cards를 정렬한다.
        Arrays.sort(cards);

        // M을 입력받는다.
        int M = Integer.parseInt(br.readLine());

        // 하나씩 보면서 이진탐색을 통해 있으면 결과배열에 1을, 없으면 0을 저장한다.
        StringBuilder result = new StringBuilder();
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < M; i++) {
            int findValue = Integer.parseInt(st.nextToken());
            if (Arrays.binarySearch(cards, findValue) < 0) {
                result.append(0);
            } else {
                result.append(1);
            }
            result.append(" ");
        }

        System.out.println(result.toString().trim());
    }
}

// 알고리즘: 완전탐색으로 하나씩 비교하기에는 N과 M이 최대 50만이기 때문에 시간초과가 발생한다.
// 정렬을 통해 해결하려고 했지만 출력 결과가 순서를 보장해야되기 때문에 이건 어려울 것 같다.
// 상근이의 카드 cards 를 정렬하고 O(NlogN), 이분 탐색(MlogN)을 통해서 찾으면 NlogN 시간동안 찾을 수 있다.

// 시간복잡도: O(NlogN)

// 정수 범위: int
