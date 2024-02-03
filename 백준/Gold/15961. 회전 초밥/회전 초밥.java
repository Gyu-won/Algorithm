import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int count = 0;
    private static int maxCount = 0;
    private static int c;
    private static int[] current;

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public long solution() throws IOException {
        // n, d, k, c를 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        // int[] sushi를 입력받는다.
        int[] sushi = new int[n];
        for (int i = 0; i < n; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        // int[d+1] currrent를 선언한다.
        current = new int[d + 1];
        // 처음 경우의 current와 count를 초기화한다.
        int end = 0;
        for (; end < k; end++) {
            addLast(sushi[end]);
            updateMaxCount();
        }

        // 투포인터를 사용하여 current 여부에 따라 count를 증감을 결정한다.
        int start = 0;
        for (; start < n; start++, end++) {
            removeFirst(sushi[start]);
            addLast(sushi[end % n]);
            updateMaxCount();
        }

        // maxCount 최대값을 출력한다.
        return maxCount;
    }

    private void updateMaxCount() {
        // current[c]가 0이면 count에 1을 더해서 최대값을 update 해준다.
        if (current[c] == 0) {
            maxCount = Math.max(maxCount, count + 1);
        } else {
            maxCount = Math.max(maxCount, count);
        }
    }

    private void addLast(int end) {
        if (current[end] == 0) {
            count++;
        }
        current[end]++;
    }

    private void removeFirst(int start) {
        if (current[start] == 1) {
            count--;
        }
        current[start]--;
    }
}

// 17:33-18:15
// 총 걸린 시간: 42분

// 알고리즘: 가능한 모든 조합을 구하고, 중복 제거 + 2번 이벤트를 적용하면 구할 수 있다.
// 하지만 O(n*k) 이기 때문에 불가능하다.
// 따라서 투포인터 알고리즘을 사용하여 처음 count를 구해놓고 int[d+1] 를 활용하여 개수를 저장하며
// 값의 존재여부에 따라 count를 증감시키며 count의 최대값을 구한다.
//

// 시간복잡도: O(n)

// 정수 범위: int
