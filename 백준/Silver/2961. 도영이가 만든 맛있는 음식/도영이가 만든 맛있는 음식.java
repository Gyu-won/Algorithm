import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static int minDifference = Integer.MAX_VALUE;
    private static List<int[]> ingredients;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public int solution() throws IOException {
        // n을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        // List<int[]> ingredients 를 입력받는다.
        StringTokenizer st;
        ingredients = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            ingredients.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        // 몇개의 재료를 선택할지 계산한다.
        for (int i = 1; i <= n; i++) {
            visited = new boolean[n];
            checkCombination(i, 0, 1, 0);
        }

        return minDifference;
    }

    private void checkCombination(int k, int current, int sour, int bitter) {
        if (current == k) {
            minDifference = Math.min(minDifference, Math.abs(sour - bitter));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                int[] ingredient = ingredients.get(i);
                checkCombination(k, current + 1, sour * ingredient[0], bitter + ingredient[1]);
                visited[i] = false;
            }
        }
    }
}

// 알고리즘: 재료의 조합을 선택하고, 그 중에서 쓴맛과 신맛의 최소 차이를 구해야한다.
// 완탐으로 풀면 총 nC1 + nC2 + nC3 +.. nCn 이라서 2의 n승 -1 가지 (n이 10이기 떄문에 가능)

// 시간복잡도: O(2^n -1)

// 정수 범위:
