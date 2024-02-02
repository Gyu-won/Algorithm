import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public int solution() throws IOException {
        // n과 k를 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // frontStudent를 두고 현재 student와 비교하여 차이를 int[n-1]difference 에 추가한다.
        st = new StringTokenizer(br.readLine(), " ");
        int frontStudent = Integer.parseInt(st.nextToken());

        int[] difference = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            int currentStudent = Integer.parseInt(st.nextToken());
            difference[i] = currentStudent - frontStudent;
            frontStudent = currentStudent;
        }

        // difference를 정렬한다.
        Arrays.sort(difference);

        // 0 부터 n-k-1 까지 합을 구한다.
        int cost = 0;
        for (int i = 0; i < n - k; i++) {
            cost += difference[i];
        }
        return cost;
    }
}

// 알고리즘: 모든 조합을 다 고려하기에는 n이 30만까지이기 때문에 불가능하다.
// 비용이 최소가 되려면, 가장 차이가 적은 것끼리 먼저 묶어야 한다.
// 즉 차이를 기준으로 오름차순 정렬해서, n-k 번째 까지의 합을 구하면 된다.

// 시간복잡도: O(nlogn)

// 정수 범위:
