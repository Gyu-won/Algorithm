import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final int[] xDirection = new int[]{0, -1, 0, 1};
    private static final int[] yDirection = new int[]{-1, 0, 1, 0};
    private static int[][] priceMap;
    private static int n;
    private static int minPrice = Integer.MAX_VALUE;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public int solution() throws IOException {
        // n을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        // int[n][n] price를 입력받는다.
        priceMap = new int[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                priceMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 꽃의 중앙을 기준으로 (1, 1) 부터, (n-2, n-2) 까지 돌면서 가능한 곳을 확인한다.
        visited = new boolean[n][n];
        findMinPrice(0, 0);

        // 가능하다면, 해당 칸을 모두 visited[][] true로 만들고 다음 꼿을 지정한다.
        return minPrice;
    }

    private void findMinPrice(int flower, int price) {
        if (flower > 2) {
            minPrice = Math.min(minPrice, price);
            return;
        }

        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (isPossible(i, j, visited)) {
                    int temp = price + priceMap[i][j];
                    for (int k = 0; k < 4; k++) {
                        int x = i + xDirection[k];
                        int y = j + yDirection[k];
                        visited[x][y] = true;
                        temp += priceMap[x][y];
                    }
                    findMinPrice(flower + 1, temp);
                    for (int k = 0; k < 4; k++) {
                        int x = i + xDirection[k];
                        int y = j + yDirection[k];
                        visited[x][y] = false;
                    }
                }
            }
        }
    }

    private boolean isPossible(int i, int j, boolean[][] visited) {
        if (visited[i][j]) {
            return false;
        }
        for (int k = 0; k < 4; k++) {
            int x = i + xDirection[k];
            int y = j + yDirection[k];
            if (visited[x][y]) {
                return false;
            }
        }
        return true;
    }
}

// 알고리즘: 모든 조합을 다 고려한다면 O(n^6)까지 가능한데 n이 10이라서 가능하다.

// 시간복잡도: O(n^6)

// 정수 범위:
